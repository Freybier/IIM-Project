/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yann Leymann
 */
public class ReadCSVs {

    public static List<LV> createLVListFromCSV(String handtuchCSVFilePath) {
    List<LV> lvList = new ArrayList<>();
    Map<String, LV> lvMap = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath))) {
        String line;
        String[] header = br.readLine().split(";");
        int lvKuerzelIndex = getColumnIndex("LV-Kürzel", header);
        int poIndex = getColumnIndex("PO", header);
        int fullNameIndex = getColumnIndex("Bezeichnung", header);
        int dozentNameIndex = getColumnIndex("Dozent", header);

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            String lvKuerzel = parts[lvKuerzelIndex];
            String po = parts[poIndex];
            String fullName = parts[fullNameIndex];
            String dozentName = parts[dozentNameIndex];

            String lvKey = lvKuerzel + ";" + po;
            if (!lvMap.containsKey(lvKey)) {
                LV newLV = new LV(lvKuerzel, fullName, po);
                lvList.add(newLV);
                lvMap.put(lvKey, newLV);
            }

            LV lv = lvMap.get(lvKey);
            lv.addDozenten(dozentName);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return lvList;
}

private static int getColumnIndex(String columnName, String[] header) {
    for (int i = 0; i < header.length; i++) {
        if (header[i].equals(columnName)) {
            return i;
        }
    }
    return -1; // Column not found
}





    public void getLVforDozentfromCSV(List<Dozent> dozenten, List<LV> lvList) {
    String handtuchCSVFilePath = "src/iim/Handtuch/HandtuchOutput.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath))) {
        String line;
        String[] header = br.readLine().split(";");

        int dozentIndex = getColumnIndex("Dozent", header);
        int lvKuerzelIndex = getColumnIndex("LV-Kürzel", header);
        int poIndex = getColumnIndex("PO", header);

        while ((line = br.readLine()) != null) {
            String[] columns = line.split(";");

            String dozentFromCsv = columns[dozentIndex];
            String lvKuerzel = columns[lvKuerzelIndex];
            String poFromCsv = columns[poIndex];

            for (Dozent dozent : dozenten) {
                if (dozent.getName().equals(dozentFromCsv)) {
                    for (LV lv : lvList) {
                        if (lv.getName().equals(lvKuerzel) && lv.getPO().equals(poFromCsv)) {
                            dozent.addLV(lv); // Das LV-Objekt zur Dozenten-Instanz hinzufügen
                            break; // Wenn das LV-Objekt gefunden wurde, die Schleife verlassen
                        }
                    }
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static List<Zug> createZugListfromCSV(String handtuchCSVFilePath, List<LV> lvList) {
    List<Zug> zugList = new ArrayList<>();
    Map<String, Zug> zugMap = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath))) {
        String line;
        String[] header = br.readLine().split(";"); // Header-Zeile einlesen und Spaltennamen speichern
        int zugIndex = getColumnIndex("Zug", header);
        int lvKuerzelIndex = getColumnIndex("LV-Kürzel", header);
        int poIndex = getColumnIndex("PO", header);

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            String zugName = parts[zugIndex];
            String lvKuerzel = parts[lvKuerzelIndex];
            String zugPO = parts[poIndex];

            if (!zugMap.containsKey(zugName)) {
                Zug newZug = new Zug(zugName, zugPO);
                zugList.add(newZug);
                zugMap.put(zugName, newZug);
            }

            Zug zug = zugMap.get(zugName);

            // Suche nach übereinstimmendem LV-Objekt in der LV-Liste
            LV matchingLV = lvList.stream()
                    .filter(lv -> lv.getName().equals(lvKuerzel) && lv.getPO().equals(zugPO))
                    .findFirst()
                    .orElse(null);

            if (matchingLV != null) {
                zug.addLV(matchingLV); // Füge das LV-Objekt dem Zug hinzu
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return zugList;
}




}
