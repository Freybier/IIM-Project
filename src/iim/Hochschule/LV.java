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

    private String name;
    private final String nickName;
    private final String fullName;
    private long scheduledLV = 0;
    private final String dozentName;
    private final String secondDozentName;
    private List<String> zugNameList = new ArrayList<>();
    private String leadingZugName;
    private final List<Zug> zugList = new ArrayList<>();
    private String sws;
    private int swsBlocks = 0;
    private int swsBlocksTook = 0;
    private final boolean geblockt;
    private final String lva;
    private Zug leadingZug;
    private Dozent dozentLV;
    private Dozent secondDozentLV;
    private String roomNumber = "-";

    public LV(String name, String fullName, String dozentName, String sws, boolean geblockt, String lva, String secondDozentName, String nickName) {
        this.name = name;
        this.fullName = fullName;
        //this.po = po;
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
//    public String getHandtuchPointer(){
//        return handtuchPointer;
//    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
//    public boolean getIsScheduled(){
//        return isScheduled;
//    }
//    public void setIsScheduled(boolean isScheduled){
//        this.isScheduled = isScheduled;
//    }

    @Override
    public String toString() {
        return name;

    }

}
