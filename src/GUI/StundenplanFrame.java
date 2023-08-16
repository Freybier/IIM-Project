/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import iim.Handtuch.ReadHandtuch2;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yann Leymann
 */
public class StundenplanFrame extends javax.swing.JFrame {
    
    public int columnNr = 10;
    private Object[] columnNames;
    /**
     * Creates new form StundenplanFrame
     */
    public StundenplanFrame() {
        initComponents();
        build();
        buildJTable();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ProfName = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jScrollKurse = new javax.swing.JScrollPane();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
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

        ProfName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------" }));
        ProfName.setToolTipText("");
        ProfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(ProfName, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addGap(112, 112, 112))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ProfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(197, 197, 197))
        );

        jTabbedPane1.addTab("Handtuch", jPanel1);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        javax.swing.GroupLayout jInfoFeldLayout = new javax.swing.GroupLayout(jInfoFeld);
        jInfoFeld.setLayout(jInfoFeldLayout);
        jInfoFeldLayout.setHorizontalGroup(
            jInfoFeldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInfoFeldLayout.setVerticalGroup(
            jInfoFeldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jComboDoZug.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSuchfeldKurse, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollKurse, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jComboDoZug, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioDozent)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioZug)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)))
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSuchfeldKurse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollKurse)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonSpeichern, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonZurücksetzen, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                    .addComponent(jKonfliktFeld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jInfoFeld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Name", jPanel5);

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

    private void ProfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfNameActionPerformed
        // TODO add your handling code here:
        if(ProfName.getSelectedItem() != null){
            try{
                if (ProfName.getSelectedItem().toString().equalsIgnoreCase("---------")){
                    sorter.setRowFilter(null);
                }else{
                    String filter = ProfName.getSelectedItem().toString();
                    filterUpdate(filter, columnNr);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            sorter.setRowFilter(null);
        }
        
    }//GEN-LAST:event_ProfNameActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        String chosentitle = jComboBox1.getSelectedItem().toString();
        Set<String> words = null;
        ProfName.removeAllItems();

        for(int i = 0; i<columnNames.length; i++){
            if(chosentitle.equals(this.columnNames[i].toString())){
                columnNr = i;
                words = ReadHandtuch2.getter(i);
                break;
            }
        }
        if (words != null){
        updateComboBox(words, ProfName);
        filterUpdate(ProfName.getSelectedItem().toString(), columnNr);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButtonSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSpeichernActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSpeichernActionPerformed

    private void jComboDoZugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboDoZugActionPerformed
        // TODO add your handling code here:
        if(jComboDoZug.getSelectedItem() != null){
            int tabIndex = jTabbedPane1.getSelectedIndex();
            String jLabelText = jComboDoZug.getSelectedItem().toString();
            
            jLabelName.setText(jLabelText);
            // if-construction for not changing the Handtuch-Title
            if(tabIndex != 0){
                jTabbedPane1.setTitleAt(tabIndex, jLabelText);
            }
        }
    }//GEN-LAST:event_jComboDoZugActionPerformed

    private void jRadioDozentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDozentActionPerformed
        // TODO add your handling code here:
        Set words = ReadHandtuch2.getter(9);
        jComboDoZug.removeAllItems();
        updateComboBox(words, jComboDoZug);
    }//GEN-LAST:event_jRadioDozentActionPerformed

    private void jRadioZugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioZugActionPerformed
        // TODO add your handling code here:
        Set words = ReadHandtuch2.getter(0);
        jComboDoZug.removeAllItems();
        updateComboBox(words, jComboDoZug);
    }//GEN-LAST:event_jRadioZugActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        JPanel panel = new JPanel();
        
        jTabbedPane1.add(panel);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void filterUpdate(String word, int index){
        // Filter the table based on a specific column
        if (ProfName.getSelectedItem().toString().equalsIgnoreCase("---------") || index == 10 || ProfName.getSelectedItem().toString().equalsIgnoreCase(jComboBox1.getSelectedItem().toString())){
            sorter.setRowFilter(null);
        }else{
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
        jRadioDozent.doClick();
    }
    
    public void buildJTable(){
        Object[][] data = ReadHandtuch2.getObjectTable();
        this.columnNames = data[0];
        Object[][] content = new Object[data.length-1][];
        
        for (int i = 1; i < data.length; i++) {
            content[i - 1] = data[i];
        }
        
        DefaultTableModel model = new DefaultTableModel(content, columnNames);
        jTable1.setModel(model);
        
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        columnNameFilterBox(columnNames);
    }
    
    public void updateComboBox(Set words, JComboBox<String> comboBox){
        comboBox.addItem("---------");
        for(Object word : words){
            comboBox.addItem((String)word);
        }
        sort(comboBox);
    }
    
    public void columnNameFilterBox(Object[] columnames){
        for(Object title : columnames){
            jComboBox1.addItem((String)title);
        }
        sort(jComboBox1);
    }
    
    private void sort(JComboBox<String> comboBox) {
        Object[] o = new Object[comboBox.getItemCount()];
        for(int i=0; i<o.length; i++) {
            o[i] = comboBox.getItemAt(i);
        }
        Arrays.sort(o);
        for(int i=0; i<o.length; i++) {
            comboBox.removeItemAt(i);
            comboBox.insertItemAt((String) o[i], i);
        }
        comboBox.setSelectedIndex(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ProfName;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonSpeichern;
    private javax.swing.JButton jButtonZurücksetzen;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboDoZug;
    private javax.swing.JPanel jInfoFeld;
    private javax.swing.JPanel jKonfliktFeld;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioDozent;
    private javax.swing.JRadioButton jRadioZug;
    private javax.swing.JScrollPane jScrollKurse;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jSuchfeldKurse;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
