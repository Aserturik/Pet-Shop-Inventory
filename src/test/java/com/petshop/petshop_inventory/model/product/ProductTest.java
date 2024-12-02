package com.petshop.petshop_inventory.model.product;



import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.dto.person.PersonUpdateDTO;
import com.petshop.petshop_inventory.model.person.Person;
import com.petshop.petshop_inventory.model.person.add_ons.DocumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private PersonRegisterDTO personRegisterDTO;
    private PersonUpdateDTO personUpdateDTO;
    private Person person;

    @BeforeEach
    void setUp() {
        // Configuración inicial antes de cada test
        personRegisterDTO = new PersonRegisterDTO("John Doe", 123456789L, DocumentType.CC, "john.doe@example.com", "123-456-789");
        personUpdateDTO = new PersonUpdateDTO(1L, "Jane Doe", 987654321L, DocumentType.CC, "jane.doe@example.com", "987-654-321");

        // Crear una instancia de Person utilizando el constructor
        person = new Person(personRegisterDTO);
    }

    @Test
    void testConstructorFromPersonRegisterDTO() {
        assertNotNull(person);
        assertEquals("John Doe", person.getName());
        assertEquals(123456789L, person.getDocumentNumber());
        assertEquals(DocumentType.CC, person.getDocumentType());
        assertEquals("john.doe@example.com", person.getEmail());
        assertEquals("123-456-789", person.getPhone());
    }

    @Test
    void testUpdatePerson() {
        // Llamar al método update con el DTO de actualización
        person.update(personUpdateDTO);

        // Verificar que los valores se hayan actualizado correctamente
        assertEquals("Jane Doe", person.getName());
        assertEquals(987654321L, person.getDocumentNumber());
        assertEquals(DocumentType.CC, person.getDocumentType());
        assertEquals("jane.doe@example.com", person.getEmail());
        assertEquals("987-654-321", person.getPhone());
    }

    @Test
    void testUpdatePersonWithNullValues() {
        // Crear un PersonUpdateDTO con valores nulos
        PersonUpdateDTO emptyUpdateDTO = new PersonUpdateDTO(null, null, null, null, null, null);

        // Llamar al método update
        person.update(emptyUpdateDTO);

        // Verificar que los valores no hayan cambiado
        assertEquals("John Doe", person.getName());
        assertEquals(123456789L, person.getDocumentNumber());
        assertEquals(DocumentType.CC, person.getDocumentType());
        assertEquals("john.doe@example.com", person.getEmail());
        assertEquals("123-456-789", person.getPhone());
    }

    @Test
    void testPersonWithoutChanges() {
        // Verificar que no haya cambios cuando el DTO de actualización es vacío
        PersonUpdateDTO emptyDTO = new PersonUpdateDTO(null, null, null, null, null, null);
        person.update(emptyDTO);

        assertEquals("John Doe", person.getName());
        assertEquals(123456789L, person.getDocumentNumber());
        assertEquals(DocumentType.CC, person.getDocumentType());
        assertEquals("john.doe@example.com", person.getEmail());
        assertEquals("123-456-789", person.getPhone());
    }
}
