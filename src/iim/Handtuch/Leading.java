/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Handtuch;


import iim.Hochschule.LV;


/**
 *
 * @author Yann Leymann
 */
public class Leading {

    private String name;
    private String dozent;
    private String zug;
    private String LVName;
    private boolean leading;
    private LV lv;
    
    

    public Leading(String name, String dozent, String zug) {
        this.name = name;
        this.dozent = dozent;
        this.zug = zug;
    }
    
    public void setLeading(boolean haupt){
        this.leading = leading;
    }
    
    public boolean getLeading(){
        return leading;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDozent(){
        return dozent;
    }
    
    public String getZug(){
        return zug;
    }
    public LV getLV(){
        return lv;
    }

}
