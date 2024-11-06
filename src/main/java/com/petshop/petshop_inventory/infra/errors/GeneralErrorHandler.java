package com.petshop.petshop_inventory.infra.errors;

import com.petshop.petshop_inventory.exception.product.DuplicateBarcodeException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GeneralErrorHandler {

    // 1. Manejo de EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleError404() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", "NOT_FOUND");
        response.put("message", "El recurso solicitado no fue encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 2. Manejo de MethodArgumentNotValidException (Errores de validación de campos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleError400(MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", "VALIDATION_ERROR");
        response.put("message", "Uno o más campos son inválidos.");

        List<Map<String, String>> fieldErrors = e.getFieldErrors().stream().map(fieldError -> {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("field", fieldError.getField());
            errorDetails.put("error", fieldError.getDefaultMessage());
            return errorDetails;
        }).toList();

        response.put("details", fieldErrors);
        return ResponseEntity.badRequest().body(response);
    }

    // 3. Manejo de IntegrityValidation (Errores de integridad de datos)
    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity<Map<String, Object>> errorHandlerValidationsIntegrity(IntegrityValidation e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", "DATA_INTEGRITY_ERROR");
        response.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    // 4. Manejo de ValidationException (Errores de validaciones de negocio)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> errorHandlerBusinessValidations(ValidationException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", "BUSINESS_VALIDATION_ERROR");
        response.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    // Clase interna para manejar errores de validación específicos
    private record DataErrorValidation(String field, String error) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }





}
