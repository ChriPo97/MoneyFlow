/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Languagemanager;
import Controller.Propertymanager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author ChriPo97 
 * Klasse zur Erstellung des Hauptfensters mit KeyListener für den Handscanner.
 */
public class Hauptfenster extends JFrame implements KeyListener {

    String barCode = "";
    boolean barCodeActive = false;
    Artikelinformation artikelinformation = new Artikelinformation();
    Warenliste warenliste = new Warenliste();
    Ziffernblock ziffernblock = new Ziffernblock();

    public Hauptfenster() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1024, 768));
        this.setTitle(Languagemanager.getProperty("Hauptfenster.Titel"));
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        resize();
        this.add(artikelinformation, BorderLayout.WEST);
        this.add(warenliste, BorderLayout.CENTER);
        this.add(ziffernblock, BorderLayout.EAST);
        setFrameMovedListener();
        setMenuBar();
    }

    //Funktion zum setzen einer neuen Größe für alle PAnels im Hauptfenster
    private void resize() {
        artikelinformation.setPreferredSize(new Dimension((int) (this.getSize().width * 0.25), this.getSize().height));
        warenliste.setPreferredSize(new Dimension((int) (this.getSize().width * 0.5), this.getSize().height));
        ziffernblock.setPreferredSize(new Dimension((int) (this.getSize().width * 0.25), this.getSize().height));
    }

    //Hinzufügen der MenuBar
    private void setMenuBar() {
        MenuBar mb = new MenuBar();
        this.setJMenuBar(mb);
    }

    //Ein Listener zum Resizen des Hauptfensters
    private void setFrameMovedListener() {
        this.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener() {
            
            @Override
            public void ancestorResized(HierarchyEvent e) {
                resize();
            }
            
            @Override
            public void ancestorMoved(HierarchyEvent e) {
            }
            
        });
    }

    //Funktionen des KeyListeners für den Handscanner wird implementiert
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == Propertymanager.getProperty("BarcodeScannerPrefix").toCharArray()[0] && !(barCodeActive)) {
            barCodeActive = true;
            return;
        }
        if (barCodeActive && !(e.getKeyChar() == Propertymanager.getProperty("BarcodeScannerPrefix").toCharArray()[0])
                && !(e.getKeyChar() == Propertymanager.getProperty("BarcodeScannerSuffix").toCharArray()[0])) {
            barCode = barCode + String.valueOf(e.getKeyChar());
            return;
        }
        if (e.getKeyChar() == Propertymanager.getProperty("BarcodeScannerSuffix").toCharArray()[0] && barCodeActive) {
            barCodeActive = false;
            Warenliste.addArtikel(Integer.valueOf(barCode));
            barCode = "";
            return;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
