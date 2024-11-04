package com.petshop.petshop_inventory.dto.sale.add_ons;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleDetailsRegisterDTO(

        @NotBlank
        String productBarCode,

        @NotNull
        @Positive
        Integer quantity
) {

}
