/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;


import iim.Hochschule.LV;
import java.io.Serializable;


/**
 *
 * @author Yann Leymann
 */
public class Leading implements Serializable {

    private String lv;
    private String dozent;
    private String zug;
    private String LVName;
    private boolean leading;
    private LV lvObject;
    
    

    public Leading(String lv, String dozent, String zug ,boolean leading) {
        this.lv = lv;
        this.dozent = dozent;
        this.zug = zug;
        this.leading = true;
        
    }
    
    
    public boolean getLeading(){
        return leading;
    }
    
    public String getLv(){
        return lv;
    }
    
    public String getDozent(){
        return dozent;
    }
    
    public String getZug(){
        return zug;
    }
    public void setLeadingLV(LV lvObject){
        this.lvObject = lvObject;
    }
    public LV getLVObject(){
        return lvObject;
    }

    @Override
    public String toString() {
        return zug;
                
    }
}
