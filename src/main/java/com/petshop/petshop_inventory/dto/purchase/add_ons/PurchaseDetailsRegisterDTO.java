package com.petshop.petshop_inventory.dto.purchase.add_ons;

import com.petshop.petshop_inventory.dto.product.add_ons.BatchRegisterDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseDetailsRegisterDTO(

        @NotBlank
        String productBarCode,

        @NotNull
        @Positive
        Double unitPrice,

        @NotNull
        @Positive
        Integer quantity,

        @NotNull
        BatchRegisterDTO batch
) {


}
