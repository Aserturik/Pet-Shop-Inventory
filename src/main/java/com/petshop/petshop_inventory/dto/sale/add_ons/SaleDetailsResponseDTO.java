package com.petshop.petshop_inventory.dto.sale.add_ons;

import com.petshop.petshop_inventory.model.sale.add_ons.SaleDetails;

public record SaleDetailsResponseDTO(

        String productBarCode,
        String productName,
        Double unitPrice,
        Integer quantity,
        Double total
) {
    public SaleDetailsResponseDTO(SaleDetails saleDetails) {
        this(saleDetails.getProduct().getBarCode(),
                saleDetails.getProduct().getName(),
                saleDetails.getUnitSalePrice(),
                saleDetails.getQuantity(),
                saleDetails.getTotal());
    }
}
