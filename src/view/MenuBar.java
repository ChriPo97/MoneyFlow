/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Christoph
 */
public class MenuBar extends JMenuBar{
    
    public enum ArtikelMode {
        ADD, CHANGE, DELETE
    };
    JMenu datei = new JMenu("Datei");
    JMenu tools = new JMenu("Tools");
    JMenu datenbank = new JMenu("Datenbank");
    JMenuItem addArtikel = new JMenuItem("Artikel hinzufügen");
    JMenuItem changeArtikel = new JMenuItem("Artikel ändern");
    JMenuItem deleteArtikel = new JMenuItem("Artikel löschen");
    JMenu hilfe = new JMenu("Hilfe");
    JMenu ueber = new JMenu("Über");
    
    public MenuBar() {
        addArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.ADD);
            }
        });
        changeArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.CHANGE);
            }
        });
        deleteArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArtikelDialog artikelDialog = new ArtikelDialog(ArtikelMode.DELETE);
            }
        });
        datenbank.add(addArtikel);
        datenbank.add(changeArtikel);
        datenbank.add(deleteArtikel);
        tools.add(datenbank);
        this.add(datei);
        this.add(tools);
        this.add(hilfe);
        this.add(ueber);
    }
    
}
