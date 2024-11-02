package com.petshop.petshop_inventory.dto.product;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ProductUpdateDTO(

        @NotNull
        Long id,


        String name,

        String barCode,

        @Positive
        Double salePrice


) {

}
