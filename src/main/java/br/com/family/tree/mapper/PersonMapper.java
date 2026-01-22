package br.com.family.tree.mapper;

import br.com.family.tree.domain.Person;
import br.com.family.tree.dto.PersonRequest;
import br.com.family.tree.dto.PersonResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toEntity(PersonRequest request) {
        if (request == null) {
            return null;
        }

        Person person = new Person();
        person.setName(request.name());
        person.setBirthDate(request.birthDate());
        person.setDeathDate(request.deathDate());
        return person;
    }


    public PersonResponse toResponse(Person person) {
        if (person == null) {
            return null;
        }

        return new PersonResponse(
                person.getId(),
                person.getName(),
                person.getBirthDate()
        );
    }

}
