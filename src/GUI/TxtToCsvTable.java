/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Yann Leymann
 */
public class TxtToCsvTable extends JFrame{
    private JTable table;

    public TxtToCsvTable(List<String[]> data) {
        String[] columnNames = data.get(0); // Annahme: Die erste Zeile enthält die Spaltenüberschriften

        // Die Daten ohne die erste Zeile (Spaltenüberschriften)
        List<String[]> tableData = data.subList(1, data.size());

        table = new JTable(tableData.toArray(new Object[0][]), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setTitle("CSV Table Viewer");
        setVisible(true);
    }

    public static List<String[]> readCsvFromFile(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(";"); // Hier ',' als Trennzeichen, kann auch ';' sein
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
