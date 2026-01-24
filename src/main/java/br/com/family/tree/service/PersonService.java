package br.com.family.tree.service;

import br.com.family.tree.domain.Person;
import br.com.family.tree.domain.RelationshipType;
import br.com.family.tree.dto.PersonRequest;
import br.com.family.tree.dto.PersonResponse;
import br.com.family.tree.exception.BusinessRuleException;
import br.com.family.tree.mapper.PersonMapper;
import br.com.family.tree.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public void addFather(UUID childId, UUID fatherId) {
        addParent(childId, fatherId, RelationshipType.FATHER);
    }

    public void addMother(UUID childId, UUID motherId) {
        addParent(childId, motherId, RelationshipType.MOTHER);
    }


    private void addParent(UUID childId, UUID parentId, RelationshipType type) {
        Person child = findPerson(childId);
        Person parent = findPerson(parentId);

        validateNotSamePerson(childId, parentId);
        validateParentChildDates(child, parent);
        validateNoCycle(childId, parentId);
        validateParentNotAlreadyExists(childId, type);

        createRelationship(parentId, childId, type);
    }

    private Person findPerson(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Person not found"));
    }

    private void validateNotSamePerson(UUID aId, UUID bId) {
        if (aId.equals(bId)) {
            throw new BusinessRuleException("A person cannot be related to themselves");
        }
    }

    private void validateParentChildDates(Person child, Person parent) {
        if (parent.getBirthDate().isAfter(child.getBirthDate())) {
            throw new BusinessRuleException("Parent cannot be younger than child");
        }
    }

    private void validateNoCycle(UUID childId, UUID parentId) {
        List<Person> ancestors = repository.findAncestors(parentId);

        boolean createsCycle = ancestors.stream()
                .anyMatch(p -> p.getId().equals(childId));

        if (createsCycle) {
            throw new BusinessRuleException("This relationship creates a family cycle");
        }
    }

    private void validateParentNotAlreadyExists(UUID childId, RelationshipType type) {
        boolean alreadyExists = repository.findParents(childId)
                .stream()
                .anyMatch(p -> type == RelationshipType.FATHER || type == RelationshipType.MOTHER);

        if (alreadyExists) {
            throw new BusinessRuleException("Parent of this type already exists");
        }
    }

    private void createRelationship(
            UUID fromId,
            UUID toId,
            RelationshipType type
    ) {
        repository.createRelationship(fromId, toId, type.name());
    }

}
