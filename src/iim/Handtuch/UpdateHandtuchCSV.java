/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Handtuch;

import iim.Hochschule.Dozent;
import iim.Hochschule.LV;
import iim.Hochschule.Zug;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Yann Leymann
 */
public class UpdateHandtuchCSV {

    public void updateHandtuchCSV(String path, List<Dozent> dozentenList, List<Zug> zugList, List<LV> lvList) {

        List<String> dozentenNamen = new ArrayList<>();
        List<String> zugNamen = new ArrayList<>();

        for (Zug zug : zugList) {
            zugNamen.add(zug.getName());
        }
        for (Dozent dozent : dozentenList) {
            dozentenNamen.add(dozent.getName());
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String[]> rows = new ArrayList<>();
            String header = reader.readLine(); // Lese die Kopfzeile, um Spaltennamen zu erhalten
            String[] headerColumns = header.split(";");
            int dozentColumnIndex = Arrays.asList(headerColumns).indexOf("Dozent"); // Finde den Index der "Dozent"-Spalte
            int lvKuerzelColumnIndex = Arrays.asList(headerColumns).indexOf("LV-Kürzel"); // Finde den Index der "LV-Kürzel"-Spalte

            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                rows.add(row);
            }
            reader.close();

            for (String[] row : rows) {
                String dozentInSpalte = row[dozentColumnIndex];
                for (Zug zug : zugList) {
                    if (dozentInSpalte.equals(zug.getName())) {

                        String lvKuerzel = row[lvKuerzelColumnIndex];

                        for (LV lvInZug : zug.getLV()) {
                            if (lvKuerzel.equals(lvInZug.getName())) {
                                row[dozentColumnIndex] = lvInZug.getDozentName(); // Aktualisiere den Dozenten in der Zeile
                            }

                        }
                    }
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("src/iim/Handtuch/HandtuchOutputUpdate.csv"));
            writer.write(header); // Schreibe die Kopfzeile zurück in die Datei
            writer.newLine();
            for (String[] row : rows) {
                writer.write(String.join(";", row));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addParallel(String updateCSVPath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(updateCSVPath));
            List<String[]> rows = new ArrayList<>();
            String header = reader.readLine(); // Lese die Kopfzeile
            String[] headerColumns = header.split(";");
            int lvKuerzelColumnIndex = Arrays.asList(headerColumns).indexOf("LV-Kürzel");
            int poColumnIndex = Arrays.asList(headerColumns).indexOf("PO");
            int dozentColumnIndex = Arrays.asList(headerColumns).indexOf("Dozent");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                rows.add(row);
            }
            reader.close();

            // Schreibe die "Parallel"-Spalte in die Datei
            BufferedWriter writer = new BufferedWriter(new FileWriter(updateCSVPath));
            writer.write(header + ";Parallel");
            writer.newLine();
            for (int i = 0; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String lvKuerzel = row[lvKuerzelColumnIndex];
                String po = row[poColumnIndex];
                String dozent = row[dozentColumnIndex];

                List<String> parallelColumn = new ArrayList<>();

                // Suche nach Übereinstimmungen und füge Werte zur Parallelliste hinzu
                for (String[] otherRow : rows) {
                    if (!Arrays.equals(otherRow, row)) {
                        if (lvKuerzel.equals(otherRow[lvKuerzelColumnIndex])
                                
                                && dozent.equals(otherRow[dozentColumnIndex])) {
                            parallelColumn.add(otherRow[0]); // Annahme: Index des "Zug"-Felds ist 0
                        }
                    }
                }

                String parallelValue = String.join(",", parallelColumn); // Erstelle einen String aus der Liste
                writer.write(String.join(";", row) + ";" + parallelValue);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
