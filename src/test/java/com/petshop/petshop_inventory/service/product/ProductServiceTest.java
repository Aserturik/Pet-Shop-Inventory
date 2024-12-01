package com.petshop.petshop_inventory.service.product;

import com.petshop.petshop_inventory.dto.product.ProductRegisterDTO;
import com.petshop.petshop_inventory.dto.product.ProductResponseDTO;
import com.petshop.petshop_inventory.dto.product.ProductUpdateDTO;
import com.petshop.petshop_inventory.exception.product.ProductNotFoundException;
import com.petshop.petshop_inventory.model.product.Product;
import com.petshop.petshop_inventory.repository.product.ProductRepository;
import com.petshop.petshop_inventory.validation.product.ProductRegistrationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductRegistrationValidator productRegistrationValidator; // Mock de los validadores

    @InjectMocks
    private ProductService productService;

    private ProductRegisterDTO productRegisterDTO;
    private Product product;

    @BeforeEach
    void setUp() {
        productRegisterDTO = new ProductRegisterDTO(
                "Product Name",
                "123456789",
                100.0,
                150.0,
                "http://example.com/image.jpg"
        );
        product = new Product(productRegisterDTO);
    }



    @Test
    void testUpdateProduct() {
        // Arrange
        ProductUpdateDTO updateDTO = new ProductUpdateDTO(1L, "Updated Name", "123456789", 160.0, "http://example.com/updated.jpg");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // Act
        ProductResponseDTO responseDTO = productService.updateProduct(updateDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals("Updated Name", responseDTO.name());
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void testGetProductByBarCode_ProductNotFound() {
        // Arrange
        when(productRepository.findByBarCode("123456789")).thenReturn(Optional.empty());

        // Act & Assert
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () ->
                productService.getProductByBarCode("123456789")
        );
        assertEquals("Producto no encontrado con el código de barras: 123456789", exception.getMessage());
    }

    @Test
    void testGetProductByBarCode_Success() {
        // Arrange
        when(productRepository.findByBarCode("123456789")).thenReturn(Optional.of(product));

        // Act
        ProductResponseDTO responseDTO = productService.getProductByBarCode("123456789");

        // Assert
        assertNotNull(responseDTO);
        assertEquals("Product Name", responseDTO.name());
    }
}
