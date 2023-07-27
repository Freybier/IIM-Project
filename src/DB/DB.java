/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import iim.pvZeiten.Professor;
import java.sql.Connection;
//import java.beans.Statement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Yann Leymann
 */
public class DB {
    public static void speichern(List<Professor> professoren) {
    try {
            // Verbindung zur Datenbank herstellen
            Connection conn = DriverManager.getConnection("jdbc:derby:IIM-DB;create=true");

            // Tabelle erstellen
            Statement stmt = (Statement) conn.createStatement();
            String createTableQuery = "CREATE TABLE professors (name VARCHAR(100), zeiten VARCHAR(1000))";
            stmt.executeUpdate(createTableQuery);

            // Daten einfügen
            String updateQuery = "UPDATE professors SET zeiten = 'verfügbar,verfügbar,kann nicht,kann nicht,will nicht,will nicht' WHERE name = 'John Doe'";
            stmt.executeUpdate(updateQuery);

            // Daten abfragen
            String selectDataQuery = "SELECT * FROM professors";
            ResultSet rs = stmt.executeQuery(selectDataQuery);
            while (rs.next()) {
                String name = rs.getString("name");
                String zeiten = rs.getString("zeiten");
                System.out.println("Name: " + name);
                System.out.println("Zeiten: " + zeiten);
            }

            // Verbindung schließen
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}