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
import iim.Hochschule.LV;
import iim.Hochschule.ReadCSVs;
import iim.Hochschule.Zug;
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


        String relativePath = "src/iim/pvZeiten/pvZeiten.txt";
        
        
        
        SwingUtilities.invokeLater(() -> {
            //new StundenplanGUI(dozenten);
        });
            
              // Dateinamen für die CSV-Datei
        

        // Instanz der ProfVerarbeitung-Klasse erstellen
        DozentToCSV verarbeitung = new DozentToCSV();

        // Daten in CSV-Format speichern
        
        
        ReadHandtuch2.readFromFile();
        
        List<LV> lvList = ReadCSVs.createLVListFromCSV("src/iim/Handtuch/HandtuchOutput.csv");
        
        List<Dozent> dozenten = pvZeitenToDozent.splittNameWishList(relativePath);
        
        ReadCSVs readCSVs = new ReadCSVs();        
        readCSVs.getLVforDozentfromCSV(dozenten, lvList);
            
        String filename = "dozenten.csv";
        verarbeitung.saveAsCSV(dozenten, filename);
        
        String filePath = "src/iim/Handtuch/HandtuchOutput.csv";
        List<String[]> data = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data));
        
        String handtuchCSVFilePath = "src/iim/Handtuch/HandtuchOutput.csv";
        List<Zug> zugList = ReadCSVs.createZugListfromCSV(handtuchCSVFilePath, lvList);
        
        readCSVs.addZugToLV(lvList, zugList);
        
        filePath = "dozenten.csv";
         List<String[]> data2 = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data2));

        //DB.speichern(dozenten);
        StundenplanFrame gui = new StundenplanFrame(); 
        /*
        for(Dozent lecturer : dozenten){
            if(lecturer.getName().equals("YAN")){
                System.out.print(lecturer.getAvailable());
            }
        }*/
        
        for (LV lv : lvList) {
            System.out.println("LV Name: " + lv.getName());
            System.out.println("LV Full Name: " + lv.getFullName());
            System.out.println("LV PO: " + lv.getPO());
            System.out.println("Dozenten: " + lv.getDozentName());
            System.out.println("Zug Name: " + lv.getZugNameList());
            System.out.println("Zug: " + lv.getZugList());

            /*List<Zug> zugListe = lv.getZugList();
            System.out.println("Zug Liste:");
            for (Zug zug : zugListe) {
                System.out.println("- Zug Name: " + zug.getName());
                System.out.println("  Zug PO: " + zug.getPO());
                // Weitere Informationen über den Zug ausgeben
            }*/

            System.out.println("----------------------------------");
        }
}
}

    

