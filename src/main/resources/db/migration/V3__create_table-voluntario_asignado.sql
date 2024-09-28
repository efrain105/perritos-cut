CREATE TABLE IF NOT EXISTS voluntario_asignado
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    activo        BOOLEAN,
    fecha_inicio  DATE   NOT NULL,
    fecha_fin     DATE,
    notas         VARCHAR(255),
    perro_id      BIGINT not null,
    voluntario_id BIGINT not null,
    encargado_id  BIGINT not null,
    FOREIGN KEY (perro_id) REFERENCES perro (id),
    FOREIGN KEY (voluntario_id) REFERENCES voluntario (id),
    FOREIGN KEY (encargado_id) REFERENCES voluntario (id)
);
