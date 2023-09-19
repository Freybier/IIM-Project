/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

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
    
    public CreateSetzer(List<Zug> zugList){
        this.zugList = zugList;
        createFile();
    }
    
    public void createFile(){
        String csvFilePath = "Data/setzer.csv";
        try {
            // Create a FileWriter and BufferedWriter to write to the CSV file
            FileWriter fileWriter = new FileWriter(csvFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Loop to write data to the CSV file
            for (int i = 1; i <= 10; i++) {
                // Generate some sample data (e.g., numbers and strings)
                String data = "Row " + i + "," + "Value " + i + "," + (i * 2);

                // Write the data to the CSV file
                bufferedWriter.write(data);
                bufferedWriter.newLine(); // Add a new line for the next row
            }

            // Close the BufferedWriter and FileWriter
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
