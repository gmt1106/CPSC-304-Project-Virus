package model;

public class InfectedLivesIn implements Model{

    private final String nationality;
    private final int sinum;
    private final String cname;

    public InfectedLivesIn(String nationality, int sinum, String cname) {
        this.nationality = nationality;
        this.sinum = sinum;
        this.cname = cname;
    }

    public String getNationality() { return this.nationality; }
    public int getSinum() { return this.sinum; }
    public String getCname() { return this.cname; }

    @Override
    public String[] tupleToListOfString() {
        return new String[0];
    }

    @Override
    public String[] columnNameListOfString() {
        return new String[0];
    }
}

   /* CREATE TABLE InfectedLivesIn (
            nationality char(10),
            sinum int,
            cname char(10) NOT NULL,
            PRIMARY KEY (nationality, sinum),
            FOREIGN KEY (cname) REFERENCES Country (name) ON DELETE CASCADE
            );*/