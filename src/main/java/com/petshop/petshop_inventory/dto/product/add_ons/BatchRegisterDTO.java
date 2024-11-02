package com.petshop.petshop_inventory.dto.product.add_ons;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BatchRegisterDTO(

        @NotBlank
        String batchNumber,
        @NotNull
        Long productId,

        LocalDate expirationDate,
        Integer quantity,
        Double unitPrice




) {

}
