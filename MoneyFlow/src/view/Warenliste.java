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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christoph
 */
public class Warenliste extends JPanel {

    JLabel warenlisteLabel = new JLabel("Warenliste");
    JTable table;
    DefaultTableModel tableModel = new DefaultTableModel();

    public Warenliste() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(warenlisteLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        setTable();
    }

    private void setTable() {
        tableModel.addColumn("Produkt");
        tableModel.addColumn("Kategorie");
        tableModel.addColumn("Rabatt");
        tableModel.addColumn("Einheit");
        tableModel.addColumn("Preis");
        tableModel.addColumn("MwSt");
        table = new JTable(tableModel) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        JScrollPane scrollpaneTable = new JScrollPane(table);
        //scrollpaneTable.setPreferredSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
        this.add(scrollpaneTable);
    }
    
}
