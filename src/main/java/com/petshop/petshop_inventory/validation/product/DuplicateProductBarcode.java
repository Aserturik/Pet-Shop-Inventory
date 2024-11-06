package com.petshop.petshop_inventory.validation.product;

import com.petshop.petshop_inventory.dto.product.ProductRegisterDTO;
import com.petshop.petshop_inventory.exception.product.DuplicateBarcodeException;
import com.petshop.petshop_inventory.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateProductBarcode implements ProductRegistrationValidator {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void validateRegistration(ProductRegisterDTO productRegisterDTO) {
        var product = productRepository.findByBarCode(productRegisterDTO.barCode());
        if (product.isPresent()) {
            throw new DuplicateBarcodeException(
                    "Ya existe un producto con el mismo código de barras.",
                    "DUPLICATE_BARCODE",
                    "barCode",
                    productRegisterDTO.barCode()
            );
        }
    }
}
