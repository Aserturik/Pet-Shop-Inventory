package com.petshop.petshop_inventory.dto.person;

import com.petshop.petshop_inventory.model.person.add_ons.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRegisterDTO(

        @NotBlank
        String name,
        @NotNull
        Long documentNumber,
        @NotNull
        DocumentType documentType,

        @NotBlank
        String email,
        @NotBlank
        String phone
) {
}
