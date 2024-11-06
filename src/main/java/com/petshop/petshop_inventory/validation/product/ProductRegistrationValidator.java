package com.petshop.petshop_inventory.validation.product;

import com.petshop.petshop_inventory.dto.product.ProductRegisterDTO;

public interface ProductRegistrationValidator {
    public void validateRegistration(ProductRegisterDTO productRegisterDTO);
}
