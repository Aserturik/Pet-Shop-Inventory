package com.petshop.petshop_inventory.validation.person;


import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.exception.person.DuplicatePhoneException;
import com.petshop.petshop_inventory.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicatePhone implements PersonRegistrationValidator {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void validateRegistration(PersonRegisterDTO personRegisterDTO) {
        var person = personRepository.findByPhone(personRegisterDTO.phone());
        if(person != null){
            throw new DuplicatePhoneException(
                    "Ya existe una persona con el mismo número de teléfono.",
                    "DUPLICATE_PHONE",
                    "phone",
                    personRegisterDTO.phone()
            );
        }

    }


}
