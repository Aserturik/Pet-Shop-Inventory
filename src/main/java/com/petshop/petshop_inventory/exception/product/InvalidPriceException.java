package com.petshop.petshop_inventory.exception.product;

import lombok.Getter;

@Getter
public class InvalidPriceException extends RuntimeException{
    private final String code;
    private final String field;
    private final double purchasePrice;
    private final double salePrice;

    public InvalidPriceException(double purchasePrice, double salePrice) {
        super("El precio de compra no puede ser mayor o igual al precio de venta.");
        this.code = "INVALID_PRICE_RELATION";
        this.field = "price";
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
    }
}
