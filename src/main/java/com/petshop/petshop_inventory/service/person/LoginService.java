package com.petshop.petshop_inventory.service.person;

import com.petshop.petshop_inventory.dto.person.add_ons.LoginRegisterDTO;
import com.petshop.petshop_inventory.dto.person.add_ons.LoginResponseDTO;
import com.petshop.petshop_inventory.model.person.Login;
import com.petshop.petshop_inventory.repository.person.LoginRepository;
import com.petshop.petshop_inventory.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PersonRepository personRepository;


    public LoginResponseDTO registerLogin(LoginRegisterDTO loginRegisterDTO) throws Exception {
        var person = personRepository.findById(loginRegisterDTO.idPerson()).orElseThrow(() -> new Exception("Person not found"));
        var login = new Login(loginRegisterDTO, person);
        loginRepository.save(login);
        return new LoginResponseDTO(login);
    }

    public Login getLoginByUsername(String username){
        return (Login) loginRepository.findByUsername(username);
    }

}
