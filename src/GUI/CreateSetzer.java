/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import iim.Hochschule.LV;
import iim.Hochschule.Zug;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Frey
 */
public class CreateSetzer {

    public List<Zug> zugList;

    public CreateSetzer(List<Zug> zugList, String fileName) {
        createFile(zugList ,fileName);
    }

    public void createFile(List<Zug> zugList, String fileName) {
        String csvFilePath = "Data/" + fileName +".csv";
        try {
            // Create a FileWriter and BufferedWriter to write to the CSV file
            FileWriter fileWriter = new FileWriter(csvFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Loop to write data to the CSV file
            for (Zug zug : zugList) {
                for (LV lv : zug.getLV()) {
                    long sceduler = lv.getScheduledLV();
                    for(int i = 1; i<=34; i++){
                        String data;
                        if(sceduler%2==1){
                            String zugName = zug.getName();
                            String block = Integer.toString(35-i);
                            String eins = "1";
                            String nickName = lv.getNickName();
                            String dozentName;
                            if(lv.getLeadingZugName().equals(zugName)){
                              dozentName = lv.getDozentName();  
                            }else{
                                dozentName = lv.getLeadingZugName();
                            }
                            
                            String raumNr = lv.getRoomNumber();
                            data = zugName+block+eins+nickName+dozentName+raumNr;
                            bufferedWriter.write(data+";");
                            bufferedWriter.write(zugName+";");
                            bufferedWriter.write(block+";");
                            bufferedWriter.write(eins+";");
                            bufferedWriter.write(nickName+";");
                            bufferedWriter.write(dozentName+";");
                            bufferedWriter.write(raumNr);
                            bufferedWriter.newLine(); // Add a new line for the next row
                        }
                        
                        sceduler = sceduler >> 1;
                    }
                }
            }

            // Close the BufferedWriter and FileWriter
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}