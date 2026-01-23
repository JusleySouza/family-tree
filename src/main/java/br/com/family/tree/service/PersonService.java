package br.com.family.tree.service;

import br.com.family.tree.domain.Person;
import br.com.family.tree.dto.PersonRequest;
import br.com.family.tree.dto.PersonResponse;
import br.com.family.tree.mapper.PersonMapper;
import br.com.family.tree.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private Person person;
    private final PersonRepository repository;
    private final PersonMapper mapper;

    public PersonResponse createPerson(PersonRequest request) {
        person.validateDates();
        person = mapper.toEntity(request);
        repository.save(person);
        return mapper.toResponse(person);
    }



}
