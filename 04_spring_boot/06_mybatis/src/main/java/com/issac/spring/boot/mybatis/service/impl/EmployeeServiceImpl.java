package com.issac.spring.boot.mybatis.service.impl;

import com.issac.spring.boot.mybatis.dao.EmployeeDao;
import com.issac.spring.boot.mybatis.po.EmployeePo;
import com.issac.spring.boot.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author: ywy
 * @date: 2019-11-02
 * @desc:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @CacheEvict(value = "redisTimeCache", allEntries = true)
    @Transactional
    @Override
    public void addEmployee(EmployeePo employeePo) {
        dao.insertEmployee(employeePo);
//        int i = 3 / 0;
//        dao.insertEmployee(employeePo);
    }

    @Cacheable(value = "redisTimeCache"
//            , key = "'employee_'+ #id "
    )
    @Override
    public EmployeePo findEmployeeById(Integer id) {
        return dao.selectEmployeeById(id);
    }


//    使用双重检测锁解决热点缓存问题

    @Override
    public Integer findEmployeeCount() {
//        获取redis操作对象
        BoundValueOperations<Object, Object> ops = redisTemplate.boundValueOps("count");
//        从缓存中读取数据
        Object count = ops.get();
        if (count == null) {
            // 单例对象锁this
            synchronized (this) {
                count = ops.get();
                if (count == null) {
                    // 从db中查询
                    count = dao.selectEmployeeCount();
                    // 将查询的数据写入redis 缓存，并设置到期时限
                    ops.set(count, 10, TimeUnit.SECONDS);
                }
            }
        }
        return (Integer) count;
//        return dao.selectEmployeeCount();
    }
}
