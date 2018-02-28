/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Einkaufsmanager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 *
 * @author ChriPo97 
 * Klasse der MenuBar der Benutzeroberfläche.
 */
public class MenuBar extends JMenuBar {

    public enum ArtikelMode {
        ADD, CHANGE, DELETE
    };
    private JMenu datei = new JMenu("Datei");
    private JMenu tools = new JMenu("Tools");
    private JMenu datenbank = new JMenu("Datenbank");
    private JMenuItem impressum = new JMenuItem("Impressum ändern");
    private JMenuItem addArtikel = new JMenuItem("Artikel hinzufügen");
    private JMenuItem changeArtikel = new JMenuItem("Artikel ändern");
    private JMenuItem deleteArtikel = new JMenuItem("Artikel löschen");
    private JMenu ueber = new JMenu("Über");
    private JMenuItem information = new JMenuItem("Information");
    private JSeparator seperator = new JSeparator();

    public MenuBar() {

        //Listener für die MenutItems
        addArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Einkaufsmanager.getEinkaufskorb().isEmpty()) {
                    ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.ADD);
                } else {
                    JOptionPane.showMessageDialog(null, "Für diese Aktion darf kein Einkauf aktiv sein.");
                }
            }
        });
        changeArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Einkaufsmanager.getEinkaufskorb().isEmpty()) {
                    ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.CHANGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Für diese Aktion darf kein Einkauf aktiv sein.");
                }
            }
        });
        deleteArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Einkaufsmanager.getEinkaufskorb().isEmpty()) {
                    ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.DELETE);
                } else {
                    JOptionPane.showMessageDialog(null, "Für diese Aktion darf kein Einkauf aktiv sein.");
                }
            }
        });
        impressum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImpressumDialog impressumDialog = new ImpressumDialog();
            }
        });
        information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InformationDialog ueberDialog = new InformationDialog();
            }
        });
        datenbank.add(addArtikel);
        datenbank.add(changeArtikel);
        datenbank.add(deleteArtikel);
        tools.add(datenbank);
        tools.add(seperator);
        tools.add(impressum);
        ueber.add(information);
        this.add(datei);
        this.add(tools);
        this.add(ueber);
    }

}
