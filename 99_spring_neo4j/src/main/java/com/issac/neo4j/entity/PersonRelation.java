package com.issac.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-22
 * @desc:
 */
@RelationshipEntity(type = "PersonRelation")
@Data
public class PersonRelation {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person startNode;

    @EndNode
    private Person endNode;

}
