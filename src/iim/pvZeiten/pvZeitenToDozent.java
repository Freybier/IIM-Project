/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.pvZeiten;
import iim.Hochschule.ReadCSVs;
import iim.Hochschule.Dozent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Yann Leymann
 */
public class pvZeitenToDozent {

public static List<Dozent> splittNameWishList(String filename) {
        List<Dozent> dozentenList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String name = line.substring(0, 3);
                String wishList = line.substring(3); // Trim, um führende/trailing Leerzeichen zu entfernen
                
                long available = 0;
                long doesNotWant = 0;
                
                
                
            for (int i = 0; i < wishList.length(); i++) {
                char pos = wishList.charAt(i);
                
                if(pos == ' '){
                   available++;                 
                } else if (pos == 'x') {
                    doesNotWant++;
                }
                if(i != wishList.length()-1){
                available = available << 1;
                doesNotWant = doesNotWant << 1;
                }
            }
                Dozent dozent = new Dozent(name, wishList, available, doesNotWant);
                dozentenList.add(dozent);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        return dozentenList;
    }

    
}

