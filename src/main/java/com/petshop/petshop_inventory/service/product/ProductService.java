package com.petshop.petshop_inventory.service.product;



import com.petshop.petshop_inventory.dto.product.ProductRegisterDTO;
import com.petshop.petshop_inventory.dto.product.ProductResponseDTO;
import com.petshop.petshop_inventory.dto.product.ProductUpdateDTO;
import com.petshop.petshop_inventory.model.product.Product;
import com.petshop.petshop_inventory.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO registerProduct(ProductRegisterDTO productRegisterDTO) {
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
        var product = productRepository.findByBarCode(barCode) .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return new ProductResponseDTO(product);
    }
}
