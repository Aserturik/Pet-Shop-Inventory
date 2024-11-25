package com.petshop.petshop_inventory.validation.person;

import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;

public interface PersonRegistrationValidator {

    public void validateRegistration(PersonRegisterDTO personRegisterDTO);
}
