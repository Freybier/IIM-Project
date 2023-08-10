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
            br.readLine(); // Überspringen der Header-Zeile

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String lvKuerzel = parts[1];
                String po = parts[2];
                String fullName = parts[3];
                String dozentName = parts[9];

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

    public void getLVforDozentfromCSV(List<Dozent> dozenten, List<LV> lvList) {
        String handtuchCSVFilePath = "src/iim/Handtuch/HandtuchOutput.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(";");

                String dozentFromCsv = columns[9]; // Index basiert auf Ihrer Spaltenreihenfolge
                String lvKuerzel = columns[1]; // Index basiert auf Ihrer Spaltenreihenfolge
                String poFromCsv = columns[2]; // Index basiert auf Ihrer Spaltenreihenfolge

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
        br.readLine(); // Überspringen der Header-Zeile

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            String zugName = parts[0];
            String lvKuerzel = parts[1];

            if (!zugMap.containsKey(zugName)) {
                Zug newZug = new Zug(zugName);
                zugList.add(newZug);
                zugMap.put(zugName, newZug);
            }

            Zug zug = zugMap.get(zugName);

            // Suche nach übereinstimmendem LV-Objekt in der LV-Liste
            LV matchingLV = lvList.stream()
                    .filter(lv -> lv.getName().equals(lvKuerzel))
                    .findFirst()
                    .orElse(null);

            if (matchingLV != null && matchingLV.getPO().equals(parts[2])) {
                zug.addLV(matchingLV); // Füge das LV-Objekt dem Zug hinzu
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return zugList;

    }

}
