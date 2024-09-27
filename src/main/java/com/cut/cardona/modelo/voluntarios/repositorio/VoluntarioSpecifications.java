package com.cut.cardona.modelo.voluntarios.repositorio;

import org.springframework.data.jpa.domain.Specification;
import com.cut.cardona.modelo.voluntarios.Voluntario;

public class VoluntarioSpecifications {

    public static Specification<Voluntario> nombreContains(String query) {
        return (root, criteriaQuery, cb) -> cb.like(cb.lower(root.get("nombre")), "%" + query.toLowerCase().replace(" ", "").replace("-", "") + "%"
        );
    }

    public static Specification<Voluntario> apellidoContains(String query) {
        return (root, criteriaQuery, cb) -> cb.like(cb.lower(root.get("apellido")), "%" + query.toLowerCase().replace(" ", "").replace("-", "") + "%"
        );
    }

    public static Specification<Voluntario> telefonoContains(String query) {
        return (root, criteriaQuery, cb) -> cb.like(
                cb.lower(cb.function("REPLACE", String.class, cb.function("REPLACE", String.class, root.get("telefono"), cb.literal(" "), cb.literal("")), cb.literal("-"), cb.literal(""))),
                "%" + query.toLowerCase().replace(" ", "").replace("-", "") + "%"
        );
    }

    public static Specification<Voluntario> emailContains(String query) {
        return (root, criteriaQuery, cb) -> cb.like(cb.lower(root.get("email")), "%" + query.toLowerCase().replace(" ", "").replace("-", "") + "%"
        );
    }

}