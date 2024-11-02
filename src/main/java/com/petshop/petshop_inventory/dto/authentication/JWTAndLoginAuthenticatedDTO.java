package com.petshop.petshop_inventory.dto.authentication;


import com.petshop.petshop_inventory.model.person.Login;

public record JWTAndLoginAuthenticatedDTO(
        Long id,
        String username,
        String role,
        String jwtToken

) {
    public JWTAndLoginAuthenticatedDTO(Login login, String jwtToken)
    {
        this(login.getId(), login.getUsername(), login.getRole().toString(), jwtToken);
    }
}
