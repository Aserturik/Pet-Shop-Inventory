package com.petshop.petshop_inventory.dto.person.add_ons;

import com.petshop.petshop_inventory.model.person.Login;
import com.petshop.petshop_inventory.model.person.add_ons.Role;

public record LoginResponseDTO(
        Long id,
        String email,
        Role role
) {

    public LoginResponseDTO(Login login) {
        this(login.getId(), login.getUsername(), login.getRole());
    }
}
