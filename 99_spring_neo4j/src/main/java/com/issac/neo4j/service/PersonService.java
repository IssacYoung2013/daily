package com.issac.neo4j.service;

import com.issac.neo4j.entity.Person;
import com.issac.neo4j.repository.PersonRelationRepository;
import com.issac.neo4j.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ywy
 * @date: 2019-10-22
 * @desc:
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRelationRepository personRelationRepository;

    public int addPerson(Person person) {
        List<Person> people = personRepository.addPersonNodeList(person.getName(), person.getBorn());
        return 1;
    }

    public List<Person> getPersonNodeList() {
        return personRepository.getPersonNodeList();
    }

    public int addPersonRelation(Person firstPerson,Person secondPerson) {
        personRelationRepository.addPersonRelation(firstPerson.getName(),secondPerson.getName());
        return 1;
    }
}
