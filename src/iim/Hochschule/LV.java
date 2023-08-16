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
    private final String po;
    private long scheduledLV = 0;
    //private final List<String> dozentenNameList = new ArrayList<>();
    private final String dozentName;
    private final List<String> zugNameList = new ArrayList<>();
    private final List<Zug> zugList = new ArrayList<>();

    public LV(String name, String fullName, String po, String dozentName) {
        this.name = name;
        this.fullName = fullName;
        this.po = po;
        this.dozentName = dozentName;
    }
    
    public String getName(){
        return name;
    }
    
    public String getFullName(){
        return fullName;
    }
    
    public String getPO(){
        return po;
    }
    
    public String getDozentName(){
        return dozentName;
    }
    
    public List<String> getZugNameList(){
        return zugNameList;
    }
    
    public void addZugToNameList(String zugName){
        this.zugNameList.add(zugName);
    }
    
    public List<Zug> getZugList(){
    return zugList;
    }
    
    public void addZug(Zug zug){
        zugList.add(zug);
    }
     public long getScheduledLV() {
        return scheduledLV;
    }
    public void setScheduledLV(long scheduled) {
        this.scheduledLV = scheduled;
    }
    
    @Override
    public String toString() {
    return "LV Name: " + name +
           "\nLV Full Name: " + fullName +
           "\nLV PO: " + po +
           "\nDozenten: " + dozentName +
           "\nZug Name: " + zugNameList +
           "\nZug: " + zugList;
}
    
    
}
