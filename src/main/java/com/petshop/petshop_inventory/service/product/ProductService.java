package com.petshop.petshop_inventory.service.product;



import com.petshop.petshop_inventory.dto.product.ProductRegisterDTO;
import com.petshop.petshop_inventory.dto.product.ProductResponseDTO;
import com.petshop.petshop_inventory.dto.product.ProductUpdateDTO;
import com.petshop.petshop_inventory.exception.product.ProductNotFoundException;
import com.petshop.petshop_inventory.model.product.Product;
import com.petshop.petshop_inventory.repository.product.ProductRepository;
import com.petshop.petshop_inventory.validation.product.ProductRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    List<ProductRegistrationValidator> registrationValidations;

    public ProductResponseDTO registerProduct(ProductRegisterDTO productRegisterDTO) {

        registrationValidations.forEach(validation -> validation.validateRegistration(productRegisterDTO));

        var product = new Product(productRegisterDTO);
        productRepository.save(product);
        return new ProductResponseDTO(product);
    }

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll( pageable).map(ProductResponseDTO::new);
    }

    public ProductResponseDTO updateProduct(ProductUpdateDTO productUpdateDTO){
        var product = productRepository.findById(productUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.update(productUpdateDTO);
        productRepository.save(product);
        return new ProductResponseDTO(product);

    }


    public ProductResponseDTO getProductByBarCode(String barCode) {
        var product = productRepository.findByBarCode(barCode).orElseThrow(() -> new ProductNotFoundException(
                "Producto no encontrado con el código de barras: " + barCode,
                "PRODUCT_NOT_FOUND",
                "barCode",
                barCode
        ));
        return new ProductResponseDTO(product);
    }
}
