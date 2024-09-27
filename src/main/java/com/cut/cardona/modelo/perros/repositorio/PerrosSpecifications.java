package com.cut.cardona.modelo.perros.repositorio;

import org.springframework.data.jpa.domain.Specification;
import com.cut.cardona.modelo.perros.Perro;

public class PerrosSpecifications {


    public static Specification<Perro> nombreContains(String query) {
        // Normalizamos el texto eliminando espacios redundantes y caracteres especiales
        return (root, criteriaQuery, cb) -> cb.like(cb.lower(root.get("nombre")), "%" + normalizeQuery(query) + "%");
    }

    public static Specification<Perro> edadIs(Integer edad) {
        // Si la edad es numérica, debería usarse equal en lugar de like
        return (root, criteriaQuery, cb) -> cb.equal(root.get("edad"), edad);
    }

    public static Specification<Perro> razaContains(String query) {
        return (root, criteriaQuery, cb) -> cb.like(cb.lower(root.get("raza")), "%" + normalizeQuery(query) + "%");
    }

    public static Specification<Perro> colorContains(String query) {
        return (root, criteriaQuery, cb) -> cb.like(cb.lower(root.get("color")), "%" + normalizeQuery(query) + "%");
    }


    // Método auxiliar para limpiar y normalizar las consultas
    private static String normalizeQuery(String query) {
        if (query == null) {
            return "";
        }
        return query.toLowerCase().trim().replaceAll("\\s+", "").replaceAll("-", "");
    }
}
