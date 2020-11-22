package model;

public class MedicineKills implements Model{
    private final int medicineID;
    private final int numberOfKills;

    public MedicineKills(int medicineID, int numberOfKills) {
        this.medicineID = medicineID;
        this.numberOfKills = numberOfKills;
    }

    public int getmedicineID() { return this.medicineID; }
    public int getnumberOfKills() { return this.numberOfKills; }

    public String[] tupleToListOfString() {

        String[] tupleValues = { String.valueOf(this.medicineID), String.valueOf(this.numberOfKills) };
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "medicineID", "numberOfKills" };
        return columnNames;
    }

}
