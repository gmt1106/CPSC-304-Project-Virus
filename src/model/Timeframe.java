package model;

public class Timeframe {
    private final String startTime;
    private final String endTime;

    public Timeframe (String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() { return this.startTime; }
    public String getEndTime() { return this.endTime; }

    public String[] tupleToListOfString() {

        String[] tupleValues = { this.startTime, this.endTime };
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "startTime", "endTime" };
        return columnNames;
    }
}

/*
CREATE TABLE Timeframe (
	startTime TIMESTAMP,
	endTime TIMESTAMP,
	PRIMARY KEY (startTime, endTime)
);

 */