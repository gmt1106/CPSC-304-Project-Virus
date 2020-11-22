package model;

public class NationalityCount implements Model {
    private final String nationality;
    private final int count;

    public NationalityCount(String nationality, int count) {
        this.nationality = nationality;
        this.count = count;
    }

    public String getNationality() { return this.nationality; };
    public int getCount() { return this.count; };


    public String[] tupleToListOfString() {

        String[] tupleValues = { this.nationality, String.valueOf(this.count)};
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "nationality", "number of people infected" };
        return columnNames;
    }
}
