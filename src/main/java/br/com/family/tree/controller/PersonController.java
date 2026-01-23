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

}
