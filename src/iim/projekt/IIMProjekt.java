/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iim.projekt;


import DB.DB;
import iim.Professor;
import iim.Test;

import iim.Zeiten;
import static iim.Zeiten.parseStundenplan;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import iim.Handtuch.readhandtuch;

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

        // Durchlauf der Liste der Professoren und Ausgabe der Namen und Zeiten
        for (Professor professor : professoren) {
            System.out.println("Name: " + professor.getName());
            System.out.println("Zeiten: " + professor.getZeiten());
        }
        
        for (Professor professor : professoren) {
        professor.wochenZeiten();
        System.out.println();
        String[][] handtuchData = readhandtuch.read();

    }
        
        //DB.speichern(professoren);
}
}
    

