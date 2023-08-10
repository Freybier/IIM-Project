/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iim.projekt;


//import iim.GUI.StundenplanFrame;

import GUI.StundenplanGUI;
import GUI.TxtToCsvTable;
import static GUI.TxtToCsvTable.readCsvFromFile;
import iim.Hochschule.Dozent;
import iim.pvZeiten.pvZeitenToDozent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import iim.Handtuch.ReadHandtuch2;
import iim.pvZeiten.DozentToCSV;
import javax.swing.SwingUtilities;
import GUI.StundenplanFrame;
import static iim.pvZeiten.pvZeitenToDozent.splittNameWishList;

/**
 *
 * @author frey
 */
public class IIMProjekt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


            String relativePath = "src/iim/pvZeiten/pvZeitenTest.txt";
        
        List<Dozent> dozenten = pvZeitenToDozent.splittNameWishList(relativePath);
        
        SwingUtilities.invokeLater(() -> {
            //new StundenplanGUI(dozenten);
        });
            
              // Dateinamen für die CSV-Datei
        String filename = "dozenten.csv";

        // Instanz der ProfVerarbeitung-Klasse erstellen
        DozentToCSV verarbeitung = new DozentToCSV();

        // Daten in CSV-Format speichern
        verarbeitung.saveAsCSV(dozenten, filename);
        
        ReadHandtuch2.read();
        
        //String filePath = "dozenten.csv";
        String filePath = "src/iim/Handtuch/HandtuchOutput.csv";
        List<String[]> data = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data));
        
        
        filePath = "dozenten.csv";
         List<String[]> data2 = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data2));

        //DB.speichern(dozenten);
        StundenplanFrame gui = new StundenplanFrame(); 
        
        for(Dozent lecturer : dozenten){
            if(lecturer.getName().equals("YAN")){
                System.out.print(lecturer.getAvailable());
            }
        }
}
}
    

