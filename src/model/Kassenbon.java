/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Controller.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author lykoju
 */
public class Kassenbon {

    private final List<Artikel> warenkorb;
    private final LocalDateTime zeitstempel;
    private final String impressum;
    private static final DateTimeFormatter ZEITFORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATUMFORMATTER = DateTimeFormatter.ofPattern("E, dd.MM.YY");

    public Kassenbon(List<Artikel> warenkorb) {
        zeitstempel = LocalDateTime.now();
        this.warenkorb = warenkorb;
        impressum = Propertymanager.getProperty("MoneyFlow.Impressum");
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
        String nullen = String.format("%04d€", gesamtpreis);
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
        float satz = DBVerbindung.getMwstByKlasse(mwstklasse).getSteuer();
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
        String nullen = String.format("%04d€", getBruttoByMwstklasse(mwstklasse));
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
        String nullen = String.format("%04d€", getNettoByMwstklasse(mwstklasse));
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

}
