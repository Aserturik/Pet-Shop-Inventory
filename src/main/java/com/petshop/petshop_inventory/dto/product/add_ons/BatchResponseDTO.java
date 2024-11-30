package com.petshop.petshop_inventory.dto.product.add_ons;

import com.petshop.petshop_inventory.model.product.add_ons.Batch;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record BatchResponseDTO(


        String batchNumber,

        LocalDate expirationDate
) {

    public BatchResponseDTO(Batch bacth) {
        this(
                bacth.getBatchNumber(),
                bacth.getExpirationDate()
        );
    }
}
