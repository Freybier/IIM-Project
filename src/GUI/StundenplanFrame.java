/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.CSVToObjectArrayConverter;
import iim.Hochschule.Dozent;
import iim.Hochschule.Zug;
import iim.Hochschule.LV;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Yann Leymann
 */
public class StundenplanFrame extends javax.swing.JFrame {

    public int columnNr = 10;
    private Object[] columnNames;
    public CSVToObjectArrayConverter oArray;
    public JPanel layoutpanel;
    public List<Dozent> dozentenList;
    public List<Zug> zugList;
    //public JTable jTable;
    public TableTransferHandler tableTransferHandler;
    private Boolean lvLististZug;
    private Boolean lvLististDozent;

    /**
     * Creates new form StundenplanFrame
     */
    public StundenplanFrame(List<Dozent> dozentenList, List<Zug> zugList, List<LV> lvList) {
        oArray = new CSVToObjectArrayConverter("src/iim/Handtuch/HandtuchOutputUpdate.csv");
        this.dozentenList = dozentenList;
        this.zugList = zugList;
        initComponents();
        build();
        buildJTable();
        setVisible(true);
        timeToJTable();

        jLVList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    // Überprüfen Sie, ob ein Element ausgewählt wurde
                    if (!jLVList.isSelectionEmpty()) {

                        // Deklarieren Sie selectedLV hier außerhalb des if-Blocks
                        int selectedIndex = jLVList.getSelectedIndex();
                        LV selectedLV = null;

                        if (jComboDoZug.getSelectedItem() != null) {

                            int tabIndex = jTabbedPane1.getSelectedIndex();
                            String jLabelText = jComboDoZug.getSelectedItem().toString();
                            jLabelName.setText(jLabelText);
                            // if-construction for not changing the Handtuch-Title
                            if (tabIndex != 0) {

                                jTabbedPane1.setTitleAt(tabIndex, jLabelText);
                            }

                            // Direkter Zugriff auf das ausgewählte Dozenten- oder Zug-Objekt
                            if (lvLististZug && !lvLististDozent) {

                                Zug selectedZug = getObjectFromName(jComboDoZug.getSelectedItem().toString(), zugList);
                                selectedLV = selectedZug.getLV().get(selectedIndex); // LV-Objekt aus den Zügen auswählen
                            } else {

                                Dozent selectedDozent = getObjectFromName(jComboDoZug.getSelectedItem().toString(), dozentenList);
                                selectedLV = selectedDozent.getLV().get(selectedIndex);

                            }
                        }

                        // Hier überprüfen, ob selectedLV nicht null ist
                        if (selectedLV != null) {
                            // Annahme: Sie haben ein JLabel namens lblFullName, um den vollständigen Namen anzuzeigen
                            updateInfoPanel(selectedLV);

                            for (Dozent dozent : dozentenList) {
                                if (selectedLV.getDozentName().equals(dozent.getName())) {
                                    MyTableCellRenderer cellRenderer = new MyTableCellRenderer(dozent);
                                    for (int i = 1; i < 7; i++) {
                                        jTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
                                    }
                                }
                            }

                            // Hier können Sie weitere Informationen anzeigen oder spezifische Aktionen ausführen
                        }
                    }
                }
            }
        });

        jLVList.setDragEnabled(true);
        jLVList.setTransferHandler(new ListTransferHandler(jLVList));
        this.tableTransferHandler = new TableTransferHandler(jTable, lvList, dozentenList, zugList);
        jTable.setTransferHandler(tableTransferHandler);

        // Erstellen Sie eine Instanz des MyTableCellRenderer
        //jScrollPane3.setViewportView(jTable);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jColumnFilter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SubFilter = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jSuchfeldKurse = new javax.swing.JTextField();
        jLabelName = new javax.swing.JLabel();
        jButtonSpeichern = new javax.swing.JButton();
        jButtonZurücksetzen = new javax.swing.JButton();
        jRadioDozent = new javax.swing.JRadioButton();
        jRadioZug = new javax.swing.JRadioButton();
        jKonfliktFeld = new javax.swing.JPanel();
        jInfoFeld = new javax.swing.JPanel();
        jComboDoZug = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLVList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jColumnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SubFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(415, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Handtuch", jPanel1);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Time", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setRowHeight(46);
        jScrollPane3.setViewportView(jTable);

        jSuchfeldKurse.setText("Suche");

        jLabelName.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabelName.setText("Name:");

        jButtonSpeichern.setText("Speichern");
        jButtonSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSpeichernActionPerformed(evt);
            }
        });

        jButtonZurücksetzen.setText("Zurücksetzten");

        buttonGroup1.add(jRadioDozent);
        jRadioDozent.setText("Dozent*in");
        jRadioDozent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDozentActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioZug);
        jRadioZug.setText("Zug");
        jRadioZug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioZugActionPerformed(evt);
            }
        });

        jKonfliktFeld.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jKonfliktFeldLayout = new javax.swing.GroupLayout(jKonfliktFeld);
        jKonfliktFeld.setLayout(jKonfliktFeldLayout);
        jKonfliktFeldLayout.setHorizontalGroup(
            jKonfliktFeldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jKonfliktFeldLayout.setVerticalGroup(
            jKonfliktFeldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jInfoFeld.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInfoFeld.setLayout(new java.awt.BorderLayout());

        jComboDoZug.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bitte wähle Dozent oder Zug" }));
        jComboDoZug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboDoZugActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jLVList);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSuchfeldKurse, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(jComboDoZug, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioDozent)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioZug)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(0, 131, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane3))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSpeichern, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jButtonZurücksetzen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jInfoFeld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jKonfliktFeld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioZug)
                            .addComponent(jRadioDozent)
                            .addComponent(jComboDoZug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelName)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSuchfeldKurse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonSpeichern, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonZurücksetzen, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                    .addComponent(jKonfliktFeld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jInfoFeld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Name", jPanel5);

        jLabel1.setText("jLabel1");
        jTabbedPane1.addTab("+", jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private DefaultListModel setLVDozentList() {

        DefaultListModel<String> listModel = new DefaultListModel<>();
        Dozent dozent = getObjectFromName(jComboDoZug.getSelectedItem().toString(), dozentenList);
        if (dozent != null) {
            
            
            for (int row = 0; row < 6; row++) {
                for (int col = 1 ; col < 7; col++) {
                    jTable.setValueAt("", row, col); // Setzen Sie den Wert auf einen leeren String
                }
            }
            // LV-Objekt aus den Dozenten auswählen
            //updateTableCells(jTable);
            MyTableCellRenderer cellRenderer = new MyTableCellRenderer(dozent);
            tableTransferHandler.setDozentenName(dozent.getName());
            
            for(LV dozentLV : dozent.getLV()){
                System.out.println(dozentLV);
                System.out.println(dozentLV.getScheduledLV());
                if(dozentLV.getScheduledLV() != 0){
                   System.out.println("get Scheduled LV!");
                   long lvScheduled = dozentLV.getScheduledLV();
                   for(int i = 39; i > 5; i--){
                       if(lvScheduled%2 == 1){
                            int row = i%6;
                            int column = i/6;
                            
                            jTable.setValueAt(dozentLV.getName(), row, column);
                            ((AbstractTableModel) jTable.getModel()).fireTableCellUpdated(row, column);

                       }
                       lvScheduled = lvScheduled >> 1;
                       
                   }
                }
            }
            
            // Setzen Sie den Renderer für die gewünschte Spalte (in diesem Fall Spalte 0)
            for (int i = 1; i < 7; i++) {
                jTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            }
            for (LV lvElement : dozent.getLV()) {
                listModel.addElement(lvElement.getName());
                //System.out.println(lvElement.getName() + "\t" + lvElement.getDozentName());
            }
        }
        return listModel;
    }

    private DefaultListModel setLVZugtList() {

        DefaultListModel<String> listModel = new DefaultListModel<>();
        Zug zug = getObjectFromName(jComboDoZug.getSelectedItem().toString(), zugList);
        if (zug != null) {
            for (LV lvElement : zug.getLV()) {
                listModel.addElement(lvElement.getName());
            }
        }
        return listModel;
    }

    private <T> T getObjectFromName(String name, List<T> objectList) {
        T foundObject = null;
        for (T object : objectList) {
            if (object instanceof Dozent && ((Dozent) object).getName().equals(name)) {
                foundObject = object;
                break;
            } else if (object instanceof Zug && ((Zug) object).getName().equals(name)) {
                foundObject = object;
                break;
            }
        }
        return foundObject;
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    }//GEN-LAST:event_formMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        /*addNewStundenplanTab();
        System.out.println("Hallo");*/
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        /*JPanel panel = new JPanel();

        jTabbedPane1.add(panel);
        JTable tableCopy = new JTable(jTable.getModel());
        panel.add(tableCopy);

        jTabbedPane1.revalidate();
        jTabbedPane1.repaint();*/
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jComboDoZugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboDoZugActionPerformed
        // TODO add your handling code here:
        if (jComboDoZug.getSelectedItem() != null) {
            int tabIndex = jTabbedPane1.getSelectedIndex();
            String jLabelText = jComboDoZug.getSelectedItem().toString();
            jLabelName.setText(jLabelText);
            // if-construction for not changing the Handtuch-Title
            if (tabIndex != 0) {
                jTabbedPane1.setTitleAt(tabIndex, jLabelText);
            }
            if (lvLististZug && !lvLististDozent) {
                jLVList.setModel(setLVZugtList());
            } else {
                jLVList.setModel(setLVDozentList());
            }

        }
    }//GEN-LAST:event_jComboDoZugActionPerformed

    private void jRadioZugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioZugActionPerformed
        // TODO add your handling code here:
        this.lvLististDozent = false;
        this.lvLististZug = true;

        jComboDoZug.removeAllItems();
        updateZugComboBox(this.zugList, jComboDoZug);
    }//GEN-LAST:event_jRadioZugActionPerformed

    private void jRadioDozentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDozentActionPerformed
        // TODO add your handling code here:
        this.lvLististZug = false;
        this.lvLististDozent = true;

        jComboDoZug.removeAllItems();
        updateDozentComboBox(dozentenList, jComboDoZug);
    }//GEN-LAST:event_jRadioDozentActionPerformed

    private void jButtonSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSpeichernActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSpeichernActionPerformed

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

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        JTabbedPane sourceTabbedPane = (JTabbedPane) evt.getSource();
        int selectedTabIndex = sourceTabbedPane.getSelectedIndex();

        if (selectedTabIndex != -1) {
            Component selectedTab = sourceTabbedPane.getComponentAt(selectedTabIndex);
            if (selectedTab == jLabel1) { // Ändern Sie dies auf die tatsächliche Komponente der "+"-Registerkarte
                addNewTab();
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void addNewTab() {
        JPanel tabContent = (JPanel) new Test().getContentPane(); // Erhalte den Inhalt der Test-Klasse
        String tabTitle = "Tab " + (jTabbedPane1.getTabCount() + 1); // Titel für die neue Registerkarte
        jTabbedPane1.addTab(tabTitle, tabContent);
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StundenplanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StundenplanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StundenplanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StundenplanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

    public void updateDozentComboBox(List<Dozent> dozentenListe, JComboBox<String> comboBox) {
        comboBox.addItem("---------");
        Collections.sort(dozentenListe, (Dozent dozent1, Dozent dozent2) -> dozent1.getName().compareTo(dozent2.getName()));

        for (Dozent dozent : dozentenListe) {
            comboBox.addItem(dozent.getName());
        }
    }

    public void updateZugComboBox(List<Zug> zugListe, JComboBox<String> comboBox) {
        comboBox.addItem("---------");
        Collections.sort(zugListe, (Zug zug1, Zug zug2) -> zug1.getName().compareTo(zug2.getName()));

        for (Zug zug : zugListe) {
            comboBox.addItem(zug.getName());
        }
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

    private void updateInfoPanel(LV selectedLV) {

        // Löschen Sie alle Komponenten aus dem jInfoFeld-Panel
        jInfoFeld.removeAll();

        // Fügen Sie die Informationen zur Lehrveranstaltung hinzu
        JLabel nameLabel = new JLabel("   Name: " + selectedLV.getFullName());
        JLabel dozentenLabel = new JLabel("   Dozent: " + selectedLV.getDozentName());
        JLabel zugNameLabel = new JLabel("   ZugList: " + selectedLV.getZugNameList());
        // Hier können Sie weitere Informationen hinzufügen, je nach Bedarf

        // Fügen Sie die Labels oder andere Komponenten zum jInfoFeld-Panel hinzu
        jInfoFeld.add(nameLabel, BorderLayout.NORTH);
        jInfoFeld.add(zugNameLabel, BorderLayout.CENTER);
        jInfoFeld.add(dozentenLabel, BorderLayout.SOUTH);
        // Fügen Sie weitere Komponenten hinzu

        // Aktualisieren Sie das jInfoFeld-Panel
        jInfoFeld.revalidate();
        jInfoFeld.repaint();
    }

    private void timeToJTable() {
        List<String> textList = Arrays.asList("8:00", "10:00", "12:00", "14:00", "16:00", "18:00");

        for (int i = 0; i < textList.size(); i++) {
            jTable.setValueAt(textList.get(i), i, 0); // Fügen Sie den Text in die erste Spalte ein
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> SubFilter;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonSpeichern;
    private javax.swing.JButton jButtonZurücksetzen;
    private javax.swing.JComboBox<String> jColumnFilter;
    private javax.swing.JComboBox<String> jComboDoZug;
    private javax.swing.JPanel jInfoFeld;
    private javax.swing.JPanel jKonfliktFeld;
    private javax.swing.JList<String> jLVList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioDozent;
    private javax.swing.JRadioButton jRadioZug;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jSuchfeldKurse;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
