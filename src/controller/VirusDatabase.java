package controller;

import database.DatabaseConnectionHandler;
import javafx.util.Pair;
import model.*;
import org.apache.ibatis.session.SqlSessionException;

//import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public Person[] searchPersonInfo(String nationality, int routeNum, Date startingAt, Date endingAt) {

        return dbHandler.searchPersonInfo(nationality, routeNum, startingAt, endingAt);

    }

    public Place[] getPlacesInfectedVisited(Date lowerBoundDate, Date upperBoundDate) {

        return dbHandler.getPlacesInfectedVisited(lowerBoundDate, upperBoundDate);

    }

    public Person[] searchNotInfectedButMightInfected() {

        return dbHandler.searchNotInfectedButMightInfected();

    }

    public void updateRoute(String nationality, int sinum, int routeNum, Date startingAt, Date endingAt) {

        dbHandler.updateRoute(nationality, sinum, routeNum, startingAt, endingAt);

    }

    public RoutePerson_WentAt[] getRoutePeopleInfo() {

        return dbHandler.getRoutePeopleInfo();

    }

    public MedicineKills[] searchVirus(Date startedAfter) {

        return dbHandler.searchVirus(startedAfter);

    }

    public Kills[] getCuresKillingVirus() {

        return dbHandler.getCuresKillingVirus();

    }

    public Route[] showRoute() {

        return dbHandler.showRoute();

    }

    public void deleteRoute(int routeID) {

        dbHandler.deleteRoute(routeID);

    }

    public RouteIDCount[] getRouteIDCount() {

        return dbHandler.getRouteIDCount();

    }

    public NationalityCount[] getNationalityCount() {

        return dbHandler.getNationalityCount();

    }

    public CnameMedicineID[] searchCountryHasCure(String nationality, int sinum) {

        return dbHandler.searchCountryHasCure(nationality, sinum);

    }
}