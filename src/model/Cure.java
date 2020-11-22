package model;

public class Cure implements Model {
    private final int medicineID;

    public Cure(int medicineID) {
        this.medicineID = medicineID;
    }

    public int getMedicineID() { return this.medicineID; }

    public String[] tupleToListOfString() {
        String[] tupleValues = { String.valueOf(this.medicineID) };
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "medicineID" };
        return columnNames;
    }
}
