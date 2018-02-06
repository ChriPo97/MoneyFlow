/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.*;
import java.util.ArrayList;

/**
 *
 * @author lykoju
 * Klasse welche einen Einkaufsablauf managed
 */
public class Einkaufmanager {
    
    private static ArrayList<Artikel> einkaufskorb = new ArrayList<>();

    public Einkaufmanager() {
        
    }
    
    public boolean hinzufuegenArtikel (int id) {
        
        return false;
    }
    /**
     * Storniert einen Artikel.
     * @param artikel der Artikel der storniert werden soll.
     */
    public void stornierenArtikel(Artikel artikel) {
        
        
    }
    /**
     * Entfernt den letzen Artikel aus dem Einkaufskorb.
     */
    public void stornierenLetztenArtikel() {
        
        if (einkaufskorb.size()>0) {
            einkaufskorb.remove(einkaufskorb.size()-1);
        }
    }
    
    public Kassenbon abschliessenEInkauf() {
        
        return null;
    }
    
    public static ArrayList<Artikel> getEinkaufskorb() {
        return einkaufskorb;
    }

}
