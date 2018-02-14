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
import model.Mehrwertsteuer;

/**
 *
 * @author lykoju Klasse welche einen Einkaufsablauf managed
 */
public class Einkaufsmanager {

    //Aktuell nicht in Benutzung
    private HashMap artikelTabelle = new HashMap();
    private static final ArrayList<Artikel> einkaufskorb = new ArrayList<>();

    private Einkaufsmanager() {
    }

    /**
     * Fuegt dem Einkaufskorb einen Artikel anhand der ID hinzu.
     *
     * @param id ID des Artikels in der Datenbank
     * @return Der Artikel der hinzugefuegt wurde, oder (@code null) wenn dieser
     * nicht gefunden wurde.
     */
    public static Artikel hinzufuegenArtikel(int id, int menge) {

        Artikel artikel = DBVerbindung.getArtikelbyID(id);
        // Ist der Artikel bereits enthalten wird die Menge addiert. Sonst wird der Artikel dem Einkaufskorb hinzugefuegt.
        System.out.println(artikel.getMenge());
        boolean bereitsEnthalten = false;
        for (Artikel a : einkaufskorb) {
            if (a.getId() == id) {
                a.erhoehenMenge(menge);
                bereitsEnthalten = true;
                return artikel;
            }
        }
        if (!bereitsEnthalten) {
            //hinzufuegen eines neuen Artikels
            einkaufskorb.add(artikel);
        }
        return artikel;
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

    public static String getGesamtPreisString() {
        String nullen = String.format("%04d€", getGesamtpreis());
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }
}
