package com.petshop.petshop_inventory.controller.authentication;


import com.petshop.petshop_inventory.dto.authentication.JWTAndLoginAuthenticatedDTO;
import com.petshop.petshop_inventory.dto.authentication.LoginAuthenticationDTO;
import com.petshop.petshop_inventory.dto.person.add_ons.LoginRegisterDTO;
import com.petshop.petshop_inventory.dto.person.add_ons.LoginResponseDTO;
import com.petshop.petshop_inventory.model.person.Login;
import com.petshop.petshop_inventory.service.authentication.TokenService;
import com.petshop.petshop_inventory.service.person.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
@Tag(name = "Authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginService loginService;



    @PostMapping
    @Transactional
    public ResponseEntity authenticateLogin(@RequestBody @Valid LoginAuthenticationDTO loginAuthenticationData) {

        var login = loginService.getLoginByUsername(loginAuthenticationData.username());

        Authentication authToken = new UsernamePasswordAuthenticationToken(loginAuthenticationData.username(),
                loginAuthenticationData.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((Login) authenticatedUser.getPrincipal(), (Login) login);



        return ResponseEntity.ok(new JWTAndLoginAuthenticatedDTO((Login) login, JWTToken));
    }


    @PostMapping("/register")
    @Transactional
    public ResponseEntity<LoginResponseDTO> registerLogin (@RequestBody @Valid LoginRegisterDTO loginRegisterDTO, UriComponentsBuilder uriBuilder) throws Exception {
        var login = loginService.registerLogin(loginRegisterDTO);
        var uri = uriBuilder.path("/login/{id}").buildAndExpand(login.id()).toUri();
        return ResponseEntity.created(uri).body(login);
    }



}
