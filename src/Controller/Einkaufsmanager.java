/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.Artikel;
import model.Kassenbon;
import java.util.ArrayList;
import java.util.Collections;
import model.Mehrwertsteuer;

/**
 *
 * @author lykoju Klasse welche einen Einkaufsablauf managed
 */
public class Einkaufsmanager {

    private static final ArrayList<Artikel> EINKAUFSKORB = new ArrayList<>();

    private Einkaufsmanager() {
    }

    /**
     * Fuegt dem Einkaufskorb einen Artikel anhand der ID hinzu.
     *
     * @param id ID des Artikels in der Datenbank
     * @param menge die Menge des Artikels im Einkauf
     * @return {@code true} wenn der Artikel erfolgreich hinzugefuegt wurde,
     * sonst {@code false}
     */
    public static boolean hinzufuegenArtikel(int id, int menge) {
        Artikel artikel = DBVerbindung.getArtikelbyID(id, menge);
        if (artikel == null) {
            return false;
        }
        // Ist der Artikel bereits enthalten wird die Menge addiert. Sonst wird der Artikel dem Einkaufskorb hinzugefuegt.
        for (Artikel a : EINKAUFSKORB) {
            if (a.getId() == id) {
                a.erhoehenMenge(menge);
                return true;
            }
        }
        //hinzufuegen eines neuen Artikels
        EINKAUFSKORB.add(artikel);
        return true;
    }

    /**
     * Storniert einen Artikel.
     *
     * @param artikel der Artikel der storniert werden soll.
     */
    public static void stornierenArtikel(Artikel artikel) {
        EINKAUFSKORB.remove(artikel);
    }

    /**
     * Entfernt den letzen Artikel aus dem Einkaufskorb. Ist der Einkaufskorb
     * leer passiert nichts.
     */
    public static void stornierenLetztenArtikel() {

        if (EINKAUFSKORB.size() > 0) {
            EINKAUFSKORB.remove(EINKAUFSKORB.size() - 1);
        }
    }

    /**
     * Storniert den Einkauf.
     */
    public static void stornierenEinkauf() {
        EINKAUFSKORB.clear();
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
        Collections.copy(einkaufsbonkorb, EINKAUFSKORB);
        Kassenbon kassenbon = new Kassenbon(einkaufsbonkorb);
        EINKAUFSKORB.clear();
        return kassenbon;
    }

    /**
     * Berechnet den Gesamtpreis des Einkaufskorbs und gibt ihn zurueck.
     *
     * @return Der Gesamtpreis des Einkaufskorbs
     */
    public static int getGesamtpreis() {
        int gesamtpreis = 0;
        for (Artikel a : EINKAUFSKORB) {
            gesamtpreis += a.getPreis();
        }
        return gesamtpreis;
    }

    public static ArrayList<Artikel> getEinkaufskorb() {
        return EINKAUFSKORB;
    }

    /**
     * Gibt einen String aus welcher den Gesamtpreis formatiert anzeigt.
     *
     * @return der Gesamtpreis formatiert als String
     */
    public static String getGesamtPreisString() {
        String nullen = String.format("%03d€", getGesamtpreis());
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    /**
     * Gibt einen String aus welcher die Nettomehrwertsteuer fuer den gesamten Einkauf formatiert anzeigt.
     * @return die Nettomehrwertsteuer formatiert als String
     */
    public static String getGesamtMwstString() {
        int gesamtmwst = 0;
        float mwst;
        for (Artikel a : EINKAUFSKORB) {
            mwst = DBVerbindung.getMwstByKlasse(a.getMehrwertsteuerklasse()).getSteuer();
            gesamtmwst += Math.round((a.getPreis() / 1+mwst)*mwst);
        }
        String gesamtString = String.format("%03d€", gesamtmwst);
        return gesamtString.substring(0, gesamtString.length() - 3) + ',' + gesamtString.substring(gesamtString.length() - 3);
    }
}
