/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Einkaufsmanager;
import model.Artikel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christoph
 */
public class Warenliste extends JPanel {

    private JTextField summe = new JTextField();
    private JTextField mwst = new JTextField();
    private static JTable table;
    private JScrollPane scrollpaneTable;
    private static DefaultTableModel tableModel = new DefaultTableModel();

    public Warenliste() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents() {

        summe.setEditable(false);
        summe.setPreferredSize(new Dimension(Short.MAX_VALUE, 70));
        summe.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
        summe.setBackground(Color.WHITE);
        mwst.setPreferredSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setEditable(false);
        mwst.setBackground(Color.WHITE);

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
        scrollpaneTable = new JScrollPane(table);
        table.getTableHeader().setReorderingAllowed(false);
        String[] test = {"Banane", "Obst", "0%", "kg", "1,20â‚¬", "0,23â‚¬"};
        tableModel.addRow(test);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                for (Artikel artikel : Einkaufsmanager.getEinkaufskorb()) {
                    if (tableModel.getValueAt(row, 0).equals(artikel.getName())) {
                        Artikelinformation.setArtikelInformationen(artikel);
                    }
                }
            }

        });
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //HinzufÃ¼gen der Scroll Pane mit der Table
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(scrollpaneTable);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(summe);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(mwst);
        this.add(Box.createRigidArea(new Dimension(0, 12)));
    }

    public void addArtikel(Artikel artikel) {
        tableModel.addRow(new String[]{artikel.getName(), artikel.getKategorie().getBezeichnung(),
            "0%", artikel.getEinheit().toString(), String.valueOf(artikel.getPreis()),
            String.valueOf(artikel.getMehrwertsteuersatz())});
    }

    public static void removeArtikel(Artikel artikel) {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 0) == artikel.getName()) {
                tableModel.removeRow(row);
                return;
            }
        }
    }

    public static int getLastSelectedTableRow() {
        int rowIndex = table.getSelectedRow();
        return rowIndex;
    }

}
