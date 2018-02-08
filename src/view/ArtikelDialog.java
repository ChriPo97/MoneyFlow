/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Einkaufsmanager;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
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
 * @author Christoph
 */
public class ArtikelDialog extends JDialog {

    JPanel dialogPanel = new JPanel();
    JPanel artikelPanel = new JPanel();
    JPanel listPanel = new JPanel();
    GridLayout listLayout;
    JScrollPane listPane;
    JTree tree;
    GroupLayout artikelPanelGroupLayout;
    GridLayout dialogLayout;
    private JLabel kategorieLabel = new JLabel("Kategorie:");
    private JLabel produktLabel = new JLabel("Produkt:");
    private JLabel einheitLabel = new JLabel("Einheit:");
    private JLabel einzelpreisLabel = new JLabel("Einzelpreis:");
    private JLabel mwstLabel = new JLabel("Mehrwertsteuersatz:");
    private static JComboBox kategorieComboBox = new JComboBox(new String[]{"Obst", "Sexspielzeug"});
    private static JTextField produktField = new JTextField();
    private static JComboBox einheitComboBox = new JComboBox(new String[]{"Kg", "Menge"});
    private static JTextField einzelpreisField = new JTextField();
    private static JComboBox mwstComboBox = new JComboBox(new String[]{"A", "B"});
    JButton modeButton = new JButton();

    public ArtikelDialog(MenuBar.ArtikelMode artikelMode) {

        initComponents();
        dialogPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        if (artikelMode == MenuBar.ArtikelMode.ADD) {
            this.setMinimumSize(new Dimension(300, 250));
            modeButton.setText("Artikel hinzufügen");
        }
        if (artikelMode == MenuBar.ArtikelMode.CHANGE) {
            this.setMinimumSize(new Dimension(600, 250));
            modeButton.setText("Artikel ändern");
            initList();
        }
        if (artikelMode == MenuBar.ArtikelMode.DELETE) {
            this.setMinimumSize(new Dimension(600, 250));
            modeButton.setText("Artikel löschen");
            kategorieComboBox.setEnabled(false);
            produktField.setEnabled(false);
            einheitComboBox.setEnabled(false);
            einzelpreisField.setEnabled(false);
            mwstComboBox.setEnabled(false);
            initList();
        }

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);

    }

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
                                .addComponent(kategorieLabel)
                                .addComponent(kategorieComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(produktLabel)
                                .addComponent(produktField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(einheitLabel)
                                .addComponent(einheitComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(einzelpreisLabel)
                                .addComponent(einzelpreisField)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(mwstLabel)
                                .addComponent(mwstComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createSequentialGroup()
                                .addComponent(modeButton)
                        )
        );
        artikelPanelGroupLayout.setVerticalGroup(
                artikelPanelGroupLayout.createSequentialGroup()
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(kategorieLabel)
                                .addComponent(kategorieComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(produktLabel)
                                .addComponent(produktField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(einheitLabel)
                                .addComponent(einheitComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(einzelpreisLabel)
                                .addComponent(einzelpreisField)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(mwstLabel)
                                .addComponent(mwstComboBox)
                        )
                        .addGroup(artikelPanelGroupLayout.createParallelGroup()
                                .addComponent(modeButton)
                        )
        );
        artikelPanelGroupLayout.linkSize(SwingConstants.HORIZONTAL, kategorieLabel, produktLabel, einheitLabel, einzelpreisLabel,
                mwstLabel);
        artikelPanelGroupLayout.linkSize(SwingConstants.VERTICAL, kategorieComboBox, produktField, einheitComboBox, einzelpreisField,
                mwstComboBox);
        dialogPanel.add(artikelPanel);
        this.add(dialogPanel);
    }

    private void initList() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Artikel");
        DefaultMutableTreeNode obst = new DefaultMutableTreeNode("Obst");
        DefaultMutableTreeNode banane = new DefaultMutableTreeNode("Banane");
        obst.add(banane);
        top.add(obst);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                for (Artikel artikel : Einkaufsmanager.getEinkaufskorb()) {
                    if (artikel.getName() == e.getPath().getLastPathComponent().toString()) {
                        kategorieComboBox.setEnabled(false);
                        produktField.setText(artikel.getName());
                        einheitComboBox.setEnabled(false);
                        einzelpreisField.setText(String.valueOf(artikel.getPreis()));
                        mwstComboBox.setEnabled(false);
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
