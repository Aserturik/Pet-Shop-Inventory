package com.petshop.petshop_inventory.validation.person;


import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.exception.person.DuplicateDocumentException;
import com.petshop.petshop_inventory.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateDocument implements PersonRegistrationValidator{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void validateRegistration(PersonRegisterDTO personRegisterDTO) {
        var person = personRepository.findByDocumentNumber(personRegisterDTO.documentNumber());
        if (person != null) {
            throw new DuplicateDocumentException(
                    "Ya existe una persona con el mismo documento.",
                    "DUPLICATE_DOCUMENT",
                    "document",
                    personRegisterDTO.documentNumber().toString()
            );
        }


    }
}
