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

    public Dozent(String name, String wishList, long available, long doesNotWant) {
        this.name = name;
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
    }
}