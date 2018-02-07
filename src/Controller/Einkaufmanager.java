/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lykoju
 * Klasse welche einen Einkaufsablauf managed
 */
public class Einkaufmanager {
    
    private int gesamtpreis;
    private HashMap artikelTabelle = new HashMap();
    private ArrayList<Artikel> einkaufskorb = new ArrayList<>();

    public Einkaufmanager() {
    }
    
    public void ladenDatenbank() {
        //TODO Josy soll mal die DB-Methoden machen LUL
    }
    
    /**
     * Fuegt dem Einkaufskorb einen Artikel anhand der ID hinzu.
     * @param id ID des Artikels in der Datenbank
     * @return (@code true) wenn der Artikel erfolgreich in der Datenbank gefunden wurde, (@code false) wenn nicht.
     */
    public boolean hinzufuegenArtikel (int id, float menge) {
        //Logik um aus der DB Artikel nach id zu holen
        Artikel dummy = new Artikel("dummy", new Kategorie(-1, "dummy"), -1, 100, Artikel.Einheit.NUMMER, 1.09f, 10);
        gesamtpreis += dummy.getPreis();
        return false;
    }
    /**
     * Storniert einen Artikel.
     * @param artikel der Artikel der storniert werden soll.
     */
    public void stornierenArtikel(Artikel artikel) {
        einkaufskorb.remove(artikel);
    }
    /**
     * Entfernt den letzen Artikel aus dem Einkaufskorb. Ist der Einkaufskorb leer passiert nichts.
     */
    public void stornierenLetztenArtikel() {
        
        if (einkaufskorb.size()>0) {
            einkaufskorb.remove(einkaufskorb.size()-1);
        }
    }
    
    /**
     * Schließt den aktuellen Einkauf ab und gibt einen Kassenbon aus. Danach wird der Einkaufskorb zurueckgesetzt.
     * @return Ein Kassenbon-Objekt
     */
    public Kassenbon abschliessenEinkauf() {
        
        //Erstellt einen neue Liste und uebergibt sie einem Kassenbon. Danach wird der aktuelle Einkaufskorb geleert.
        ArrayList<Artikel> einkaufsbonkorb = new ArrayList<>();
        Collections.copy(einkaufsbonkorb, einkaufskorb);
//        Kassenbon kassenbon = new Kassenbon(einkaufsbonkorb);
        einkaufskorb.clear();
        return null;
    }

    public int getGesamtpreis() {
        return gesamtpreis;
    }

    public ArrayList<Artikel> getEinkaufskorb() {
        return einkaufskorb;
    }
    
    

}