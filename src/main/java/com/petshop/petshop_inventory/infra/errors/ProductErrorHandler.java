package com.petshop.petshop_inventory.infra.errors;

import com.petshop.petshop_inventory.exception.product.DuplicateBarcodeException;
import com.petshop.petshop_inventory.exception.product.InvalidPriceException;
import com.petshop.petshop_inventory.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProductErrorHandler {
    @ExceptionHandler(DuplicateBarcodeException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateBarcodeException(DuplicateBarcodeException ex) {
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

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<Map<String, Object>> handlePurchasePriceGreaterThanSalePriceException(InvalidPriceException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());

        Map<String, Object> details = new HashMap<>();
        details.put("field", ex.getField());
        details.put("purchasePrice", ex.getPurchasePrice());
        details.put("salePrice", ex.getSalePrice());
        response.put("details", details);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(ProductNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());

        Map<String, String> details = new HashMap<>();
        details.put("field", ex.getField());
        details.put("value", ex.getValue());
        response.put("details", details);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
