package model;

public class PostalCodeCityProvince implements Model {

    private final String postalCode;
    private final String city;
    private final String province;

    public PostalCodeCityProvince(String postalCode, String city, String province) {
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
    }

    public String getPostalCode() { return this.postalCode; }
    public String getCity() { return this.city; }
    public String getProvince() { return this.province; }

    public String[] tupleToListOfString() {

        String[] tupleValues = { this.postalCode, this.city, this.province };
        return tupleValues;
    }

    public String[] columnNameListOfString() {

        String[] columnNames = { "postalCode", "city", "province" };
        return columnNames;
    }
}

/*
CREATE TABLE PostalCodeCityProvince (
	postalCode CHAR(10) PRIMARY KEY,
	city CHAR(10),
	province CHAR(10),
	FOREIGN KEY (postalCode) REFERENCES Place
	    ON DELETE CASCADE
);
 */