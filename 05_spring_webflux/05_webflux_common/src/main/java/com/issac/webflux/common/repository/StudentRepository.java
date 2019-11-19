package com.issac.webflux.common.repository;

import com.issac.webflux.common.bean.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-05
 * @desc:
 */
@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student,String> {


    Flux<Student> findByAgeBetween(int below, int top);

    @Query("{age:{$gte:?0,$lte:?1}}")
    Flux<Student> queryByAge(int below,int top);
}
