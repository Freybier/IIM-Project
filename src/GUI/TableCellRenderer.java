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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableCellRenderer extends DefaultTableCellRenderer {

    private List<Dozent> dozentList;
    private Dozent dozent;
    private Zug zug;
    private List<LV> lvList;
    private String label;
    private LV lvSelected;
    private List<Zug> zugList;

    public TableCellRenderer(Dozent dozent, List<Dozent> dozentList, List<LV> lvList, String label, LV lvSelected, List<Zug> zugList) {
        this.dozent = dozent;
        this.dozentList = dozentList;
        this.lvList = lvList;
        this.label = label;
        this.lvSelected = lvSelected;
        this.zugList = zugList;
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
        String cellValue = (String) value;
        int checkSum = 0;
        long schiebAvailable = dozent.getAvailable();
        long schiebDoesNotWant = dozent.getDoesNotWant();
        long scheduledDozent = dozent.getScheduledDozent();

        List<String> stringList = new ArrayList<>();
        for (LV lv : lvList) {
            stringList.add(lv.getName());
        }
        
        //  || !dozent.getDoesHavePVZeiten()
        if (dozent == null) {
            cellComponent.setBackground(Color.LIGHT_GRAY);
            return cellComponent;
        }
        Color noPVZeitenColor = new Color(220, 220, 220);
        Color doesNotWantColor = new Color(250, 227, 75);
        Color availableColor = new Color(127, 250, 75);
        Color canNotColor = new Color(250, 75, 75);
        cellComponent.setBackground(noPVZeitenColor);
        if (!(column == 0)) {
            if (!(column == 6 && row == 5)) {
                if (!(column == 6 && row == 4)) {
                    if (scheduledDozent == 0) {
                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                        tableModel.setValueAt("", row, column);
                        table.revalidate();
                        table.repaint();
                    }
                    checkSum = 33 - ((column) * 6) + (6 - row);

                    schiebAvailable = schiebAvailable >> checkSum;
                    schiebDoesNotWant = schiebDoesNotWant >> checkSum;
                    scheduledDozent = scheduledDozent >> checkSum;
                    // Überprüfen, ob es sich um die erste Zelle (row = 0, column = 0) handelt
                    if (schiebAvailable % 2 == 1 && dozent.getDoesHavePVZeiten()) {
                        cellComponent.setBackground(availableColor);
                    } else if (schiebDoesNotWant % 2 == 1 && dozent.getDoesHavePVZeiten()) {
                        cellComponent.setBackground(doesNotWantColor);
                    } else if ((schiebAvailable ^ schiebDoesNotWant) % 2 == 0 && dozent.getDoesHavePVZeiten()) {
                        cellComponent.setBackground(canNotColor);
                    } else {
                        // Setzen Sie die Standardhintergrundfarbe für andere Zellen
                        cellComponent.setBackground(noPVZeitenColor);
                    }
                    if (scheduledDozent % 2 == 1 && zug == null) {
                        //cellComponent.setForeground(Color.PINK);
                        cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                        setBorder(new CompoundBorder(new LineBorder(Color.BLUE, 5), new EmptyBorder(5, 5, 5, 5)));

                        if (!stringList.contains(value.toString())) {
                            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                            setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY, 5), new EmptyBorder(5, 5, 5, 5)));
                            if (cellValue.equals("")) {
                                for (LV lv : dozent.getLV()) {
                                    long lvScheduled = lv.getScheduledLV();
                                    lvScheduled = lvScheduled >> checkSum;
                                    if (lvScheduled % 2 == 1) {
                                        String lvName = lv.getName();
                                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                                        tableModel.setValueAt(lvName, row, column);
                                        table.revalidate();
                                        table.repaint();
                                        cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                                        setBorder(new CompoundBorder(new LineBorder(Color.BLUE, 5), new EmptyBorder(5, 5, 5, 5)));
                                        break;
                                    }
                                }
                            }
                        }

                    } else if (zug != null) {

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

                        } else {
//                            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//                            tableModel.setValueAt("", row, column);
//                            table.revalidate();
//                            table.repaint();
                        }
                    } else if (lvSelected != null) {
                        String cellInput;
                        int i = 0;
                        for (LV lvS : lvSelected.getDozentLV().getLV()) {
                            long lvScheduled = lvS.getScheduledLV();
                            lvScheduled = lvScheduled >> checkSum;
                            if (lvScheduled % 2 == 1) {
                                //cellInput = "<html>" + zugLVDozent.getName() + ": " + "<br/>" + lvZugLVDozent.getName()+ "<html>";
                                cellInput = lvS.getName();
                                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                                tableModel.setValueAt(cellInput, row, column);
                                table.revalidate();
                                table.repaint();
                                i++;
                            } else {
                                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                                tableModel.setValueAt("", row, column);
                                table.revalidate();
                                table.repaint();
                            }
                        }
                        for (Dozent dozent : dozentList) {
                            if (dozent.getName().equals(label)) {
                                for (LV lvDozent : dozent.getLV()) {
                                    for (Zug zugLVDozent : lvSelected.getZugList()) {
                                        for (LV lvZugLVDozent : zugLVDozent.getLV()) {
                                            long lvScheduled = lvZugLVDozent.getScheduledLV();
                                            lvScheduled = lvScheduled >> checkSum;
                                            if (lvScheduled % 2 == 1) {
                                                //cellInput = "<html>" + zugLVDozent.getName() + ": " + "<br/>" + lvZugLVDozent.getName()+ "<html>";
                                                cellInput = zugLVDozent.getName() + ": " + lvZugLVDozent.getName();
                                                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                                                tableModel.setValueAt(cellInput, row, column);
                                                table.revalidate();
                                                table.repaint();
                                                i++;
                                            } else {
//                                                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//                                                tableModel.setValueAt("", row, column);
//                                                table.revalidate();
//                                                table.repaint();
                                            }
                                        }
                                    }
                                }
                            }

                        }

                        for (Zug zug : zugList) {
                            if (zug.getName().equals(label)) {
                                for (Zug zugsSelected : lvSelected.getZugList()) {
                                    for (LV lvZugsSelected : zugsSelected.getLV()) {
                                        long lvScheduled = lvZugsSelected.getScheduledLV();
                                        lvScheduled = lvScheduled >> checkSum;
                                        if (lvScheduled % 2 == 1) {
                                            cellInput = zugsSelected.getName() + ": " + lvZugsSelected.getName();
                                            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                                            tableModel.setValueAt(cellInput, row, column);
                                            table.revalidate();
                                            table.repaint();
                                            i++;
                                        } else {
//                                            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//                                            tableModel.setValueAt("", row, column);
//                                            table.revalidate();
//                                            table.repaint();
                                        }
                                    }
                                }
                            }

//                            int i = 0;
//                            for (LV lvDozent : dozent.getLV()) {
//                                for (Zug zugLVDozent : lvDozent.getZugList()) {
//                                    for (LV lvZugLVDozent : zugLVDozent.getLV()) {
//                                        long lvScheduled = lvZugLVDozent.getScheduledLV();
//                                        lvScheduled = lvScheduled >> checkSum;
//                                        if (lvScheduled % 2 == 1 && i == 0) {
//
//                                            table.revalidate();
//                                            table.repaint();
//                                            i++;
//                                        }
//                                    }
//                                }
//                            }
                            if (i == 0) {
                                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                                tableModel.setValueAt("", row, column);
                                table.revalidate();
                                table.repaint();
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
        }else {setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("Arial", Font.BOLD, 16));
                // Setzen Sie die Standardhintergrundfarbe für andere Zellen
                cellComponent.setBackground((Color.LIGHT_GRAY));
        }

        if (column == 0) {
            
        }

        return cellComponent;
    }
}