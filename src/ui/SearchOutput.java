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
        outputTable.setBounds(60, 40, 1200, 400);

        //add the table to the frame
        this.add(new JScrollPane(outputTable));
        this.setTitle("Result Table");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }
}
