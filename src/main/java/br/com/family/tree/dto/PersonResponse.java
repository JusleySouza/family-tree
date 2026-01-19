package br.com.family.tree.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PersonResponse(
        UUID id,
        String name,
        LocalDate birthDate
) { }
