package com.petshop.petshop_inventory.dto.product;


import com.petshop.petshop_inventory.model.product.Product;

public record ProductResponseDTO(
        Long id,
        String name,
        String barCode,
        Double purchasePrice,
        Double salePrice,
        Integer stock,
        String urlImage



) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getBarCode(), product.getPurchasePrice(), product.getSalePrice(), product.getStock(), product.getUrlImage());
    }
}
