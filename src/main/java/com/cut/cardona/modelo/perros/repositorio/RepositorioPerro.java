package com.cut.cardona.modelo.perros.repositorio;

import com.cut.cardona.modelo.perros.Perro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositorioPerro extends JpaRepository<Perro,Long>, JpaSpecificationExecutor<Perro> {


    Page<Perro> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

}
