/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iim.projekt;


//import iim.GUI.StundenplanFrame;

import GUI.StundenplanGUI;
import GUI.TxtToCsvTable;
import static GUI.TxtToCsvTable.readCsvFromFile;
import iim.pvZeiten.Lecturer;
import iim.pvZeiten.pvZeitenToLecturer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import iim.Handtuch.ReadHandtuch2;
import iim.pvZeiten.LecturerToCSV;
import javax.swing.SwingUtilities;
import GUI.StundenplanFrame;
import static iim.pvZeiten.pvZeitenToLecturer.splittNameWishList;

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
        
        List<Lecturer> lecturers = pvZeitenToLecturer.splittNameWishList(relativePath);
        
        SwingUtilities.invokeLater(() -> {
            //new StundenplanGUI(lecturers);
        });
            
              // Dateinamen für die CSV-Datei
        String filename = "lecturers.csv";

        // Instanz der ProfVerarbeitung-Klasse erstellen
        LecturerToCSV verarbeitung = new LecturerToCSV();

        // Daten in CSV-Format speichern
        verarbeitung.speichernAlsCSV(lecturers, filename);
        
        ReadHandtuch2.read();
        
        //String filePath = "lecturers.csv";
        String filePath = "src/iim/Handtuch/HandtuchOutput.csv";
        List<String[]> data = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data));
        
        
        filePath = "lecturers.csv";
         List<String[]> data2 = readCsvFromFile(filePath);
        SwingUtilities.invokeLater(() -> new TxtToCsvTable(data2));

        //DB.speichern(lecturers);
        StundenplanFrame gui = new StundenplanFrame(); 
        
        for(Lecturer lecturer : lecturers){
            if(lecturer.getName().equals("YAN")){
                System.out.print(lecturer.getAvailable());
            }
        }
}
}
    

