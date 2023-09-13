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

    private  String name;
    private final String fullName;
    private long scheduledLV = 0;
    private final String dozentName;
    private List<String> zugNameList = new ArrayList<>();
    private String leadingZugName;
    private final List<Zug> zugList = new ArrayList<>();
    private String sws;
    private int swsBlocks = 0;
    private int swsBlocksTook = 0;
    private final boolean geblockt;
    private boolean leading;
    private final String lva;
    

    public LV(String name, String fullName, String dozentName, String sws, boolean geblockt, String lva) {
        this.name = name;
        this.fullName = fullName;
        //this.po = po;
        this.dozentName = dozentName;
        this.sws = sws;
        try {
            Double check = Double.valueOf(sws);
            if(check%0.5 == 0){
                check = (check + 0.5);
            }
            if(check%2 != 0){
                check++;
            } 
        this.swsBlocks = (int)(check/2);
        
        } catch (NumberFormatException e) {
            
        }
        this.geblockt = geblockt;
        this.lva = lva;
    }

    public void setName(String  name){
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
    
    public void setLeading(boolean leading){
        this.leading = leading;
    }
    
    public boolean getLeading(){
        return leading;
    }
    public void setLeadingZugName(String leadingZugName){
        this.leadingZugName = leadingZugName;
    }
    public String getLeadingZugName(){
        return leadingZugName;
    }

    @Override
    public String toString() {
        return name;
                
    }


}
