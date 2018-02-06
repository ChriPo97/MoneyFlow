/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Christoph
 */
public class MenuBar extends JMenuBar{
    
    JMenu datei = new JMenu("Datei");
    JMenu tools = new JMenu("Tools");
    JMenu hilfe = new JMenu("Hilfe");
    JMenu ueber = new JMenu("Über");
    
    public MenuBar() {
        
        this.add(datei);
        this.add(tools);
        this.add(hilfe);
        this.add(ueber);
        
    }
    
}
