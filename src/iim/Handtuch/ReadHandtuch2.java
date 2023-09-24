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


/**
 *
 * @author Frey
 */
public class ReadHandtuch2 {

    private static String[][] tableData = null;
    public static int numRows = 0;
    public static int numCols = 0;

    public static String[][] readFromFile(String filename) {
        String[][] tableArray = null;
        try {
            // Read the HTML file using Jsoup
            Document doc = Jsoup.parse(new File(filename), "UTF-8");

            tableArray = read(doc);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tableArray;
    }

    public static String[][] read(Document doc) {
        // Find the table element
//        String url = "";
//        try {
//            // Fetch the HTML content of the website using Jsoup
//            doc = Jsoup.connect(url).get();
//
//        } catch (IOException e) {
//            System.out.println(e);
//        }
        
        Element table = doc.select("table").first();

        // Get all rows from the table
        Elements rows = table.select("tr");

        // Count the number of rows and columns
        numRows = rows.size();
        numCols = 0;
        for (Element row : rows) {
            Elements cells = row.select("td, th");
            numCols = Math.max(numCols, cells.size());
        }

        // Create a two-dimensional array to store the table entries
        tableData = new String[numRows][numCols];

        // Iterate over the rows and columns, and store the table entries in the array
        for (int i = 0; i < numRows; i++) {
            Element row = rows.get(i);
            Elements cells = row.select("td, th");
            for (int j = 0; j < cells.size(); j++) {
                Element cell = cells.get(j);

                tableData[i][j] = cell.text();
            }
        }
        String[][] tableArray = tableData;
        createCSV(tableArray);
        return tableArray;
    }

    public static void createCSV(String data[][]) {
        String absolutPath = new File("").getAbsolutePath();
        String filePath = absolutPath + "\\src\\iim\\Handtuch\\HandtuchOutput.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {

                    writer.append(data[i][j]);
                    if (j < data[i].length - 1) {
                        writer.append(";");

                    }
                }
                writer.append("\n");
            }
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
        String websiteUrl = "file:///C:/Users/Frey/Downloads/Handtuch%20(1).html";  // Replace with the actual website URL
        tableData = readFromWebsite(websiteUrl);

        if (tableData != null) {
            // Call the createCSV method to create the CSV file
            createCSV(tableData);
        }
    }

    public static Object[][] getObjectTable() {
        String convertable[][] = tableData;
        Object[][] tableObject = convert(convertable);

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
}
