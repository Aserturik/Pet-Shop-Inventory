package com.petshop.petshop_inventory.exception.product;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final String code;
    private final String field;
    private final String value;

    public ProductNotFoundException(String message, String code, String field, String value) {
        super(message);
        this.code = code;
        this.field = field;
        this.value = value;
    }
}
