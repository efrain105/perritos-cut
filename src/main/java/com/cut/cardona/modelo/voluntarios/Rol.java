package com.cut.cardona.modelo.voluntarios;

import lombok.Getter;

@Getter
public enum Rol {

    /*VOLUNTARIO("Voluntario"),
    ADOPTANTE("Adoptante"),
    ENCARGADO("Encargado");*/

    VOLUNTARIO("Voluntario"),
    ADOPTANTE("Adoptante"),
    ENCARGADO("Encargado");

    private final String label;

    Rol(String label) {
        this.label = label;
    }

}
