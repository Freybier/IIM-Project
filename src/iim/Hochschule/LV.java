/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yann Leymann
 */
public class LV {

    private final String name;
    private final String fullName;
    //private final String po;
    private long scheduledLV = 0;
    //private final List<String> dozentenNameList = new ArrayList<>();
    private final String dozentName;
    private final List<String> zugNameList = new ArrayList<>();
    private final List<Zug> zugList = new ArrayList<>();
    private String sws;
    private boolean geblockt;
    private String lva;

    public LV(String name, String fullName, String dozentName, String sws, boolean geblockt, String lva) {
        this.name = name;
        this.fullName = fullName;
        //this.po = po;
        this.dozentName = dozentName;
        this.sws = sws;
        this.geblockt = geblockt;
        this.lva = lva;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    /*
    public String getPO(){
        return po;
    }
     */
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
        //System.out.println("add sucsess!");
    }

    public long getScheduledLV() {
        return scheduledLV;
    }

    public void setScheduledLV(long scheduled) {
        this.scheduledLV = scheduled;
    }

    public String getSWS() {
        return this.sws;
    }

    public boolean getGeblockt() {
        return this.geblockt;
    }

    public String getLVA() {
        return this.lva;
    }

    @Override
    public String toString() {
        return "LV Name: " + name
                + "\nLV Full Name: " + fullName
                + //"\nLV PO: " + po +
                "\nDozenten: " + dozentName
                + "\nZug Name: " + zugNameList
                + "\nZug: " + zugList
                + "\nSWS: " + sws
                + "\ngeblockt: " + geblockt
                + "\nLVA: " + lva;
    }

}
