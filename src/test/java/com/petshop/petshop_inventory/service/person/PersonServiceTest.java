package com.petshop.petshop_inventory.service.person;

import static org.junit.jupiter.api.Assertions.*;


import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.dto.person.PersonResponseDTO;
import com.petshop.petshop_inventory.dto.person.PersonUpdateDTO;
import com.petshop.petshop_inventory.model.person.Person;
import com.petshop.petshop_inventory.model.person.add_ons.DocumentType;
import com.petshop.petshop_inventory.repository.person.PersonRepository;
import com.petshop.petshop_inventory.validation.person.PersonRegistrationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private List<PersonRegistrationValidator> registrationValidations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerPerson_ShouldRegisterAndReturnResponseDTO() {
        // Arrange
        PersonRegisterDTO dto = new PersonRegisterDTO(
                "John Doe",
                123456L,
                DocumentType.CC,
                "john.doe@example.com",
                "123-456-7890"
        );
        Person person = new Person(dto);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        // Act
        PersonResponseDTO response = personService.registerPerson(dto);

        // Assert
        verify(personRepository).save(any(Person.class));
        verify(registrationValidations).forEach(any());
        assertEquals(dto.name(), response.name());
        assertEquals(dto.email(), response.email());
    }

    @Test
    void getAllPeople_ShouldReturnPaginatedResponseDTOs() {
        // Arrange
        Person person = new Person();
        person.setId(1L);
        person.setName("John Doe");
        Page<Person> page = new PageImpl<>(List.of(person));
        when(personRepository.findAll(any(PageRequest.class))).thenReturn(page);

        // Act
        Page<PersonResponseDTO> response = personService.getAllPeople(PageRequest.of(0, 10));

        // Assert
        verify(personRepository).findAll(any(PageRequest.class));
        assertEquals(1, response.getTotalElements());
        assertEquals("John Doe", response.getContent().get(0).name());
    }

    @Test
    void updatePerson_ShouldUpdateAndReturnResponseDTO() {
        // Arrange
        Person person = new Person();
        person.setId(1L);
        person.setName("John Doe");
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        PersonUpdateDTO dto = new PersonUpdateDTO(1L, "Jane Doe", null, null, null, null);

        // Act
        PersonResponseDTO response = personService.updatePerson(dto);

        // Assert
        verify(personRepository).findById(1L);
        verify(personRepository).save(any(Person.class));
        assertEquals("Jane Doe", response.name());
    }

    @Test
    void updatePerson_ShouldThrowExceptionWhenPersonNotFound() {
        // Arrange
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());
        PersonUpdateDTO dto = new PersonUpdateDTO(1L, "Jane Doe", null, null, null, null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> personService.updatePerson(dto));
        verify(personRepository).findById(1L);
        verify(personRepository, never()).save(any(Person.class));
    }
}
