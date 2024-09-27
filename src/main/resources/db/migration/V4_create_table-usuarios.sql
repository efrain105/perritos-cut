CREATE TABLE usuarios
(
    id char(36) PRIMARY KEY,
    user_name              VARCHAR(100) UNIQUE NOT NULL,
    email                  VARCHAR(150) UNIQUE NOT NULL,
    password               VARCHAR(255)        NOT NULL,
    fecha_creacion         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultimo_acceso          TIMESTAMP,
    activo                 BOOLEAN   DEFAULT TRUE,
    token                  VARCHAR(255),
    fecha_expiracion_token TIMESTAMP
);
