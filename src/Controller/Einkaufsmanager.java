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
     * @return der Artikel der hinzugefuegt wurde, oder {@code null} wenn dieser
     * nicht gefunden wurde
     */
    public static Artikel hinzufuegenArtikel(int id, int menge) {
        Artikel artikel = DBVerbindung.getArtikelbyID(id, menge);
        // Ist der Artikel bereits enthalten wird die Menge addiert. Sonst wird der Artikel dem Einkaufskorb hinzugefuegt.
        boolean bereitsEnthalten = false;
        for (Artikel a : EINKAUFSKORB) {
            if (a.getId() == id) {
                a.erhoehenMenge(menge);
                bereitsEnthalten = true;
                return artikel;
            }
        }
        if (!bereitsEnthalten) {
            //hinzufuegen eines neuen Artikels
            EINKAUFSKORB.add(artikel);
        }
        return artikel;
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
     * @return der Gesamtpreis formatiert als String
     */
    public static String getGesamtPreisString() {
        String nullen = String.format("%04d€", getGesamtpreis());
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }
}
