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
public class Zug implements Serializable {
    private final String name;
    private long scheduledZug = 0;
    private String po;
    private List<LV> lvList = new ArrayList<>();
    private boolean leading;

    public Zug(String name) {
        this.name = name;
        //this.po = po;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPO(){
        return po;
    }
    
    public List<LV> getLV(){
    return lvList;
    }
    
    public void addLV(LV lv){
        this.lvList.add(lv);
    }
        
    public long getScheduledZug(){
        return scheduledZug;
    }
    
    public void setScheduledZug(long scheduled){
        this.scheduledZug = scheduled;
    }
    
    public void setLeading(boolean leading){
        this.leading = leading;
    }
    
    public boolean getLeading(){
        return leading;
    }
    
    @Override
    public String toString() {
    return name ;
           
            
}
    
    
    
}
