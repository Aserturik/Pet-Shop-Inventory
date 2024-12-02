package com.petshop.petshop_inventory.dto.person.add_ons;

import com.petshop.petshop_inventory.dto.person.PersonResponseDTO;

public record AdminResponseDTO(
        PersonResponseDTO personResponseDTO,
        LoginResponseDTO loginResponseDTO
) {

}
