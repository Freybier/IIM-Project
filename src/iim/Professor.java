/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim;

/**
 *
 * @author Yann Leymann
 */
public class Professor {
     private String kuerzel;
    private boolean verfuegbar;
    private boolean willNicht;
    private int[][] time = new int[6][6]; 
    
    public Professor(String kuerzel) {
        this.kuerzel = kuerzel;
        this.verfuegbar = false;
        this.willNicht = false;
    }

    public String getKuerzel() {
        return kuerzel;
    }
    
    public int[][] getTime(){
        return time;
    }

    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }

    public boolean isWillNicht() {
        return willNicht;
    }

    public void setWillNicht(boolean willNicht) {
        this.willNicht = willNicht;
    }
}
