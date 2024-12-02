package com.petshop.petshop_inventory.model.purchase;

import static org.junit.jupiter.api.Assertions.*;



import com.petshop.petshop_inventory.dto.purchase.PurchaseRegisterDTO;
import com.petshop.petshop_inventory.model.purchase.add_ons.PurchaseDetails;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    private PurchaseRegisterDTO purchaseRegisterDTO;
    private Purchase purchase;
    private PurchaseDetails purchaseDetails1;
    private PurchaseDetails purchaseDetails2;


    @Test
    void testConstructorFromPurchaseRegisterDTO() {
        // Verificar que el objeto Purchase ha sido creado correctamente desde el DTO
        assertNotNull(purchase);
        assertEquals("P123", purchase.getPurchaseNumber());
        assertEquals(LocalDate.now(), purchase.getPurchaseDate()); // Se espera la fecha actual
        assertNotNull(purchase.getPurchaseDetails());
        assertTrue(purchase.getPurchaseDetails().isEmpty()); // La lista debe estar vacía inicialmente
    }

    @Test
    void testAddPurchaseDetails() {
        // Agregar detalles de compra a la lista
        purchase.addPurchaseDetails(Arrays.asList(purchaseDetails1, purchaseDetails2));

        // Verificar que los detalles se han agregado correctamente
        assertEquals(2, purchase.getPurchaseDetails().size());
        assertTrue(purchase.getPurchaseDetails().contains(purchaseDetails1));
        assertTrue(purchase.getPurchaseDetails().contains(purchaseDetails2));
    }

    @Test
    void testAddEmptyPurchaseDetails() {
        // Intentar agregar una lista vacía de detalles de compra
        purchase.addPurchaseDetails(Arrays.asList());

        // Verificar que la lista sigue vacía
        assertTrue(purchase.getPurchaseDetails().isEmpty());
    }
}
