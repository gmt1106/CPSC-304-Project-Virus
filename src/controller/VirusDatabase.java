package controller;

import database.DatabaseConnectionHandler;
import model.Country;
import model.Person;
import model.Place;
import model.Route;
import org.apache.ibatis.session.SqlSessionException;

import java.sql.SQLException;
import java.util.Date;

public class VirusDatabase {
    private DatabaseConnectionHandler dbHandler = null;

    public VirusDatabase() { dbHandler = new DatabaseConnectionHandler();}

    // connects to Oracle database with supplied username and password
    public boolean login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createDatabase() {

        try {
            dbHandler.createDatabase();
        }
        catch (SQLException se) {
            se.printStackTrace( );
            return false;
        }
        return true;
    }

    public Place[] getPlaceInfo() {

        return dbHandler.getPlaceInfo();
    }

    public void insertPlace(Place place) {

        dbHandler.insertPlace(place);
    }

    public Country[] getCountryInfo() {

        return dbHandler.getCountryInfo();
    }

    public Person[] getPersonInfo() {

        return dbHandler.getPersonInfo();

    }

    public Place[] getPlacesInfectedVisited(Date lowerBoundDate, Date upperBoundDate) {

        return dbHandler.getPlacesInfectedVisited(lowerBoundDate, upperBoundDate);

    }
}