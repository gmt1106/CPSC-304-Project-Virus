DROP TABLE Includes;
DROP TABLE Place;
DROP TABLE InfectedLivesIn;
DROP TABLE Country;
DROP TABLE RoutePerson_WentAt;
DROP TABLE Person;
DROP TABLE Timeframe;
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

CREATE TABLE Person (
	nationality CHAR(20),
	sinum INTEGER,
	name CHAR(20),
	PRIMARY KEY (nationality, sinum)
);

CREATE TABLE Timeframe (
	startTime TIMESTAMP,
	endTime TIMESTAMP,
	PRIMARY KEY (startTime, endTime)
);

CREATE TABLE RoutePerson_WentAt(
	startTime TIMESTAMP NOT NULL,
	endTime TIMESTAMP NOT NULL,
	routeID INTEGER,
	nationality CHAR(20),
	sinum INTEGER,
	PRIMARY KEY (routeID, nationality, sinum),
	FOREIGN KEY (startTime, endTime) REFERENCES Timeframe (startTime, endTime),
	FOREIGN KEY (routeID) REFERENCES Route (routeID),
	FOREIGN KEY (nationality, sinum) REFERENCES Person (nationality, sinum)
);

CREATE TABLE InfectedLivesIn (
    nationality char(10),
    sinum int,
    cname char(10) NOT NULL,
    PRIMARY KEY (nationality, sinum),
    FOREIGN KEY (cname) REFERENCES Country (name) ON DELETE CASCADE
);



INSERT INTO Country VALUES ('Canada');

INSERT INTO Place VALUES ('UBC Nest', 6133, 'University Blvd', 'V6T 1Z1', 'Canada');

INSERT INTO Place VALUES ('CN Tower', 290, 'Bremner Blvd', 'M5V 3L9', 'Canada');

INSERT INTO Place VALUES ('Vancouver Aquarium', 845, 'Avison Way', 'V6G 3E2', 'Canada');

INSERT INTO Place VALUES ('Pacific Center', 701, 'W Georgia St', 'V7Y 1G5', 'Canada');

INSERT INTO Place VALUES ('Calgary International Airport', 2000, 'Airport Rd NE', 'T2E 6W5', 'Canada');

INSERT INTO Route VALUES (5);

INSERT INTO Route VALUES (11);

INSERT INTO Route VALUES (23);

INSERT INTO Route VALUES (31);

INSERT INTO Route VALUES (47);

INSERT INTO Includes
VALUES (6133, 'University Blvd', 'V6T 1Z1', 'Canada', 5, TO_TIMESTAMP('2020-09-21 17:00:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Includes
VALUES (701, 'W Georgia St', 'V7Y 1G5', 'Canada', 5, TO_TIMESTAMP('2020-09-21 18:30:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Includes
Values (290, 'Bremner Blvd', 'M5V 3L9', 'Canada', 11, TO_TIMESTAMP('2020-10-21 18:52:21','YYYY/MM/DD HH24:MI:SS') );

INSERT INTO Includes
Values (2000, 'Airport Rd NE', 'T2E 6W5', 'Canada', 23, TO_TIMESTAMP('2020-05-17 02:54:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Includes
Values (845, 'Avison Way', 'V6G 3E2', 'Canada', 31, TO_TIMESTAMP('2020-07-15 05:11:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Includes
Values (2000, 'Airport Rd NE', 'T2E 6W5', 'Canada', 47, TO_TIMESTAMP('2020-05-17 19:54:30', 'YYYY/MM/DD HH24:MI:SS'));


INSERT INTO Person Values ('Canadian', 11111111, 'John Smith');

INSERT INTO Person Values ('American', 11111111, 'Daniel Jones');

INSERT INTO Person Values ('Portuguese', 22222222, 'Christiano Ronaldo');

INSERT INTO Person Values ('American', 33333333, 'Jay Park');

INSERT INTO Person Values ('Argentinian', 44444444, 'Lionel Messi');

INSERT INTO Person Values ('Chinese', 55555555, 'Alex Smith');

INSERT INTO Person Values ('Italian', 66666666, 'Charlies Martin');

INSERT INTO Person Values ('French', 77777777, 'Ian Chinook');

INSERT INTO Timeframe Values (TO_TIMESTAMP('2020-05-15 00:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-16 00:00:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Timeframe Values (TO_TIMESTAMP('2020-05-17 10:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-18 16:00:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Timeframe Values (TO_TIMESTAMP('2020-05-13 00:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-13 00:00:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Timeframe Values (TO_TIMESTAMP('2020-05-18 12:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-19 23:00:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO Timeframe Values (TO_TIMESTAMP('2020-05-18 06:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-18 14:00:00', 'YYYY/MM/DD HH24:MI:SS'));

INSERT
INTO RoutePerson_WentAt
Values (TO_TIMESTAMP('2020-05-15 00:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-16 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), 5, 'Canadian', 11111111);

INSERT
INTO RoutePerson_WentAt
Values (TO_TIMESTAMP('2020-05-17 10:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-18 16:00:00', 'YYYY/MM/DD HH24:MI:SS'), 11, 'American', 11111111);

INSERT
INTO RoutePerson_WentAt
Values (TO_TIMESTAMP('2020-05-13 00:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-13 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), 23, 'Portuguese', 22222222);

INSERT
INTO RoutePerson_WentAt
Values (TO_TIMESTAMP('2020-05-18 12:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-19 23:00:00', 'YYYY/MM/DD HH24:MI:SS'), 31, 'American', 33333333);

INSERT
INTO RoutePerson_WentAt
Values (TO_TIMESTAMP('2020-05-18 06:00:00', 'YYYY/MM/DD HH24:MI:SS'),
TO_TIMESTAMP('2020-05-18 14:00:00', 'YYYY/MM/DD HH24:MI:SS'), 47, 'Argentinian', 44444444);

INSERT INTO InfectedLivesIn VALUES ('Canadian', 11111111, 'Canada');

INSERT INTO InfectedLivesIn VALUES ('American', 11111111, 'Canada');

INSERT INTO InfectedLivesIn VALUES ('Italian', 66666666, 'Canada');

INSERT INTO InfectedLivesIn VALUES ('French', 77777777, 'Canada');

INSERT INTO InfectedLivesIn VALUES ('English', 88888888, 'Canada');
