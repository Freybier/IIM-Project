/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yann Leymann
 */
public class ReadCSVs {
    
    public void getLVforDozentfromCSV(List<Dozent> dozenten){
        
        
        String handtuchCSVFilePath = "src/iim/Handtuch/HandtuchOutput.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(handtuchCSVFilePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(";");

                String dozentFromCsv = columns[9]; // Index basiert auf Ihrer Spaltenreihenfolge
                String lvKuerzel = columns[1]; // Index basiert auf Ihrer Spaltenreihenfolge

                for (Dozent dozent : dozenten) {
                    if (dozent.getName().equals(dozentFromCsv)) {
                        dozent.addLV(lvKuerzel); // Methode zum Hinzufügen des LV-Kürzels zur Dozenten-Instanz
                        //System.out.println("dozent: " + dozentFromCsv + "LV: " + lvKuerzel);
                    }
                }
               
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
