package model;

public class Place implements Model {

    private final String name;
    private final int houseNum;
    private final String streetName;
    private final String postalCode;
    private final String cname;

    public Place(String name, int houseNum, String streetName, String postalCode, String cname) {
        this.name = name;
        this.houseNum = houseNum;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.cname = cname;
    }

    public String getName() { return name; }
    public int getHouseNum() { return houseNum; }
    public String getStreetName() { return streetName; }
    public String getPostalCode() { return postalCode; }
    public String getCname() { return cname; }

    public String[] tupleToListOfString() {

        String[] tupleValues = { name, String.valueOf(houseNum), streetName, postalCode, cname};
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "name", "houseNum", "streetName", "postalCode", "cname" };
        return columnNames;
    }
}



/*
CREATE TABLE Place (
    name CHAR(20),
    houseNum INTEGER,
    streetName CHAR(20),
    postalCode CHAR(10),
    cname CHAR(20),
    PRIMARY KEY (houseNum, streetName, postalCode, cname)
    FOREIGN KEY (cname) REFERENCES Country (name),
    ON DELETE CASCADE
    );
 */
