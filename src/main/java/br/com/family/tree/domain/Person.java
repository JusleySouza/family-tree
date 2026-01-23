package br.com.family.tree.domain;

import br.com.family.tree.exception.BusinessRuleException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;
import java.util.UUID;

@Node("Person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate birthDate;

    private LocalDate deathDate;

    public void updateDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
        validateDates();
    }

    public void validateDates(){
        if(birthDate == null){
            throw new BusinessRuleException("Birth date is required.");
        }
        if(birthDate.isAfter(LocalDate.now())){
            throw new BusinessRuleException("Birth date cannot be in the future.");
        }
        if(deathDate != null && deathDate.isBefore(birthDate)){
            throw new BusinessRuleException("Death date cannot be before birth date.");
        }
    }

}
