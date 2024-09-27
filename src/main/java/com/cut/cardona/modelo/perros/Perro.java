package com.cut.cardona.modelo.perros;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "perro")
@Entity(name = "Perro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Perro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private String edad;
    private String raza;
    private String color;
    private Boolean esterilizacion;
    private String comentarios;

    public Perro(DatosPerro datosPerro) {
        this.nombre = datosPerro.nombre();
        this.edad = datosPerro.edad();
        this.raza = datosPerro.raza();
        this.color = datosPerro.color();
        this.esterilizacion = datosPerro.esterilizacion();
        this.comentarios = datosPerro.comentarios();
    }

    public void actualizarDatos(DatosPerro datosPerro) {
        if (datosPerro.nombre() != null && !datosPerro.nombre().isEmpty()) {
            this.nombre = datosPerro.nombre();
        }
        if (datosPerro.edad() != null && !datosPerro.edad().isEmpty()) {
            this.edad = datosPerro.edad();
        }
        if (datosPerro.raza() != null && !datosPerro.raza().isEmpty()) {
            this.raza = datosPerro.raza();
        }
        if (datosPerro.color() != null) {
            this.color = datosPerro.color();
        }
        if (datosPerro.esterilizacion() != null) {
            this.esterilizacion = datosPerro.esterilizacion();
        }
        if (datosPerro.comentarios() != null && !datosPerro.comentarios().isEmpty()) {
            this.comentarios = datosPerro.comentarios();
        }
    }





    public void setId(Long id) {
        this.id = id;
    }
}
