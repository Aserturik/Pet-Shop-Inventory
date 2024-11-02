package com.petshop.petshop_inventory.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record LoginAuthenticationDTO(
        @NotBlank
        @JsonProperty("username")
        String username,

        @NotBlank
        @JsonProperty("password")
        String password

) {
}
