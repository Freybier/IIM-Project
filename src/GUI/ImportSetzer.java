/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import iim.Hochschule.LV;
import iim.Hochschule.Zug;
import java.io.BufferedReader;
import java.io.File;
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
    public File fileName;

    public ImportSetzer(List<Zug> zugList, File fileName) {
        this.zugList = zugList;
        this.fileName = fileName;
        importSetzer();
    }

    public void importSetzer() {

        for (Zug zug : zugList) {
            for (LV lv : zug.getLV()) {
                lv.setScheduledLV(0);
                lv.getDozentLV().setScheduledDozent(0);
                lv.setSWSBlocksTookZero();
            }
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;

            while ((line = br.readLine()) != null) {
                String zugName = line.substring(0, 3);
                String scheduled = line.substring(3, 5);
                String lvKuerzel = line.substring(6, 10);
                String dozentName = line.substring(10, 13);
                String raumNr = line.substring(13, 18);

                zugName = zugName.trim();
                scheduled = scheduled.trim();
                lvKuerzel = lvKuerzel.trim();
                dozentName = dozentName.trim();
                raumNr = raumNr.trim();

                System.out.println("Zugname: " + zugName);
                System.out.println("Geplante Zeit: " + scheduled);
                System.out.println("LV-Kürzel: " + lvKuerzel);
                System.out.println("Dozentenname: " + dozentName);
                System.out.println("Raumnummer: " + raumNr);

                for (Zug zug : zugList) {
                    for (LV lv : zug.getLV()) {
                        if (zug.getName().equals(zugName) && lv.getNickName().equals(lvKuerzel)
                                && lv.getDozentName().equals(dozentName) && lv.getLeadingZugName().equals(zugName)) {
                            long scheduler = 1;
                            int shift = Integer.parseInt(scheduled);
                            scheduler = scheduler << (34 - shift);
                            lv.setScheduledLV(lv.getScheduledLV() | scheduler);
                            lv.getDozentLV().setScheduledDozent(lv.getDozentLV().getScheduledDozent() | scheduler);
                            lv.addOneSWSBlocksTook();
                            lv.setRoomNumber(raumNr);
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
