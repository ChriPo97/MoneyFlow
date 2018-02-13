/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class Einkaufsmanager {

    //Aktuell nicht in Benutzung
    private HashMap artikelTabelle = new HashMap();
    private static final ArrayList<Artikel> einkaufskorb = new ArrayList<>();
    
    public static final HashMap<Character, Float> MWSTKLASSEN = new HashMap<>();

    private Einkaufsmanager() {
    }

    public void ladenDatenbank() {
        //TODO Josy soll mal die DB-Methoden machen LUL
        MWSTKLASSEN.put('A', 1.19f);
        MWSTKLASSEN.put('B', 1.07f);
    }

    /**
     * Fuegt dem Einkaufskorb einen Artikel anhand der ID hinzu.
     *
     * @param id ID des Artikels in der Datenbank
     * @return (@code true) wenn der Artikel erfolgreich in der Datenbank
     * gefunden wurde, (@code false) wenn nicht.
     */
    public static boolean hinzufuegenArtikel(int id, int menge) {

        String artikelName = DBVerbindung.artikelIDtoArtikelName(id);
        Artikel.Einheit artikelEinheit;
        if(DBVerbindung.artikelNametoEinheit(artikelName).toUpperCase() == Artikel.Einheit.GEWICHT.toString()) {
            artikelEinheit = Artikel.Einheit.GEWICHT;
        }
        else {
            artikelEinheit = Artikel.Einheit.STÜCK;
        }
        Artikel artikel = new Artikel(artikelName, new Kategorie(1, DBVerbindung.artikelNametoKategorie(artikelName)), id, DBVerbindung.artikelNametoPreis(artikelName), artikelEinheit, DBVerbindung.artikelNametoMehrwersteuerklasse(artikelName).charAt(0), 1);
        // Ist der Artikel bereits enthalten wird die Menge addiert. Sonst wird der Artikel dem Einkaufskorb hinzugefuegt.
        boolean bereitsEnthalten = false;
        for (Artikel a : einkaufskorb) {
            if (a.getArtikelnummer() == id) {
                a.erhoehenMenge(menge);
                bereitsEnthalten = true;
                return false;
            }
        }
        if (!bereitsEnthalten) {
            einkaufskorb.add(artikel);
        }
        return true;
    }

    /**
     * Storniert einen Artikel.
     *
     * @param artikel der Artikel der storniert werden soll.
     */
    public static void stornierenArtikel(Artikel artikel) {
        einkaufskorb.remove(artikel);
    }

    /**
     * Entfernt den letzen Artikel aus dem Einkaufskorb. Ist der Einkaufskorb
     * leer passiert nichts.
     */
    public static void stornierenLetztenArtikel() {

        if (einkaufskorb.size() > 0) {
            einkaufskorb.remove(einkaufskorb.size() - 1);
        }
    }

    /**
     * Storniert den Einkauf.
     */
    public static void stornierenEinkauf() {

        einkaufskorb.clear();
    }

    /**
     * Schliesst den aktuellen Einkauf ab und gibt einen Kassenbon aus. Danach
     * wird der Einkaufskorb zurueckgesetzt.
     *
     * @return Ein Kassenbon-Objekt
     */
    public static Kassenbon abschliessenEinkauf() {

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
    public static int getGesamtpreis() {
        int gesamtpreis = 0;
        for (Artikel a : einkaufskorb) {
            gesamtpreis += a.getPreis();
        }
        return gesamtpreis;
    }

    public static ArrayList<Artikel> getEinkaufskorb() {
        return einkaufskorb;
    }
}
