/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Artikel;
import model.Kategorie;
import model.Kassenbon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author lykoju Klasse welche einen Einkaufsablauf managed
 */
public class Einkaufmanager {

    private HashMap artikelTabelle = new HashMap();
    private ArrayList<Artikel> einkaufskorb = new ArrayList<>();

    public Einkaufmanager() {
    }

    public void ladenDatenbank() {
        //TODO Josy soll mal die DB-Methoden machen LUL
    }

    /**
     * Fuegt dem Einkaufskorb einen Artikel anhand der ID hinzu.
     *
     * @param id ID des Artikels in der Datenbank
     * @return (@code true) wenn der Artikel erfolgreich in der Datenbank
     * gefunden wurde, (@code false) wenn nicht.
     */
    public boolean hinzufuegenArtikel(int id, int menge) {

        //Logik um aus der DB Artikel nach id zu holen
        Artikel dummy = new Artikel("dummy", new Kategorie(-1, "dummy"), -1, 100, Artikel.Einheit.NUMMER, 1.09f, 10);
        // Ist der Artikel bereits enthalten wird die Menge addiert. Sonst wird der Artikel dem Einkaufskorb hinzugefuegt.
        boolean bereitsEnthalten = false;
        for (Artikel a : einkaufskorb) {
            if (a.getArtikelnummer() == id) {
                a.erhoehenMenge(menge);
                bereitsEnthalten = true;
            }
        }
        if (!bereitsEnthalten) {
            //hinzufuegen eines neuen Artikels
        }
        return false;
    }

    /**
     * Storniert einen Artikel.
     *
     * @param artikel der Artikel der storniert werden soll.
     */
    public void stornierenArtikel(Artikel artikel) {
        einkaufskorb.remove(artikel);
    }

    /**
     * Entfernt den letzen Artikel aus dem Einkaufskorb. Ist der Einkaufskorb
     * leer passiert nichts.
     */
    public void stornierenLetztenArtikel() {

        if (einkaufskorb.size() > 0) {
            einkaufskorb.remove(einkaufskorb.size() - 1);
        }
    }

    /**
     * Storniert den Einkauf.
     */
    public void stornierenEinkauf() {

        einkaufskorb.clear();
    }

    /**
     * Schliesst den aktuellen Einkauf ab und gibt einen Kassenbon aus. Danach
     * wird der Einkaufskorb zurueckgesetzt.
     *
     * @return Ein Kassenbon-Objekt
     */
    public Kassenbon abschliessenEinkauf() {

        //Erstellt einen neue Liste und uebergibt sie einem Kassenbon. Danach wird der aktuelle Einkaufskorb geleert.
        ArrayList<Artikel> einkaufsbonkorb = new ArrayList<>();
        Collections.copy(einkaufsbonkorb, einkaufskorb);
        Kassenbon kassenbon = new Kassenbon(einkaufsbonkorb);
        einkaufskorb.clear();
        return kassenbon;
    }

    /**
     * Berechnet den Gesamtpreis des Einkaufskorbs und gibt ihn zurueck.
     *
     * @return Der Gesamtpreis des Einkaufskorbs
     */
    public int getGesamtpreis() {
        int gesamtpreis = 0;
        for (Artikel a : einkaufskorb) {
            gesamtpreis += a.getPreis();
        }
        return gesamtpreis;
    }

    public ArrayList<Artikel> getEinkaufskorb() {
        return einkaufskorb;
    }
}
