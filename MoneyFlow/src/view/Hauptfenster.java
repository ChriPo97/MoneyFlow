/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Christoph
 */
public class Hauptfenster extends JFrame {

    Artikelinformation artikelinformation = new Artikelinformation();
    Warenliste warenliste = new Warenliste();
    Ziffernblock ziffernblock = new Ziffernblock();

    public Hauptfenster() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1024, 768));
        this.setTitle("Kassensystem MoneyFlow");
        this.setLocationRelativeTo(null);
        setLayout();
        setFrameMovedListener();
        setMenuBar();

    }

    private void setLayout() {

        resize();

        this.add(artikelinformation, BorderLayout.WEST);
        this.add(warenliste, BorderLayout.CENTER);
        this.add(ziffernblock, BorderLayout.EAST);

//        this.add(artikelinformation);
//        this.add();
//        this.add();
    }

    private void resize() {
        artikelinformation.setPreferredSize(new Dimension((int) (this.getSize().width * 0.25), this.getSize().height));
        warenliste.setPreferredSize(new Dimension((int) (this.getSize().width * 0.5), this.getSize().height));
        ziffernblock.setPreferredSize(new Dimension((int) (this.getSize().width * 0.25), this.getSize().height));
    }

    private void setMenuBar() {
        MenuBar mb = new MenuBar();
        this.setJMenuBar(mb);
    }

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

}
