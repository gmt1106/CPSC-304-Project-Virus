DROP TABLE Place;
DROP TABLE Country;


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

INSERT INTO Country VALUES ('Canada');

INSERT INTO Place VALUES ('UBC Nest', 6133, 'University Blvd', 'V6T 1Z1', 'Canada');

INSERT INTO Place VALUES ('CN Tower', 290, 'Bremner Blvd', 'M5V 3L9', 'Canada');

INSERT INTO Place VALUES ('Vancouver Aquarium', 845, 'Avison Way', 'V6G 3E2', 'Canada');

INSERT INTO Place VALUES ('Pacific Center', 701, 'W Georgia St', 'V7Y 1G5', 'Canada');

INSERT INTO Place VALUES ('Calgary International Airport', 2000, 'Airport Rd NE', 'T2E 6W5', 'Canada');
