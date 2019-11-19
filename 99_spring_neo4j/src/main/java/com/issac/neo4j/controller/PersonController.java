package com.issac.neo4j.controller;

import com.issac.neo4j.entity.Person;
import com.issac.neo4j.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-22
 * @desc:
 */
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }

    @GetMapping("/addPerson")
    private Object addPerson(Person person) {
        return personService.addPerson(person);
    }



}
