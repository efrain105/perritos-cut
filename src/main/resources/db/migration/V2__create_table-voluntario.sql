CREATE TABLE voluntario
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(255),
    apellido    VARCHAR(100) NOT NULL,
    email       VARCHAR(255) NOT NULL unique,
    telefono    VARCHAR(255) NOT NULL unique,
    rol         VARCHAR(255),
    casa_puente BOOLEAN
);
