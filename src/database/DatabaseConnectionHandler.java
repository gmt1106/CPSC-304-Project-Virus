package database;


import model.Country;
import model.Person;
import model.Place;
import model.Route;
import oracle.jdbc.driver.SQLUtil;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseConnectionHandler {

    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private static Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public static void createDatabase() throws SQLException {
        try {
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(connection);
            //Creating a reader object
            Reader reader = new BufferedReader(new FileReader("resources/sql/databaseSetup.sql"));
            //Running the script
            sr.runScript(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String userID, String password) {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(ORACLE_URL, userID, password);
            connection.setAutoCommit(false);

            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public void deletePlace(String name, int houseNum, String streetName, String postalCode, String cname) {

        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Place WHERE name = ? AND houseNum = ? AND streetName = ? AND postalCode = ? AND cname = ?");

            ps.setString(1, name);
            ps.setInt(2, houseNum);
            ps.setString(3, streetName);
            ps.setString(4, postalCode);
            ps.setString(5, cname);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + name + houseNum + streetName + postalCode + cname + " does not exist!");
            }
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertPlace (Place place) {
        try {

            //insert the routeID first in the Route table
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Country VALUES (?)");
            ps.setString(1, place.getCname());

            ps.executeUpdate();

            //Then insert the info into Place table
            ps = connection.prepareStatement("INSERT INTO Place VALUES (?,?,?,?,?)");

            ps.setString(1, place.getName());
            ps.setInt(2, place.getHouseNum());
            ps.setString(3, place.getStreetName());
            ps.setString(4, place.getPostalCode());
            ps.setString(5, place.getCname());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Place[] getPlaceInfo() {
        ArrayList<Place> result = new ArrayList<Place>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Place");

            while(rs.next()) {
                Place place = new Place(rs.getString("name"),
                                        rs.getInt("houseNum"),
                                        rs.getString("streetName"),
                                        rs.getString("postalCode"),
                                        rs.getString("cname"));
                result.add(place);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Place[result.size()]);
    }

    public Country[] getCountryInfo() {

        ArrayList<Country> result = new ArrayList<Country>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Country");

            while(rs.next()) {
                Country country = new Country(rs.getString("name"));
                result.add(country);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Country[result.size()]);
    }


    public Person[] getPersonInfo() {

        ArrayList<Person> result = new ArrayList<Person>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Person");

            while(rs.next()) {
                Person person = new Person(rs.getString("nationality"),
                        rs.getInt("sinum"), rs.getString("name"));
                result.add(person);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Person[result.size()]);
    }

    public Place[] getPlacesInfectedVisited (Date lowerBoundDate, Date upperBoundDate) {

        //TODO: need to change the date type to somethign compareable in sql and add the where cluase
        //TODO: Might have to change the layout type in the gui

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lowerBoundDateString = formatter.format(lowerBoundDate);
        String upperBoundDateString = formatter.format(upperBoundDate);
        System.out.println(lowerBoundDateString);

        ArrayList<Place> result = new ArrayList<Place>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Pl.name, Pl.houseNum, Pl.streetName, Pl.postalCode, Pl.cname FROM Place Pl, Includes I,  RoutePerson_WentAt W,  InfectedLivesIn L WHERE I.houseNum = Pl.houseNum AND I.streetName = Pl.streetName AND I.postalCode = Pl.postalCode AND I.routeID = W.routeID AND W.nationality = L.nationality AND W.sinum = L.sinum AND I.time > TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS') AND I.time < TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS')");

            ps.setString(1, lowerBoundDateString);
            ps.setString(2, upperBoundDateString);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Place place = new Place(rs.getString("name"),
                        rs.getInt("houseNum"),
                        rs.getString("streetName"),
                        rs.getString("postalCode"),
                        rs.getString("cname"));
                result.add(place);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Place[result.size()]);
    }

    public Person[] searchPersonInfo (String nationality, int routeNum, Date startingAt, Date endingAt) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startingAtDateString = formatter.format(startingAt);
        String endingAtDateString = formatter.format(endingAt);
        ArrayList<Person> result = new ArrayList<Person>();

        System.out.println(nationality);
        System.out.println(routeNum);
        System.out.println(startingAtDateString);
        System.out.println(endingAtDateString);


        try {
            PreparedStatement ps = connection.prepareStatement("SELECT P.nationality, P.sinum, P.name FROM Person P, RoutePerson_WentAt RW WHERE P.nationality = RW.nationality AND P.sinum = RW.sinum AND P.nationality = 'Canadian' AND RW.routeID = ? AND RW.startTime >= TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS') AND RW.endTime <= TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS')");

//            ps.setString(1, nationality);
            ps.setInt(1, routeNum);
            ps.setString(2, startingAtDateString);
            ps.setString(3, endingAtDateString);


            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Person person = new Person(rs.getString("nationality"),
                        rs.getInt("sinum"), rs.getString("name"));
                result.add(person);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Person[result.size()]);
    }

    public void updateRoute (String nationality, int routeNum, int sinum, Date startingAt, Date endingAt) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startingAtDateString = formatter.format(startingAt);
        String endingAtDateString = formatter.format(endingAt);

        try {
            PreparedStatement ps1 = connection.prepareStatement("INSERT INTO Place VALUES (TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS'), TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS'))");

            ps1.setString(1, startingAtDateString);
            ps1.setString(2, endingAtDateString);

            ps1.executeUpdate();
            connection.commit();

            ps1.close();

            PreparedStatement ps2 = connection.prepareStatement("UPDATE RoutePerson_WentAt " +
                    "SET startTime =  TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS'), endTime = TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS') " +
                    "WHERE routeID = ? AND nationality = ? AND sinum = ?");

            ps2.setString(1, startingAtDateString);
            ps2.setString(2, endingAtDateString);
            ps2.setInt(3, routeNum);
            ps2.setString(4, nationality);
            ps2.setInt(5, sinum);

            ps2.executeUpdate();
            connection.commit();

            ps2.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

//    public Virus[] searchVirus (Date startedAfter) {
//        return ;
//    }

}