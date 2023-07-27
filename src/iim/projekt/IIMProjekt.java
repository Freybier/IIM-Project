/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iim.projekt;


//import iim.GUI.StundenplanFrame;

import DB.DB;
import GUI.StundenplanGUI;
import GUI.TxtToCsvTable;
import static GUI.TxtToCsvTable.readCsvFromFile;
import iim.pvZeiten.Professor;
import iim.pvZeiten.Test;
import iim.pvZeiten.Zeiten;
import static iim.pvZeiten.Zeiten.parseStundenplan;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import iim.Handtuch.readhandtuch;
import iim.pvZeiten.ProfToCSV;
import javax.swing.SwingUtilities;

/**
 *
 * @author frey
 */
public class IIMProjekt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        Test.hallo();

        
            String relativePath = "src/iim/pvZeiten/pvZeiten.txt";
        
        List<Professor> professoren = Zeiten.parseStundenplan(relativePath);
        /*
        // Durchlauf der Liste der Professoren und Ausgabe der Namen und Zeiten
        for (Professor professor : professoren) {
            System.out.println("Name: " + professor.getName());
            System.out.println("Zeiten: " + professor.getZeiten());
        }
        
        for (Professor professor : professoren) {
        professor.wochenZeiten();
        System.out.println();
       // String[][] handtuchData = readhandtuch.read();

    }
*/
        SwingUtilities.invokeLater(() -> {
            //new StundenplanGUI(professoren);
        });
        
       
       
             DB.speichern(professoren);
             
              // Dateinamen für die CSV-Datei
        String filename = "professoren.csv";

        // Instanz der ProfVerarbeitung-Klasse erstellen
        ProfToCSV verarbeitung = new ProfToCSV();

        // Daten in CSV-Format speichern
        verarbeitung.speichernAlsCSV(professoren, filename);
        String filePath = "professoren.csv";

        List<String[]> data = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data));
        

        //DB.speichern(professoren);

}
}
    

