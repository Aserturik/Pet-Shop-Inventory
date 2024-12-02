package com.petshop.petshop_inventory.service.person;

import com.petshop.petshop_inventory.dto.person.add_ons.AdminRegisterDTO;
import com.petshop.petshop_inventory.dto.person.add_ons.AdminResponseDTO;
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

    @Autowired
    private PersonService personService;


    public LoginResponseDTO registerLogin(LoginRegisterDTO loginRegisterDTO) throws Exception {
        var login = new Login(loginRegisterDTO);
        loginRepository.save(login);
        return new LoginResponseDTO(login);
    }

    public Login getLoginById(Long id){
        return loginRepository.findById(id).get();
    }

    public Login getLoginByUsername(String username){
        return (Login) loginRepository.findByUsername(username);
    }

    public AdminResponseDTO registerAdmin(AdminRegisterDTO adminRegisterDTO) throws Exception {
        var personResponseDTO = personService.registerPerson(adminRegisterDTO.personRegisterDTO());
        var loginResponseDTO = registerLogin(adminRegisterDTO.loginRegisterDTO());

        var login = loginRepository.findById(loginResponseDTO.id()).get();
        var person = personRepository.findById(personResponseDTO.id()).get();
        login.setPerson(person);
        loginRepository.save(login);
        return new AdminResponseDTO(personResponseDTO, loginResponseDTO);

    }
}
