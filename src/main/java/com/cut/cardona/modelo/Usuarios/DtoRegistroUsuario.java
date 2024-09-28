package com.cut.cardona.modelo.Usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DtoRegistroUsuario(
        String userName,
        String email,
        String confirmEmail,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String confirmPassword,
        Boolean terms

) {
}