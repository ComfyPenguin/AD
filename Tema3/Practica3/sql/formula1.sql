CREATE DATABASE IF NOT EXISTS formula1;

USE formula1;

CREATE TABLE pilotos(
    idPiloto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    equipo VARCHAR(50),
    pais VARCHAR(100),
    edad INT,
    puntos DOUBLE,
    podios INT,
    victorias INT,
    activo BOOLEAN,
    altura_m DOUBLE,
    vuelta_rapida_seg DOUBLE
);