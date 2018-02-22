/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.DBVerbindung;
import Controller.Einkaufsmanager;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import model.Artikel;

/**
 *
 * @author Christoph Klasse zur Erstellunbg eines Dialog fensters für das
 * Hinzufügen/Ändern/Löschen eines Artikels in der Datenbank
 */
public class ArtikelDialog extends JDialog {

    private JPanel dialogPanel = new JPanel();
    private JPanel artikelPanel = new JPanel();
    private JPanel listPanel = new JPanel();
    private GridLayout listLayout;
    private JScrollPane listPane;
    private JTree tree;
    private GroupLayout artikelPanelGroupLayout;
    private GridLayout dialogLayout;
    private JLabel kategorieLabel = new JLabel("Kategorie:");
    private JLabel produktLabel = new JLabel("Produkt:");
    private JLabel einheitLabel = new JLabel("Einheit:");
    private JLabel einzelpreisLabel = new JLabel("Einzelpreis:");
    private JLabel mwstLabel = new JLabel("MwSt:");
    private static JTextField kategorieField = new JTextField();
    private static JTextField produktField = new JTextField();
    private static JComboBox einheitComboBox = new JComboBox(new String[]{"STUECK", "GEWICHT"});
    private static JTextField einzelpreisField = new JTextField();
    private static JComboBox mwstComboBox = new JComboBox(new String[]{"A", "B"});
    JButton modeButton = new JButton();

    public ArtikelDialog(MenuBar.ArtikelMode artikelMode) {

        initComponents();
        dialogPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        //Die Größe des Fensters wird anhand des Modes festgelegt
        if (artikelMode == MenuBar.ArtikelMode.ADD) {
            this.setTitle("Artikel hinzufügen");
            this.setMinimumSize(new Dimension(300, 250));
            modeButton.setText("Artikel hinzufügen");
            kategorieField.setEnabled(true);
            produktField.setEnabled(true);
            einheitComboBox.setEnabled(true);
            einzelpreisField.setEnabled(true);
            mwstComboBox.setEnabled(true);
            modeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (produktField.getText() != "") {
                        //DBVerbindung.artikelAnlegen(produktField.getText(), kategorieField.getText(), 0, einheitComboBox.getSelectedItem().toString(), 0, 0);
                    }
                }
            });
            //Barcode generieren
        }
        if (artikelMode == MenuBar.ArtikelMode.CHANGE) {
            this.setTitle("Artikel ändern");
            this.setMinimumSize(new Dimension(600, 250));
            modeButton.setText("Artikel ändern");
            kategorieField.setEnabled(true);
            produktField.setEnabled(true);
            einheitComboBox.setEnabled(true);
            einzelpreisField.setEnabled(true);
            mwstComboBox.setEnabled(true);
            initTree();
            modeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (produktField.getText() != "") {
                        int id = DBVerbindung.artikelNametoArtikelID(produktField.getText());
                        DBVerbindung.artikelBearbeitenKategorie(id, kategorieField.getText());
                        DBVerbindung.artikelBearbeitenName(id, produktField.getText());
                        //DBVerbindung.artikelBearbeitenEinheit(id, einheitComboBox.getSelectedItem().toString());
                        //DBVerbindung.artikelBearbeitenPreis(id, einzelpreisField.getText());
                        //DBVerbindung.artikelBearbeitenMwstklasse(id, );
                    }
                }
            });
        }
        if (artikelMode == MenuBar.ArtikelMode.DELETE) {
            this.setTitle("Artikel löschen");
            this.setMinimumSize(new Dimension(600, 250));
            modeButton.setText("Artikel löschen");
            kategorieField.setEnabled(false);
            produktField.setEnabled(false);
            einheitComboBox.setEnabled(false);
            einzelpreisField.setEnabled(false);
            mwstComboBox.setEnabled(false);
            initTree();
            modeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (produktField.getText() != "") {
                        int id = DBVerbindung.artikelNametoArtikelID(produktField.getText());
                        DBVerbindung.artikelLoeschen(id);
                    }
                }
            });
        }

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);

    }

    //Initiert alle Komponenten des Dialogfensters, außer den Tree
    private void initComponents() {
        dialogLayout = new GridLayout(1, 2);
        dialogPanel.setLayout(dialogLayout);

        artikelPanelGroupLayout = new GroupLayout(artikelPanel);
        artikelPanelGroupLayout.setAutoCreateGaps(true);
        artikelPanel.setLayout(artikelPanelGroupLayout);
        artikelPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 10)));
        artikelPanelGroupLayout.setHorizontalGroup(
                artikelPanelGroupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(produktLabel)
                                .addComponent(produktField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(einheitLabel)
                                .addComponent(einheitComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(kategorieLabel)
                                .addComponent(kategorieField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(mwstLabel)
                                .addComponent(mwstComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(einzelpreisLabel)
                                .addComponent(einzelpreisField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(modeButton)
                        )
        );
        artikelPanelGroupLayout.setVerticalGroup(
                artikelPanelGroupLayout.createSequentialGroup()
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(produktLabel)
                                .addComponent(produktField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(einheitLabel)
                                .addComponent(einheitComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(kategorieLabel)
                                .addComponent(kategorieField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(mwstLabel)
                                .addComponent(mwstComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(einzelpreisLabel)
                                .addComponent(einzelpreisField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(modeButton)
                        )
        );
        artikelPanelGroupLayout.linkSize(SwingConstants.HORIZONTAL, kategorieLabel, produktLabel, einheitLabel, einzelpreisLabel,
                mwstLabel);
        artikelPanelGroupLayout.linkSize(SwingConstants.VERTICAL, kategorieField, produktField, einheitComboBox, einzelpreisField,
                mwstComboBox);
        dialogPanel.add(artikelPanel);
        this.add(dialogPanel);
    }

    //Initiert des Tree mit allen Artikeln
    private void initTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Artikel");
        ArrayList<String> tempKategorien = new ArrayList<String>();
        ArrayList<Artikel> tempArtikel = DBVerbindung.alleArtikelAuslesen();
        for (Artikel artikel : tempArtikel) {
            if (!tempKategorien.contains(artikel.getKategorie())) {
                tempKategorien.add(artikel.getKategorie());
            }
        }
        for (String tempKategorie : tempKategorien) {
            DefaultMutableTreeNode kategorieNode = new DefaultMutableTreeNode(tempKategorie);
            for (Artikel artikel : tempArtikel) {
                if (artikel.getKategorie().equals(tempKategorie)) {
                    DefaultMutableTreeNode artikelNode = new DefaultMutableTreeNode(artikel.getName());
                    kategorieNode.add(artikelNode);
                }
            }
            top.add(kategorieNode);
        }
        tree = new JTree(top);

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                for (Artikel artikel : DBVerbindung.alleArtikelAuslesen()) {
                    if (artikel.getName().equals(e.getPath().getLastPathComponent().toString())) {
                        kategorieField.setText(artikel.getKategorie());
                        produktField.setText(artikel.getName());
                        einheitComboBox.setSelectedItem(artikel.getEinheit().toString());
                        einzelpreisField.setText(String.valueOf(artikel.getPreisString()));
                        mwstComboBox.setSelectedItem(String.valueOf(artikel.getMehrwertsteuerklasse()));
                    }
                }
            }
        });
        listPane = new JScrollPane(tree);
        listLayout = new GridLayout();
        listPanel.setLayout(listLayout);
        listPanel.add(listPane);
        dialogPanel.add(listPanel);
    }

}
