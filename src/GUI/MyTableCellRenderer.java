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
import iim.Hochschule.LV;
import iim.Hochschule.Zug;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

    private List<Dozent> dozentList;
    private Dozent dozent;
    private Zug zug;
    private List<LV> lvList;

    public MyTableCellRenderer(Dozent dozent, List<Dozent> dozentList,List<LV> lvList) {
        this.dozent = dozent;
        this.dozentList = dozentList;
        this.lvList = lvList;

        //System.out.println(dozent);
    }

    public void setZug(Zug zug) {
        this.zug = zug;
        if (zug != null) {
            
        }
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
        
        List<String> stringList = new ArrayList<>();
        for(LV lv: lvList){
            stringList.add(lv.getName());
        }
        
        if (dozent == null || !dozent.getDoesHavePVZeiten()) {
            cellComponent.setBackground(table.getBackground());
            return cellComponent;
        }

        if (!(column == 0)) {
            if (!(column == 6 && row == 5)) {
                if (!(column == 6 && row == 4)) {
                    checkSum = 33 - ((column) * 6) + (6 - row);

                    schiebAvailable = schiebAvailable >> checkSum;
                    schiebDoesNotWant = schiebDoesNotWant >> checkSum;
                    scheduledDozent = scheduledDozent >> checkSum;
                    // Überprüfen, ob es sich um die erste Zelle (row = 0, column = 0) handelt
                    if (schiebAvailable % 2 == 1) {
                        cellComponent.setBackground(Color.GREEN);
                    } else if (schiebDoesNotWant % 2 == 1) {
                        cellComponent.setBackground(Color.YELLOW);
                    } else if ((schiebAvailable ^ schiebDoesNotWant) % 2 == 0) {
                        cellComponent.setBackground(Color.RED);
                    } else {
                        // Setzen Sie die Standardhintergrundfarbe für andere Zellen
                        cellComponent.setBackground(table.getBackground());
                    }
                    if (scheduledDozent % 2 == 1 && zug == null) {
                        //cellComponent.setForeground(Color.PINK);
                        cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                        setBorder(new CompoundBorder(new LineBorder(Color.BLUE, 5), new EmptyBorder(5, 5, 5, 5)));
                        
                            if(!stringList.contains(value.toString())){
                                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                            setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY, 5), new EmptyBorder(5, 5, 5, 5)));
                            }
                        
                    }else if (zug != null) {
                        //System.out.println("zug ungleich null!!");
                        if (scheduledDozent % 2 == 1) {
                           // System.out.println("CYAN!!!!!!!!!");
                            
                            for (LV lvDozent : dozent.getLV()) {
                                for (Zug zugLV : lvDozent.getZugList()) {
                                    if (zugLV.getName().equals(zug.getName())) {
                                        //System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBLue!!!!!!!!!");
                                        cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                                        setBorder(new CompoundBorder(new LineBorder(Color.BLUE, 5), new EmptyBorder(5, 5, 5, 5)));
                                    }
                                }
                            }

                        }
                    }
                } else {
                    // Setzen Sie die Standardhintergrundfarbe für andere Zellen
                    cellComponent.setBackground(Color.LIGHT_GRAY);
                }
            } else {
                // Setzen Sie die Standardhintergrundfarbe für andere Zellen
                cellComponent.setBackground((Color.LIGHT_GRAY));
            }
        }

        return cellComponent;
    }
}
