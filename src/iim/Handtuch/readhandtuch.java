/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Handtuch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
/**
 *
 * @author Frey
 */
public class readhandtuch {
     public static String[][] read() {
         String[][] tableData = null;
        try {
            // Read the HTML file using Jsoup
            Document doc = Jsoup.parse(new File("src/iim/Handtuch/Handtuch.html"), "UTF-8");

            // Find the table element
            Element table = doc.select("table").first();

            // Get all rows from the table
            Elements rows = table.select("tr");

            // Create a BufferedWriter to write to a text file
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/iim/Handtuch/output.txt"));
            
             // Count the number of rows and columns
            int numRows = rows.size();
            int numCols = 0;
            for (Element row : rows) {
                Elements cells = row.select("td");
                numCols = Math.max(numCols, cells.size());
            }
            
            // Create a two-dimensional array to store the table entries
            tableData = new String[numRows][numCols];
            
             // Iterate over the rows and columns, and store the table entries in the array
            for (int i = 0; i < numRows; i++) {
                Element row = rows.get(i);
                Elements cells = row.select("td");
                for (int j = 0; j < cells.size(); j++) {
                    Element cell = cells.get(j);
                    tableData[i][j] = cell.text();
                }
            }

            // Iterate over the rows and write the content to the text file
            for (Element row : rows) {
                Elements cells = row.select("td");
                for (Element cell : cells) {
                    String cellText = cell.text();
                    if(cellText == null){
                        continue;
                    }
                    else if(cellText.isEmpty()){
                        writer.write("noInfo");
                    }else{
                        writer.write(cellText);
                    }
                    writer.newLine();
                }
                writer.newLine();
            }

            // Close the writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        createCSV(tableData);
        return tableData;
    }
     
    public static void createCSV(String data[][]){
        String filePath = "src/iim/Handtuch/output.csv";
        String[] titel = {"Zug", "LV-Kürzel", "PO", "Bezeichnung", "LVA", "SWS", "geblockt", "online", "SPT", "Dozent"};
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (i == 0){
                        writer.append(titel[j]);
                        writer.append(",");
                    }else{
                    writer.append(data[i][j]);
                    if (j < data[i].length - 1) {
                        writer.append(",");
                    }
                    }
                }
                writer.append("\n");
            }
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.err.println("Error while creating CSV file: " + e.getMessage());
        }
    }

    
}
