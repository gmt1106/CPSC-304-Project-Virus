package model;

public class Person {
    private final String nationality;
    private final int sinum;
    private final String name;

    public Person(String nationality, int sinum, String name) {
        this.nationality = nationality;
        this.sinum = sinum;
        this.name = name;
    }

    public String getNationality() { return this.nationality; }
    public int getSinum() { return this.sinum; }
    public String getName() { return this.name; }

    public String[] tupleToListOfString() {

        String[] tupleValues = { this.nationality, String.valueOf(this.sinum), this.name };
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "nationality", "sinum", "name" };
        return columnNames;
    }
}

/*
CREATE TABLE Person (
	nationality CHAR(10),
	sinum INTEGER,
	name CHAR(20),
	PRIMARY KEY (nationality, sinum)
);

 */