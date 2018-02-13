/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Artikel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Christoph
 */
public class Artikelinformation extends JPanel {

    private JPanel artikelPanel = new JPanel();
    private JLabel kategorieLabel = new JLabel("Kategorie:");
    private JLabel produktLabel = new JLabel("Produkt:");
    private JLabel produktNummerLabel = new JLabel("Produktnummer:");
    private JLabel einzelpreisLabel = new JLabel("Einzelpreis:");
    private JLabel mengeLabel = new JLabel("Menge:");
    private JLabel summeLabel = new JLabel("Summe:");
    private static JTextField kategorieField = new JTextField();
    private static JTextField produktField = new JTextField();
    private static JTextField produktNummerField = new JTextField();
    private static JTextField einzelpreisField = new JTextField();
    private static JTextField mengeField = new JTextField();
    private static JTextField summeField = new JTextField();
    private JPanel panelButtons = new JPanel();
    private JButton stornoButton = new JButton("Storno");
    private JButton produktnummerButton = new JButton("Produktnummer");
    private JButton mengeButton = new JButton("Menge");
    private JButton rabattButton = new JButton("Rabatt");
    private JButton verkaufAbbrechenButton = new JButton("Verkauf abbrechen");

    public Artikelinformation() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
        initComponents();
    }

    private void initComponents() {

        //Artikel Panel mit den Artikelinformationen
        stornoButton.setFont(new Font("Arial", 0, 24));
        stornoButton.setForeground(new java.awt.Color(255, 51, 51));
        stornoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Warenliste.getLastSelectedTableRow() >= 0) {
                    //Warenliste.removeArtikel(null);
                }
            }
        });
        
        produktnummerButton.setFont(new Font("Arial", 0, 24));
        produktnummerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ziffernblock.setModeProduktnummer();
            }
        });
        
        mengeButton.setFont(new Font("Arial", 0, 24));
        mengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ziffernblock.setModeMenge();
            }
        });
        
        rabattButton.setFont(new Font("Arial", 0, 24));
        rabattButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ziffernblock.setModeRabatt();
            }
        });
        
        verkaufAbbrechenButton.setFont(new Font("Arial", 0, 24));
        verkaufAbbrechenButton.setForeground(new java.awt.Color(255, 51, 51));
        kategorieField.setEditable(false);
        kategorieField.setBackground(Color.WHITE);
        produktField.setEditable(false);
        produktField.setBackground(Color.WHITE);
        produktNummerField.setEditable(false);
        produktNummerField.setBackground(Color.WHITE);
        einzelpreisField.setEditable(false);
        einzelpreisField.setBackground(Color.WHITE);
        mengeField.setEditable(false);
        mengeField.setBackground(Color.WHITE);
        summeField.setEditable(false);
        summeField.setBackground(Color.WHITE);
        
        GroupLayout artikelPanelGroupLayout = new GroupLayout(artikelPanel);
        artikelPanelGroupLayout.setAutoCreateGaps(true);
        artikelPanel.setLayout(artikelPanelGroupLayout);
        artikelPanelGroupLayout.setHorizontalGroup(
                artikelPanelGroupLayout.createParallelGroup()
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(kategorieLabel)
                                .addComponent(kategorieField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(produktLabel)
                                .addComponent(produktField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(produktNummerLabel)
                                .addComponent(produktNummerField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(einzelpreisLabel)
                                .addComponent(einzelpreisField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(mengeLabel)
                                .addComponent(mengeField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(summeLabel)
                                .addComponent(summeField)
                        )
        );
        artikelPanelGroupLayout.setVerticalGroup(
                artikelPanelGroupLayout.createSequentialGroup()
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(kategorieLabel)
                                .addComponent(kategorieField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(produktLabel)
                                .addComponent(produktField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(produktNummerLabel)
                                .addComponent(produktNummerField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(einzelpreisLabel)
                                .addComponent(einzelpreisField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(mengeLabel)
                                .addComponent(mengeField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(summeLabel)
                                .addComponent(summeField)
                        )
        );
        artikelPanelGroupLayout.linkSize(SwingConstants.HORIZONTAL, kategorieLabel, produktLabel, produktNummerLabel, einzelpreisLabel,
                mengeLabel, summeLabel);
        artikelPanelGroupLayout.linkSize(SwingConstants.VERTICAL, kategorieField, produktField, produktNummerField, einzelpreisField,
                mengeField, summeField);

        //Panel mit den Buttons
        panelButtons.setLayout(new GridLayout(7, 0));
        stornoButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, stornoButton.getMinimumSize().height * 2));
        panelButtons.add(stornoButton);
        panelButtons.add(Box.createRigidArea(new Dimension(0, stornoButton.getMinimumSize().height * 2)));
        produktnummerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, produktnummerButton.getMinimumSize().height * 2));
        panelButtons.add(produktnummerButton);
        mengeButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mengeButton.getMinimumSize().height * 2));
        panelButtons.add(mengeButton);
        rabattButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, rabattButton.getMinimumSize().height * 2));
        panelButtons.add(rabattButton);
        panelButtons.add(Box.createRigidArea(new Dimension(0, stornoButton.getMinimumSize().height * 2)));
        verkaufAbbrechenButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, verkaufAbbrechenButton.getMinimumSize().height * 4));
        panelButtons.add(verkaufAbbrechenButton);

        //Hinzufügen der Panels auf das Artikelinformation Panel
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(artikelPanel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(panelButtons);
    }
    
    //Setzt das Panel der Artkelinformationen anhand eines übergeben Artikels
    public static void setArtikelInformationen(Artikel artikel){
        kategorieField.setText(artikel.getKategorie().getBezeichnung());
        produktField.setText(artikel.getName());
        produktNummerField.setText(String.valueOf(artikel.getId()));
        einzelpreisField.setText(String.valueOf(artikel.getPreis()));
        mengeField.setText(String.valueOf(artikel.getMenge()));
        summeField.setText(String.valueOf(artikel.getMenge() * artikel.getPreis()));
    }
 
}
