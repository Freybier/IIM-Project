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
import GUI.Export;

import iim.Handtuch.ReadHandtuch2;
import iim.pvZeiten.DozentToCSV;
import javax.swing.SwingUtilities;
import GUI.StundenplanFrame;
import GUI.TestYann;
import iim.Handtuch.Leading;
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
        String handtuchCSVFilePathOld = "src/iim/Handtuch/HandtuchOutput.csv";
        
        String handtuchCSVFilePath = "src/iim/Handtuch/HandtuchNeu.csv";
        
        SwingUtilities.invokeLater(() -> {
            //new StundenplanGUI(dozenten);
        });
            
              // Dateinamen für die CSV-Datei
        

        // Instanz der ProfVerarbeitung-Klasse erstellen
        //DozentToCSV verarbeitung = new DozentToCSV();

        // Daten in CSV-Format speichern
        
        
        ReadHandtuch2.readFromFile();
        
        
        List<LV> lvList = ReadCSVs.createLVListFromCSV(handtuchCSVFilePath);
        
        List<Dozent> dozentenList = pvZeitenToDozent.splittNameWishList(relativePath);
        
        
        
        ReadCSVs readCSVs = new ReadCSVs();        
        
            
        //String dozentenCSV = "dozenten.csv";
       // verarbeitung.saveAsCSV(dozentenList, dozentenCSV);
        
        String filePath = "src/iim/Handtuch/HandtuchOutput.csv";
        List<String[]> data = readCsvFromFile(handtuchCSVFilePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data));
        
        
        List<Zug> zugList = ReadCSVs.createZugListfromCSV(handtuchCSVFilePath, lvList);
        //readCSVs.setLVforZug(lvList, zugList);
        
        readCSVs.addZugToLV(lvList, zugList);
        
        filePath = "dozenten.csv";
         List<String[]> dozentenPVZeitenList = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(dozentenPVZeitenList));

        //DB.speichern(dozenten);
        
        /*
        for(Dozent lecturer : dozenten){
            if(lecturer.getName().equals("YAN")){
                System.out.print(lecturer.getAvailable());
            }
        }*/
       
        
        String handtuchOutputUpdatePath = "src/iim/Handtuch/HandtuchOutputUpdate.csv";
        UpdateHandtuchCSV update = new UpdateHandtuchCSV(); 
        update.updateHandtuchCSV(handtuchCSVFilePath, dozentenList, zugList, lvList);
        //update.addParallel(handtuchOutputUpdatePath);
        
        //lvList = ReadCSVs.createLVListFromCSV(handtuchOutputUpdatePath);
        //update.findLeadingLVs("src/iim/Handtuch/HandtuchOutput.csv", lvList);
        dozentenList = readCSVs.addDozentNotInPVZeiten(dozentenList, handtuchOutputUpdatePath);
        readCSVs.getLVforDozentfromCSV(dozentenList, lvList, handtuchCSVFilePath);
        zugList = ReadCSVs.createZugListfromCSV(handtuchOutputUpdatePath, lvList);
        
        readCSVs.addZugToLV(lvList, zugList);
        
        List<Leading> leadingList = update.findLeadingLVs("src/iim/Handtuch/HandtuchOutput.csv", lvList, zugList);
        update.setSingleLeading(lvList, leadingList);
        for(Leading leadingLV: leadingList){
            //System.out.println(leadingLV.getName() + " " + leadingLV.getZug() + " " + leadingLV.getDozent());
        }
        readCSVs.setLVLeading(lvList, leadingList);
        
        readCSVs.setLVforZug(lvList, zugList);
        readCSVs.cleanZugLVListDouble(zugList);
        readCSVs.cleanZugLVListWrongLV(zugList);
        /*
        for (LV lv : lvList) {     
            System.out.println(lv.toString());
            System.out.println("----------------------------------"); 
        } 
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"); 
        for(Zug zug : zugList){
            System.out.println(zug.getLV());
            System.out.println("----------------------------------"); 
        }
        
        
        for(Dozent dozent: dozentenList){
            System.out.println("Dozent Name: " + dozent.getName());
            System.out.println("LVs: " + dozent.getLV());
            System.out.println("------------------------");
            
        }*/

        StundenplanFrame gui = new StundenplanFrame(dozentenList, zugList, lvList, leadingList); 
        TestYann testYann = new TestYann();
        testYann.frame();
        
        
}
}

    

