package ui;

import controller.VirusDatabase;
import model.Country;
import model.Place;
import model.Route;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class DatabaseDialog extends JFrame {
    private static VirusDatabase virusDatabase = null;
    private SearchOutput searchOutput = null;

    public JPanel databaseDialog;
    //Place Table
    private JLabel place;
    private JTextField nameTextFieldFromPlace;
    private JTextField houseNumTextFieldFromPlace;
    private JTextField streetNameTextFieldFromPlace;
    private JTextField postalCodeTextFieldFromPlace;
    private JTextField cnameTextFieldFromPlace;
    private JButton showPlaceTable;
    private JButton submitPlaceData;
    private JSpinner lowerboundDateSpinnerForSearchPlace;
    private JSpinner upperboundDateSpinnerForSearchPlace;
    private JButton searchPlaceByDateButton;

    //Country Table
    private JLabel country;
    private JTextField nameTextFieldFromCountry;
    private JButton submitCountryData;
    private JButton showCountryTable;

    //Person Table
    private JButton showPersonTable;
    private JTextField nationalityField_Person;
    private JTextField visitedRouteField_Person;
    private JTextField startingAtField_Person;
    private JTextField endingAtField_Person;
    private JButton searchPerson;
    private JTextField sinumField_Person;
    private JButton updateRouteButton;
    private JTextField startedAfterField_Person;
    private JButton searchVirus;

    public DatabaseDialog(VirusDatabase virusDatabase)  {

        this.virusDatabase = virusDatabase;

        //set the Jspinner for searching place in give date
        //set up lowerboundDateSpinnerForSearchPlace
        SpinnerDateModel model = new SpinnerDateModel();
        lowerboundDateSpinnerForSearchPlace.setModel(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(lowerboundDateSpinnerForSearchPlace, "MM/dd/yyyy");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        lowerboundDateSpinnerForSearchPlace.setEditor(editor);
        //set up upperboundDateSpinnerForSearchPlace
        SpinnerDateModel model2 = new SpinnerDateModel();
        upperboundDateSpinnerForSearchPlace.setModel(model2);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(upperboundDateSpinnerForSearchPlace, "MM/dd/yyyy");
        DateFormatter formatter2 = (DateFormatter)editor2.getTextField().getFormatter();
        formatter2.setAllowsInvalid(false);
        formatter2.setOverwriteMode(true);
        upperboundDateSpinnerForSearchPlace.setEditor(editor2);

        //set up this Database Dialog
        setTitle("Virus Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);


        submitPlaceData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameTextFieldFromPlace.getText();
                int houseNum = Integer.parseInt(houseNumTextFieldFromPlace.getText());
                String streetName = streetNameTextFieldFromPlace.getText();
                String postalCode = postalCodeTextFieldFromPlace.getText();
                String cname = cnameTextFieldFromPlace.getText();

                Place place = new Place(name, houseNum, streetName, postalCode, cname);
                virusDatabase.insertPlace(place);

                // Check if the data is successfully added by showing the place table
                showPlaceTable.doClick();
            }
        });

        showPlaceTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Place[] result = virusDatabase.getPlaceInfo();

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });

        submitCountryData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        showCountryTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Country[] result = virusDatabase.getCountryInfo();

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });
        searchPlaceByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date lowerBoundDate = (Date)lowerboundDateSpinnerForSearchPlace.getModel().getValue();
                Date upperBoundDate = (Date)upperboundDateSpinnerForSearchPlace.getModel().getValue();

                System.out.println(upperBoundDate.toString());

                Place[] result = virusDatabase.getPlacesInfectedVisited(lowerBoundDate, upperBoundDate);

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });
    }

    public void createSearchOutputDialog(String[][] data, String[] columnNames) {

        searchOutput = new SearchOutput(virusDatabase, data, columnNames);
        searchOutput.add(searchOutput.searchOutput);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
