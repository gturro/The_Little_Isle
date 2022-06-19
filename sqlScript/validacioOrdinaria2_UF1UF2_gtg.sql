drop database if exists myMusic ;
CREATE DATABASE myMusic;
USE myMusic;

CREATE TABLE Artistes(
	id_art INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(35)
);

CREATE TABLE Albums(
	id_alb INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(30),
	anyPublicacio YEAR,
    artista INT,
    CONSTRAINT fk_artista FOREIGN KEY (artista) REFERENCES Artistes(id_art) on delete cascade
);

CREATE TABLE Tracks(
	id_trk INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(30),
    duracio FLOAT,
    estil VARCHAR(30),
    album INT,
    CONSTRAINT fk_album FOREIGN KEY (album) REFERENCES Albums(id_alb) on delete cascade
);


INSERT INTO Artistes VALUES (null, "Jamiroquai");
INSERT INTO Artistes VALUES (null, "Rammstein");

-- albums de Jamiroquai
INSERT INTO Albums VALUES (null, "Automaton", 2017, 1),
						  (null, "Travelling without Moving", 1996, 1);
-- albums de Rammstein
INSERT INTO Albums VALUES (null, "Zeit", 2022, 2),
						  (null, "Rise, Rise", 2004, 2);

INSERT INTO Tracks VALUES (null, "Shake it on", 5.15, "Funk", 1);
INSERT INTO Tracks VALUES (null, "Virtual Insanity", 5.41, "Funk", 2);
INSERT INTO Tracks VALUES (null, "Zeit", 5.22, "Metal", 3);
INSERT INTO Tracks VALUES (null, "Amerika", 3.47, "Metal", 4);


DELETE FROM Artistes WHERE id_art = 1;
