/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Handtuch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Frey
 */
public class ReadHandtuch2 {
    private static String[][] tableData = null;
    public static int numRows = 0;
    public static int numCols = 0;
    
    
    public static String[][] readFromFile() {
        String[][] tableArray = null;
        try {
            // Read the HTML file using Jsoup
            Document doc = Jsoup.parse(new File("src/iim/Handtuch/Handtuch.html"), "UTF-8");

            tableArray = read(doc);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return tableArray;
    }
    
    public static String[][] read(Document doc) {
            // Find the table element
            Element table = doc.select("table").first();

            // Get all rows from the table
            Elements rows = table.select("tr");

            
             // Count the number of rows and columns
            numRows = rows.size();
            numCols = 0;
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
        String[][] tableArray = tableData;
        createCSV(tableArray);
        return tableArray;
    }
     
    public static void createCSV(String data[][]){
        String filePath = "src/iim/Handtuch/HandtuchOutput.csv";
        String[] titel = {"Zug", "LV-Kürzel", "PO", "Bezeichnung", "LVA", "SWS", "geblockt", "online", "SPT", "Dozent"};
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (i == 0){
                        writer.append(titel[j]);
                        writer.append(";");
                    }else{
                    writer.append(data[i][j]);
                    if (j < data[i].length - 1) {
                        writer.append(";");
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
 
    public static String[][] readFromWebsite(String url) {
        
        String[][] tableArray = null;
        try {
            // Fetch the HTML content of the website using Jsoup
            Document doc = Jsoup.connect(url).get();

            tableArray = read(doc);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return tableArray;
    }

    // Rest of your existing code (createCSV method) remains the same

    public static void webReader() {
        String websiteUrl = "https://example.com";  // Replace with the actual website URL
        tableData = readFromWebsite(websiteUrl);

        if (tableData != null) {
            // Call the createCSV method to create the CSV file
            createCSV(tableData);
        }
    }
    
    public static Object[][] getObjectTable(){
        String convertable[][] = tableData; 
        Object[][] tableObject= convert(convertable);
        
        return tableObject;
    }
    
    public static Object[][] convert(String[][] stringArray) {
        numRows = stringArray.length;
        numCols = stringArray[0].length;

        Object[][] objectArray = new Object[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                objectArray[i][j] = stringArray[i][j];
            }
        }
        return objectArray;
    }
    
    public static Set getNames(){
        
        Set<String> names = new HashSet<>();
        for(int i = 1; i < numRows; i++){
            names.add(tableData[i][9]);
        }
        return names;
    }
    
    public static Set getZug(){
        
        Set<String> zuege = new HashSet<>();
        for(int i = 1; i < numRows; i++){
            zuege.add(tableData[i][0]);
        }
        return zuege;
    }
    
    public static Set getLV(){
        
        Set<String> names = new HashSet<>();
        for(int i = 1; i < numRows; i++){
            names.add(tableData[i][1]);
        }
        return names;
    }
    
}

