/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iim.projekt;

//import iim.GUI.StundenplanFrame;
import iim.Hochschule.Dozent;
import iim.pvZeiten.pvZeitenToDozent;
import java.util.List;
import iim.Handtuch.ReadHandtuch2;
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
        // gets path till "IIM-Projekt", needed to be started via jar-file
        String absolutPath = System.getProperty("user.dir");
        String pvzeiten = absolutPath + "\\src\\iim\\pvZeiten\\pvZeiten.txt";
        
        String handtuchCSVFilePath = absolutPath + "\\src\\iim\\Handtuch\\HandtuchNeu.csv";
        String handtuchHTMLReadPath = absolutPath + "\\src\\iim\\Handtuch\\Handtuch.html";
        
        // differentialte if you need to read from file or if you need to read from a browser
        ReadHandtuch2.readFromFile(handtuchHTMLReadPath);
        //ReadHandtuch2.readFromWebsite("file:///C:/Users/Frey/Downloads/Handtuch%20(1).html");
        
        // creates Dozenten-list from file pvZeiten
        List<Dozent> dozentList = pvZeitenToDozent.splittNameWishList(pvzeiten);
        
        // creates foundation for creating the other Lists from Handtuch 
        ReadCSVs.createObjects(handtuchCSVFilePath, dozentList);
        
        List<LV> lvList = ReadCSVs.getLVList();
        List<Zug> zugList = ReadCSVs.getZugList();
        dozentList = ReadCSVs.getDozentList();

        StundenplanFrame gui = new StundenplanFrame(dozentList, zugList, lvList, handtuchCSVFilePath);

    }
}
