package model;

public class Route {
    private final int routeID;

    public Route(int routeID) {
        this.routeID = routeID;
    }

    public int getRouteID() { return this.routeID; };

    public String[] tupleToListOfString() {

        String[] tupleValues = { String.valueOf(this.routeID) };
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "routeID" };
        return columnNames;
    }
}

/*
CREATE TABLE Route (
	routeID INTEGER PRIMARY KEY
);
 */