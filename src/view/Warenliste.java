/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Einkaufsmanager;
import Controller.Languagemanager;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ChriPo97 Klasse für das Panel des mittleren Teils des Hauptfensters
 * mit der Warenliste und den Feldern für Summe und MwSt.
 */
public class Warenliste extends JPanel {
    
    private static JTextField summe = new JTextField(Languagemanager.getProperty("Warenliste.summe") + ": ");
    private static JTextField mwst = new JTextField(Languagemanager.getProperty("Warenliste.mwst") + ": ");
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
        summe.setText(Languagemanager.getProperty("Warenliste.summe") + ": " + Einkaufsmanager.getGesamtPreisString());
        mwst.setPreferredSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setEditable(false);
        mwst.setBackground(Color.WHITE);
        mwst.setFont(new Font("Ariel", 0, 16));
        mwst.setText(Languagemanager.getProperty("Warenliste.mwst") + ": " + Einkaufsmanager.getGesamtMwstString());

        //Erstellen des Table Models und der JTabel
        tableModel.addColumn(Languagemanager.getProperty("Warenliste.table.column.1"));
        tableModel.addColumn(Languagemanager.getProperty("Warenliste.table.column.2"));
        tableModel.addColumn(Languagemanager.getProperty("Warenliste.table.column.3"));
        tableModel.addColumn(Languagemanager.getProperty("Warenliste.table.column.4"));
        table = new JTable(tableModel) {
            @Override
            public Class getColumnClass(int column) {
                return String.class;
            }
        };
        scrollpaneTable = new JScrollPane(table);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

        //Hinzufügen der Scroll Pane mit der Table
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

    //Funktion zum Updaten der Summe und MwSt. Felder
    public static void updateSummeUndMwst() {
        summe.setText(Languagemanager.getProperty("Warenliste.summe") + ": " + Einkaufsmanager.getGesamtPreisString());
        mwst.setText(Languagemanager.getProperty("Warenliste.mwst") + ": " + Einkaufsmanager.getGesamtMwstString());
    }

    //Funktion zum Updaten der Warenliste
    public static void updateWarenliste() {
        tableModel.setRowCount(0);
        for (Artikel artikelEinkaufskorb : Einkaufsmanager.getEinkaufskorb()) {
            tableModel.addRow(new String[]{String.valueOf(artikelEinkaufskorb.getMengeFormatiert()), artikelEinkaufskorb.getName(),
                String.valueOf((100 - (artikelEinkaufskorb.getRabatt() * 100))) + "%", String.valueOf(artikelEinkaufskorb.getPreisString())});
        }
    }

    //Funktion zum Rabattieren eines Artikels
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

    //Funktion zum Ändern der Menge eines Artikels
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

    //Funktion zum Leeren der Warenliste
    public static void clearTable() {
        tableModel.setRowCount(0);
        updateSummeUndMwst();
        updateWarenliste();
    }
    
}
