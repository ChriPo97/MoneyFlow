/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author lykoju
 */
public class Kassenbon {

    private ArrayList<Artikel> warenkorb = new ArrayList<>();
    private final LocalDate datum;
    private final LocalTime zeit;
    private String impressum;
    private final HashMap<String, Integer> mehrwertsteuergruppen = new HashMap<>();
    private static final DateTimeFormatter ZEITFORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
    public Kassenbon(ArrayList<Artikel> warenkorb) {
        datum = LocalDate.now();
        zeit = LocalTime.now();
        this.warenkorb = warenkorb;
    }

    public ArrayList<Artikel> getWarenkorb() {
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

    public HashMap<String, Integer> getMehrwertsteuergruppen() {
        return mehrwertsteuergruppen;
    }
    
    
}
