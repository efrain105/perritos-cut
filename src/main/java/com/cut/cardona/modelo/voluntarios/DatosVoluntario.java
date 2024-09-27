package com.cut.cardona.modelo.voluntarios;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Valid
public record DatosVoluntario(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
        String apellido,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        String email,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "\\d{10}|(\\d{3}-\\d{3}-\\d{4})", message = "El teléfono debe tener 10 dígitos o estar en el formato xxx-xxx-xxxx")
        String telefono,

        @NotNull(message = "El rol es obligatorio")
        Rol rol,

        @NotNull(message = "El campo casaPuente es obligatorio")
        Boolean casaPuente
) {

}
