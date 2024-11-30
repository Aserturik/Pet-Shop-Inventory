package com.petshop.petshop_inventory.controller.person;


import com.petshop.petshop_inventory.dto.person.PersonRegisterDTO;
import com.petshop.petshop_inventory.dto.person.PersonResponseDTO;
import com.petshop.petshop_inventory.dto.person.PersonUpdateDTO;
import com.petshop.petshop_inventory.infra.errors.IntegrityValidation;
import com.petshop.petshop_inventory.service.person.PersonService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    @Transactional
    public ResponseEntity<PersonResponseDTO> registerPerson (@RequestBody @Valid PersonRegisterDTO personRegisterDTO, UriComponentsBuilder uriBuilder) throws IntegrityValidation {
        var person = personService.registerPerson(personRegisterDTO);
        var uri = uriBuilder.path("/person/{id}").buildAndExpand(person.id()).toUri();
        return ResponseEntity.created(uri).body(person);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<PagedModel<PersonResponseDTO>> getAllPeople(Pageable pageable){
        var people = personService.getAllPeople(pageable);
        PagedModel<PersonResponseDTO> pagedModel = new PagedModel<>(people);
        return ResponseEntity.ok(pagedModel);
    }


    @PutMapping
     @Transactional
        public ResponseEntity<PersonResponseDTO> updatePerson(@RequestBody @Valid PersonUpdateDTO personUpdateDTO){
            var person = personService.updatePerson(personUpdateDTO);
            return ResponseEntity.ok(person);
        }



}
