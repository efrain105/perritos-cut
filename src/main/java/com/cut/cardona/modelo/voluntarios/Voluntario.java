package com.cut.cardona.modelo.voluntarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "voluntario")
@Entity(name = "Voluntario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private Boolean casaPuente;


    public Voluntario(DatosVoluntario datosVoluntario) {
        this.nombre = datosVoluntario.nombre();
        this.apellido = datosVoluntario.apellido();
        this.email = datosVoluntario.email();
        this.telefono = datosVoluntario.telefono();
        this.rol = datosVoluntario.rol();
        this.casaPuente = datosVoluntario.casaPuente();
    }

    public void actualizarDatos(DatosVoluntario datosActualizarVoluntario) {
        if (datosActualizarVoluntario.nombre() != null && !datosActualizarVoluntario.nombre().isEmpty()) {
            this.nombre = datosActualizarVoluntario.nombre();
        }
        if (datosActualizarVoluntario.apellido() != null && !datosActualizarVoluntario.apellido().isEmpty()) {
            this.apellido = datosActualizarVoluntario.apellido();
        }
        if (datosActualizarVoluntario.email() != null && !datosActualizarVoluntario.email().isEmpty()) {
            this.email = datosActualizarVoluntario.email();
        }
        if (datosActualizarVoluntario.telefono() != null && !datosActualizarVoluntario.telefono().isEmpty()) {
            this.telefono = datosActualizarVoluntario.telefono();
        }
        if (datosActualizarVoluntario.rol() != null && !datosActualizarVoluntario.rol().toString().isEmpty()) {
            this.rol = datosActualizarVoluntario.rol();
        }
        if (datosActualizarVoluntario.casaPuente() != null) {
            this.casaPuente = datosActualizarVoluntario.casaPuente();
        }
    }

}
