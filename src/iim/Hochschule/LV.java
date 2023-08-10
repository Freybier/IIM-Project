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
    private String fullName;
    private String po;
    private List<String> dozenten = new ArrayList<>();

    public LV(String name, String fullName, String po) {
        this.name = name;
        this.fullName = fullName;
        this.po = po;      
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
    
    public List<String> getDozenten(){
        return dozenten;
    }
    
    public void addDozenten(String dozenten){
        this.dozenten.add(dozenten);
    }
}
