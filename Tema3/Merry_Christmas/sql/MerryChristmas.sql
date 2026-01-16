DROP DATABASE IF EXISTS MerryChristmas;
CREATE DATABASE MerryChristmas;
USE MerryChristmas;

CREATE TABLE Camel (
    camel_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE MagicKing (
    id_magicking BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    camel BIGINT,
    FOREIGN KEY (camel) REFERENCES Camel(camel_id) ON DELETE SET NULL
);

CREATE TABLE Child (
    child_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    isNice BOOLEAN NOT NULL DEFAULT TRUE,
    myKing BIGINT NOT NULL,
    FOREIGN KEY (myKing) REFERENCES MagicKing(id_magicking) ON DELETE CASCADE
);

CREATE TABLE Gift (
    id_gift BIGINT AUTO_INCREMENT PRIMARY KEY,
    giftName VARCHAR(100) NOT NULL,
    child BIGINT NOT NULL,
    isPackaged BOOLEAN DEFAULT FALSE,
    isSent BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (child) REFERENCES Child(child_id) ON DELETE CASCADE
);

-- Tabla de pajes reales (packaging gifts)
CREATE TABLE Paje (
    paje_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Packaging (
    paje BIGINT NOT NULL,
    gift BIGINT NOT NULL,
    FOREIGN KEY (paje) REFERENCES Paje(paje_id) ON DELETE CASCADE,
    FOREIGN KEY (gift) REFERENCES Gift(id_gift) ON DELETE CASCADE,
    PRIMARY KEY (paje, gift)
);

-- Tabla de emisarios (envíos)
CREATE TABLE Emissary (
    emissary_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    workFor BIGINT NOT NULL,  
    FOREIGN KEY (workFor) REFERENCES MagicKing(id_magicking) ON DELETE CASCADE
);

CREATE TABLE GiftDelivery (
    gift BIGINT NOT NULL,
    emissary BIGINT NOT NULL,
    FOREIGN KEY (gift) REFERENCES Gift(id_gift) ON DELETE CASCADE,
    FOREIGN KEY (emissary) REFERENCES Emissary(emissary_id) ON DELETE CASCADE,
    PRIMARY KEY (gift, emissary)
);

INSERT INTO Camel (name) VALUES
('Bactriano'),
('Dromedario'),
('Arabiano');

INSERT INTO MagicKing (name, camel) VALUES
('Melchor', 1),
('Gaspar', 2),
('Baltasar', 3);

INSERT INTO Child (name, address, isNice, myKing) VALUES
('Juan Pérez', 'Calle Falsa 123, Madrid', TRUE, 1),
('María López', 'Calle del Sol 456, Barcelona', TRUE, 1),
('Pedro Gómez', 'Avenida de la Paz 789, Valencia', FALSE, 2),
('Ana Sánchez', 'Calle del Mar 101, Sevilla', TRUE, 2),
('Luis Fernández', 'Calle Mayor 202, Zaragoza', TRUE, 3),
('Carmen Díaz', 'Calle Real 303, Bilbao', FALSE, 3),
('Carlos Rodríguez', 'Calle Larga 404, Madrid', TRUE, 1),
('Laura Martínez', 'Calle del Norte 505, Salamanca', TRUE, 1),
('José Pérez', 'Avenida Central 606, Alicante', TRUE, 2),
('Raquel García', 'Calle del Sol 707, Madrid', TRUE, 2),
('Miguel Torres', 'Calle de los Ángeles 808, Sevilla', FALSE, 3),
('Elena Gómez', 'Calle del Río 909, Valencia', TRUE, 3),
('Alba Ruiz', 'Calle del Campo 1010, Barcelona', TRUE, 1),
('Javier Morales', 'Calle de la Luna 1111, Zaragoza', TRUE, 2),
('Marina Fernández', 'Calle del Sol 1212, Madrid', FALSE, 3),
('Rocío Martínez', 'Calle Mayor 1313, Valencia', TRUE, 1),
('Pablo López', 'Calle de la Paz 1414, Madrid', TRUE, 2),
('Beatriz Jiménez', 'Avenida de la Paz 1515, Bilbao', TRUE, 3),
('David González', 'Calle del Mar 1616, Salamanca', FALSE, 1),
('Eva Torres', 'Calle Real 1717, Alicante', TRUE, 2),
('Sergio Sánchez', 'Calle del Sol 1818, Zaragoza', TRUE, 3),
('Patricia López', 'Avenida Central 1919, Madrid', TRUE, 1),
('Victoria Ruiz', 'Calle Larga 2020, Valencia', TRUE, 2),
('Marta Sánchez', 'Paseo La Castellana 1212, Madrid', TRUE, 2),
('Tomás Pérez', 'Calle del Río 2121, Barcelona', TRUE, 3);

INSERT INTO Paje (name) VALUES
('Juan el Empaquetador'),
('Ana la Empaquetadora'),
('Carlos el Rápido'),
('Elena la Cuidadosa'),
('María la Hábil'),
('José el Fuerte'),
('Lucía la Ágil'),
('Manuel el Preciso'),
('Rocío la Dedicada'),
('Sergio el Organizado'),
('Pablo el Paciente'),
('Raquel la Creativa');

INSERT INTO Emissary (name, workFor) VALUES
('Emisario 1', 1),
('Emisario 2', 1),
('Emisario 3', 1),
('Emisario 4', 2),
('Emisario 5', 2),
('Emisario 6', 2),
('Emisario 7', 3),
('Emisario 8', 3),
('Emisario 9', 3);

INSERT INTO Gift (giftName, child, isPackaged, isSent) VALUES
('Bicicleta', 1, FALSE, FALSE),
('Muñeca', 2, TRUE, FALSE),
('Pelota', 3, TRUE, TRUE),
('Peluche', 4, FALSE, FALSE),
('Libro de cuentos', 5, TRUE, TRUE),
('Set de lego', 6, TRUE, FALSE),
('Pizarra magnética', 7, FALSE, TRUE),
('Coche de juguete', 8, TRUE, FALSE),
('Juego de mesa', 9, FALSE, FALSE),
('Patinete', 10, TRUE, TRUE),
('Cámara de fotos', 11, TRUE, FALSE),
('Balón de fútbol', 12, TRUE, TRUE),
('Patines', 13, FALSE, FALSE),
('Tetra pack de juegos', 14, TRUE, TRUE),
('Videojuego', 15, TRUE, FALSE),
('Set de pintura', 16, FALSE, TRUE),
('Dron', 17, TRUE, FALSE),
('Monopatín', 18, TRUE, TRUE),
('Pelota de rugby', 19, FALSE, FALSE),
('Patines en línea', 20, TRUE, TRUE),
('Juguete de madera', 21, TRUE, FALSE),
('Estuche de dibujo', 22, FALSE, TRUE),
('Coche de carreras', 23, TRUE, TRUE),
('Sofá de muñecos', 24, TRUE, FALSE),
('Muñeco de acción', 25, FALSE, TRUE);

INSERT INTO Packaging (paje, gift) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5),
(6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
(11, 11), (12, 12), (1, 13), (2, 14), (3, 15),
(4, 16), (5, 17), (6, 18), (7, 19), (8, 20),
(9, 21), (10, 22), (11, 23), (12, 24), (1, 25);

INSERT INTO GiftDelivery (gift, emissary) VALUES
(1, 1), (2, 1), (3, 1), (4, 2), (5, 2),
(6, 2), (7, 3), (8, 3), (9, 3), (10, 4),
(11, 4), (12, 4), (13, 5), (14, 5), (15, 5),
(16, 6), (17, 6), (18, 6), (19, 7), (20, 7),
(21, 7), (22, 8), (23, 8), (24, 8), (25, 9);
