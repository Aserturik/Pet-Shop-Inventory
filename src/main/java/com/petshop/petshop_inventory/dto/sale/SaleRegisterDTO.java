package com.petshop.petshop_inventory.dto.sale;

import com.petshop.petshop_inventory.dto.sale.add_ons.SaleDetailsRegisterDTO;
import com.petshop.petshop_inventory.model.sale.add_ons.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SaleRegisterDTO(


        @NotNull
        List<SaleDetailsRegisterDTO> saleDetails,
        @NotNull
        PaymentMethod paymentMethod,

        @NotNull
        String username
) {
}
