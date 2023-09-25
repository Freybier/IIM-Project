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
import java.awt.*;
import static java.awt.Color.LIGHT_GRAY;
import java.util.HashMap;
import java.util.Map;
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
    
    //Sets th numbers of SWS left next to the LV and if there are 0 left turns the elemnt from the List gray
    
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

                    
                    if (lvZug.getSWSBlocksTook() == lvZug.getSWSBlocks()) {
                        renderer.setBackground(LIGHT_GRAY);
                        return renderer; 
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

                    
                    if (lvDozent.getSWSBlocksTook() == lvDozent.getSWSBlocks()) {
                        renderer.setBackground(LIGHT_GRAY);
                        return renderer; 
                    }

                }
            }
        }

        //If the condtions do not fit, the list element has the default color 
        renderer.setBackground(list.getBackground());

        
        return renderer;
    }

}