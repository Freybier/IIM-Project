/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iim.projekt;


//import iim.GUI.StundenplanFrame;

import GUI.TxtToCsvTable;
import static GUI.TxtToCsvTable.readCsvFromFile;
import iim.Hochschule.Dozent;
import iim.pvZeiten.pvZeitenToDozent;
import java.util.List;

import iim.Handtuch.ReadHandtuch2;
import javax.swing.SwingUtilities;
import GUI.StundenplanFrame;
import GUI.TestYann;
import iim.Hochschule.LV;
import iim.Hochschule.Leading;
import iim.Hochschule.ReadCSVs;
import iim.Hochschule.Zug;

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
        
        
       
        
        filePath = "dozenten.csv";
         List<String[]> dozentenPVZeitenList = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(dozentenPVZeitenList));

       

        String handtuchOutputUpdatePath = "src/iim/Handtuch/HandtuchOutputUpdate.csv";
        
        
        dozentenList = readCSVs.addDozentNotInPVZeiten(dozentenList, handtuchOutputUpdatePath, lvList);
        readCSVs.addLVforDozent(dozentenList, lvList);
        
        readCSVs.addZugToLV(lvList, zugList);
        
        
        
        readCSVs.setLVforZug(lvList, zugList);
       
        List<Leading> leadingList = readCSVs.getLeadingList();
        
        
        StundenplanFrame gui = new StundenplanFrame(dozentenList, zugList, lvList, leadingList); 
        TestYann testYann = new TestYann();
        testYann.frame();
        
        
}
}

    

