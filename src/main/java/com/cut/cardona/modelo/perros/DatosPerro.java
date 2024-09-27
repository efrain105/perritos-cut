package com.cut.cardona.modelo.perros;

public record DatosPerro(
        String nombre,
        String edad,
        String raza,
        String color,
        Boolean esterilizacion,
        String comentarios
) {

}
