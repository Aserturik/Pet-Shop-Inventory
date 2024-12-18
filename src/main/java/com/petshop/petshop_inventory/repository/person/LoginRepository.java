package com.petshop.petshop_inventory.repository.person;

import com.petshop.petshop_inventory.model.person.Login;
import com.petshop.petshop_inventory.model.person.Person;
import com.petshop.petshop_inventory.model.person.add_ons.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginRepository extends JpaRepository<Login, Long> {

    UserDetails findByUsername(String username);

    @Query("SELECT l FROM Login l WHERE l.username = ?1")
    Login findByUsernameData(String username);

    Page<Login> findAllByRole(Role role, Pageable pageable);

}
