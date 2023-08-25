/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class TableTransferHandler extends TransferHandler {
    private JTable jTable;

    public TableTransferHandler(JTable jTable) {
        this.jTable = jTable;
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
            jTable.setValueAt(data, row, col);
            return true;
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
