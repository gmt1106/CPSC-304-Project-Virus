package model;

public class RoutePerson_WentAt {
    private final String startTime;
    private final String endTime;
    private final int routeID;
    private final String nationality;
    private final int sinum;

    public RoutePerson_WentAt(String startTime, String endTime, int routeID, String nationality, int sinum) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.routeID= routeID;
        this.nationality = nationality;
        this.sinum = sinum;
    }

    public String getStartTime() { return this.startTime; };
    public String getEndTime() { return this.endTime; };
    public int getRouteID() { return this.routeID; };
    public String getNationality() { return this.nationality; };


    public String[] tupleToListOfString() {

        String[] tupleValues = { this.startTime, this.endTime, String.valueOf(this.routeID), this.nationality, String.valueOf(this.sinum)};
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "startTime", "endTime", "routeID", "nationality", "sinum" };
        return columnNames;
    }
}

/*
CREATE TABLE RoutePerson_WentAt(
	startTime TIMESTAMP NOT NULL,
	endTime TIMESTAMP NOT NULL,
	routeID INTEGER,
	nationality CHAR(10),
	sinum INTEGER,
	PRIMARY KEY (routeID, nationality, sinum)
	FOREIGN KEY (startTime, endTime) REFERENCES Timeframe
	FOREIGN KEY (routeID) REFERENCES Route
	FOREIGN KEY (nationality, sinum) REFERENCES Person
);
 */