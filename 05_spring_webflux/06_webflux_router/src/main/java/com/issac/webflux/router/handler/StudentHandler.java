package com.issac.webflux.router.handler;

import com.issac.webflux.router.bean.Student;
import com.issac.webflux.router.repository.StudentRepository;
import com.issac.webflux.router.util.NameValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author: ywy
 * @date: 2019-11-06
 * @desc:
 */
@Component
public class StudentHandler {

    @Autowired
    StudentRepository repository;

    public Mono<ServerResponse> findAllHandle(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.findAll(), Student.class)
                ;
    }

    public Mono<ServerResponse> saveHandle(ServerRequest request) {
        // 请求中获取要添加的数据，并封装成指定对象
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.saveAll(studentMono), Student.class);

    }

    public Mono<ServerResponse> delHandle(ServerRequest request) {
        // 请求中获取要添加的数据，并封装成指定对象
        String id = request.pathVariable("id");
        return repository.findById(id)
                .flatMap(student -> repository.delete(student)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateHandle(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        return studentMono
                .flatMap(student -> {
                    NameValidateUtil.validateName(student.getName());
                    student.setId(id);
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(repository.save(student), Student.class);
                });
    }
}
