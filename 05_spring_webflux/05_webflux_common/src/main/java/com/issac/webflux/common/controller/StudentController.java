package com.issac.webflux.common.controller;

import com.issac.webflux.common.bean.Student;
import com.issac.webflux.common.repository.StudentRepository;
import com.issac.webflux.common.util.NameValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author: ywy
 * @date: 2019-11-05
 * @desc:
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping("/all")
    public Flux<Student> getAll() {
        return repository.findAll();
    }

    /**
     * 以sse形式实时性返回
     *
     * @return
     */
    @GetMapping(value = "/sse/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getSseAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public Mono<Student> saveStudent(@Valid Student student) {
        NameValidateUtil.validateName(student.getName());
        return repository.save(student);
    }

    /**
     * json添加
     *
     * @param student
     * @return
     */
    @PostMapping("/save2")
    public Mono<Student> saveStudent2(@Valid @RequestBody Student student) {
        NameValidateUtil.validateName(student.getName());
        return repository.save(student);
    }

    /**
     * 无状态删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delcomm/{id}")
    public Mono<Void> deleteStudent(@PathVariable("id") String id) {
        return repository.deleteById(id);
    }

    /**
     * 有状态删除
     * ResponseEntity 封装响应体的数据，即响应码
     * FlatMap 与 Map 都可以做元素映射，选择标准：
     * 映射过程中需要再执行一些操作的用 FlatMap
     * 若仅仅是元素映射无需操作用 Map
     * 若一个方法没有返回值，可以用then构造返回值
     * defaultIfEmpty 如果 Mono 中无元素，执行返回
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public Mono<ResponseEntity<Void>> deleteStudent2(@PathVariable("id") String id) {
        return repository.findById(id)
                .flatMap(student -> repository.delete(student).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable("id") String id,
                                                       @Valid @RequestBody Student student) {
        NameValidateUtil.validateName(student.getName());
        return repository.findById(id)
                .flatMap(stu -> {
                    stu.setName(student.getName());
                    stu.setAge(student.getAge());
                    return repository.save(stu);
                })
                .map(stu -> new ResponseEntity<>(stu, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/find/{id}")
    public Mono<ResponseEntity<Student>> findStudent(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/age/{below}/{top}")
    public Flux<Student> findStudentByAge(@PathVariable("below") int below,
                                          @PathVariable("top") int top) {
        return repository.findByAgeBetween(below, top);
    }

    /**
     * SSE实时性返回
     * @param below
     * @param top
     * @return
     */
    @GetMapping(value = "/sse/age/{below}/{top}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> findStudentByAgeSse(@PathVariable("below") int below,
                                             @PathVariable("top") int top) {
        return repository.findByAgeBetween(below, top);
    }

    @GetMapping("/query/age/{below}/{top}")
    public Flux<Student> queryStudentByAge(@PathVariable("below") int below,
                                          @PathVariable("top") int top) {
        return repository.queryByAge(below, top);
    }

    @GetMapping(value = "/sse/query/age/{below}/{top}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> queryStudentByAgeSse(@PathVariable("below") int below,
                                           @PathVariable("top") int top) {
        return repository.queryByAge(below, top);
    }

}
