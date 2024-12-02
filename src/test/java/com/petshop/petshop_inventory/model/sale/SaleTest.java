package com.petshop.petshop_inventory.model.sale;

import static org.junit.jupiter.api.Assertions.*;


import com.petshop.petshop_inventory.dto.sale.SaleRegisterDTO;
import com.petshop.petshop_inventory.model.person.Login;
import com.petshop.petshop_inventory.model.person.Person;
import com.petshop.petshop_inventory.model.sale.add_ons.PaymentMethod;
import com.petshop.petshop_inventory.model.sale.add_ons.SaleDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private SaleRegisterDTO saleRegisterDTO;
    private Sale sale;
    private Login login;
    private Person person;
    private SaleDetails saleDetails1;
    private SaleDetails saleDetails2;


    @Test
    void testConstructorFromSaleRegisterDTO() {
        // Verificar que el objeto Sale ha sido creado correctamente desde el DTO y login
        assertNotNull(sale);
        assertEquals(PaymentMethod.CREDIT_CARD, sale.getPaymentMethod());
        assertNotNull(sale.getSaleDate());  // Verificar que la fecha no es nula
        assertEquals(login, sale.getLogin()); // Verificar que el login se ha asignado
        assertNull(sale.getPerson()); // La persona debería ser null al no establecerla en el constructor
        assertTrue(sale.getSaleDetails().isEmpty()); // La lista de detalles de la venta debe estar vacía
    }

    @Test
    void testAddSaleDetails() {
        // Agregar detalles de venta a la lista
        sale.addSaleDetails(Arrays.asList(saleDetails1, saleDetails2));

        // Verificar que los detalles se han agregado correctamente
        assertEquals(2, sale.getSaleDetails().size());
        assertTrue(sale.getSaleDetails().contains(saleDetails1));
        assertTrue(sale.getSaleDetails().contains(saleDetails2));
    }

    @Test
    void testAddEmptySaleDetails() {
        // Intentar agregar una lista vacía de detalles de venta
        sale.addSaleDetails(Arrays.asList());

        // Verificar que la lista sigue vacía
        assertTrue(sale.getSaleDetails().isEmpty());
    }

    @Test
    void testSetPerson() {
        // Verificar si se puede asignar una persona
        sale.setPerson(person);

        // Verificar que la persona se ha asignado correctamente
        assertEquals(person, sale.getPerson());
    }
}
