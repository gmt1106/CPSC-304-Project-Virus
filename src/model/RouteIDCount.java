package model;

public class RouteIDCount implements Model {
    private final int routeID;
    private final int count;

    public RouteIDCount(int routeID, int count) {
        this.routeID = routeID;
        this.count = count;
    }

    public int getRouteID() { return this.routeID; };
    public int getCount() { return this.count; };


    public String[] tupleToListOfString() {

        String[] tupleValues = { String.valueOf(this.routeID), String.valueOf(this.count)};
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "routeID", "number of people visited" };
        return columnNames;
    }
}
