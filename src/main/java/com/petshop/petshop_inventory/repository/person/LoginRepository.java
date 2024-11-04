package com.petshop.petshop_inventory.repository.person;

import com.petshop.petshop_inventory.model.person.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginRepository extends JpaRepository<Login, Long> {

    UserDetails findByUsername(String username);

    @Query("SELECT l FROM Login l WHERE l.username = ?1")
    Login findByUsernameData(String username);

}
