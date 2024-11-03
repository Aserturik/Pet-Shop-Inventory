package com.petshop.petshop_inventory.dto.purchase;

import com.petshop.petshop_inventory.dto.purchase.add_ons.PurchaseDetailsRegisterDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PurchaseRegisterDTO(

        @NotBlank
        String purchaseNumber,

        @NotNull
        List<PurchaseDetailsRegisterDTO> purchaseDetails
) {
}
