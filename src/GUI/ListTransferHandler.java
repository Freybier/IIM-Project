/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ListTransferHandler extends TransferHandler {
    private JList<String> jList;

    public ListTransferHandler(JList<String> jList) {
        this.jList = jList;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Object selectedValue = jList.getSelectedValue();

        if (selectedValue != null) {
            String selectedString = selectedValue.toString();
            return new StringSelection(selectedString);
        } else {
            return null;
        }
    }
}

