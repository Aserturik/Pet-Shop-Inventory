package com.petshop.petshop_inventory.repository.person;

import com.petshop.petshop_inventory.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByDocumentNumber(Long document);
    Person findByEmail(String email);
    Person findByPhone(String phone);

}
