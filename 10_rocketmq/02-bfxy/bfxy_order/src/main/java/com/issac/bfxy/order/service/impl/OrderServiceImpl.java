package com.issac.bfxy.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.issac.bfxy.order.constant.OrderStatus;
import com.issac.bfxy.order.entity.Order;
import com.issac.bfxy.order.mapper.OrderMapper;
import com.issac.bfxy.order.service.OrderService;
import com.issac.bfxy.order.service.producer.OrderlyProducer;
import com.issac.bfxy.order.util.FastJsonConvertUtil;
import com.issac.bfxy.store.service.api.StoreServiceApi;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.issac.bfxy.store.service.api.StoreServiceApi",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private StoreServiceApi storeServiceApi;

    @Autowired
    private OrderlyProducer orderlyProducer;

    @Override
    public boolean createOrder(String cityId, String platformId, String userId, String suppliedId, String goodsId) {
        boolean flag = true;
        try {
            Order order = new Order();
            order.setOrderId(UUID.randomUUID().toString());
            order.setCityId(cityId);
            order.setGoodsId(goodsId);
            order.setPlatformId(platformId);
            order.setSupplierId(suppliedId);
            order.setOrderType("1");
            order.setUserId(userId);
            order.setOrderStatus(OrderStatus.ORDER_CREATED.getStatus());

            order.setRemark("");
            order.setCreateBy("admin");
            Date currentTime = new Date();
            order.setCreateTime(currentTime);
            order.setUpdateBy("admin");
            order.setUpdateTime(currentTime);

            int currentVersion = storeServiceApi.selectVersion(suppliedId, goodsId);
            int updateResultCount = storeServiceApi.updateStoreCountByVersion(currentVersion, suppliedId, goodsId, "admin", currentTime);
            if (updateResultCount == 1) {
                //TODO 如果出现SQL异常入库失败，那么要对库存的数量和版本号进行回滚操作
                orderMapper.insertSelective(order);
            }
            // 没有更新成功
            // 1. 高并发时乐观锁生效
            // 2. 库存不足
            else if (updateResultCount == 0) {
                // 下单失败
                flag = false;
                int currentStoreCount = storeServiceApi.selectStoreCount(suppliedId, goodsId);
                if (currentStoreCount == 0) {
                    System.err.println("当前库存不足...");
                } else {
                    System.err.println("乐观锁生效...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 具体捕获的异常是什么异常
            flag = false;
        }
        return flag;
    }

    public static final String PKG_TOPIC = "pkg_topic";
    public static final String PKG_TAGS = "pkg";
    @Override
    public void sendOrderlyMessage4Pkg(String userId, String orderId) {
        //
        List<Message> messageList = new ArrayList<>();

        Map<String,Object> param1 = new HashMap<>();
        param1.put("userId",userId);
        param1.put("orderId",orderId);
        param1.put("txt","创建包裹操作----1");

        String key1 = UUID.randomUUID().toString() + "$" + System.currentTimeMillis();

        Message message1 = new Message(
            PKG_TOPIC,
                PKG_TAGS,
                key1,
                FastJsonConvertUtil.convertObjectToJSON(param1).getBytes()
        );

        messageList.add(message1);

        Map<String,Object> param2 = new HashMap<>();
        param2.put("userId",userId);
        param2.put("orderId",orderId);
        param2.put("txt","发送物流通知操作----2");

        String key2 = UUID.randomUUID().toString() + "$" + System.currentTimeMillis();

        Message message2 = new Message(
                PKG_TOPIC,
                PKG_TAGS,
                key2,
                FastJsonConvertUtil.convertObjectToJSON(param2).getBytes()
        );

        messageList.add(message2);

        // 顺序消息应该按供应商id与topic和messageQueueId绑定
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Integer messageQueueNumber = Integer.valueOf(order.getSupplierId());

        // 对应顺序消息的生产者
        orderlyProducer.sendOrderlyMessage(messageList,messageQueueNumber);
    }
}
