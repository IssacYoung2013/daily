package com.issac.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-22
 * @desc:
 */
@NodeEntity(label = "Person")
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long nodeId;

    private String name;

    private int born;
}
