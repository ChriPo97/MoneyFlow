/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Controller.DBVerbindung;
import Controller.Einkaufsmanager;
import Controller.Propertymanager;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lykoju
 */
public class Kassenbon {

    private final List<Artikel> warenkorb;
    private final LocalDateTime zeitstempel;
    private final String impressum;
    private static final DateTimeFormatter ZEITFORMATTER = DateTimeFormatter.ofPattern("HH-mm-ss");
    private static final DateTimeFormatter DATUMFORMATTER = DateTimeFormatter.ofPattern("dd-MM-YY");

    public Kassenbon(List<Artikel> warenkorb) {
        zeitstempel = LocalDateTime.now();
        this.warenkorb = warenkorb;
        impressum = Propertymanager.getProperty("Impressum");
    }

    public List<Artikel> getWarenkorb() {
        return warenkorb;
    }

    /**
     * Gibt einen String aus, welcher den Zeitpunk des Einkaufs enhaelt.
     *
     * @return Der Zeitpunkt des Einkaufs als String
     */
    public String getZeitString() {
        return zeitstempel.format(Kassenbon.ZEITFORMATTER);
    }

    /**
     * Gibt einen String aus, welcher den Zeitpunk des Einkaufs enhaelt.
     *
     * @return Der Zeitpunkt des Einkaufs als String
     */
    public String getDatumString() {
        return zeitstempel.format(Kassenbon.DATUMFORMATTER);
    }

    public String getImpressum() {
        return impressum;
    }

    public String getGesamtpreisString() {
        int gesamtpreis = 0;
        for (Artikel a : warenkorb) {
            gesamtpreis += a.getPreis();
        }
        String nullen = String.format("%03d€", gesamtpreis);
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    public int getBruttoByMwstklasse(char mwstklasse) {
        int brutto = 0;
        //Summiere alle Preise
        for (Artikel a : warenkorb) {
            if (a.getMehrwertsteuerklasse() == mwstklasse) {
                brutto += a.getPreis();
            }
        }
        return brutto;
    }

    public int getNettoByMwstklasse(char mwstklasse) {
        //Ermittle den Mehrwertsteuersatz fuer die gesuchte Klasse;
        float satz = 1 + DBVerbindung.getMwstByKlasse(mwstklasse).getSteuer();
        int netto = 0;
        //Summiere alle Preise
        for (Artikel a : warenkorb) {
            if (a.getMehrwertsteuerklasse() == mwstklasse) {
                netto += a.getPreis() / satz;
            }
        }
        return netto;
    }

    /**
     * Gibt einen String aus, welcher den Gesamtpreis einer Mehrwertsteuerklasse
     * in Brutto (Ladenpreis) formatiert anzeigt.
     *
     * @param mwstklasse die Klasse der Mehrwertsteuer, z.B. 'A'
     * @return ein formatierter String des Gesamtpreises einer
     * Mehrwertsteuerklasse in Brutto
     */
    public String getBruttoByMwstklasseString(char mwstklasse) {
        String nullen = String.format("%03d€", getBruttoByMwstklasse(mwstklasse));
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    /**
     * Gibt einen String aus, welcher den Gesamtpreis einer Mehrwertsteuerklasse
     * in Netto (vor Steuer) formatiert anzeigt.
     *
     * @param mwstklasse die Klasse der Mehrwertsteuer, z.B. 'A'
     * @return ein formatierter String des Gesamtpreises einer
     * Mehrwertsteuerklasse in Netto
     */
    public String getNettoByMwstklasseString(char mwstklasse) {
        String nullen = String.format("%03d€", getNettoByMwstklasse(mwstklasse));
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    public ArrayList<String> getKassebonAufbereitet() throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<String> tempKategorien = new ArrayList<String>();
        lines.add(Propertymanager.getProperty("Impressum"));
        lines.add("");
        for (Artikel artikel : this.getWarenkorb()) {
            if (!tempKategorien.contains(artikel.getKategorie())) {
                tempKategorien.add(artikel.getKategorie());
            }
        }
        for (String kategorie : tempKategorien) {
            lines.add(kategorie + "\n");
            for (Artikel artikel : this.getWarenkorb()) {
                if (artikel.getKategorie().equals(kategorie)) {
                    lines.add(" " + artikel.getMengeFormatiert() + " " + artikel.getName() + " "
                            + artikel.getPreisString() + " " + artikel.getMehrwertsteuerklasse() + "\n");
                }
            }
        }
        lines.add("");
        lines.add("Summe: " + this.getGesamtpreisString());
        lines.add("");
        lines.add("MwSt. A: " + this.getNettoByMwstklasseString('A'));
        lines.add("MwSt. B: " + this.getNettoByMwstklasseString('B'));
        
        return lines;
    }

}
