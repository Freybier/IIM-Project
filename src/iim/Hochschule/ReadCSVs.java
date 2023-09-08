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
        //Map<String, LV> lvMap = new HashMap<>();
        Set<String> stringSet = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath));
            String line;
            String[] header = br.readLine().split(";");
            int zugNameIndex = getColumnIndex("Zug", header);
            int lvKuerzelIndex = getColumnIndex("LV-Kürzel", header);
            int dozentNameIndex = getColumnIndex("Dozent", header);

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String zugName = parts[zugNameIndex];

                stringSet.add(zugName);
                
            }

            br.close(); // Schließe den BufferedReader
            lvList = setLVsWithOneZug(stringSet, dozentNameIndex, lvKuerzelIndex, handtuchCSVFilePath, lvList);
            lvList = setLVsWithMoreZug(stringSet, dozentNameIndex, lvKuerzelIndex, handtuchCSVFilePath, lvList);
            //lvList = setLVsWithNoDozent(dozentNameIndex, lvKuerzelIndex, handtuchCSVFilePath, lvList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lvList;
    }

    private static List<LV> setLVsWithOneZug(Set<String> stringSet, int dozentNameIndex, int lvKuerzelIndex, String path, List<LV> lvList) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            String line;
            String[] header = br.readLine().split(";");
            int zugNameIndex = getColumnIndex("Zug", header);
            int fullNameIndex = getColumnIndex("Bezeichnung", header);
            int swsIndex = getColumnIndex("SWS", header);
            int geblocktIndex = getColumnIndex("geblockt", header);
            int lvaIndex = getColumnIndex("LVA", header);
            //int i = 0;

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

                if (!stringSet.contains(dozentName) && !dozentName.equals("-")) {
                    LV newLV = new LV(lvKuerzel, fullName, dozentName, swsValue, geblockt, lva);
                    newLV.addZugToNameList(zugName);
                    newLV.setLeadingLVName(zugName);
                    lvList.add(newLV);
                }

            }

            br.close(); // Schließe den BufferedReader

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lvList;
    }

    private static List<LV> setLVsWithMoreZug(Set<String> stringSet, int dozentNameIndex, int lvKuerzelIndex, String path, List<LV> lvList) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String[] header = br.readLine().split(";");
            int zugNameIndex = getColumnIndex("Zug", header);

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String zugName = parts[zugNameIndex];
                String lvKuerzel = parts[lvKuerzelIndex];
                String dozentName = parts[dozentNameIndex];

                if (stringSet.contains(dozentName)) {
                    //System.out.println();
                    //System.out.println("stringSet contains: " + dozentName);
                    for (LV lv : lvList) {
                        //System.out.println("LV:  " + lv.getName() + " lvKürzel: " + lvKuerzel + " lvLeadingLVName: " + lv.getLeadingLVName() + " zugName: " + zugName);
                        if (lv.getName().equals(lvKuerzel) && lv.getLeadingLVName().equals(dozentName)) {

                            lv.addZugToNameList(zugName);
                            //System.out.println("LV: " + lv.getName() + " Zug: " + zugName);
                            //System.out.println("ZugNameList: " + lv.getZugNameList());

                        }
                    }
                }
            }

            br.close(); // Schließe den BufferedReader

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lvList;
    }

    private static List<LV> setLVsWithNoDozent(int dozentNameIndex, int lvKuerzelIndex, String path, List<LV> lvList) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String[] header = br.readLine().split(";");
            int zugNameIndex = getColumnIndex("Zug", header);

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

                if (dozentName.equals("-")) {
                    LV newLV = new LV(lvKuerzel, fullName, dozentName, swsValue, geblockt, lva);
                    newLV.addZugToNameList(zugName);
                    lvList.add(newLV);
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

    public List<Dozent> getDozentNotInPVZeiten(List<Dozent> dozenten, String handtuchCSVFilePath) {
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

                        index++;
                        //System.out.println("DOZENT: " + dozent.getName() + " LVs: " + dozent.getLVName());
                    }
                }
                if (index == 0) {
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
        for (LV lv : lvList) {
            for (Dozent dozent : dozenten) {
                if (lv.getDozentName().equals(dozent.getName())) {
                    dozent.addLV(lv);
                    dozent.addLVName(lv.getName());
                }
            }
        }
        Map<String, Integer> nameCountMap = new HashMap<>();
        for (Dozent dozent : dozenten) {
            int count = 0;
            for (LV lvDozent : dozent.getLV()) {
                String name = lvDozent.getName();
                // Überprüfe, ob der Name bereits in der Map vorhanden ist
                if (nameCountMap.containsKey(name)) {
                    // Inkrementiere die Anzahl der Vorkommen
                    count = nameCountMap.get(name) + 1;
                    nameCountMap.put(name, count);
                    // Setze den neuen Namen mit der Anzahl der Vorkommen
                    lvDozent.setName(count + "." + name);
                } else {
                    // Füge den Namen zur Map hinzu, wenn er zum ersten Mal vorkommt
                    nameCountMap.put(name, 1);
                    lvDozent.setName(1 + "." + name);
                }
            }
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
               // String zugPO = parts[poIndex];
                String zugDozent = parts[DozentIndex];

                if (!zugMap.containsKey(zugName)) {
                    Zug newZug = new Zug(zugName);
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

    public void setLVLeading(List<LV> lvList, List<Leading> leadingList) {
        for (LV lv : lvList) {
            int count = 0;
            for (Zug zugLV : lv.getZugList()) {
                count++;
            }
            if (count > 1) {
                lv.setLeading(true);
            }
        }
        //return lvList;

    }

    public void setLVforZug(List<LV> lvList, List<Zug> zugList) {
        //System.out.println("OI setLVforZUg");
        for (LV lv : lvList) {
           // System.out.println(lv.getName() + " " + lv.getZugList().size());
            for (Zug zugLV : lv.getZugList()) {
                for (Zug zugZug : zugList) {
                   // System.out.println(zugLV.getName() + " " + zugZug.getName());
                    if (zugLV.getName().equals(zugZug.getName())) {
                        zugZug.addLV(lv);
                        //System.out.println("OI " + lv);
                    }
                }
            }
        }
    }

    public void cleanZugLVListDouble(List<Zug> zugList) {

        for (Zug lvZug : zugList) {

            Set<String> seenNames = new HashSet<>();
            Iterator<LV> iterator = lvZug.getLV().iterator();
            while (iterator.hasNext()) {
                LV obj = iterator.next();
                String name = obj.getName();
                if (seenNames.contains(name)) {
                    iterator.remove(); // Entferne das Objekt aus der Liste
                } else {
                    seenNames.add(name); // Füge den Namen zur Menge hinzu
                }
            }
        }
    }

    public void cleanZugLVListWrongLV(List<Zug> zugList) {
        List<LV> lvToRemove = new ArrayList<>();

        for (Zug zug : zugList) {
            List<LV> lvList = zug.getLV();
            List<LV> lvListCopy = new ArrayList<>(lvList); // Kopie der Liste, um ConcurrentModificationException zu vermeiden

            for (LV lvZug : lvListCopy) {
                int i = lvZug.getZugList().size();
                for (Zug zugLVZug : lvZug.getZugList()) {
                    if (!zugLVZug.getName().equals(zug.getName())) {
                        i--;
                    }
                    if (i <= 0) {
                        lvToRemove.add(lvZug);
                    }
                }
            }
        }

// Entferne die gesammelten LV-Objekte außerhalb der Schleife
        for (Zug zug : zugList) {
            zug.getLV().removeAll(lvToRemove);
        }

    }

}
