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
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.LIGHT_GRAY;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CustomListCellRenderer extends DefaultListCellRenderer {

    private final Map<Integer, Color> backgroundColors = new HashMap<>();

    private java.util.List<LV> lvJLVList;
    private LV lv;

    public CustomListCellRenderer(List<LV> lvJLVList, LV lv) {
        this.lvJLVList = lvJLVList;
        this.lv = lv;
    }

    public void setBackgroundColor(int index, Color color) {
        backgroundColors.put(index, color);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        String lvRenderer = (String) value;

        for (LV lvList : lvJLVList) {
            // Überprüfe, ob eine benutzerdefinierte Hintergrundfarbe für diesen Eintrag festgelegt wurde
            if (lvList.getSWSBlocksTook() == lvList.getSWSBlocks() && lvList.getName().equals(lvRenderer)) {
                //System.out.println(lvList.getName() + " !!!!!!!!!!!!!!!!!!!!!!!!!!!! " + lvRenderer + "&&&&&" + index);
                renderer.setBackground(LIGHT_GRAY);
                return renderer; // Hier beenden, nachdem die Farbe festgelegt wurde
            }
        }

        // Wenn die Bedingung für das aktuelle Element nicht zutrifft, setze den Hintergrund auf die Standardfarbe
        renderer.setBackground(list.getBackground());

        //setBorder(new EmptyBorder(5, 10, 5, 10)); // optional: Füge einen Innenabstand hinzu
        return renderer;
    }
}
