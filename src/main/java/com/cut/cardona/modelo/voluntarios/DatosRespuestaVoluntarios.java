package com.cut.cardona.modelo.voluntarios;

public record DatosRespuestaVoluntarios(
        Long id,
        String nombre,
        String apellido,
        String email,
        String telefono,
        Rol rol,
        Boolean casaPuente


) {

    public DatosRespuestaVoluntarios(Voluntario voluntarios) {
        this(voluntarios.getId(), voluntarios.getNombre(), voluntarios.getApellido(), voluntarios.getEmail(), voluntarios.getTelefono(), voluntarios.getRol(),voluntarios.getCasaPuente());
    }

}