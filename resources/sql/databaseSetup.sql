DROP TABLE Includes;
DROP TABLE Place;
DROP TABLE Country;
DROP TABLE Route;


CREATE TABLE Country (
    name char(10) PRIMARY KEY
);

CREATE TABLE Place (
	name CHAR(30),
    houseNum INTEGER,
	streetName CHAR(20),
	postalCode CHAR(10),
	cname CHAR(10),
	PRIMARY KEY (houseNum, streetName, postalCode, cname),
	FOREIGN KEY (cname) REFERENCES Country (name) ON DELETE CASCADE
);

CREATE TABLE Route (
	routeID INTEGER PRIMARY KEY
);

CREATE TABLE Includes (
	houseNum INTEGER,
	streetName CHAR(20),
	postalCode CHAR(10),
	cname CHAR(10),
	routeID INTEGER,
	time TIMESTAMP,
	PRIMARY KEY (houseNum, streetName, postalCode, cname, routeID),
	FOREIGN KEY (houseNum, streetName, postalCode, cname) REFERENCES Place (houseNum, streetName, postalCode, cname),
	FOREIGN KEY (routeID) REFERENCES Route (routeID)
);

INSERT INTO Country VALUES ('Canada');

INSERT INTO Place VALUES ('UBC Nest', 6133, 'University Blvd', 'V6T 1Z1', 'Canada');

INSERT INTO Place VALUES ('CN Tower', 290, 'Bremner Blvd', 'M5V 3L9', 'Canada');

INSERT INTO Place VALUES ('Vancouver Aquarium', 845, 'Avison Way', 'V6G 3E2', 'Canada');

INSERT INTO Place VALUES ('Pacific Center', 701, 'W Georgia St', 'V7Y 1G5', 'Canada');

INSERT INTO Place VALUES ('Calgary International Airport', 2000, 'Airport Rd NE', 'T2E 6W5', 'Canada');

INSERT INTO Route VALUES (5);

INSERT INTO Includes VALUES (6133, 'University Blvd', 'V6T 1Z1', 'Canada', 5, TO_TIMESTAMP('2020-09-21 17:00:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Includes VALUES (701, 'W Georgia St', 'V7Y 1G5', 'Canada', 5, TO_TIMESTAMP('2020-09-21 18:30:00', 'YYYY/MM/DD HH24:MI:SS'));
