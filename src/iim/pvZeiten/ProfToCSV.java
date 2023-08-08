/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.pvZeiten;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Yann Leymann
 */
public class ProfToCSV {
       public void speichernAlsCSV(List<Professor> professoren, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Name;Montag 1. Block;Montag 2. Block;Montag 3. Block;Montag 4. Block;Montag 5. Block;Montag 6. Block;Dienstag 1. Block;Dienstag 2. Block;Dienstag 3. Block;Dienstag 4. Block;Dienstag 5. Block;Dienstag 6. Block;Mittwoch 1. Block;Mittwoch 2. Block;Mittwoch 3. Block;Mittwoch 4. Block;Mittwoch 5. Block;Mittwoch 6. Block;Donnerstag 1. Block;Donnerstag 2. Block;Donnerstag 3. Block;Donnerstag 4. Block;Donnerstag 5. Block;Donnerstag 6. Block;Freitag 1. Block;Freitag 2. Block;Freitag 3. Block;Freitag 4. Block;Freitag 5. Block;Freitag 6. Block;Samstag 1. Block;Samstag 2. Block;Samstag 3. Block;Samstag 4. Block\n");

            String[] tage = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};
            String[] blocks = {"1. block", "2. block", "3. block", "4. block", "5. block", "6. block"};
            
            for (Professor professor : professoren) {
            StringBuilder line = new StringBuilder(professor.getName());

            int countBlocks = 0;
            for (int i = 0; i < professor.getZeiten().length(); i++) {
                char zeit = professor.getZeiten().charAt(i);
                String status;

                if (zeit == ' ') {
                    status = "verfügbar";
                } else if (zeit == 'X') {
                    status = "kann nicht";
                } else if (zeit == 'x') {
                    status = "will nicht";
                } else {
                    status = "";
                }
                
                if (i % 6 == 0) {
                   // line.append(",");
                    countBlocks = 0; // Zurücksetzen des Blocks-Zählers für den nächsten Tag
                }
                
                // Prüfen, ob der Status leer ist, und einen Leerwert einfügen
                if (status.trim().isEmpty()) {
                    line.append(";");
                } else {
                    line.append(";").append(status);
                }
                
                countBlocks++;

                // Für Samstag mit 4 Blöcken die Zeilen begrenzen
                if (tage[countBlocks - 1].equals("Samstag") && countBlocks == 4) {
                    break;
                }
            }

            writer.append(line.toString()).append("\n");
        }
            
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
