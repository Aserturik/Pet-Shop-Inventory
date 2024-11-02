package com.petshop.petshop_inventory.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRegisterDTO(
        @NotBlank
        String name,
        @NotBlank
        String barCode,

        @NotNull
        @Positive
        Double purchasePrice,
        @NotNull
        @Positive
        Double salePrice,
        @NotNull
        @PositiveOrZero
        Integer stock
) {
}
