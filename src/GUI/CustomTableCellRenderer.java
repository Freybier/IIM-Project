/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Yann Leymann
 */
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer 
{
    public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        System.out.println("JA Moin");
        if( value instanceof Object )
        {
            //Integer amount = (Integer) value;
            if( value.equals("-1")  )
            {
                cell.setBackground( Color.red );
                // You can also customize the Font and Foreground this way
                // cell.setForeground();
                // cell.setFont();
                // Wenn Ihre Zelle ein JTextField enthält, können Sie die Hintergrundfarbe ändern
                if (cell instanceof JTextField) {
                    JTextField textField = (JTextField) cell;
                    textField.setBackground(Color.red);
                }
            }
            else
            {
                cell.setBackground( Color.white );
                
                // Wenn Ihre Zelle ein JTextField enthält, können Sie die Hintergrundfarbe ändern
                if (cell instanceof JTextField) {
                    JTextField textField = (JTextField) cell;
                    textField.setBackground(Color.white);
                }
            }
        }
        return cell;
    }
}
