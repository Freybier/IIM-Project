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
public class Zug {
    private final String name;
    private long scheduled = 0;
    private String po;
    private List<LV> lv = new ArrayList<>();

    public Zug(String name, String po) {
        this.name = name;
        this.po = po;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPO(){
        return po;
    }
    
    public List<LV> getLV(){
    return lv;
    }
    
    public void addLV(LV lv){
        this.lv.add(lv);
    }
        
    public long getScheduled(){
        return scheduled;
    }
    
    public void setScheduled(long scheduled){
        this.scheduled = scheduled;
    }
    
    
    
    
}
