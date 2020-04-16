package com.issac.spring.boot.demo;

import com.issac.spring.boot.demo.bean.Demo;
import com.issac.spring.boot.demo.bean.DemoExample;
import com.issac.spring.boot.demo.mapper.DemoMapper;
import com.issac.spring.boot.demo.service.DemoQueryService;
import com.issac.spring.boot.demo.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: ywy
 * @date: 2020-03-01
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTest6 {

    @Autowired
    DemoMapper demoMapper;

    @Test
    public void insert() {
        Demo demo = new Demo();
        demo.setName("zhangsan");
        demo.setJob("student");
        demoMapper.insert(demo);
    }

    @Test
    public void update() {
        Demo demo = new Demo();
        demo.setName("lisi");
        demo.setId(5);
        demoMapper.updateByPrimaryKey(demo);
    }

    @Test
    public void update2() {
        Demo demo = new Demo();
        demo.setName("zhangsan1");
        demo.setId(5);
        // 为null自动过滤
        demoMapper.updateByPrimaryKeySelective(demo);
    }

    @Test
    public void update3() {
        Demo demo = new Demo();
        demo.setJob("teacher");
        DemoExample demoExample = new DemoExample();
        demoExample.createCriteria()
                .andJobIsNull();
        demoMapper.updateByExampleSelective(demo, demoExample);
    }

    @Test
    public void query() {

        DemoExample demoExample = new DemoExample();
        demoExample.createCriteria()
                .andJobEqualTo("teacher");
        List<Demo> demos = demoMapper.selectByExample(demoExample);
        System.out.println(Arrays.toString(demos.toArray()));
    }

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testRedis() throws Exception {
        redisUtil.set("issac", "test", 3);
        System.out.println(redisUtil.get("issac"));
        Thread.sleep(3000);
        System.out.println(redisUtil.get("issac"));
    }

    @Autowired
    private DemoQueryService demoQueryService;

    @Test
    public void testQueryRedis() {
        System.out.println(demoQueryService.queryDemoById(1));
        System.out.println(demoQueryService.queryDemoById(1));

        System.out.println(demoQueryService.queryDemoById(2));
        System.out.println(demoQueryService.queryDemoById(2));

        System.out.println(demoQueryService.queryDemoById(3));
        System.out.println(demoQueryService.queryDemoById(3));

    }

    @Test
    public void testReactor() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
        Mono<Integer> mono = Mono.just(1);
        Integer[] integers = {1, 2, 3, 4, 5, 6};
        Flux<Integer> arrayFlux = Flux.fromArray(integers);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Flux<Integer> listFlux = Flux.fromIterable(list);
        Flux<Integer> fluxFlux = Flux.from(flux);
        Flux<Integer> streamFlux = Flux.fromStream(Stream.of(1, 2, 3, 4, 5, 6));
//        flux.subscribe();
//
//        arrayFlux.subscribe(System.out::println);
//        listFlux.subscribe(System.out::println, System.err::println);
//        fluxFlux.subscribe(System.out::println, System.err::println,
//                () -> System.out.println("complete"));
//        streamFlux.subscribe(System.out::println, System.err::println,
////                () -> System.out.println("complete"),
////                subscription -> subscription.request(3));
//        streamFlux.subscribe(new DemoSubscriber());
        System.out.println("=======1=======");
        flux.map(i -> i * 3).subscribe(System.out::println);
        System.out.println("=======2=======");

        arrayFlux.flatMap(i -> flux).subscribe(System.out::println);
        System.out.println("=======3=======");

        listFlux.filter(i -> i > 3).subscribe(System.out::println);
        System.out.println("=======4=======");

        Flux.zip(fluxFlux, streamFlux).subscribe(zip -> System.out.println(zip.getT1() + zip.getT2()));
        flux.map(i -> {
            System.out.println(Thread.currentThread().getName() + "-map1");
            return i * 3;
        })
                .publishOn(Schedulers.elastic())
                .map(i -> {
                    System.out.println(Thread.currentThread().getName() + "-map2");
                    return i * 3;
                })
                .subscribeOn(Schedulers.parallel())
                .subscribe(it -> System.out.println(Thread.currentThread().getName() + "-" + it));
        while (true){

        }
    }

    class DemoSubscriber extends BaseSubscriber<Integer> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("subscribe");
            subscription.request(1);
        }

        @Override
        protected void hookOnNext(Integer value) {
            if (value == 4) {
                cancel();
            }
            System.out.println(value);
            request(1);
        }
    }
}
