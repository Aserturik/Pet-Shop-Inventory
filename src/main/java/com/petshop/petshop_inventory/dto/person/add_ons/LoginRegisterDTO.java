package com.petshop.petshop_inventory.dto.person.add_ons;

import com.petshop.petshop_inventory.model.person.add_ons.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRegisterDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        Role role
) {
}
