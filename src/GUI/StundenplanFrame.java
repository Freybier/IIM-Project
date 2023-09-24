/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import iim.Hochschule.Dozent;
import iim.Hochschule.Zug;
import iim.Hochschule.LV;
import java.awt.Component;
import java.awt.Container;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 * @author Feizi, Frey, Leymann
 */
public class StundenplanFrame extends javax.swing.JFrame {

    public int columnNr = 10;
    public Object[] columnNames;
    public CSVToObjectArrayConverter oArray;
    public JPanel layoutpanel;
    public List<Dozent> dozentenList;
    public List<Zug> zugList;
    public List<LV> lvList;
   
    public TableTransferHandler tableTransferHandler;

    public TableCellRenderer tableCellRenderer;
    public CustomListCellRenderer listCellRenderer;
    private boolean isAddingTab = false;

    /**
     * Creates new form StundenplanFrame
     */
    public StundenplanFrame(List<Dozent> dozentenList, List<Zug> zugList, List<LV> lvList, String handtuchCSVFilePath) {
        // oArray is the Object Array created from the Handtuch csv
        String absolutPath = new File("").getAbsolutePath();
        oArray = new CSVToObjectArrayConverter(absolutPath+"\\src\\iim\\Handtuch\\HandtuchNeu.csv");
        this.dozentenList = dozentenList;
        this.zugList = zugList;
        this.lvList = lvList;


        build();
        initComponents();
        buildJTable();
        addNewTab();
        setVisible(true);

    }

    public TableRowSorter<TableModel> sorter;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jColumnFilter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SubFilter = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabbedPane1MousePressed(evt);
            }
        });

        jColumnFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------" }));
        jColumnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jColumnFilterActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name vom Prof", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Name vom Prof");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Montag");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Dienstag");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Mittwoch");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("Donnerstag");
            jTable1.getColumnModel().getColumn(5).setHeaderValue("Freitag");
            jTable1.getColumnModel().getColumn(6).setHeaderValue("Samstag");
        }

        SubFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------" }));
        SubFilter.setToolTipText("");
        SubFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jColumnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jColumnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SubFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(532, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Handtuch", jPanel1);

        jLabel1.setText("jLabel1");
        jTabbedPane1.addTab("+", jLabel1);

        jMenu1.setText("File");

        jMenuItem3.setText("Importieren");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Exportieren");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Zurücksetzten");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    }//GEN-LAST:event_formMouseClicked
    // Action-listener for deleting tabs on right-click if they are not "Handtuch"
    // the getY <= 20 is for only accepting right-clicks on the "tab" itself 
    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MousePressed
        if (SwingUtilities.isRightMouseButton(evt) && evt.getY() <= 20) {

            int index = jTabbedPane1.getSelectedIndex();
            Object clickedElement = jTabbedPane1.getSelectedComponent();

            if (index != 0 && clickedElement instanceof MusterPanel) {
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem delete = new JMenuItem("Delete");
                delete.addActionListener(new ActionListener() {
                    // Action-listener for click on "Delete" in popupmenu
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        isAddingTab = true;
                        jTabbedPane1.remove(index);
                        isAddingTab = false;
                    }
                });
                popupMenu.add(delete);
                popupMenu.show(this, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_jTabbedPane1MousePressed
    // Action-listener for creating a new tab
    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        JTabbedPane sourceTabbedPane = (JTabbedPane) evt.getSource();
        int selectedTabIndex = sourceTabbedPane.getSelectedIndex();
        Component selectedTab = sourceTabbedPane.getComponentAt(selectedTabIndex);
        if (!isAddingTab && selectedTabIndex != -1) {
            if (selectedTab == jLabel1) { // Check if selected tab is the "+" tab and add new tab
                isAddingTab = true;
                addNewTab();
                isAddingTab = false;
            }
        } else if (isAddingTab && selectedTab == jLabel1 ) {
            // when the most right tab is deleted, the tab in focus is the left to the deleted one, preventing the jLabel1 being shown
            int previousTabIndex = selectedTabIndex - 1;
            sourceTabbedPane.setSelectedIndex(previousTabIndex);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged
    
    
    // action-listener for choosing filter parameter in the second JComboBox in Handtuch 
    private void SubFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubFilterActionPerformed
        if (SubFilter.getSelectedItem() != null) {
            try {
                // "------" is the first String in the list and is equal to no search parameter
                if (SubFilter.getSelectedItem().toString().equalsIgnoreCase("---------")) {
                    // sets RowFilter to null to prevent error and set back the Table to "normal"
                    sorter.setRowFilter(null);
                } else {
                    // calling for filterUpdate method when a parameter is chosen, columnNr is needed to know which column to check the parameter for
                    String filter = SubFilter.getSelectedItem().toString();
                    filterUpdate(filter, columnNr);
                }
            } catch (Exception e) {
                
            }
        } else {
            sorter.setRowFilter(null);
        }
    }//GEN-LAST:event_SubFilterActionPerformed

    // action-listener for first JComboBox, choosing for which column to filter by, exact filter parameter gets chosen in subFilter-ComboBox
    private void jColumnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jColumnFilterActionPerformed

        String chosentitle = jColumnFilter.getSelectedItem().toString();
        // creating a Set to prevent duplicates in list
        Set<String> words = null;
        
        // clear previous filter-parameters from comboBox
        SubFilter.removeAllItems();

        for (Object columnName : columnNames) {
            if (chosentitle.equals(columnName.toString())) {
                columnNr = oArray.getColumnIndex(chosentitle);
                // puts all entries in the oArray in the given column into the set
                words = oArray.getter(columnNr);
                break;
            }
        }
        // if the set is not empty, the subfilter-ComboBox is updated with the set
        if (words != null) {
            updateSubFilterComboBox(words, SubFilter);
            filterUpdate(SubFilter.getSelectedItem().toString(), columnNr);
        }
    }//GEN-LAST:event_jColumnFilterActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        ImportFrame importF = new ImportFrame(zugList);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ExportFrame expoF = new ExportFrame(zugList);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    
    // method for creating new tab using the MusterPanel.java
    private void addNewTab() {
        JPanel tabContent = new MusterPanel(dozentenList, zugList, lvList, jTabbedPane1); // Erhalte den Inhalt der Test-Klasse

        // titel for the new tab
        String tabTitle = "Tab " + (jTabbedPane1.getTabCount());
        // position for the new tab
        int position = jTabbedPane1.getTabCount() - 1;
        // creating new tab and change current tab to new created tab

        jTabbedPane1.insertTab(tabTitle, null, tabContent, null, position);
        jTabbedPane1.setSelectedComponent(tabContent);
    }
    
    
    public void filterUpdate(String word, int index) {
        // filter the table based on a specific column
        String selectedFilter = SubFilter.getSelectedItem().toString();
        // checks if a correct parameter is chosen ("--------" is the standart first "empty"-parameter) 
        if (selectedFilter.equalsIgnoreCase("---------") || selectedFilter.equalsIgnoreCase(jColumnFilter.getSelectedItem().toString())) {
            sorter.setRowFilter(null);
        } else {
            // filters for selected query in the selected column(index == columnNr)
            sorter.setRowFilter(RowFilter.regexFilter(word, index));
        }

    }
    
    // basic method to create the style of the window
    public final void build() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

                // possible look switch when entered e.g. "Nimbus" instead of "Metal"

                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StundenplanFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StundenplanFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StundenplanFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StundenplanFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }
    
    // method to fill the table on Handtuch-tab
    public final void buildJTable() {

        Object[][] data = oArray.getData();
        this.columnNames = data[0];
        Object[][] content = new Object[data.length - 1][];
        
        // for loop to stop the columnNames from printing inside the Tabel 
        for (int i = 1; i < data.length; i++) {
            content[i - 1] = data[i];
        }

        DefaultTableModel model = new DefaultTableModel(content, columnNames);
        jTable1.setModel(model);

        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        columnNameFilterBox(columnNames);
    }
    
    // method for filling subFilter ComboBox
    public void updateSubFilterComboBox(Set words, JComboBox<String> comboBox) {
        comboBox.addItem("---------");
        for (Object word : words) {
            comboBox.addItem((String) word);
        }
    }
    
    // method for filling columnName-ComboBox with given clumnNames from handtuch-csv
    public void columnNameFilterBox(Object[] columnames) {
        Arrays.sort(columnames);
        for (Object title : columnames) {
            jColumnFilter.addItem((String) title);
        }
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> SubFilter;
    private javax.swing.JComboBox<String> jColumnFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
