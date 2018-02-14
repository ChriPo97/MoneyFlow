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
import model.Kategorie;

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
        mwst.setPreferredSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        mwst.setEditable(false);
        mwst.setBackground(Color.WHITE);
        mwst.setFont(new Font("Ariel", 0, 16));

        //Erstellen des Table Models und der JTabel
        tableModel.addColumn("Menge");
        tableModel.addColumn("Produkt");
        tableModel.addColumn("Kategorie");
        tableModel.addColumn("Rabatt");
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

        //MousListener für die Table - Artikelinformationen werden angezeigt
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
        updateSummeField();
    }

    //Funktion zum Löschen eines Artikels in der Table
    public static void removeArtikel(Artikel artikel) {
        updateSummeField();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 0) == artikel.getName()) {
                tableModel.removeRow(row);
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
    public static void updateSummeField() {
        summe.setText("Summe: " + Einkaufsmanager.getGesamtpreis());
    }

    public static void updateWarenliste() {
        tableModel.setRowCount(0);
        for (Artikel artikelEinkaufskorb : Einkaufsmanager.getEinkaufskorb()) {
            tableModel.addRow(new String[]{String.valueOf(artikelEinkaufskorb.getMenge()), artikelEinkaufskorb.getName(), artikelEinkaufskorb.getKategorie().getBezeichnung(),
                String.valueOf((100 - (artikelEinkaufskorb.getRabatt()*100))) + "%", String.valueOf(artikelEinkaufskorb.getPreis()),
                String.valueOf(artikelEinkaufskorb.getMehrwertsteuerklasse())});
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
        updateSummeField();
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
        updateSummeField();
        updateWarenliste();
    }

}
