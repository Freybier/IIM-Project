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
public class Dozent {
    private final String name;
    private String wishList;
    private long available;
    private long doesNotWant;
    private long scheduledDozent = 0;
    private List<LV> lv = new ArrayList<>();
    private List<String> lvName = new ArrayList<>();
    private boolean pvZeiten = true;

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