/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Yann Leymann
 */
import iim.Hochschule.Dozent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

    private Dozent dozent;

    public MyTableCellRenderer(Dozent dozent) {
        this.dozent = dozent;
        System.out.println(dozent);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component cellComponent = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
        int checkSum = 0;
        long schiebAvailable = dozent.getAvailable();
        long schiebDoesNotWant = dozent.getDoesNotWant();
        long scheduledDozent = dozent.getScheduledDozent();

        if (!(column == 0)) {
            if (!(column == 6 && row == 5)) {
                if (!(column == 6 && row == 4)) {
                    checkSum = 33 - ((column) * 6) + (6-row);

                    schiebAvailable = schiebAvailable >> checkSum;
                    schiebDoesNotWant = schiebDoesNotWant >> checkSum;
                    scheduledDozent = scheduledDozent >> checkSum;
                    // Überprüfen, ob es sich um die erste Zelle (row = 0, column = 0) handelt
                    if (schiebAvailable % 2 == 1) {
                        cellComponent.setBackground(Color.GREEN);
                    } else if (schiebDoesNotWant % 2 == 1) {
                        cellComponent.setBackground(Color.YELLOW);
                    } else if ((schiebAvailable ^ schiebDoesNotWant)%2 == 0) {
                        cellComponent.setBackground(Color.RED);
                    } else {
                        // Setzen Sie die Standardhintergrundfarbe für andere Zellen
                        cellComponent.setBackground(table.getBackground());
                    }
                    if(scheduledDozent % 2 == 1){
                        cellComponent.setBackground(Color.CYAN);
                    }
                }else {
                        // Setzen Sie die Standardhintergrundfarbe für andere Zellen
                        cellComponent.setBackground(Color.LIGHT_GRAY);
                    }
            }else {
                        // Setzen Sie die Standardhintergrundfarbe für andere Zellen
                        cellComponent.setBackground((Color.LIGHT_GRAY));
                    }
        }

        return cellComponent;
    }
}
