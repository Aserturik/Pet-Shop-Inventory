package com.petshop.petshop_inventory.infra.errors;


import com.petshop.petshop_inventory.exception.person.DuplicateDocumentException;
import com.petshop.petshop_inventory.exception.person.DuplicateEmailException;
import com.petshop.petshop_inventory.exception.person.DuplicatePhoneException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PersonErrorHandler {

    @ExceptionHandler(DuplicatePhoneException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicatePhoneException(DuplicatePhoneException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());
        Map<String, String> details = new HashMap<>();
        details.put("field", ex.getField());
        details.put("value", ex.getValue());
        response.put("details", details);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEmailException(DuplicateEmailException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());
        Map<String, String> details = new HashMap<>();
        details.put("field", ex.getField());
        details.put("value", ex.getValue());
        response.put("details", details);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicateDocumentException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateDocumentException(DuplicateDocumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());
        Map<String, String> details = new HashMap<>();
        details.put("field", ex.getField());
        details.put("value", ex.getValue());
        response.put("details", details);
        return ResponseEntity.badRequest().body(response);
    }


}
