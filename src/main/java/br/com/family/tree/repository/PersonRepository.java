package br.com.family.tree.repository;

import br.com.family.tree.domain.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface PersonRepository extends Neo4jRepository<Person, UUID> {

    @Query("""
        MATCH (p:Person {id: $personId})<-[:FATHER|MOTHER*]-(ancestor)
        RETURN ancestor
            """)
    List<Person> findAncestors(@Param("personId") UUID personId);

    @Query("""
        MATCH (child:Person {id: $childId})<-[:FATHER|MOTHER]-(parent)
        RETURN parent
    """)
    List<Person> findParents(@Param("childId") UUID childId);

    @Query("""
        MATCH (from:Person {id: $fromId}),
              (to:Person {id: $toId})
        CREATE (from)-[:${type}]->(to)
    """)
    void createRelationship(
            @Param("fromId") UUID fromId,
            @Param("toId") UUID toId,
            @Param("type") String type
    );

}
