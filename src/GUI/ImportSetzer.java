/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import iim.Hochschule.LV;
import iim.Hochschule.Zug;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yann Leymann
 */
public class ImportSetzer {

    public List<Zug> zugList;

    public ImportSetzer(List<Zug> zugList) {
        this.zugList = zugList;
    }

    public void importSetzer() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/setzer.csv"));
            String line;
            String[] header = br.readLine().split(";");
            int zugNameIndex = 1;
            int scheduledIndex = 2;
            int lvKuerzelIndex = 4;
            int dozentNameIndex = 5;
            int raumIndex = 6;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String zugName = parts[zugNameIndex];
                String scheduled = parts[scheduledIndex];
                String lvKuerzel = parts[lvKuerzelIndex];
                String dozentName = parts[dozentNameIndex];
                String raumNr = parts[raumIndex];
                
                for(Zug zug : zugList){
                    for(LV lv : zug.getLV()){
                        if(zug.getName().equals(zugName) && lv.getNickName().equals(lvKuerzel) && lv.getDozentName().equals(dozentName)){
                            long scheduler = 1;
                            int shift = Integer.parseInt(scheduled);
                            scheduler = scheduler << (34-shift);
                            lv.setScheduledLV(lv.getScheduledLV() | scheduler);
                            lv.getDozentLV().setScheduledDozent(lv.getDozentLV().getScheduledDozent() | scheduler);
                            break;
                        }
                    }
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImportSetzer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImportSetzer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
