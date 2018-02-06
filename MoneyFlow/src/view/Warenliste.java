/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christoph
 */
public class Warenliste extends JPanel {

    JTable table;
    DefaultTableModel tableModel = new DefaultTableModel();

    public Warenliste() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents() {
        
        JTextField summe = new JTextField();
        summe.setEditable(false);
        summe.setPreferredSize(new Dimension(Short.MAX_VALUE, 70));
        summe.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
        JTextField mwst = new JTextField();
        mwst.setPreferredSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setEditable(false);
        
        //Erstellen des Table Models und der JTabel
        tableModel.addColumn("Produkt");
        tableModel.addColumn("Kategorie");
        tableModel.addColumn("Rabatt");
        tableModel.addColumn("Einheit");
        tableModel.addColumn("Preis");
        tableModel.addColumn("MwSt");
        table = new JTable(tableModel) {
            @Override
            public Class getColumnClass(int column) {
                return String.class;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollpaneTable = new JScrollPane(table);
        String[] test = {"Banane", "Obst", "0%", "kg", "1,20€","0,23€"};
        tableModel.addRow(test);
        
        //Hinzufügen der Scroll Pane mit der Table
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(scrollpaneTable);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(summe);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(mwst);
        this.add(Box.createRigidArea(new Dimension(0, 12)));
    }
    
    public void addRow(String[] row) {
        tableModel.addRow(row);
    }
    
}
