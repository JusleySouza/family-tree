package br.com.family.tree.controller;

import br.com.family.tree.dto.PersonRequest;
import br.com.family.tree.dto.PersonResponse;
import br.com.family.tree.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @PostMapping
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody PersonRequest request) {
        return new ResponseEntity<>(service.createPerson(request), HttpStatus.CREATED);
    }

    @PostMapping("/{childId}/father/{fatherId}")
    public ResponseEntity<Void> addFather( @PathVariable UUID childId, @PathVariable UUID fatherId ) {
        service.addFather(childId, fatherId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{childId}/mother/{motherId}")
    public ResponseEntity<Void> addMother( @PathVariable UUID childId, @PathVariable UUID motherId) {
        service.addMother(childId, motherId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{personAId}/spouse/{personBId}")
    public ResponseEntity<Void> addSpouse( @PathVariable UUID personAId, @PathVariable UUID personBId) {
        service.addSpouse(personAId, personBId);
        return ResponseEntity.ok().build();
    }

}
