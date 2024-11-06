package com.petshop.petshop_inventory.validation.product;

import com.petshop.petshop_inventory.dto.product.ProductRegisterDTO;
import com.petshop.petshop_inventory.exception.product.InvalidPriceException;
import org.springframework.stereotype.Component;

@Component
public class PurchasePriceGreaterThanSalePrice implements ProductRegistrationValidator {

    @Override
    public void validateRegistration(ProductRegisterDTO productRegisterDTO) {

        if (productRegisterDTO.purchasePrice() >= productRegisterDTO.salePrice()) {
            throw new InvalidPriceException(
                    productRegisterDTO.purchasePrice(),
                    productRegisterDTO.salePrice()
            );
        }
    }
}
