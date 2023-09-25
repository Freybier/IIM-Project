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
import GUI.StundenplanFrame;
import iim.Hochschule.Zug;

public class CustomListCellRenderer extends DefaultListCellRenderer {

    private Object obj = null;
    private final Map<Integer, Color> backgroundColors = new HashMap<>();


    public CustomListCellRenderer( Object obj) {
        this.obj = obj;
    }

    

    public Object getObject() {
        return obj;
    }

    public void setBackgroundColor(int index, Color color) {
        backgroundColors.put(index, color);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        String lvRenderer = (String) value;

        String[] lvUndZahl = lvRenderer.split(" ");
        if (obj instanceof Zug) {
            for (LV lvZug : ((Zug) obj).getLV()) {

                if (lvZug.getName().equals(lvUndZahl[0])) {
                    lvUndZahl[1] = Integer.toString(lvZug.getSWSBlocks() - lvZug.getSWSBlocksTook());
                    lvRenderer = lvUndZahl[0] + "  " + lvUndZahl[1];

                    DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
                    listModel.setElementAt(lvRenderer, index);
                    list.revalidate();
                    list.repaint();

                    // Überprüfe, ob eine benutzerdefinierte Hintergrundfarbe für diesen Eintrag festgelegt wurde
                    if (lvZug.getSWSBlocksTook() == lvZug.getSWSBlocks()) {
                        renderer.setBackground(LIGHT_GRAY);
                        return renderer; // Hier beenden, nachdem die Farbe festgelegt wurde
                    }

                }
            }
        } else if (obj instanceof Dozent){
            for (LV lvDozent : ((Dozent) obj).getLV()) {

                if (lvDozent.getName().equals(lvUndZahl[0])) {
                    lvUndZahl[1] = Integer.toString(lvDozent.getSWSBlocks() - lvDozent.getSWSBlocksTook());
                    lvRenderer = lvUndZahl[0] + "  " + lvUndZahl[1];

                    DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
                    listModel.setElementAt(lvRenderer, index);
                    list.revalidate();
                    list.repaint();

                    // Überprüfe, ob eine benutzerdefinierte Hintergrundfarbe für diesen Eintrag festgelegt wurde
                    if (lvDozent.getSWSBlocksTook() == lvDozent.getSWSBlocks()) {
                        renderer.setBackground(LIGHT_GRAY);
                        return renderer; // Hier beenden, nachdem die Farbe festgelegt wurde
                    }

                }
            }
        }
        // Wenn die Bedingung für das aktuelle Element nicht zutrifft, setze den Hintergrund auf die Standardfarbe
        renderer.setBackground(list.getBackground());

        //setBorder(new EmptyBorder(5, 10, 5, 10)); // optional: Füge einen Innenabstand hinzu
        return renderer;
    }

}