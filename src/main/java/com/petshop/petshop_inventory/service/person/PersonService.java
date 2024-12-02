package com.petshop.petshop_inventory.service.person;

import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.dto.person.PersonResponseDTO;
import com.petshop.petshop_inventory.dto.person.PersonUpdateDTO;
import com.petshop.petshop_inventory.model.person.Person;
import com.petshop.petshop_inventory.model.person.add_ons.Role;
import com.petshop.petshop_inventory.repository.person.LoginRepository;
import com.petshop.petshop_inventory.repository.person.PersonRepository;
import com.petshop.petshop_inventory.validation.person.PersonRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    List<PersonRegistrationValidator> registrationValidations;

    @Autowired
    private LoginRepository loginRepository;

    //Update


    public PersonResponseDTO registerPerson(PersonRegisterDTO personRegisterDTO) {

        registrationValidations.forEach(validation -> validation.validateRegistration(personRegisterDTO));
        var person = new Person(personRegisterDTO);
        personRepository.save(person);
        return new PersonResponseDTO(person);
    }
    public Page<PersonResponseDTO> getAllPeople(Pageable pageable) {
        return personRepository.findAll(pageable).map(PersonResponseDTO::new);
    }


    public PersonResponseDTO updatePerson(PersonUpdateDTO personUpdateDTO) {
        var person = personRepository.findById(personUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("Person not found"));
        person.update(personUpdateDTO);
        personRepository.save(person);
        return new PersonResponseDTO(person);
    }

    public Page<PersonResponseDTO> getAllEmployees(Pageable pageable) {
        var login = loginRepository.findAllByRole(Role.WORKER, pageable);

        var employees = login.map(l -> personRepository.findById(l.getPerson
        ().getId()).get());
        return employees.map(PersonResponseDTO::new);
    }
}
