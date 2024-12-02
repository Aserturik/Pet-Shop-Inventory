package com.petshop.petshop_inventory.repository.person;

import com.petshop.petshop_inventory.model.person.Person;
import com.petshop.petshop_inventory.model.person.add_ons.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByDocumentNumber(Long document);
    Person findByEmail(String email);
    Person findByPhone(String phone);



}
