/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;

/**
 *
 * @author Yann Leymann
 */
public class Zug {
    private final String name;
    private long scheduled = 0;
    private int numberOfLV;
    private String[] lv;

    public Zug(String name, int numberOfLV) {
        this.name = name;
        this.numberOfLV = numberOfLV;
        this.lv = new String[numberOfLV];
    }
    
    public String getName(){
        return name;
    }
    
    public String[] getLV(){
    return lv;
    }
    
    public void setLV(String[] lv){
        this.lv = lv;
    }
        
    public long getScheduled(){
        return scheduled;
    }
    
    public void setScheduled(long scheduled){
        this.scheduled = scheduled;
    }
    
    
    
    
}
