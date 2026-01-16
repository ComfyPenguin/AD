DROP DATABASE IF EXISTS GalacticTribesHB;
CREATE DATABASE GalacticTribesHB;
USE GalacticTribesHB;

CREATE TABLE Planet (
    planet_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    environment_type VARCHAR(50) NOT NULL -- Ej. Desert, Water, Lava, etc.
);

CREATE TABLE Tribe (
    tribe_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    members INT DEFAULT 0
);

CREATE TABLE Ecosystem (
	planet BIGINT,
    tribe BIGINT,
    FOREIGN KEY (tribe) REFERENCES Tribe(tribe_id),
    FOREIGN KEY (planet) REFERENCES Planet(planet_id),
    PRIMARY KEY (tribe, planet)
);

CREATE TABLE Player (
    player_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    points INT,
    myTribe BIGINT,
    FOREIGN KEY (myTribe) REFERENCES Tribe(tribe_id)
);

CREATE TABLE Battle (
    battle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(100) NOT NULL,
    winner BIGINT,
    FOREIGN KEY (winner) REFERENCES Tribe(tribe_id)
    
);

CREATE TABLE History_Fights (
    tribe BIGINT,
    battle BIGINT,
    FOREIGN KEY (tribe) REFERENCES Tribe(tribe_id),
    FOREIGN KEY (battle) REFERENCES Battle(battle_id),
    PRIMARY KEY (tribe, battle)
);

CREATE TABLE Weapon (
    weapon_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    damage INT NOT NULL,
    type VARCHAR(50), -- Ej. Laser, Plasma, Missile, etc.
    owner BIGINT,
    FOREIGN KEY (owner) REFERENCES Player(player_id)
);

INSERT INTO Planet (name, environment_type) VALUES
('Zyphra', 'Desert'),
('Icaron', 'Ice'),
('Vulcana', 'Lava'),
('Aqualis', 'Water'),
('Grython', 'Jungle'),
('Phorax', 'Rocky'),
('Zephyros', 'Windy'),
('Klymia', 'Arid');

INSERT INTO Tribe (tribe_id, name, members) VALUES
(1,'Sandwalkers', 385),
(3,'Frostbound', 777),
(5,'Fireforge', 1900),
(7, 'Oceanhearts', 50),
(9, 'Jungle Stalkers', 76),
(11, 'Rock Guardians', 124),
(13, 'Sky Nomads', 462),
(15, 'Dune Seekers', 200);

INSERT INTO Ecosystem (tribe, planet) VALUES
(1, 1),
(1, 8),
(3, 2),
(3, 7),
(5, 3),
(7, 4),
(9, 5),
(11, 6),
(13, 7),
(15, 8);

INSERT INTO Player (player_id, name, points, myTribe) VALUES
(1, 'Lara Astron', 100, 1),
(3, 'Zorath Blaze', 200, 5),
(5, 'Eliara Wave', 300, 7),
(7, 'Jax Rother', 120, 5),
(9, 'Thyra Gale', 60, 13),
(11, 'Kael Stone', 20, 11),
(12, 'Nira Leaf', 180, 9),
(13, 'Bryn Ash', 560, 9),
(14, 'Lira Ocean', 220, 13),
(15, 'Tarek Storm', 133, 13),
(20, 'Cai Ember', 710, 5),
(21, 'Fera Rock', 815, 11);

INSERT INTO Battle (location, winner) VALUES
('Orbit of Zyphra', 3),
('Frostbound Tundra', 5),
('Lava Plains of Vulcana', 9),
('Aqualis Depths', 1),
('Jungle Ruins of Grython', 15);

INSERT INTO History_Fights (tribe, battle) VALUES
(1, 1),
(3, 1),
(5, 2),
(7, 2),
(9, 3),
(11, 3),
(3, 4),
(11, 4),
(15, 5),
(3, 5);

INSERT INTO Weapon (name, damage, type, owner) VALUES
('Plasma Rifle', 120, 'Plasma', 1),
('Laser Sword', 150, 'Laser', 7),
('Ice Spear', 100, 'Piercing', 3),
('Water Launcher', 90, 'Explosive', 14),
('Jungle Vines', 75, 'Constriction', 12),
('Rock Shredder', 130, 'Blunt', 11),
('Sky Blade', 140, 'Cutting', 9);
