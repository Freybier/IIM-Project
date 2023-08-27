/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import iim.Hochschule.Dozent;
import iim.Hochschule.LV;
import iim.Hochschule.Zug;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.List;

public class TableTransferHandler extends TransferHandler {

    private JTable jTable;
    private List<LV> lvList;
    private String dozentenName;
    private List<Dozent> dozentenList;
    private List<Zug> zugList;
    
    public TableTransferHandler(JTable jTable, List<LV> lvList, List<Dozent> dozentenList, List<Zug> zugList) {
        this.jTable = jTable;
        this.lvList = lvList;
        this.dozentenList = dozentenList;
        this.zugList = zugList;
    }
    
    public void setDozentenName(String dozentenName){
        this.dozentenName = dozentenName;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(DataFlavor.stringFlavor)
                && support.getComponent() instanceof JTable;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
        int row = dl.getRow();
        int col = dl.getColumn();

        Transferable transferable = support.getTransferable();
        try {
            String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            //DataFlavor customDataFlavor = new DataFlavor(LV.class, "Custom LV Object");
            //LV lvObjekt = (LV) transferable.getTransferData(customDataFlavor);
            System.out.println(data);
            for (LV lv : lvList) {
                if (lv.getName().equals(data) && lv.getDozentName().equals(dozentenName)) {
                    long scheduled = 1;
                    int check = 33 - ((col * 6) - 6) - row;
                    scheduled = scheduled << check;
                    scheduled = scheduled | lv.getScheduledLV();
                    lv.setScheduledLV(scheduled);
                    System.out.println(lv);
                    System.out.println(lv.getScheduledLV());
                    System.out.println("LVScheduled wurde geseted");
                    
                }
            }

            jTable.setValueAt(data, row, col);
            return true;
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
