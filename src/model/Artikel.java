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
        PIECE, WEIGHT
    };

    private final String name;
    private final String kategorie;
    private final int id;
    //Gibt den Anteil des Preises nach Rabbatt an, z.B. 20% Rabatt -> 0.8
    private float rabatt = 1;

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
     * @param id Die Nummer des Artikels - Entspricht der ID aus der Datenbank
     * @param einheitspreis Der Preis fuer eine Einheit - Also fuer ein Stueck
     * oder 1,000g
     * @param einheit Ob der Artikel in Stueck oder Gewicht abgerechnet wird.
     * @param mehrwertsteuerklasse Die Mehrwertsteuerklasse des Artikels. Ein
     * einzelner {@code char} wie z.B. 'A'
     * @param menge Die Menge des Artikels im Einkauf
     */
    public Artikel(String name, String kategorie, int id, int einheitspreis, Einheit einheit, char mehrwertsteuerklasse, int menge) {
        this.name = name;
        this.kategorie = kategorie;
        this.id = id;
        this.einheitspreis = einheitspreis;
        this.einheit = einheit;
        this.mehrwertsteuerklasse = mehrwertsteuerklasse;
        this.menge = menge;

    }

    public String getName() {
        return name;
    }

    public String getKategorie() {
        return kategorie;
    }

    public int getId() {
        return id;
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
        return Math.round((this.einheit == Artikel.Einheit.WEIGHT ? menge / 1000f : menge) * einheitspreis * rabatt);
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

    public float getRabatt() {
        return rabatt;
    }

    public void erhoehenMenge(int anzahl) {
        if (anzahl > 0) {
            menge += anzahl;
        }
    }

    public void rabattieren(float rabatt) {
        this.rabatt = rabatt;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gibt den Preis als String aus, mit Kommatrennzeichen und €-Symbol.
     *
     * @return Ein String, der den Preis ordentlich anzeigt
     */
    public String getPreisString() {
        String nullen = String.format("%03d€", getPreis());
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    /**
     * Gibt den Einheitsreis als String aus, mit Kommatrennzeichen und €-Symbol.
     *
     * @return Ein String, der den Einheitspreis ordentlich anzeigt
     */
    public String getEinheitspreisString() {
        String nullen = String.format("%03d€", einheitspreis);
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    /**
     * Gibt die Menge ordentlich formatiert aus.
     * @return Ein String, der die Menge anhand der Einheit ordentlich formatiert.
     */
    public String getMengeFormatiert() {
        if (einheit == Artikel.Einheit.WEIGHT) {
            //Fuege fuehrende Nullen ein
            String nullen = String.format("%04dkg", menge);

            //Trenne den String anhand des einzufuegenden Kommas auf
            String vorKomma = nullen.substring(0, nullen.length() - 5);
            String nachKomma = nullen.substring(nullen.length() - 5);

            //Ist der Teil vor dem Komma laenger als 3 Zeichen, werden Orientierungszeichen eingefuegt
            int i = 3;
            String restlinks;
            String restrechts;
            while (i < vorKomma.length()) {
                restlinks = vorKomma.substring(0, vorKomma.length() - i);
                restrechts = vorKomma.substring(vorKomma.length() - i);
                vorKomma = restlinks + '.' + restrechts;
                i += 4;
            }
            return vorKomma+','+nachKomma;
        }
        
        return String.valueOf(menge);
    }

}
