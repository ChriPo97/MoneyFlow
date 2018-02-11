/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

/**
 *
 * @author lykoju Diese Klasse liest die Properties ein und macht sie fuer das Programm verfuegbar
 */
public class Propertymanager {
    
    //TODO Property-Datei einlesen und Properties verfuegbar machen
    
    public static Object getProperty(String property) {
        
        //TODO
        if (property.equals("impressum")) {
            
            return "MoneyFlow GmbH, 12345 Geldstraße Berlin";
        }
        return null;
    }

}
