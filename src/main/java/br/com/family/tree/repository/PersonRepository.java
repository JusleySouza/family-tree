package br.com.family.tree.repository;

import br.com.family.tree.domain.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.*;

public interface PersonRepository extends Neo4jRepository<Person, UUID> {

    @Query("""
        MATCH (p:Person {id: $personId})<-[:FATHER|MOTHER*]-(ancestor)
        RETURN ancestor
            """)
    List<Person> findAncestors(UUID personId);

}
