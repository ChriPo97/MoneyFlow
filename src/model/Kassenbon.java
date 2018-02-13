/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Controller.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lykoju
 */
public class Kassenbon {
    
    private ArrayList<Artikel> warenkorb = new ArrayList<>();
    private final LocalDateTime zeitstempel;
    private final String impressum;
    private static final DateTimeFormatter ZEITFORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
    public Kassenbon(ArrayList<Artikel> warenkorb) {
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
    public String getZeitstempelString() {
        return zeitstempel.format(Kassenbon.ZEITFORMATTER);
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
    
    public String getBruttoByMwstklasseString(char mwstklasse) {
        String nullen = String.format("%04d€", getBruttoByMwstklasse(mwstklasse));
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }
    
    public String getNettoByMwstklasseString(char mwstklasse) {
        String nullen = String.format("%04d€", getNettoByMwstklasse(mwstklasse));
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }
    
}
