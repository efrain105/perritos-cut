CREATE TABLE perro
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    edad           VARCHAR(30)  NOT NULL,
    raza           VARCHAR(30),
    color          VARCHAR(25),
    esterilizacion BOOLEAN      NOT NULL,
    comentarios    TEXT
);
