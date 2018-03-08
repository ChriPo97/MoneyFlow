/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Einkaufsmanager;
import Controller.Languagemanager;
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
    private JMenu tools = new JMenu(Languagemanager.getProperty("MenuBar.tools"));
    private JMenu datenbank = new JMenu(Languagemanager.getProperty("MenuBar.datenbank"));
    private JMenuItem impressum = new JMenuItem(Languagemanager.getProperty("MenuBar.impressum"));
    private JMenuItem addArtikel = new JMenuItem(Languagemanager.getProperty("MenuBar.addArtikel"));
    private JMenuItem changeArtikel = new JMenuItem(Languagemanager.getProperty("MenuBar.changeArtikel"));
    private JMenuItem deleteArtikel = new JMenuItem(Languagemanager.getProperty("MenuBar.deleteArtikel"));
    private JMenu ueber = new JMenu(Languagemanager.getProperty("MenuBar.ueber"));
    private JMenuItem information = new JMenuItem(Languagemanager.getProperty("MenuBar.information"));
    private JSeparator seperator = new JSeparator();

    public MenuBar() {

        //Listener für die MenutItems
        addArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Einkaufsmanager.getEinkaufskorb().isEmpty()) {
                    ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.ADD);
                } else {
                    JOptionPane.showMessageDialog(null, Languagemanager.getProperty("MenuBar.addchangedelete.error.text"));
                }
            }
        });
        changeArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Einkaufsmanager.getEinkaufskorb().isEmpty()) {
                    ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.CHANGE);
                } else {
                    JOptionPane.showMessageDialog(null, Languagemanager.getProperty("MenuBar.addchangedelete.error.text"));
                }
            }
        });
        deleteArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Einkaufsmanager.getEinkaufskorb().isEmpty()) {
                    ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.DELETE);
                } else {
                    JOptionPane.showMessageDialog(null, Languagemanager.getProperty("MenuBar.addchangedelete.error.text"));
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
        this.add(tools);
        this.add(ueber);
    }

}
