package com.cut.cardona.modelo.voluntarios.repositorio;


import com.cut.cardona.modelo.voluntarios.DatosVoluntario;
import com.cut.cardona.modelo.voluntarios.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RepositorioVoluntario extends JpaRepository<Voluntario, Long>, JpaSpecificationExecutor<Voluntario> {

}
