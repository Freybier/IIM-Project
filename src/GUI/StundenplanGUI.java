/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Yann Leymann
 */
import iim.Professor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StundenplanGUI {
    private JFrame frame;
    private JTable table;
    private JComboBox<String> professorDropdown;
    private List<Professor> profs;

    public StundenplanGUI(List<Professor> profs) {
        this.profs = profs;
        frame = new JFrame("Stundenplan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dropdown-Menü für Professoren
        String[] professoren = profs.stream().map(Professor::getName).toArray(String[]::new);
        professorDropdown = new JComboBox<>(professoren);
        professorDropdown.addActionListener(e -> {
            // Aktualisiere die Tabelle, wenn ein Professor ausgewählt wird
            String selectedProfessor = (String) professorDropdown.getSelectedItem();
            updateTable(selectedProfessor);
        });

        // Tabelle
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel für Dropdown-Menü und Tabelle
        JPanel panel = new JPanel();
        panel.add(professorDropdown);
        panel.add(scrollPane);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void updateTable(String selectedProfessor) {
        // Hier können Sie die Tabelle basierend auf dem ausgewählten Professor aktualisieren
        // Z.B. durch Zugriff auf die Datenbank oder ein anderes Datenmodell

        // Beispiel: Finde den ausgewählten Professor in der Liste der Professoren
        Professor professor = profs.stream()
                .filter(p -> p.getName().equals(selectedProfessor))
                .findFirst()
                .orElse(null);

        if (professor != null) {
            // Beispiel: Erstelle eine leere Tabelle mit 6 Spalten (für 6 Blöcke)
            DefaultTableModel model = new DefaultTableModel(0, 6);
            table.setModel(model);

            // Beispiel: Fülle die Tabelle mit den Zeiten des Professors
            String[] zeiten = professor.getZeiten().split(",");
            for (int row = 0; row < zeiten.length; row++) {
                String[] blockZeiten = zeiten[row].trim().split("\\s+");
                for (int col = 0; col < blockZeiten.length; col++) {
                    model.setValueAt(blockZeiten[col], row, col);
                }
            }
        }
    }
}

    
