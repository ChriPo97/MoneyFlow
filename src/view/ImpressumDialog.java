/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Propertymanager;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author cs15cp1
 */
public class ImpressumDialog extends JDialog {

    private JPanel dialogPanel;
    private BoxLayout dialogLayout;
    private JTextArea textArea;
    private JButton changeButton;

    public ImpressumDialog() {
        initComponents();
        dialogPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.setMinimumSize(new Dimension(300, 200));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private void initComponents() {
        dialogPanel = new JPanel();
        dialogLayout = new BoxLayout(dialogPanel, BoxLayout.Y_AXIS);
        dialogPanel.setLayout(dialogLayout);
        textArea = new JTextArea(Propertymanager.getProperty("MoneyFlow.Impressum").replaceAll("\"", ""));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMinimumSize(new Dimension(280, 100));
        textArea.setMaximumSize(new Dimension(280, 100));
        changeButton = new JButton("Impressum ändern");
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Propertymanager.setProperty("MoneyFlow.Impressum", "\"" + textArea.getText() + "\"");
                disposeDialog();
                JOptionPane.showMessageDialog(null, "Impressum geändert");
            }
        });
        dialogPanel.add(textArea);
        dialogPanel.add(changeButton);
        this.add(dialogPanel);
    }
    
    public void disposeDialog() {
        this.dispose();
    }

}
