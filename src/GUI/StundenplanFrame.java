/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import iim.Handtuch.Leading;
import iim.Hochschule.Dozent;
import iim.Hochschule.Zug;
import iim.Hochschule.LV;
import java.awt.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public List<Leading> leadingList;
    //public JTable jTable;
    public TableTransferHandler tableTransferHandler;

    public MyTableCellRenderer tableCellRenderer;
    public CustomListCellRenderer listCellRenderer;
    private boolean isAddingTab = false; // Füge diese Variable der Klasse hinzu

    /**
     * Creates new form StundenplanFrame
     */
    public StundenplanFrame(List<Dozent> dozentenList, List<Zug> zugList, List<LV> lvList, List<Leading> leadingList) {
        oArray = new CSVToObjectArrayConverter("src/iim/Handtuch/HandtuchOutputUpdate.csv");
        this.dozentenList = dozentenList;
        this.zugList = zugList;
        this.lvList = lvList;
        this.leadingList = leadingList;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
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
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Exportieren");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Speichern");
        jMenu2.add(jMenuItem1);

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

    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {

            int index = jTabbedPane1.getSelectedIndex();

            if (index != 0) {
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem delete = new JMenuItem("Delete");
                delete.addActionListener(new ActionListener() {

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

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        JTabbedPane sourceTabbedPane = (JTabbedPane) evt.getSource();
        int selectedTabIndex = sourceTabbedPane.getSelectedIndex();
        Component selectedTab = sourceTabbedPane.getComponentAt(selectedTabIndex);
        if (!isAddingTab && selectedTabIndex != -1) {
            if (selectedTab == jLabel1) { // Ändern Sie dies auf die tatsächliche Komponente der "+"-Registerkarte
                isAddingTab = true; // Setze die Flag auf true, um die erneute Ausführung zu verhindern
                addNewTab();
                isAddingTab = false; // Setze die Flag wieder auf false
            }
        } else if (isAddingTab && selectedTab == jLabel1 ) {
            int previousTabIndex = selectedTabIndex - 1;
            sourceTabbedPane.setSelectedIndex(previousTabIndex);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void SubFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubFilterActionPerformed
        // TODO add your handling code here:
        if (SubFilter.getSelectedItem() != null) {
            try {
                if (SubFilter.getSelectedItem().toString().equalsIgnoreCase("---------")) {
                    sorter.setRowFilter(null);
                } else {
                    String filter = SubFilter.getSelectedItem().toString();
                    filterUpdate(filter, columnNr);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            sorter.setRowFilter(null);
        }
    }//GEN-LAST:event_SubFilterActionPerformed

    private void jColumnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jColumnFilterActionPerformed

        String chosentitle = jColumnFilter.getSelectedItem().toString();
        Set<String> words = null;
        SubFilter.removeAllItems();

        for (int i = 0; i < columnNames.length; i++) {
            if (chosentitle.equals(this.columnNames[i].toString())) {
                columnNr = oArray.getColumnIndex(chosentitle);
                words = oArray.getter(columnNr);
                break;
            }
        }
        if (words != null) {
            updateHandtuchComboBox(words, SubFilter);
            filterUpdate(SubFilter.getSelectedItem().toString(), columnNr);
        }
    }//GEN-LAST:event_jColumnFilterActionPerformed

    private void addNewTab() {
        JPanel tabContent = new MusterPanel(dozentenList, zugList, lvList, leadingList, jTabbedPane1); // Erhalte den Inhalt der Test-Klasse
        //JPanel tabContent = new DesignTestPanel(dozentenList, zugList, lvList, leadingList, jTabbedPane1); // Erhalte den Inhalt der Test-Klasse
        String tabTitle = "Tab " + (jTabbedPane1.getTabCount()); // Titel für die neue Registerkarte
        int position = jTabbedPane1.getTabCount() - 1; // Position für das neue Tab
        System.out.println("Neuer Tab");
        jTabbedPane1.insertTab(tabTitle, null, tabContent, null, position);
        jTabbedPane1.setSelectedComponent(tabContent);
    }

    public void filterUpdate(String word, int index) {
        // Filter the table based on a specific column
        if (SubFilter.getSelectedItem().toString().equalsIgnoreCase("---------") || SubFilter.getSelectedItem().toString().equalsIgnoreCase(jColumnFilter.getSelectedItem().toString())) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(word, index));
        }

    }

    public void build() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    public void buildJTable() {

        Object[][] data = oArray.getData();
        this.columnNames = data[0];
        Object[][] content = new Object[data.length - 1][];

        for (int i = 1; i < data.length; i++) {
            content[i - 1] = data[i];
        }

        DefaultTableModel model = new DefaultTableModel(content, columnNames);
        jTable1.setModel(model);

        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        columnNameFilterBox(columnNames);
    }

    public void updateHandtuchComboBox(Set words, JComboBox<String> comboBox) {
        comboBox.addItem("---------");
        for (Object word : words) {
            comboBox.addItem((String) word);
        }
    }

    public void columnNameFilterBox(Object[] columnames) {
        Arrays.sort(columnames);
        for (Object title : columnames) {
            jColumnFilter.addItem((String) title);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> SubFilter;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jColumnFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
