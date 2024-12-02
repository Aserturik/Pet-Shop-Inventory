package com.petshop.petshop_inventory.dto.person.add_ons;

import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;

public record AdminRegisterDTO(
        PersonRegisterDTO personRegisterDTO,
        LoginRegisterDTO loginRegisterDTO
) {
}
