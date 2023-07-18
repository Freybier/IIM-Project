/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Yann Leymann
 */
public class Zeiten {

public static List<Professor> parseStundenplan(String filename) {
        List<Professor> professoren = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String profName = line.substring(0, 3);
                String zeiten = line.substring(3); // Trim, um führende/trailing Leerzeichen zu entfernen

                Professor professor = new Professor(profName, zeiten);
                professoren.add(professor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return professoren;
    }

    
}

