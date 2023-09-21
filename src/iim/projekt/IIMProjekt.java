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
import iim.Hochschule.LV;
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

        String handtuchCSVFilePath = "src/iim/Handtuch/HandtuchNeu.csv";

        SwingUtilities.invokeLater(() -> {
            //new StundenplanGUI(dozenten);
        });


        
        
        ReadHandtuch2.readFromFile("src/iim/Handtuch/Handtuch.html");
        //ReadHandtuch2.readFromWebsite("file:///C:/Users/Frey/Downloads/Handtuch%20(1).html");



        ReadHandtuch2.readFromFile();
        List<Dozent> dozentList = pvZeitenToDozent.splittNameWishList(relativePath);
        ReadCSVs.createLVListFromCSV(handtuchCSVFilePath, dozentList);

        

        String filePath = "src/iim/Handtuch/HandtuchOutput.csv";
        List<String[]> data = readCsvFromFile(handtuchCSVFilePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data));



        filePath = "dozenten.csv";
        List<String[]> dozentenPVZeitenList = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(dozentenPVZeitenList));
        
        List<LV> lvList = ReadCSVs.getLVList();
        List<Zug> zugList = ReadCSVs.getZugList();
        dozentList = ReadCSVs.getDozentList();

        StundenplanFrame gui = new StundenplanFrame(dozentList, zugList, lvList);

    }
}
