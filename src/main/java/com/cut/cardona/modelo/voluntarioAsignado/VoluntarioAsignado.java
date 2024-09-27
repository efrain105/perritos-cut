package com.cut.cardona.modelo.voluntarioAsignado;

import com.cut.cardona.modelo.perros.Perro;
import com.cut.cardona.modelo.voluntarios.Voluntario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "voluntario_asignado")
@Entity(name = "Voluntario_asignado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VoluntarioAsignado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Boolean activo;
    private Date fechaInicio;
    private Date fechaFin;
    private String notas;

    @ManyToOne
    @JoinColumn(name = "perro_id")
    private Perro perro;
    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;
    @ManyToOne
    @JoinColumn(name = "encargado_id")
    private Voluntario encargado;

    public void setEncargado(Voluntario encargado) {
        this.encargado = encargado;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public void setPerro(Perro perro) {
        this.perro = perro;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
