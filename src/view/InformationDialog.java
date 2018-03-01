/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Propertymanager;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ChriPo97 
 * Klasse zur Erstellung eines Dialog Fensters mit Informationen als Inhalt
 */
public class InformationDialog extends JDialog {
    
    private JPanel dialogPanel;
    private BoxLayout dialogLayout;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    
    //Informationstext
    private String text = "MoneyFlow - Kassensystem\n\n"
            + "GNU General Public License v3.0\n\n"
            + "https://github.com/ChriPo97/MoneyFlow";
    
    public InformationDialog() {
        initComponents();
        this.setTitle("Information");
        this.setMinimumSize(new Dimension(350, 200));
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }
    
    private void initComponents() {
        dialogPanel = new JPanel();
        dialogPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        dialogLayout = new BoxLayout(dialogPanel, BoxLayout.Y_AXIS);
        dialogPanel.setLayout(dialogLayout);
        textArea = new JTextArea(Propertymanager.getProperty("Impressum").replaceAll("\"", ""));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setSize(new Dimension(280, 100));
        textArea.setEditable(false);
        textArea.setText(text);
        scrollPane = new JScrollPane(textArea);
        dialogPanel.add(scrollPane);
        this.add(dialogPanel);
    }
    
}
