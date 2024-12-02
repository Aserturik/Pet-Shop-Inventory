package com.petshop.petshop_inventory.service.purchase;

import static org.junit.jupiter.api.Assertions.*;


import com.petshop.petshop_inventory.dto.purchase.PurchaseRegisterDTO;
import com.petshop.petshop_inventory.dto.purchase.PurchaseResponseDTO;
import com.petshop.petshop_inventory.dto.purchase.add_ons.PurchaseDetailsRegisterDTO;
import com.petshop.petshop_inventory.model.purchase.Purchase;
import com.petshop.petshop_inventory.model.purchase.add_ons.PurchaseDetails;
import com.petshop.petshop_inventory.repository.purchase.PurchaseRepository;
import com.petshop.petshop_inventory.repository.product.ProductRepository;
import com.petshop.petshop_inventory.repository.product.add_ons.BatchRepository;
import com.petshop.petshop_inventory.repository.purchase.add_ons.PurchaseDetailsRepository;
import com.petshop.petshop_inventory.service.purchase.add_ons.PurchaseDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private PurchaseDetailsRepository purchaseDetailsRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private PurchaseDetailsService purchaseDetailsService;

    @Mock
    private Pageable pageable;

    private PurchaseRegisterDTO purchaseRegisterDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        purchaseRegisterDTO = new PurchaseRegisterDTO("123", Collections.emptyList()); // Use real DTO data here
    }

    @Test
    void shouldRegisterPurchaseSuccessfully() {
        // Arrange
        Purchase purchase = new Purchase(purchaseRegisterDTO);
        PurchaseDetails purchaseDetails = mock(PurchaseDetails.class);
        PurchaseDetailsRegisterDTO purchaseDetailsRegisterDTO = mock(PurchaseDetailsRegisterDTO.class);

        when(purchaseDetailsService.registerPurchaseDetails(any(), any())).thenReturn(purchaseDetails);
        when(purchaseRepository.save(any(Purchase.class))).thenReturn(purchase);

        // Act
        PurchaseResponseDTO response = purchaseService.registerPurchase(purchaseRegisterDTO);

        // Assert
        assertNotNull(response);
        assertEquals(purchase.getPurchaseNumber(), response.purchaseNumber());
        verify(purchaseRepository, times(1)).save(purchase);
    }




    @Test
    void shouldThrowExceptionWhenProductNotFoundInRegisterPurchaseDetails() {
        // Arrange
        PurchaseDetailsRegisterDTO purchaseDetailsRegisterDTO = new PurchaseDetailsRegisterDTO(
                "99999", 10.0, 5, null
        );
        Purchase purchase = mock(Purchase.class);

        when(productRepository.findByBarCode(purchaseDetailsRegisterDTO.productBarCode()))
                .thenReturn(Optional.empty()); // Product not found

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            purchaseDetailsService.registerPurchaseDetails(purchaseDetailsRegisterDTO, purchase);
        });
    }
}
