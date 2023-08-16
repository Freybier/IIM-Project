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
import iim.Handtuch.UpdateHandtuchCSV;
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
        String handtuchCSVFilePath = "src/iim/Handtuch/HandtuchOutput.csv";
        
        
        SwingUtilities.invokeLater(() -> {
            //new StundenplanGUI(dozenten);
        });
            
              // Dateinamen für die CSV-Datei
        

        // Instanz der ProfVerarbeitung-Klasse erstellen
        DozentToCSV verarbeitung = new DozentToCSV();

        // Daten in CSV-Format speichern
        
        
        ReadHandtuch2.readFromFile();
        
        
        List<LV> lvList = ReadCSVs.createLVListFromCSV(handtuchCSVFilePath);
        
        List<Dozent> dozentenList = pvZeitenToDozent.splittNameWishList(relativePath);
        
        
        
        ReadCSVs readCSVs = new ReadCSVs();        
        readCSVs.getLVforDozentfromCSV(dozentenList, lvList, handtuchCSVFilePath);
            
        String filename = "dozenten.csv";
        verarbeitung.saveAsCSV(dozentenList, filename);
        
        String filePath = "src/iim/Handtuch/HandtuchOutput.csv";
        List<String[]> data = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data));
        
        
        List<Zug> zugList = ReadCSVs.createZugListfromCSV(handtuchCSVFilePath, lvList);
        
        //readCSVs.addZugToLV(lvList, zugList);
        
        filePath = "dozenten.csv";
         List<String[]> dozentenPVZeitenList = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(dozentenPVZeitenList));

        //DB.speichern(dozenten);
        StundenplanFrame gui = new StundenplanFrame(dozentenList, zugList); 
        /*
        for(Dozent lecturer : dozenten){
            if(lecturer.getName().equals("YAN")){
                System.out.print(lecturer.getAvailable());
            }
        }*/
       
        
        String handtuchOutputUpdatePath = "src/iim/Handtuch/HandtuchOutputUpdate.csv";
        UpdateHandtuchCSV update = new UpdateHandtuchCSV(); 
        update.updateHandtuchCSV(handtuchCSVFilePath, dozentenList, zugList, lvList);
        update.addParallel(handtuchOutputUpdatePath);
        
        lvList = ReadCSVs.createLVListFromCSV(handtuchOutputUpdatePath);
        zugList = ReadCSVs.createZugListfromCSV(handtuchOutputUpdatePath, lvList);
        readCSVs.addZugToLV(lvList, zugList);
        for (LV lv : lvList) {
            
            System.out.println(lv.toString());
            /*
            List<Zug> zugListe = lv.getZugList();
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

    

