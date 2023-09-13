/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import iim.Hochschule.Leading;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import iim.Hochschule.Dozent;
import iim.Hochschule.LV;
import iim.Hochschule.Zug;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Frey
 */
public class DesignTestPanel extends javax.swing.JPanel {
    
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
    public Boolean radioButtonZugBoolean;
    public Boolean radioButtonDozentBoolean;
    public MyTableCellRenderer tableCellRenderer;
    public CustomListCellRenderer listCellRenderer;
    public JTabbedPane jTabbedPane1;
    
    /**
     * Creates new form DesignTestPanel
     */
    public DesignTestPanel(List<Dozent> dozentenList, List<Zug> zugList, List<LV> lvList, List<Leading> leadingList, JTabbedPane jTabbedPane) {
        oArray = new CSVToObjectArrayConverter("src/iim/Handtuch/HandtuchOutputUpdate.csv");
        this.dozentenList = dozentenList;
        this.zugList = zugList;
        this.lvList = lvList;
        this.leadingList = leadingList;
        this.jTabbedPane1 = jTabbedPane;
        
        initComponents();
        timeToJTable();
        addSelectionListenerJList();
        
        jLVList.setDragEnabled(true);
        jLVList.setTransferHandler(new ListTransferHandler(jLVList));
        this.tableTransferHandler = new TableTransferHandler(jTable, lvList, dozentenList, zugList, jLVList);
        jTable.setTransferHandler(tableTransferHandler);
    }
    
    private void timeToJTable() {
        List<String> textList = Arrays.asList("8:00", "10:00", "12:00", "14:00", "16:00", "18:00");

        for (int i = 0; i < textList.size(); i++) {
            jTable.setValueAt(textList.get(i), i, 0); // Fügen Sie den Text in die erste Spalte ein
        }
    }
    
    public void addSelectionListenerJList() {

        jLVList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    // Überprüfen Sie, ob ein Element ausgewählt wurde
                    if (!jLVList.isSelectionEmpty()) {

                        // Deklarieren Sie selectedLV hier außerhalb des if-Blocks
                        int selectedIndex = jLVList.getSelectedIndex();
                        LV selectedLV = null;
                        // jComboDoZug, the Name is missleading. It ist the Name for the dropdown menu next to the Radiobuttons
                        if (radioButtonZugBoolean || radioButtonDozentBoolean) {


                            int tabIndex = jTabbedPane1.getSelectedIndex();
                            String jLabelText = jComboDoZug.getSelectedItem().toString();

                            //Sets the Name in the top left corner of the Panel
                            jLabelName.setText(jLabelText);
                            // if-construction for not changing the Handtuch-Title
                            if (tabIndex != 0) {

                                jTabbedPane1.setTitleAt(tabIndex, jLabelText);
                            }

                            // Direkter Zugriff auf das ausgewählte Dozenten- oder Zug-Objekt
                            //The Radiobuttons decides wich variable radioButtonZugBoolean or radioButtonDozentBoolean is true respectively false
                            //
                            if (radioButtonZugBoolean && !radioButtonDozentBoolean) {

                                Zug selectedZug = getObjectFromName(jComboDoZug.getSelectedItem().toString(), zugList);
                                selectedLV = selectedZug.getLV().get(selectedIndex); // LV-Objekt aus den Zügen auswählen
                                tableTransferHandler.setDozentenName(selectedLV.getDozentName());
                            } else if (!radioButtonZugBoolean && radioButtonDozentBoolean) {

                                Dozent selectedDozent = getObjectFromName(jComboDoZug.getSelectedItem().toString(), dozentenList);
                                selectedLV = selectedDozent.getLV().get(selectedIndex);

                            }
                        } else {
                            for (Zug zug : zugList) {
                                if (zug.getName().equals(jLabelName.getText())) {
                                    selectedLV = zug.getLV().get(selectedIndex);
                                    tableTransferHandler.setDozentenName(selectedLV.getDozentName());
                                }
                            }
                            for (Dozent dozent : dozentenList) {
                                if (dozent.getName().equals(jLabelName.getText())) {
                                    selectedLV = dozent.getLV().get(selectedIndex);
                                }
                            }
                        }

                        // Hier überprüfen, ob selectedLV nicht null ist
                        if (selectedLV != null) {
                            // Annahme: Sie haben ein JLabel namens lblFullName, um den vollständigen Namen anzuzeigen
                            
                            updateInfoPanel(selectedLV);
                            jTable.revalidate();
                            jTable.repaint();

                            for (Dozent dozent : dozentenList) {
                                if (selectedLV.getDozentName().equals(dozent.getName())) {
                                    tableCellRenderer = new MyTableCellRenderer(dozent, dozentenList, dozent.getLV());
                                    //tableCellRenderer.setZug(null);
                                    for (int i = 1; i < 7; i++) {
                                        jTable.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);
                                    }
                                    //setLVforJTable(dozent);
                                    //§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§
                                } else if (selectedLV.getDozentName().equals("-") || selectedLV.getDozentName().equals("_")) {
                                    DefaultTableCellRenderer();
                                }

                            }

                            getZugLVandDozentLVforSelectedLVinTable(selectedLV);
                            // Hier können Sie weitere Informationen anzeigen oder spezifische Aktionen ausführen
                        }
                    }
                }
            }

        });
    }
    
    public void getZugLVandDozentLVforSelectedLVinTable(LV selectedLV) {
        // When a LV is chosen (selectedLV) in the jLVList, it checks wich Zug also has to take selectedLV.
        // When checked it sets in jTable all LVs from all Zugs to display where it is NOT possible to place the selectedLV.
        // When done it checks wich Dozent is giving the selectedLV and displays(partly by overriting) all the LVs from this Dozent.

        emptyJTable();
        jTable.revalidate();
        jTable.repaint();

        //All LVs from all Zugs wich also has to take selectedLV
        getZugLVforSelectedLV(selectedLV);
        //All LVs from the Dozent giving the selectedLV
        getDozentLVforSelectedLV(selectedLV);
    }
    
     //All LVs from all Zugs wich also has to take selectedLV
    public void getZugLVforSelectedLV(LV selectedLV) {
        for (Zug zug : selectedLV.getZugList()) {
            for (LV lvZug : zug.getLV()) {

                if (lvZug.getScheduledLV() != 0) {

                    long lvScheduled = lvZug.getScheduledLV();
                    for (int i = 39; i > 5; i--) {

                        if (lvScheduled % 2 == 1) {

                            int row = i % 6;
                            int column = i / 6;
                            String cellContent = "";
                            cellContent = (String) jTable.getValueAt(row, column);
                            cellContent += "   " + zug.getName() + ": " + lvZug.getName() + "   ";

                            jTable.setValueAt(cellContent, row, column);
                            ((AbstractTableModel) jTable.getModel()).fireTableCellUpdated(row, column);
                        }
                        lvScheduled = lvScheduled >> 1;
                    }
                }
            }
        }
    }

    //All LVs from the Dozent giving the selectedLV
    public void getDozentLVforSelectedLV(LV selectedLV) {
        for (Dozent dozent : dozentenList) {

            if (selectedLV.getDozentName().equals(dozent.getName())) {

                for (LV lvDozent : dozent.getLV()) {
                    long lvScheduled = lvDozent.getScheduledLV();
                    for (int i = 39; i > 5; i--) {

                        if (lvScheduled % 2 == 1) {

                            int row = i % 6;
                            int column = i / 6;
                            String cellContent = "";

                            cellContent += lvDozent.getName();

                            jTable.setValueAt(cellContent, row, column);
                            ((AbstractTableModel) jTable.getModel()).fireTableCellUpdated(row, column);
                        }
                        lvScheduled = lvScheduled >> 1;
                    }
                }
            }
        }
    }
    
    public void emptyJTable() {

        for (int row = 0; row < 6; row++) {
            for (int col = 1; col < 7; col++) {
                jTable.setValueAt("", row, col); // Setzen Sie den Wert auf einen leeren String
            }
        }

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
    
    public void DefaultTableCellRenderer() {
        DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();

        // Setze den Renderer für alle Spalten und Zeilen
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(defaultRenderer);
        }
    }
    
    private DefaultListModel setLVZugList(Zug zug) {

        emptyJTable();

        DefaultListModel<Object> listModel = new DefaultListModel<>();
        
        if (!jSuchfeldDoZug.getText().equals("Suche")) {
            zug = getObjectFromName(jLabelName.getText(), zugList);

        }
        if (zug != null) {
            tableTransferHandler.setObject(zug);
            tableTransferHandler.setLVJLVList(zug.getLV());

            
            for (LV lvElement : zug.getLV()) {
                //listModel.addElement(lvElement.getName());
                setLVforJTable(lvElement);
                jLVList.setCellRenderer(new CustomListCellRenderer(zug.getLV(), lvElement, zug));
                // jLVList.setCellRenderer(new CustomListCellRenderer(zug.getLV(), lvElement));

                //tableCellRenderer.setZug(zug);
                int swsUebrig = lvElement.getSWSBlocks() - lvElement.getSWSBlocksTook();
                listModel.addElement(lvElement + " " + swsUebrig);

            }

        }
        return listModel;
    }
    
    public void setLVforJTable(LV lv) {

        //System.out.println(dozentLV);
        //System.out.println(dozentLV.getScheduledLV());
        if (lv.getScheduledLV() != 0) {
            //System.out.println("get Scheduled LV!");
            long lvScheduled = lv.getScheduledLV();
            for (int i = 39; i > 5; i--) {
                if (lvScheduled % 2 == 1) {
                    int row = i % 6;
                    int column = i / 6;
                    String cellContent = "";

                    cellContent += lv.getName();

                    jTable.setValueAt(cellContent, row, column);
                    ((AbstractTableModel) jTable.getModel()).fireTableCellUpdated(row, column);

                }
                lvScheduled = lvScheduled >> 1;

            }
        }

    }
    
    private DefaultListModel setLVDozentList(Dozent dozent) {

        emptyJTable();

        DefaultListModel<Object> listModel = new DefaultListModel<>();
        
        if (!jSuchfeldDoZug.getText().equals("Suche")) {
            dozent = getObjectFromName(jLabelName.getText(), dozentenList);

        }
        if (dozent != null) {

            tableTransferHandler.setObject(dozent);
            jLVList.updateUI();

            // LV-Objekt aus den Dozenten auswählen
            //updateTableCells(jTable);
            this.tableCellRenderer = new MyTableCellRenderer(dozent, dozentenList, dozent.getLV());
            //tableCellRenderer.setZug(null);
            tableTransferHandler.setDozentenName(dozent.getName());
            tableTransferHandler.setLVJLVList(dozent.getLV());

            
            for (LV dozentLV : dozent.getLV()) {
                setLVforJTable(dozentLV);
                jLVList.setCellRenderer(new CustomListCellRenderer(dozent.getLV(), dozentLV, dozent));
            }

            // Setzen Sie den Renderer für die gewünschte Spalte (in diesem Fall Spalte 0)
            for (int i = 1; i < 7; i++) {
                jTable.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);
            }
            for (LV lvElement : dozent.getLV()) {

                int swsUebrig = lvElement.getSWSBlocks() - lvElement.getSWSBlocksTook();
                listModel.addElement(lvElement + " " + swsUebrig);

                //listModel.addElement(lvElement.getName());
                //jLVList.setCellRenderer(new CustomListCellRenderer(dozent.getLV(), lvElement));
                //System.out.println(lvElement.getName() + "\t" + lvElement.getDozentName());
            }
        }
        return listModel;
    }
    
    public void updateDozentComboBox(List<Dozent> dozentenListe, JComboBox<Object> comboBox) {
        comboBox.addItem("---------");
        Collections.sort(dozentenListe, (Dozent dozent1, Dozent dozent2) -> dozent1.getName().compareTo(dozent2.getName()));

        for (Dozent dozent : dozentenListe) {
            comboBox.addItem(dozent);
        }
    }

    public void updateZugComboBox(List<Zug> zugListe, JComboBox<Object> comboBox) {
        comboBox.addItem("---------");
        Collections.sort(zugListe, (Zug zug1, Zug zug2) -> zug1.getName().compareTo(zug2.getName()));

        for (Zug zug : zugListe) {
            comboBox.addItem(zug);
        }
    }
    
    public void findMatchingObjects(String input) {

        DefaultListModel<Object> suchListModel = new DefaultListModel<>();
        try {
            for (Dozent dozentObj : dozentenList) {
                if (dozentObj.getName().toLowerCase().contains(input.toLowerCase())) {
                    suchListModel.addElement(dozentObj);
                }
            }

            for (Zug zugObj : zugList) {
                if (zugObj.getName().toLowerCase().contains(input.toLowerCase())) {
                    suchListModel.addElement(zugObj);
                }
            }

            for (LV lvObj : lvList) {
                if (lvObj.getName().toLowerCase().contains(input.toLowerCase())) {
                    suchListModel.addElement(lvObj);
                }
            }
            sucheList.setModel(suchListModel);
        } catch (java.lang.NullPointerException ex) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane4 = new javax.swing.JScrollPane();
        sucheList = new javax.swing.JList<>();
        jButtonZurücksetzen = new javax.swing.JButton();
        jRadioDozent = new javax.swing.JRadioButton();
        jRadioZug = new javax.swing.JRadioButton();
        jKonfliktFeld = new javax.swing.JPanel();
        jInfoFeld = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jComboDoZug = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jSuchfeldDoZug = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLVList = new javax.swing.JList<>();
        jLabelName = new javax.swing.JLabel();
        jButtonSpeichern = new javax.swing.JButton();

        sucheList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sucheListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(sucheList);

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
        jTable.setRowHeight(53);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable);

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

        jSuchfeldDoZug.setText("Suche");
        jSuchfeldDoZug.setToolTipText("SucheDozent/Zug");
        jSuchfeldDoZug.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSuchfeldDoZugFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSuchfeldDoZugFocusLost(evt);
            }
        });
        jSuchfeldDoZug.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jSuchfeldDoZugKeyReleased(evt);
            }
        });

        jLVList.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(jLVList);

        jLabelName.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabelName.setText("Name:");

        jButtonSpeichern.setText("Speichern");
        jButtonSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSpeichernActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSuchfeldDoZug, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboDoZug, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioDozent)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioZug)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSpeichern, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jButtonZurücksetzen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addComponent(jInfoFeld, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jKonfliktFeld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioZug)
                        .addComponent(jRadioDozent)
                        .addComponent(jComboDoZug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSuchfeldDoZug, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSpeichern, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonZurücksetzen, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                    .addComponent(jKonfliktFeld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jInfoFeld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sucheListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sucheListMouseClicked
        // TODO add your handling code here:

        jComboDoZug.removeAllItems();

        radioButtonZugBoolean = false;
        radioButtonDozentBoolean = false;
        buttonGroup1.clearSelection();

        jRadioDozent.repaint();
        jRadioDozent.revalidate();

        jRadioZug.repaint();
        jRadioZug.revalidate();

        //DefaultListModel<LV> ListModel = new DefaultListModel<LV>();

        Object selectedObject = sucheList.getSelectedValue();

        //DefaultListModel<Object> listModel = new DefaultListModel<>();
        if (selectedObject instanceof LV || selectedObject instanceof Zug || selectedObject instanceof Dozent) {

            // System.out.println(" set Label. ");
            if (selectedObject instanceof Zug) {

                jLabelName.setText(selectedObject.toString());
                jLVList.setModel(setLVZugList((Zug)selectedObject));

            } else if (selectedObject instanceof Dozent) {
                //DefaultListModel<LV> ListModel = new DefaultListModel<LV>();

                jLabelName.setText(selectedObject.toString());
                jLVList.setModel(setLVDozentList((Dozent)selectedObject));

            } else if (selectedObject instanceof LV) {
            updateInfoPanel((LV) selectedObject);
            // when directly selecting an LV in the List, open the list of the leading Zug and select the previous chosen LV      
            String jLabelText = ((LV)selectedObject).getLeadingZugName();
            
            jLabelName.setText(jLabelText);
            // if-construction for not changing the Handtuch-Title
            
            // set content of jLvList
            jLVList.setModel(setLVZugList( ((LV) selectedObject).getLeadingZug()));
            
            for (int i = 0; i <= jLVList.getLastVisibleIndex(); i++) {
                String itemName = jLVList.getModel().getElementAt(i).toString();

                if (itemName.contains(selectedObject.toString())) {
                    
                    jLVList.setSelectedIndex(i);
                    break;
                }
            }
        }

        }
        jLVList.revalidate();
        jLVList.repaint();
    }//GEN-LAST:event_sucheListMouseClicked

    private void jRadioDozentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDozentActionPerformed
        // TODO add your handling code here:
        this.radioButtonZugBoolean = false;
        this.radioButtonDozentBoolean = true;

        jComboDoZug.removeAllItems();
        updateDozentComboBox(dozentenList, jComboDoZug);

        DefaultTableCellRenderer();
    }//GEN-LAST:event_jRadioDozentActionPerformed

    private void jRadioZugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioZugActionPerformed
        // TODO add your handling code here:
        this.radioButtonDozentBoolean = false;
        this.radioButtonZugBoolean = true;

        jComboDoZug.removeAllItems();
        updateZugComboBox(this.zugList, jComboDoZug);
        jTable.revalidate();
        jTable.repaint();
        DefaultTableCellRenderer();
    }//GEN-LAST:event_jRadioZugActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 1) {
            int row = jTable.rowAtPoint(evt.getPoint());
            int col = jTable.columnAtPoint(evt.getPoint());

            // Überprüfen, ob der Klick innerhalb einer gültigen Zelle war
            if (row >= 0 && col >= 1) {
                // Hier können Sie Ihre Bearbeitungslogik implementieren
                //Object cellValue = jTable.getValueAt(row, col);
                
                if (jTable.getValueAt(row, col) != null) {
                    Object cellValue = jTable.getValueAt(row, col);
                    //System.out.println(cellValue.getClass());
                    String check = jLabelName.getText();
                    for (Dozent dozentTable : dozentenList) {
                        if (dozentTable.getName().equals(check)) {
                            for (LV lvDozentTable : dozentTable.getLV()) {
                                if (lvDozentTable.getName().equals(cellValue)) {
                                    int checkSum = 33 - ((col) * 6) + (6 - row);
                                    long delete = 1;
                                    delete = delete << checkSum;
                                    lvDozentTable.setScheduledLV(lvDozentTable.getScheduledLV() ^ delete);
                                    dozentTable.setScheduledDozent(dozentTable.getScheduledDozent() ^ delete);
                                    lvDozentTable.substractOneSWSBlocksTook();
                                    tableCellRenderer = new MyTableCellRenderer(dozentTable, dozentenList, dozentTable.getLV());
                                    //tableCellRenderer.setZug(null);
                                    jTable.setValueAt("", row, col);
                                    jLVList.setCellRenderer(new CustomListCellRenderer(dozentTable.getLV(), lvDozentTable, dozentTable));
                                    jTable.revalidate();
                                    jTable.repaint();
                                }
                            }
                            
                        }
                    }
                    for (Zug zugTable : zugList) {
                        if (zugTable.getName().equals(check)) {
                            for (LV lvZugTable : zugTable.getLV()) {
                                if (lvZugTable.getName().equals(cellValue)) {
                                    int checkSum = 33 - ((col) * 6) + (6 - row);
                                    long delete = 1;
                                    delete = delete << checkSum;
                                    lvZugTable.setScheduledLV(lvZugTable.getScheduledLV() ^ delete);
                                    String dozentLVName = lvZugTable.getDozentName();
                                    for (Dozent dozentLV : dozentenList) {
                                        if (dozentLV.getName().equals(dozentLVName)) {
                                            dozentLV.setScheduledDozent(dozentLV.getScheduledDozent() ^ delete);
                                            lvZugTable.substractOneSWSBlocksTook();
                                            tableCellRenderer = new MyTableCellRenderer(dozentLV, dozentenList, zugTable.getLV());
                                            //tableCellRenderer.setZug(zugTable);
                                            jTable.setValueAt("", row, col);
                                            jLVList.setCellRenderer(new CustomListCellRenderer(zugTable.getLV(), lvZugTable, zugTable));
                                            jTable.revalidate();
                                            jTable.repaint();
                                        }
                                    }

                                }
                            }
                            
                        }
                    }
                }
                // Öffnen Sie ein Eingabefeld oder ein Dialogfeld zur Bearbeitung des Zellwerts
                // Aktualisieren Sie Ihre Datenstruktur und die Tabelle nach der Bearbeitung
            }
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void jComboDoZugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboDoZugActionPerformed
        // TODO add your handling code here:
        if (jComboDoZug.getSelectedItem() != null) {
            DefaultTableCellRenderer();
            int tabIndex = jTabbedPane1.getSelectedIndex();
            String jLabelText = jComboDoZug.getSelectedItem().toString();
            jLabelName.setText(jLabelText);
            // if-construction for not changing the Handtuch-Title
            if (tabIndex != 0) {
                jTabbedPane1.setTitleAt(tabIndex, jLabelText);
            }

            if (radioButtonZugBoolean && !radioButtonDozentBoolean) {
                Zug zug = getObjectFromName(jComboDoZug.getSelectedItem().toString(), zugList);
                jLVList.setModel(setLVZugList(zug));
                jLVList.revalidate();
                jLVList.repaint();

            } else if (!radioButtonZugBoolean && radioButtonDozentBoolean) {
                Dozent dozent = getObjectFromName(jComboDoZug.getSelectedItem().toString(), dozentenList);
                jLVList.setModel(setLVDozentList(dozent));
                jLVList.revalidate();
                jLVList.repaint();
            }

        }
    }//GEN-LAST:event_jComboDoZugActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        /*JPanel panel = new JPanel();

        jTabbedPane1.add(panel);
        JTable tableCopy = new JTable(jTable.getModel());
        panel.add(tableCopy);

        jTabbedPane1.revalidate();
        jTabbedPane1.repaint();*/
        //setLVZugList();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSuchfeldDoZugFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSuchfeldDoZugFocusGained
        // TODO add your handling code here:
        if (jSuchfeldDoZug.getText().equals("Suche")) {
            jSuchfeldDoZug.setText("");
        }
    }//GEN-LAST:event_jSuchfeldDoZugFocusGained

    private void jSuchfeldDoZugFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSuchfeldDoZugFocusLost
        // TODO add your handling code here:
        if (jSuchfeldDoZug.getText().isEmpty()) {
            jSuchfeldDoZug.setText("Suche");
        }
    }//GEN-LAST:event_jSuchfeldDoZugFocusLost

    private void jSuchfeldDoZugKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSuchfeldDoZugKeyReleased
        // TODO add your handling code here:
        String entry = this.jSuchfeldDoZug.getText();
        
        findMatchingObjects(entry);
    }//GEN-LAST:event_jSuchfeldDoZugKeyReleased

    private void jButtonSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSpeichernActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSpeichernActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonSpeichern;
    private javax.swing.JButton jButtonZurücksetzen;
    private javax.swing.JComboBox<Object> jComboDoZug;
    private javax.swing.JPanel jInfoFeld;
    private javax.swing.JPanel jKonfliktFeld;
    private javax.swing.JList<Object> jLVList;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JRadioButton jRadioDozent;
    private javax.swing.JRadioButton jRadioZug;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jSuchfeldDoZug;
    private javax.swing.JTable jTable;
    private javax.swing.JList<Object> sucheList;
    // End of variables declaration//GEN-END:variables
}
