package br.com.family.tree.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;
import java.util.UUID;

@Node("Person")
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate birthDate;

    private LocalDate deathDate;

}
