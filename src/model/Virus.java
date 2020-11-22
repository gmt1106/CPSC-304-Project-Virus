package model;

public class Virus implements  Model{
    private final int virusID;
    private final String name;
    private final String startDate;

    public Virus(int virusID, String name, String startDate) {
        this.virusID = virusID;
        this.name = name;
        this.startDate = startDate;
    }

    public int getvirusID() { return this.virusID; }
    public String getname() { return this.name; }
    public String getstartDate() { return this.startDate; }

    public String[] tupleToListOfString() {

        String[] tupleValues = { String.valueOf(this.virusID), this.name, this.startDate };
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "virusID", "name", "startDate" };
        return columnNames;
    }

}
