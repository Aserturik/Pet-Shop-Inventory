package com.petshop.petshop_inventory.exception.person;


import lombok.Getter;

@Getter
public class DuplicateDocumentException extends RuntimeException {
    private final String code;
    private final String field;
    private final String value;

    public DuplicateDocumentException(String message, String code, String field, String value) {
        super(message);
        this.code = code;
        this.field = field;
        this.value = value;
    }


}
