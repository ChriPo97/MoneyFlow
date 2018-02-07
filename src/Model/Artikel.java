/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author lykoju
 * Laufzeitklasse fuer einen Artikel im Einkauf.
 */
public class Artikel {

    public static enum Einheit {NUMMER, GEWICHT}; 
    private final String name;
    
    private final Kategorie kategorie;
    
    private final int artikelnummer;
    
    //Preise werden in Cent angegeben und bei Anzeige formatiert
    private final int preis;
    private int einheitspreis;
    private final Einheit einheit;
    private final float mehrwertsteuersatz;
    private final float menge;

    /**
     * Konstruktor fuer einen Artikel.
     * @param name Name des Artikels
     * @param kategorie Die Kategorie zudem der Artikel gehoert
     * @param artikelnummer Die Nummer des Artikels - Entspricht der ID aus der Datenbank
     * @param einheitspreis Der Preis fuer eine Einheit - Also fuer ein Stueck oder 1kg
     * @param einheit Ob der Artikel in Stueck oder Kilo abgerechnet wird.
     * @param mehrwertsteuersatz Der Mehrwertsteuersatz des Artikels von 100% ausgehend, z.B. 1,09
     * @param menge Die Menge des Artikels im Einkauf
     */
    public Artikel(String name, Kategorie kategorie, int artikelnummer, int einheitspreis, Einheit einheit, float mehrwertsteuersatz, float menge) {
        this.name = name;
        this.kategorie = kategorie;
        this.artikelnummer = artikelnummer;
        this.preis = einheitspreis;
        this.einheit = einheit;
        this.mehrwertsteuersatz = mehrwertsteuersatz;
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

    /**
     * Berechnet den Preis fuer dies Artikel-Objekt.
     * @return Der Preis fuer diesen Eintrag. Entspricht (@code menge*einheitspreis) gerundet zum naechten Nachbarn.
     */
    public int getPreis() {
        //TODO immer aufrunden?
        return Math.round(menge*einheitspreis);
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public float getMehrwertsteuersatz() {
        return mehrwertsteuersatz;
    }    

    public int getEinheitspreis() {
        return einheitspreis;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}