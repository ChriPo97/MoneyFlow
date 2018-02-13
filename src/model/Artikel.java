/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lykoju Laufzeitklasse fuer einen Artikel im Einkauf.
 */
public class Artikel {

    public static enum Einheit {
        STÜCK, GEWICHT
    };

    private final String name;
    private final Kategorie kategorie;
    private final int artikelnummer;
    //Gibt den Anteil des Preises nach Rabbatt an, z.B. 20% Rabatt -> 80
    private int rabatt = 100;

    //Preise werden in Cent angegeben und bei Anzeige formatiert
    private final int einheitspreis;
    private final Einheit einheit;
//    private final float mehrwertsteuersatz;
    private final char mehrwertsteuerklasse;
    // Menge wird in Stueck angegeben oder gramm fuer Gewichtsangaben
    private int menge;

    /**
     * Konstruktor fuer einen Artikel.
     *
     * @param name Name des Artikels
     * @param kategorie Die Kategorie zudem der Artikel gehoert
     * @param artikelnummer Die Nummer des Artikels - Entspricht der ID aus der
     * Datenbank
     * @param einheitspreis Der Preis fuer eine Einheit - Also fuer ein Stueck
     * oder 1,000g
     * @param einheit Ob der Artikel in Stueck oder Gewicht abgerechnet wird.
     * @param mehrwertsteuerklasse Die Mehrwertsteuerklasse des Artikels. Ein einzelner (@code char) wie z.B. 'A'
     * @param menge Die Menge des Artikels im Einkauf
     */
    public Artikel(String name, Kategorie kategorie, int artikelnummer, int einheitspreis, Einheit einheit, char mehrwertsteuerklasse, int menge) {
        this.name = name;
        this.kategorie = kategorie;
        this.artikelnummer = artikelnummer;
        this.einheitspreis = einheitspreis;
        this.einheit = einheit;
        this.mehrwertsteuerklasse = mehrwertsteuerklasse;
        this.menge = menge;
        
    }

    public String getName() {
        return name;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public int getMenge() {
        return menge;
    }
    
    public void setMenge(int menge) {
        this.menge = menge;
    }

    /**
     * Berechnet den Preis fuer dieses Artikel-Objekt.
     *
     * @return Der Preis fuer diesen Eintrag. Entspricht (@code
     * menge*einheitspreis) gerundet zum naechten Nachbarn.
     */
    public int getPreis() {
        //TODO immer aufrunden?
        float rabattAsFloat = (float) (rabatt * 0.01);
        return Math.round((this.einheit == Artikel.Einheit.GEWICHT ? menge / 1000 : menge) * einheitspreis * rabattAsFloat);
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public char getMehrwertsteuerklasse() {
        return mehrwertsteuerklasse;
    }

    public int getEinheitspreis() {
        return einheitspreis;
    }
    
    public int getRabatt() {
        return rabatt;
    }

    public void erhoehenMenge(int anzahl) {
        if (anzahl > 0) {
            menge += anzahl;
        }
    }

    public void rabattieren(int rabatt) {
        this.rabatt = rabatt;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public String getMengeFormatiert() {
        
        String zeichen = "%dstk";
        if (einheit == Artikel.Einheit.GEWICHT) {
            if (menge > 999) {
                zeichen = "%dkg";
            } else {
                zeichen = "%dg";
            }
        }
        return "todo";
    }

}
