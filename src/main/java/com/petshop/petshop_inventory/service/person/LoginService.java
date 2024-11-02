package com.petshop.petshop_inventory.service.person;

import com.petshop.petshop_inventory.model.person.Login;
import com.petshop.petshop_inventory.repository.person.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public Login getLoginByUsername(String username){
        return (Login) loginRepository.findByUsername(username);
    }

}
