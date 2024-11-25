package com.petshop.petshop_inventory.validation.person;


import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.exception.person.DuplicateEmailException;
import com.petshop.petshop_inventory.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateEmail implements PersonRegistrationValidator {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void validateRegistration(PersonRegisterDTO personRegisterDTO) {
        var person = personRepository.findByEmail(personRegisterDTO.email());

        if(person != null){
            throw new DuplicateEmailException(
                    "Ya existe una persona con el mismo correo electrónico.",
                    "DUPLICATE_EMAIL",
                    "email",
                    personRegisterDTO.email()
            );
        }

    }
}
