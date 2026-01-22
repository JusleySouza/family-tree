package br.com.family.tree.dto;

import java.time.LocalDate;

public record PersonRequest(
    String name,
    LocalDate birthDate,
    LocalDate deathDate
) { }
