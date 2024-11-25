package com.petshop.petshop_inventory.dto.person;

import com.petshop.petshop_inventory.model.person.Person;
import com.petshop.petshop_inventory.model.person.add_ons.DocumentType;

public record PersonResponseDTO(
        Long id,
        String name,
        Long documentNumber,
        DocumentType documentType,
        String email,
        String phone
) {
    public PersonResponseDTO(Person person) {
        this(person.getId(), person.getName(), person.getDocumentNumber(), person.getDocumentType(), person.getEmail(), person.getPhone());
    }
}
