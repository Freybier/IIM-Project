/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Yann Leymann
 */
public class TestYann {

    public void frame() {
        JFrame window = new JFrame("Test Frame");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);

        tabbedPane.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {

                    int index = tabbedPane.getSelectedIndex();

                    if (index != 0) {
                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem delete = new JMenuItem("Delete");
                        delete.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                tabbedPane.remove(index);
                            }
                        });
                        popupMenu.add(delete);
                        popupMenu.show(window, e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        JButton addButton = new JButton("+");
        addButton.setBorder(null);
        addButton.setFocusPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setPreferredSize(new Dimension(15, 15));
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                UIManager.put("OptionPane.okButtonTex", "OK");
                UIManager.put("OptionPane.cancelButtonTex", "CANCEL");

                String tabName = JOptionPane.showInputDialog(null, "Enter Tab Name", "NEW TAB", JOptionPane.INFORMATION_MESSAGE);

                if (tabName != null) {
                    JLabel tabTitelLabel = new JLabel(tabName);
                    JTextArea textArea = new JTextArea();

                    tabbedPane.addTab(tabName, textArea);
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabTitelLabel);
                }

            }

        });

        tabbedPane.addTab("", null);

        tabbedPane.setTabComponentAt(0, addButton);

        window.add(tabbedPane);
        //window.setVisible(true);
    }

}
