/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Frey
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CSVToObjectArrayConverter {
    private Object[][] data;
    private int numRows;
    private String[] headers;
    private final String path;
    
    public CSVToObjectArrayConverter(String filePath){
        this.path = filePath;
        convertCSVToObjectArray();
    }
    
    private void convertCSVToObjectArray() {
        Object[][] fileContent = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.path));
            String line;
            this.headers = null;
            this.numRows = 0;
            int numCols = 0;

            // Read and store column headers
            if ((line = br.readLine()) != null) {
                this.headers = line.split(";");
                numCols = this.headers.length;
                this.numRows++;
            }

            // Count the number of data rows
            while (br.readLine() != null) {
                this.numRows++;
            }

            br.close();

            // Initialize the data array
            fileContent = new Object[this.numRows][numCols];

            // Read and populate data rows
            br = new BufferedReader(new FileReader(this.path));
            int row = 0;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                for (int col = 0; col < numCols; col++) {
                    if (col < values.length) {
                        fileContent[row][col] = values[col];
                    } else {
                        fileContent[row][col] = ""; // Empty value for missing data
                    }
                }
                row++;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file read error
        }

        data = fileContent;
    }
    
    public Set getter(int spalte){
        Set<String> content = new HashSet<>();
        for (int i = 1; i < this.numRows; i++) {
            content.add((String)this.data[i][spalte]);
        }
        return content;
    }
    
    public int getColumnIndex(String columnName) {
        if(this.headers != null){
            for (int i = 0; i < this.headers.length; i++) {
                if (this.headers[i].equals(columnName)) {
                    return i;
                    }
                }
            return -1;
        }else{
            return -1; // Column not found
        }
 
    }
    
    public Object[][] getData(){
        return this.data;
    }
}
