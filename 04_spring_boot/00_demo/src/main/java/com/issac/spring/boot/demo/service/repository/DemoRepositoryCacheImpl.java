package com.issac.spring.boot.demo.service.repository;

import com.alibaba.fastjson.JSON;
import com.issac.spring.boot.demo.RedisUtil;
import com.issac.spring.boot.demo.bean.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author: ywy
 * @date: 2020-04-12
 * @desc:
 */
@Component
public class DemoRepositoryCacheImpl implements DemoRepository {

    public static final String CACHE_PREFIX = "cache_prefix";

    private static final Random random = new Random();

    @Autowired
    RedisUtil redisUtil;

    @Resource(name = "demoRepositoryImpl")
    private DemoRepository demoRepository;

    private Demo nullDemo = new Demo(-1);

    @Override
    public Demo queryDemoById(Integer id) {
        Demo demo = getDemoFromCache(id);
        if (demo == null) {
            System.out.println("cache not hit");
            demo = demoRepository.queryDemoById(id);
            cacheDemo(demo, id);
        } else if (-1 == demo.getId()) {
            System.out.println("cache hit null");
            return null;
        } else {
            System.out.println("cache hit id");
        }
        return demo;
    }

    private void cacheDemo(Demo demo, Integer id) {
        if (demo != null) {
            redisUtil.set(generateCacheKey(id), JSON.toJSONString(demo), 10 + random.nextInt(5));
        } else {
            redisUtil.set(generateCacheKey(id), JSON.toJSONString(nullDemo), 10 + random.nextInt(5));
        }
    }

    private Demo getDemoFromCache(Integer id) {
        String demo = (String) redisUtil.get(generateCacheKey(id));
        if (!StringUtils.isEmpty(demo)) {
            return JSON.parseObject(demo, Demo.class);
        }
        return null;
    }

    private String generateCacheKey(Integer id) {
        return CACHE_PREFIX + id;
    }
}
