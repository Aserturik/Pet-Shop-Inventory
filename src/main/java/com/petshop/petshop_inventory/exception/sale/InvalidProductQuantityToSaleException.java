package com.petshop.petshop_inventory.exception.sale;

import lombok.Getter;

@Getter
public class InvalidProductQuantityToSaleException extends RuntimeException {
    private final String code;
    private final String field;
    private final String value;

    public InvalidProductQuantityToSaleException(String message, String code, String field, String value) {
        super(message);
        this.code = code;
        this.field = field;
        this.value = value;
    }
}
