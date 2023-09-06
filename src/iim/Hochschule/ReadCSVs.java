/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;

import iim.Handtuch.Leading;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Yann Leymann
 */
public class ReadCSVs implements Serializable {

    public static List<LV> createLVListFromCSV(String handtuchCSVFilePath) {
        List<LV> lvList = new ArrayList<>();
        Map<String, LV> lvMap = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath));
            String line;
            String[] header = br.readLine().split(";");
            int zugNameIndex = getColumnIndex("Zug", header);
            int lvKuerzelIndex = getColumnIndex("LV-Kürzel", header);
            int dozentNameIndex = getColumnIndex("Dozent", header);
            int fullNameIndex = getColumnIndex("Bezeichnung", header);
            int swsIndex = getColumnIndex("SWS", header);
            int geblocktIndex = getColumnIndex("geblockt", header);
            int lvaIndex = getColumnIndex("LVA", header);
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String zugName = parts[zugNameIndex];
                String lvKuerzel = parts[lvKuerzelIndex];
                String dozentName = parts[dozentNameIndex];
                String fullName = parts[fullNameIndex];
                String swsValue = parts[swsIndex];
                String geblocktValue = parts[geblocktIndex];
                boolean geblockt = geblocktValue.equalsIgnoreCase("ja");
                String lva = parts[lvaIndex];

                String lvKey = lvKuerzel + ";" + dozentName + ";" + zugName;
                if (!lvMap.containsKey(lvKey)) {
                    LV newLV = new LV(lvKuerzel, fullName, dozentName, swsValue, geblockt, lva);
                    
                    if(lvKuerzel.equals("INF") && dozentName.equals("VEN")){
                        i++;
                        System.out.println("HUUUUUIIII\t" + i);
                        
                    }
                    lvList.add(newLV);
                    lvMap.put(lvKey, newLV);
                }

                if (lvMap.containsKey(lvKey)) {
                    LV lv = lvMap.get(lvKey);
                    lv.addZugToNameList(zugName);
                }
            }

            br.close(); // Schließe den BufferedReader

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
    public List<Dozent> getDozentNotInPVZeiten(List<Dozent> dozenten, String handtuchCSVFilePath){
        try (BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath))) {
            String line;
            String[] header = br.readLine().split(";");

            int dozentIndex = getColumnIndex("Dozent", header);
            int lvKuerzelIndex = getColumnIndex("LV-Kürzel", header);
            

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(";");

                String dozentFromCsv = columns[dozentIndex];
                String lvKuerzel = columns[lvKuerzelIndex];
                int index = 0;

                for (Dozent dozent : dozenten) {

                    if (dozent.getName().equals(dozentFromCsv)) {

                        index ++;
                        //System.out.println("DOZENT: " + dozent.getName() + " LVs: " + dozent.getLVName());
                    }
                }
                if (index == 0){
                    Dozent dozent = new Dozent(dozentFromCsv);
                    dozent.setDoesHavePVZeiten(false);
                    dozenten.add(dozent);
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dozenten;
    }
    public void getLVforDozentfromCSV(List<Dozent> dozenten, List<LV> lvList, String handtuchCSVFilePath) {

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

                        for (LV lvFromList : lvList) {

                            if (lvFromList.getName().equals(lvKuerzel) && dozentFromCsv.equals(lvFromList.getDozentName())) {
                                if (dozent.getLV().isEmpty()) {

                                    dozent.addLV(lvFromList);

                                    //System.out.println("dozent: " + dozent.getName() + " ES IRD EINE LV GEADDED " + dozent.getLVName());
                                    //System.out.println("erste LV: " + lvFromList);
                                } else {

                                    for (LV lvFromDozent : dozent.getLV()) {

                                        //for(String lvNamenFromDozent : dozent.getLVName())
                                        if (!lvFromList.getName().equals(lvFromDozent.getName())) {

                                            //System.out.println("dozent: " + dozent.getName() + " ES IRD EINE WEITERE LV GEADDED " + dozent.getLVName());
                                            //System.out.println("Weitere LV: " + lvFromList );
                                            dozent.addLV(lvFromList);
                                            break;// Wenn das LV-Objekt gefunden wurde, die Schleife verlassen
                                        }

                                    }

                                }

                            }
                        }
                        //System.out.println("DOZENT: " + dozent.getName() + " LVs: " + dozent.getLVName());
                    }
                }
                for (Dozent dozent : dozenten) {
                    Set<String> uniqueLVNames = new HashSet<>();
                    Iterator<LV> iterator = dozent.getLV().iterator();

                    while (iterator.hasNext()) {
                        LV lv = iterator.next();
                        String lvName = lv.getName();

                        // Überprüfen, ob der Name bereits in der Menge enthalten ist
                        if (uniqueLVNames.contains(lvName)) {
                            // Duplikat gefunden, entferne es
                            iterator.remove();
                        } else {
                            // Einzigartiger Name, füge ihn zur Menge hinzu
                            uniqueLVNames.add(lvName);
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
            int DozentIndex = getColumnIndex("Dozent", header);

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String zugName = parts[zugIndex];
                String lvKuerzel = parts[lvKuerzelIndex];
                String zugPO = parts[poIndex];
                String zugDozent = parts[DozentIndex];

                if (!zugMap.containsKey(zugName)) {
                    Zug newZug = new Zug(zugName, zugPO);
                    zugList.add(newZug);
                    zugMap.put(zugName, newZug);
                }

                Zug zug = zugMap.get(zugName);

                // Suche nach übereinstimmendem LV-Objekt in der LV-Liste
                LV matchingLV = lvList.stream()
                        .filter(lv -> lv.getName().equals(lvKuerzel) && lv.getDozentName().equals(zugDozent))
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

    public void addZugToLV(List<LV> lvList, List<Zug> zugList) {
        for (LV lv : lvList) {
            List<String> lvZugName = lv.getZugNameList();
            for (Zug zug : zugList) {
                for (String zugName : lvZugName) {
                    //System.out.println("lvZugName: " + zugName + " zuggetName: " + zug.getName() );
                    // Überprüfe, ob LV-Kürzel, PO und Dozent übereinstimmen
                    if (zugName.equals(zug.getName())) {
                        // System.out.println(zug);
                        lv.addZug(zug); // Füge den Zug zum LV hinzu
                    }
                }
            }
        }
    }
    
    public void setLVLeading(List<LV> lvList, List<Leading> leadingList){
        for(LV lv: lvList){
            int count = 0;
            for(Zug zugLV : lv.getZugList()){
                count++;
            }
            if(count > 1){
                lv.setLeading(true);
            }
        }
        //return lvList;
        
    }

}
