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
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christoph
 */
public class Warenliste extends JPanel {

    private static JTextField summe = new JTextField("Summe:");
    private static JTextField mwst = new JTextField("MwSt:");
    private static JTable table;
    private JScrollPane scrollpaneTable;
    private static DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Warenliste() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
        initComponents();
    }

    private void initComponents() {

        summe.setEditable(false);
        summe.setPreferredSize(new Dimension(Short.MAX_VALUE, 70));
        summe.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
        summe.setBackground(Color.WHITE);
        summe.setFont(new Font("Ariel", 0, 30));
        summe.setText("Summe: " + Einkaufsmanager.getGesamtPreisString());
        mwst.setPreferredSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setEditable(false);
        mwst.setBackground(Color.WHITE);
        mwst.setFont(new Font("Ariel", 0, 16));
        mwst.setText("MwSt: "+ Einkaufsmanager.getGesamtPreisString());

        //Erstellen des Table Models und der JTabel
        tableModel.addColumn("Menge");
        tableModel.addColumn("Produkt");
        tableModel.addColumn("Rabatt");
        tableModel.addColumn("Preis");
        table = new JTable(tableModel) {
            @Override
            public Class getColumnClass(int column) {
                return String.class;
            }
        };
        scrollpaneTable = new JScrollPane(table);
        table.getTableHeader().setReorderingAllowed(false);

        //MousListener für die Table - Artikelinformationen werden angezeigt
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                for (Artikel artikel : Einkaufsmanager.getEinkaufskorb()) {
                    if (tableModel.getValueAt(row, 1).equals(artikel.getName())) {
                        Artikelinformation.setArtikelInformationen(artikel);
                    }
                }
            }

        });

        //table.setCellSelectionEnabled(true);
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //HinzufÃ¼gen der Scroll Pane mit der Table
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(scrollpaneTable);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(summe);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(mwst);
    }

    //Funktion zum Hinzufügen eines Artikel in die Table
    public static void addArtikel(int id) {
        Einkaufsmanager.hinzufuegenArtikel(id, 1);
        updateWarenliste();
        updateSummeUndMwst();
    }

    //Funktion zum Löschen eines Artikels in der Table
    public static void removeArtikel(int row) {
        for (Artikel artikel : Einkaufsmanager.getEinkaufskorb()) {
            if (tableModel.getValueAt(row, 1) == artikel.getName()) {
                Einkaufsmanager.stornierenArtikel(artikel);
                tableModel.removeRow(row);
                updateSummeUndMwst();
                updateWarenliste();
                return;
            }
        }
    }

    //Funktion zum Suchen der zuletzt ausgewählten Reihe in der Table
    public static int getLastSelectedTableRow() {
        int rowIndex = table.getSelectedRow();
        return rowIndex;
    }

    //Funktion zum Updaten des Summe Feldes
    public static void updateSummeUndMwst() {
        summe.setText("Summe: " + Einkaufsmanager.getGesamtPreisString());
        mwst.setText("MwSt: " + Einkaufsmanager.getGesamtPreisString());
    }

    public static void updateWarenliste() {
        tableModel.setRowCount(0);
        for (Artikel artikelEinkaufskorb : Einkaufsmanager.getEinkaufskorb()) {
            tableModel.addRow(new String[]{String.valueOf(artikelEinkaufskorb.getMengeFormatiert()), artikelEinkaufskorb.getName(),
                String.valueOf((100 - (artikelEinkaufskorb.getRabatt() * 100))) + "%", String.valueOf(artikelEinkaufskorb.getPreisString())});
        }
    }

    public static void discountArtikel(int rabatt) {
        if (getLastSelectedTableRow() >= 0) {
            for (Artikel artikelEinkaufskorb : Einkaufsmanager.getEinkaufskorb()) {
                if (artikelEinkaufskorb.getName() == tableModel.getValueAt(getLastSelectedTableRow(), 1)) {
                    artikelEinkaufskorb.rabattieren((float) ((100 - rabatt) * 0.01));
                }
            }
        }
        updateSummeUndMwst();
        updateWarenliste();
    }

    public static void changeMenge(int menge) {
        if (getLastSelectedTableRow() >= 0) {
            for (Artikel artikelEinkaufskorb : Einkaufsmanager.getEinkaufskorb()) {
                if (artikelEinkaufskorb.getName() == tableModel.getValueAt(getLastSelectedTableRow(), 1)) {
                    artikelEinkaufskorb.setMenge(menge);
                }
            }
        }
        updateSummeUndMwst();
        updateWarenliste();
    }
    
    public static void clearTable() {
        tableModel.setRowCount(0);
        updateSummeUndMwst();
        updateWarenliste();
    }

}
