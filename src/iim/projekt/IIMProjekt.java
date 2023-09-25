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
        String absolutPath = System.getProperty("user.dir");
        String relativePath = absolutPath + "\\src\\iim\\pvZeiten\\pvZeiten.txt";

        String handtuchCSVFilePath = absolutPath + "\\src\\iim\\Handtuch\\HandtuchNeu.csv";
        String fileReadPath = absolutPath + "\\src\\iim\\Handtuch\\Handtuch.html";
        ReadHandtuch2.readFromFile(fileReadPath);
        //ReadHandtuch2.readFromWebsite("file:///C:/Users/Frey/Downloads/Handtuch%20(1).html");

        List<Dozent> dozentList = pvZeitenToDozent.splittNameWishList(relativePath);
        ReadCSVs.createObjects(handtuchCSVFilePath, dozentList);
        
        List<LV> lvList = ReadCSVs.getLVList();
        List<Zug> zugList = ReadCSVs.getZugList();
        dozentList = ReadCSVs.getDozentList();

        StundenplanFrame gui = new StundenplanFrame(dozentList, zugList, lvList, handtuchCSVFilePath);

    }
}
