package com.cut.cardona.modelo.Usuarios.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RepositorioUsuarioRoles extends JpaRepository<Rol, Long> {
    Set<Rol> findByNombre(String nombre);
}
