package ui;

import controller.VirusDatabase;
import model.Place;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class DatabaseDialog extends JFrame {
    private static VirusDatabase virusDatabase = null;
    private SearchOutput searchOutput = null;

    private JTextField nameTextField;
    private JButton submitData;
    public JPanel databaseDialog;
    private JLabel place;
    private JTextField houseNumTextField;
    private JTextField streetNameTextField;
    private JTextField postalCodeTextField;
    private JTextField cnameTextField;
    private JButton showTable;

    public DatabaseDialog(VirusDatabase virusDatabase)  {

        this.virusDatabase = virusDatabase;

        submitData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //createSearchOutputDialog();


            }
        });

        showTable.addActionListener(new ActionListener() {
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
    }

    public void createSearchOutputDialog(String[][] data, String[] columnNames) {

        searchOutput = new SearchOutput(virusDatabase, data, columnNames);
        searchOutput.add(searchOutput.searchOutput);

    }
}
