package com.petshop.petshop_inventory.repository.person;

import com.petshop.petshop_inventory.model.person.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginRepository extends JpaRepository<Login, Long> {

    UserDetails findByUsername(String username);

}
