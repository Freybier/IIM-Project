/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.pvZeiten;

/**
 *
 * @author Yann Leymann
 */
public class TestBinArrayWeek {
    int blocks = 34;
    long[] binWeek = new long[blocks];
    
    public void testBin(){
     for(int i = blocks-1; i >= 0; i--){
         binWeek[i] = (long) Math.pow(2, i);
     }
    }
}
