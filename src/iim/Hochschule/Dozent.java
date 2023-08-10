/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;

/**
 *
 * @author Yann Leymann
 */
public class Dozent {
    private final String name;
    private String wishList;
    private long available;
    private long doesNotWant;
    private long scheduled;

    public Dozent(String name, String wishList, long available, long doesNotWant, long scheduled) {
        this.name = name;
        this.wishList = wishList;
        this.available = available;
        this.doesNotWant = doesNotWant;
        this.scheduled = scheduled;
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
    public long getScheduled() {
        return scheduled;
    }
    public void setScheduled(long doesNotWant) {
        this.scheduled = scheduled;
    }

}