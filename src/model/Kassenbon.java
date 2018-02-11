/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import Controller.Einkaufsmanager;
import Controller.Propertymanager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lykoju
 */
public class Kassenbon {

    private ArrayList<Artikel> warenkorb = new ArrayList<>();
    private final LocalDate datum;
    private final LocalTime zeit;
    private final String impressum;
    private final HashMap<Character, Integer> mehrwertsteuergruppen = new HashMap<>();
    private static final DateTimeFormatter ZEITFORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
    public Kassenbon(ArrayList<Artikel> warenkorb) {
        datum = LocalDate.now();
        zeit = LocalTime.now();
        this.warenkorb = warenkorb;
        impressum = (String) Propertymanager.getProperty("impressum");
    }

    public List<Artikel> getWarenkorb() {
        return warenkorb;
    }
    
    /**
     * Gibt einen String aus, welcher den Zeitpunk des Einkaufs enhaelt.
     * @return 
     */
    public String getZeitString() {
        return zeit.format(Kassenbon.ZEITFORMATTER);
    }
    
    /**
     * Gibt einen String aus, welcher das Datum des Einkaufs enthaelt
     * @return 
     */
    public String getDatumString() {
        return datum.format(Kassenbon.ZEITFORMATTER);
    }

    public String getImpressum() {
        return impressum;
    }

    public HashMap<Character, Integer> getMehrwertsteuergruppen() {
        return mehrwertsteuergruppen;
    }
    
    public int getBruttoByMwstklasse(char mwstklasse) {
        
        int brutto = 0;
        //Summiere alle Preise
        for (Artikel a:warenkorb) {
            if (a.getMehrwertsteuerklasse()== mwstklasse) {
                brutto += a.getPreis();
            }
        }
        return brutto;
    }
    
    public int getNettoByMwstklasse(char mwstklasse) {
        
        //Ermittle den Mehrwertsteuersatz fuer die gesuchte Klasse;
        float satz = Einkaufsmanager.MWSTKLASSEN.get(mwstklasse);
        int netto = 0;
        //Summiere alle Preise
        for (Artikel a:warenkorb) {
            if (a.getMehrwertsteuerklasse()== mwstklasse) {
                netto += a.getPreis()/satz;
            }
        }
        return netto;
    }
    
}
