package com.cut.cardona.modelo.perros;

public record DatosMostrarPerros(
        Long id,
        String nombre,
        String edad,
        String raza,
        String color,
        Boolean esterilizacion,
        String comentarios
) {

    public DatosMostrarPerros(Perro perro) {
        this(perro.getId(), perro.getNombre(), perro.getEdad(), perro.getRaza(), perro.getColor(),
                perro.getEsterilizacion(), perro.getComentarios());
    }

}

