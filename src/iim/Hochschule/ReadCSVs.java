/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;

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

    private static final List<Leading> leading = new ArrayList<>();

    public static List<LV> createLVListFromCSV(String handtuchCSVFilePath) {
        List<LV> lvList = new ArrayList<>();

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

                //create a Set with each ZugName. It will be used to create a pseudo List<Zug>
                //Each LV will have a List<String> with all Zugs visiting it
                //So hen the Zug objects will be created it will be easyer to conect them
                stringSet.add(zugName);

            }

            br.close();
            lvList = setLVsWithOneZug(stringSet, dozentNameIndex, lvKuerzelIndex, handtuchCSVFilePath, lvList);
            lvList = setAllZugNamesForLV(stringSet, dozentNameIndex, lvKuerzelIndex, handtuchCSVFilePath, lvList);
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
            int secondDozentIndex = getColumnIndex("2. Dozent", header);
            //int i = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String zugName = parts[zugNameIndex];
                String lvKuerzel = parts[lvKuerzelIndex];
                String dozentName = parts[dozentNameIndex];
                String fullName = parts[fullNameIndex];
                String swsValue = parts[swsIndex];
                String geblocktValue = parts[geblocktIndex];
                boolean geblockt = geblocktValue.equalsIgnoreCase("true");
                String lva = parts[lvaIndex];
                String secondDozentName = parts[secondDozentIndex];

                if (!stringSet.contains(dozentName)) {
                    //Because we have LVs for more than one Zug(example: (LV)1.GLI: (Zug)[ID1, II1, II2, MT1])
                    //threre is a Leading Zug for each LV. Each Zug wich is not Leading has in the Dozent column,
                    //not the Dozent name but the Leading Zug name, like a pointer.
                    //If the Cell in the Column Dozent contains a leading Zug name this LV is not created.  
                    //Ensuring that there are not multiple LVs when, in reality, there is only one.
                    //WRONG: (LV)1.GLI: (Zug)[ID1], (LV)2.GLI: (Zug)[II1], (LV)3.GLI: (Zug)[II2], (LV)4.GLI: (Zug)[MT2]
                    //RIGHT: (LV)1.GLI: (Zug)[ID1, II1, II2, MT1])
                    //First we add to each LV the Leading Zug name.
                    //The the other Zug names,if the LV has mutilple Zugs, are added in the method: setLVsWithMoreZug
                    LV newLV = new LV(lvKuerzel, fullName, dozentName, swsValue, geblockt, lva, secondDozentName, lvKuerzel);
                    newLV.addZugToNameList(zugName);
                    newLV.setLeadingZugName(zugName);
                    lvList.add(newLV);

                }

            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lvList;
    }

    private static List<LV> setAllZugNamesForLV(Set<String> stringSet, int dozentNameIndex, int lvKuerzelIndex, String path, List<LV> lvList) {
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

                //If an LV is visited by multiple Zugs, the value in the 'Dozent' column does not
                //represent the name of a Dozent but rather serves as a reference to the leading Zug.
                //So if the Value is a Zug we search for the combination of the referenced Zug
                //and the LV matching the LV in the Row of the 'pointer'. 
                //If found we add the Zug with the Pointer to the ZugNameList of the referenced LV.
                if (stringSet.contains(dozentName)) {
                    for (LV lv : lvList) {
                        if (lv.getName().equals(lvKuerzel) && lv.getLeadingZugName().equals(dozentName)) {
                            lv.addZugToNameList(zugName);
                        }
                    }
                }
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lvList;
    }

    public static List<Zug> createZugListfromCSV(String handtuchCSVFilePath, List<LV> lvList) {
        //This method creates Zug Objects and adds them to the ZugList
        //Furthermore the corresp onding LV Objects wich are viseted by the chosen Zug are added to the Zug Object.

        List<Zug> zugList = new ArrayList<>();
        Set<String> zugSet = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath))) {
            String line;
            String[] header = br.readLine().split(";");
            int zugIndex = getColumnIndex("Zug", header);
            int lvKuerzelIndex = getColumnIndex("LV-Kürzel", header);
            int DozentIndex = getColumnIndex("Dozent", header);

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String zugName = parts[zugIndex];
                String lvKuerzel = parts[lvKuerzelIndex];
                String zugDozent = parts[DozentIndex];

                //We check if the Zug does already exist, if not it is created.
                if (!zugSet.contains(zugName)) {
                    Zug newZug = new Zug(zugName);
                    zugList.add(newZug);
                    zugSet.add(zugName);

                    //Now netherless the Zug already existed, we now get the Object with the corresponding name.      
                    //We check the present LVname and DozentName of the row and add the corresponding Leading Object     
                    LV matchingLV = lvList.stream()
                            .filter(lv -> lv.getName().equals(lvKuerzel) && lv.getDozentName().equals(zugDozent))
                            .findFirst()
                            .orElse(null);

                    //If found, the Leading Object is added
                    if (matchingLV != null) {
                        Leading leadingObj = new Leading(lvKuerzel, zugDozent, zugName);
                        leadingObj.setLVObject(matchingLV);
                        leading.add(leadingObj);
                        //matchingLV.setLeadingZug(newZug);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zugList;
    }

    public List<Dozent> addDozentNotInPVZeiten(List<Dozent> dozenten, String handtuchCSVFilePath, List<LV> lvList) {

        //The names of the Object Dozent must be unique. So the names of all already existing elements
        //are copied in a Set.
        Set<String> existingDozenten = new HashSet<>();
        for (Dozent dozent : dozenten) {
            existingDozenten.add(dozent.getName());
        }

        //Now we compare the names from the CSV with the names containing the Set an if the name is missing
        //a new Dozent Object is created and added to the Dozent List. The Dozent created this way does not have
        //the long available and the long doesNotWant because the Dozent was not created with the help of 
        //the pvZeiten file and no information regarding the "working hours" is provided.
        for (LV lv : lvList) {
            if (!existingDozenten.contains(lv.getDozentName())) {
                Dozent dozent = new Dozent(lv.getDozentName());
                dozent.setDoesHavePVZeiten(false);
                dozenten.add(dozent);
                existingDozenten.add(lv.getDozentName());
            }
        }
        return dozenten;
    }

    public void addLVforDozent(List<Dozent> dozenten, List<LV> lvList) {
        for (LV lv : lvList) {
            for (Dozent dozent : dozenten) {
                if (lv.getDozentName().equals(dozent.getName())) {
                    dozent.addLV(lv);
                    dozent.addLVName(lv.getName());
                }
            }
        }
    }

    public void addLeadingZugAndChangeLVName(List<Dozent> dozenten, List<LV> lvList, List<Zug> zugList) {
        for (Dozent dozent : dozenten) {
            for (LV lvDozent : dozent.getLV()) {
                String name = lvDozent.getName();
                lvDozent.setName(name + "__" + lvDozent.getLeadingZugName());
                for (Zug zug : zugList) {
                    if (lvDozent.getLeadingZugName().equals(zug.getName())) {
                        lvDozent.setLeadingZug(zug);
                    }

                }
            }
        }
    }

    
    

    public void addZugToLV(List<LV> lvList, List<Zug> zugList) {
        //Now that we have the Zug Objects we can add them to the corresponding LV Objects
        //With the help of the ZugNameList, wich we create while creating the LV Objects
        //in th methods setLVsWithOneZug() and setAllZugNamesForLV()
        for (LV lv : lvList) {
            List<String> lvZugName = lv.getZugNameList();
            for (Zug zug : zugList) {
                for (String zugName : lvZugName) {
                    if (zugName.equals(zug.getName())) {
                        lv.addZug(zug);
                    }
                }
            }
        }
    }

    public void setLVforZug(List<LV> lvList, List<Zug> zugList) {

        for (LV lv : lvList) {
            for (String zugName : lv.getZugNameList()) {
                for (Zug zugZug : zugList) {
                    if (zugName.equals(zugZug.getName())) {
                        zugZug.addLV(lv);
                    }
                }
            }
        }
    }

    private static int getColumnIndex(String columnName, String[] header) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

    public List<Leading> getLeadingList() {
        return leading;
    }

    public void addDozentToLV(List<Dozent> dozentList, List<LV> lvList) {
        for (LV lv : lvList) {
            for (Dozent dozent : dozentList) {
                if (lv.getDozentName().equals(dozent.getName())) {
                    lv.setDozentLV(dozent);
                    break;
                }
            }
            for (Dozent dozent : dozentList) {
                if (lv.getSecondDozentName().equals(dozent.getName())) {
                    lv.setSecondDozentLV(dozent);
                    break;
                }
            }
        }
    }

}
