/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Christoph
 */
public class Artikelinformation extends JPanel {
    
    JButton stornoButton = new JButton("Storno");
    
    public Artikelinformation() {
        setLayout();
        this.add(stornoButton);
    }
    
    private void setLayout() {
        GridLayout gridLayout = new GridLayout(5,0);
        this.setLayout(gridLayout);
    }
    
}
