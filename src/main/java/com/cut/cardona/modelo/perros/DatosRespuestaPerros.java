package com.cut.cardona.modelo.perros;

public record DatosRespuestaPerros(

        Long id,
        String nombre,
        String edad,
        String raza,
        Boolean esterilizacion

) {

    public DatosRespuestaPerros(Perro perro) {
        this(perro.getId(), perro.getNombre(), perro.getEdad(), perro.getRaza(), perro.getEsterilizacion());
    }

}
