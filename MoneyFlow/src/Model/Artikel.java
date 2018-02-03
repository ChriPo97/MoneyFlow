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

    private String name;
    
    private Kategorie kategorie;
    
    private int artikelnummer;
    
    //Preise werden in Cent angegeben und bei Anzeige formatiert
    private int preis;
    
    //true fuer zaehlbare Einheiten, false fuer Einheiten mit Gewicht
    private boolean einheit;
    
    private float mehrwertsteuersatz;

    /**
     * Konstruktor fuer einen Artikel.
     * @param name
     * @param kategorie
     * @param artikelnummer
     * @param preis
     * @param einheit
     * @param mehrwertsteuersatz 
     */
    public Artikel(String name, Kategorie kategorie, int artikelnummer, int preis, boolean einheit, float mehrwertsteuersatz) {
        
        this.name = name;
        this.kategorie = kategorie;
        this.artikelnummer = artikelnummer;
        this.preis = preis;
        this.einheit = einheit;
        this.mehrwertsteuersatz = mehrwertsteuersatz;
        
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

    public int getPreis() {
        return preis;
    }

    public boolean isEinheit() {
        return einheit;
    }

    public float getMehrwertsteuersatz() {
        return mehrwertsteuersatz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public void setEinheit(boolean einheit) {
        this.einheit = einheit;
    }

    public void setMehrwertsteuersatz(float mehrwertsteuersatz) {
        this.mehrwertsteuersatz = mehrwertsteuersatz;
    }
    
    
    
}
