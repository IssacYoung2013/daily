package com.issac.neo4j.repository;

import com.issac.neo4j.entity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-22
 * @desc:
 */
public interface PersonRepository extends Neo4jRepository<Person,Long> {

    @Query("MATCH (n:Person) RETURN n")
    List<Person> getPersonNodeList();

    @Query("create (n:Person{name:{name},born:{born}}) RETURN n")
    List<Person> addPersonNodeList(@Param("name") String name,@Param("born") int born);
}
