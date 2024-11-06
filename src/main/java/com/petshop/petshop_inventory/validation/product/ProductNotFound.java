package com.petshop.petshop_inventory.validation.product;

import com.petshop.petshop_inventory.exception.product.ProductNotFoundException;
import com.petshop.petshop_inventory.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductNotFound implements ProductSearchValidation {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void validateSearch(String barcode) {
        var product = productRepository.findByBarCode(barcode);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(
                    "No se encontró el producto con el código de barras: " + barcode,
                    "PRODUCT_NOT_FOUND",
                    "barCode",
                    barcode
            );
        }
    }
}
