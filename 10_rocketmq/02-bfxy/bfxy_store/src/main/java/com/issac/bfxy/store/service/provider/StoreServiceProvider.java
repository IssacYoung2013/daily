package com.issac.bfxy.store.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.issac.bfxy.store.mapper.StoreMapper;
import com.issac.bfxy.store.service.api.StoreServiceApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class StoreServiceProvider implements StoreServiceApi {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public int selectVersion(String supplierId, String goodsId) {

        return storeMapper.selectVersion(supplierId,goodsId);
    }

    @Override
    public int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy, Date updateTime) {
        return storeMapper.updateStoreCountByVersion(version,supplierId,goodsId,updateBy,updateTime);
    }

    @Override
    public int selectStoreCount(String supplierId, String goodsId) {
        return storeMapper.selectStoreCount(supplierId,goodsId);
    }
}
