/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Christoph
 */
public class Artikelinformation extends JPanel {
    
    JLabel artikelinformationLabel = new JLabel("Artikelinformation");
    
    JPanel artikelPanel = new JPanel();
    JLabel kategorieLabel = new JLabel("Kategorie:");
    JLabel produktLabel = new JLabel("Produkt:");
    JLabel produktNummerLabel = new JLabel("Produktnummer:");
    JLabel einzelpreisLabel = new JLabel("Einzelpreis:");
    JLabel mengeLabel = new JLabel("Menge:");
    JLabel summeLabel = new JLabel("Summe:");
    JTextField kategorieField = new JTextField();
    
    JPanel panelButtons = new JPanel();
    JButton stornoButton = new JButton("Storno");
    JButton produktnummerButton = new JButton("Produktnummer eingeben");
    JButton rabattButton = new JButton("Rabatt");
    JButton verkaufAbbrechenButton = new JButton("Verkauf abbrechen");
    
    public Artikelinformation() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
        
        artikelPanel.setLayout(new GridLayout(6, 2));
        artikelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        artikelPanel.add(kategorieLabel);
        artikelPanel.add(kategorieField);
        artikelPanel.add(produktLabel);
        artikelPanel.add(produktNummerLabel);
        artikelPanel.add(einzelpreisLabel);
        artikelPanel.add(mengeLabel);
        artikelPanel.add(summeLabel);
        
        
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
        stornoButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, stornoButton.getMinimumSize().height * 2));
        panelButtons.add(stornoButton);
        panelButtons.add(Box.createRigidArea(new Dimension(0, stornoButton.getMinimumSize().height * 2)));
        produktnummerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, produktnummerButton.getMinimumSize().height * 2));
        panelButtons.add(produktnummerButton);
        rabattButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, rabattButton.getMinimumSize().height * 2));
        panelButtons.add(rabattButton);
        panelButtons.add(Box.createRigidArea(new Dimension(0, stornoButton.getMinimumSize().height * 2)));
        verkaufAbbrechenButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, verkaufAbbrechenButton.getMinimumSize().height * 4));
        panelButtons.add(verkaufAbbrechenButton);
        
        this.add(artikelinformationLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(artikelPanel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(panelButtons);
    }
    
}
