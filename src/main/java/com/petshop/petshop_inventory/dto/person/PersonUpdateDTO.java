package com.petshop.petshop_inventory.dto.person;

import com.petshop.petshop_inventory.model.person.add_ons.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonUpdateDTO(
        @NotNull
        Long id,
        String name,

        Long documentNumber,

        DocumentType documentType,


        String email,

        String phone
)
         {
}
