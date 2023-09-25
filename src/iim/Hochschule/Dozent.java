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
public class Dozent implements Serializable {
    
    private final String name;// Name of the Dozent
    private String wishList;// String of "X", "x" and " " wich contains the working hours of a Dozent
    private long available;// A number wich contains the information of the availability
    private long doesNotWant;// A number wich contains the information of the "doesNotWant" hours
    private long scheduledDozent = 0;// A number wich contains the information of the scheduling 
    private final List<LV> lv = new ArrayList<>();// List of LV objects given by the Dozent
    private final List<String> lvName = new ArrayList<>();//List of LV names given by the Dozent
    private boolean pvZeiten = true;//A boolean to check if the Dozent has pvZeiten-informations

    public Dozent(String name) {
        this.name = name;    
    }
    
    public void setPVZeiten( String wishList, long available, long doesNotWant){
       this.wishList = wishList;              
        this.available = available;
        this.doesNotWant = doesNotWant;
    }
    
    

    public String getName() {
        return name;
    }

    public String getWishList() {
        return wishList;
    }
    
    public long getAvailable() {
        return available;
    }
    
    public void setAvailable(long available) {
        this.available = available;
    }
    
    public long getDoesNotWant() {
        return doesNotWant;
    }
    public void setDoesNotWant(long doesNotWant) {
        this.doesNotWant = doesNotWant;
    }
    public long getScheduledDozent() {
        return scheduledDozent;
    }
    public void setScheduledDozent(long scheduled) {
        this.scheduledDozent = scheduled;
    }
    
     public List<LV> getLV(){
        return lv;
    }
    
    public void addLV(LV lv){
        this.lv.add(lv);
        addLVName(lv.getName());
    }
    public void addLVName(String lvName){
        this.lvName.add(lvName);
    }
    public List<String> getLVName(){
        return this.lvName;
    }
    public void setDoesHavePVZeiten(boolean pvZeiten){
        this.pvZeiten = pvZeiten;
    }
    public boolean getDoesHavePVZeiten(){
        return pvZeiten;
    }

    
    @Override
    public String toString() {
        return  name;
                
    }
}