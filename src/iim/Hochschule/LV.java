/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yann Leymann
 */
public class LV implements Serializable {

    private String name;//Name with modifications
    private final String nickName;//Original name from the CSV (3-4 letters)
    private final String fullName;//Name in full length
    private long scheduledLV = 0;//The number to see when the LV is scheduled
    private final String dozentName;//The name of the dozent for the LV
    private final String secondDozentName;//if there is a 2. Dozent for the LV (not in usage)
    private List<String> zugNameList = new ArrayList<>();//List of Zug name wich needs to visit this LV
    private String leadingZugName;//String of the leading Zug
    private final List<Zug> zugList = new ArrayList<>();//List of Zug objects wich needs to visit this LV
    private String sws;//Semesterwochenstunden(sws) as a String
    private int swsBlocks = 0;//sws in blocks 1sws => 1swsBlocks, 2sws => 1swsBlocks, 3sws => 2swsBlocks, 4sws => 2swsBlocks ... 
    private int swsBlocksTook = 0;//If a LV hast multiple swsBlokcs we need to know how many Blocks are already placed over the week
    private final boolean geblockt;//checks if a LV is blockt
    private final String lva;//A String wich indicates what kind of LV the LV is
    private Zug leadingZug;//Zug object of the Leading Zug
    private Dozent dozentLV;// Dozent object 
    private Dozent secondDozentLV;//Second Dozent object (not in usage)
    private String roomNumber = "-"; //Simple String for the Roomnumber 

    public LV(String name, String fullName, String dozentName, String sws, boolean geblockt, String lva, String secondDozentName, String nickName) {
        this.name = name;
        this.fullName = fullName;
        
        this.dozentName = dozentName;
        this.sws = sws;
        try {
            Double check = Double.valueOf(sws);
            if (check % 0.5 == 0) {
                check = (check + 0.5);
            }
            if (check % 2 != 0) {
                check++;
            }
            this.swsBlocks = (int) (check / 2);

        } catch (NumberFormatException e) {

        }
        this.geblockt = geblockt;
        this.lva = lva;
        this.secondDozentName = secondDozentName;
        this.nickName = nickName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDozentName() {
        return dozentName;
    }

    public List<String> getZugNameList() {
        return zugNameList;
    }

    public void addZugToNameList(String zugName) {
        this.zugNameList.add(zugName);
    }

    public List<Zug> getZugList() {
        return zugList;
    }

    public void addZug(Zug zug) {
        zugList.add(zug);
    }

    public long getScheduledLV() {
        return scheduledLV;
    }

    public void setScheduledLV(long scheduled) {
        scheduledLV = scheduled;

    }

    public String getSWS() {
        return this.sws;
    }

    public int getSWSBlocks() {
        return this.swsBlocks;
    }

    public int getSWSBlocksTook() {
        return this.swsBlocksTook;
    }

    public void setSWSBlocksTookZero() {
        this.swsBlocksTook = 0;
    }

    public void substractOneSWSBlocksTook() {
        this.swsBlocksTook--;
    }

    public void addOneSWSBlocksTook() {
        this.swsBlocksTook++;
    }

    public boolean getGeblockt() {
        return this.geblockt;
    }

    public String getLVA() {
        return this.lva;
    }

    public void setLeadingZugName(String leadingZugName) {
        this.leadingZugName = leadingZugName;
    }

    public String getLeadingZugName() {
        return leadingZugName;
    }

    public void setLeadingZug(Zug leadingZug) {
        this.leadingZug = leadingZug;
    }

    public Zug getLeadingZug() {
        return leadingZug;
    }

    public void setDozentLV(Dozent dozentLV) {
        this.dozentLV = dozentLV;
    }

    public Dozent getDozentLV() {
        return dozentLV;
    }

    public void setSecondDozentLV(Dozent secondDozentLV) {
        this.secondDozentLV = secondDozentLV;
    }

    public Dozent getSecondDozentLV() {
        return secondDozentLV;
    }

    public String getSecondDozentName() {
        return secondDozentName;
    }

    public String getNickName() {
        return nickName;
    }


    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }


    @Override
    public String toString() {
        return name;

    }

}
