package ui;

import controller.VirusDatabase;

import javax.swing.*;

public class SearchOutput extends JFrame{
    private static VirusDatabase virusDatabase = null;

    private JTable outputTable;
    public JPanel searchOutput;

    public SearchOutput(VirusDatabase virusDatabase, String[][] data, String[] columnNames) {

        this.virusDatabase = virusDatabase;

        // Initializing the JTable
        outputTable = new JTable(data, columnNames);
        outputTable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane scrollPane = new JScrollPane(outputTable);
        searchOutput.add(scrollPane);

    }
}
