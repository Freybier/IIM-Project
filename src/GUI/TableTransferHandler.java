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
    private JList jLVList;
    
    private List<LV> lvJLVList;

    private Object obj;
    
    public TableTransferHandler(JTable jTable, List<LV> lvList, List<Dozent> dozentenList, JList jLVList) {
        this.jTable = jTable;
        this.lvList = lvList;
        this.dozentenList = dozentenList;
        this.jLVList = jLVList;
        
    }
    public void setLVJLVList(List<LV> lvJLVList){
        
        this.lvJLVList = lvJLVList;
    } 

    public void setDozentenName(String dozentenName) {
        this.dozentenName = dozentenName;
    }
    
    public void setListCellRenderer(CustomListCellRenderer listCellRenderer){
    }
    public void setObject(Object obj) {
        this.obj = obj;
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
        
        //For the Drag and Drop function
        //To prevent errrors or conflicts it is not possible to drop a LV on cell, where a relevant LV is already placed
        JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
        int row = dl.getRow();
        int col = dl.getColumn();

        if (col == 0 || (col == 6 && row == 4) || (col == 6 && row == 5)) {
            return false;
        }
        Transferable transferable = support.getTransferable();
       try {
            String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            String splitter[] = data.split(" ");
            data = splitter[0];
            
            
            for (LV lv : lvList) {
                if (lv.getName().equals(data) && lv.getDozentName().equals(dozentenName)) {
                    if (lv.getSWSBlocks() > lv.getSWSBlocksTook()) {
                        
                        long scheduled = 1;
                        int check = 33 - ((col * 6) - 6) - row;
                        scheduled = scheduled << check;
                        long dozentScheduled = scheduled;
                        
                        
                        for(Dozent dozentcheck: dozentenList ){
                            if (lv.getDozentName().equals(dozentcheck.getName())){
                                for(LV lvCheck: dozentcheck.getLV()){
                                    if((lvCheck.getScheduledLV() >> check) % 2 == 1){
                                        return false;
                                    }
                                    
                                }
                            }
                        }
                        
                        for(Zug zugLV : lv.getZugList()){
                            for(LV lvZug : zugLV.getLV()){
                                if((lvZug.getScheduledLV()>> check) % 2 == 1){
                                        return false;
                                    }
                            }
                        }
                        
                        lv.addOneSWSBlocksTook();
                        scheduled = scheduled | lv.getScheduledLV();
                        lv.setScheduledLV(scheduled);

                        for (Dozent doz : dozentenList) {
                            if (lv.getDozentName().equals(doz.getName())) {
                                dozentScheduled = dozentScheduled | doz.getScheduledDozent();
                                doz.setScheduledDozent(dozentScheduled);
                                
                            }
                        }
                        
                        jLVList.setCellRenderer(new CustomListCellRenderer( obj));
                        
                        jTable.setValueAt(data, row, col);
                        jLVList.revalidate();
                        jLVList.repaint();
                    } else {
                        return false;

                    }
                }
            }

            return true;
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
