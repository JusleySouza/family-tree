package br.com.family.tree.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.UUID;

@RelationshipProperties
@Getter
@Setter
public class Relationship {

    @Id
    @GeneratedValue
    private UUID id;

    @TargetNode
    private Person target;

    private RelationshipType type;

}
