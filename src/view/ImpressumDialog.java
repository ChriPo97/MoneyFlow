/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Languagemanager;
import Controller.Propertymanager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ChriPo97 
 * Klasse zur Erstellung eines Dialog Fensters mit Impressum als Inhalt
 */
public class ImpressumDialog extends JDialog {

    private JPanel dialogPanel;
    private BoxLayout dialogLayout;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JButton changeButton;

    public ImpressumDialog() {
        initComponents();
        dialogPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.setTitle(Languagemanager.getProperty("ImpressumDialog.titel"));
        this.setMinimumSize(new Dimension(300, 200));
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private void initComponents() {
        dialogPanel = new JPanel();
        dialogLayout = new BoxLayout(dialogPanel, BoxLayout.Y_AXIS);
        dialogPanel.setLayout(dialogLayout);
        textArea = new JTextArea(Propertymanager.getProperty("Impressum").replaceAll("\"", ""));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setSize(new Dimension(280, 100));
        scrollPane = new JScrollPane(textArea);
        changeButton = new JButton(Languagemanager.getProperty("ImpressumDialog.changeButton"));
        changeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Propertymanager.setProperty("MoneyFlow.Impressum", "\"" + textArea.getText() + "\"");
                Propertymanager.schreibenProperties();
                disposeDialog();
                JOptionPane.showMessageDialog(null, Languagemanager.getProperty("ImpressumDialog.message"));
            }
        });
        dialogPanel.add(scrollPane);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        dialogPanel.add(changeButton);
        this.add(dialogPanel);
    }
    
    public void disposeDialog() {
        this.dispose();
    }

}
