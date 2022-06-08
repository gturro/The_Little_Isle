DROP DATABASE IF EXISTS LittleIslandData;
CREATE DATABASE LittleIslandData;

USE LittleIslandData;

DROP TABLE IF EXISTS userGameData;
CREATE TABLE userGameData (
	id INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(30),
    class VARCHAR (10),
    coinsCollected INT,
    keysCollected INT,
    timeGame DOUBLE (6,2),
    gameDate date
);


DROP USER IF EXISTS littleIslandAdminData;
CREATE USER littleIslandAdminData;
GRANT ALL PRIVILEGES ON LittleIslandData.userGameData TO littleIslandAdminData;

INSERT INTO  UserGameData VALUES (null, "Guillem", "Warrior", 200, 2, 60, "2022-04-12");
INSERT INTO  UserGameData VALUES (null, "Sandra", "Mage", 400, 1, 120.34, "2022-05-21");
INSERT INTO  UserGameData VALUES (null, "Monik", "Normal", 100, 4, 30, "2022-04-05");
INSERT INTO  UserGameData VALUES (null, "Jaume", "Normal", 333, 10, 160, "2022-03-22");
INSERT INTO  UserGameData VALUES (null, "Rosa", "Mage", 340, 2, 60, "2022-02-20");
INSERT INTO  UserGameData VALUES (null, "Quim", "Warrior", 600, 3, 80, "2022-04-18");
 
-- DELETE FROM userGameData;


DELIMITER //
CREATE TRIGGER insertDate BEFORE INSERT ON userGameData
FOR EACH ROW
	BEGIN
		SET NEW.gameDate = (now());
	END; //
DELIMITER ;

DELIMITER //

CREATE TRIGGER updateDate BEFORE UPDATE ON userGameData
FOR EACH ROW
	BEGIN
		SET NEW.gameDate = (now());
	END; //
DELIMITER ;

-- SELECT * FROM userGameData ORDER BY gameDate DESC;


 

