/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lykoju Diese Klasse liest die Properties ein und macht sie fuer das
 * Programm verfuegbar
 */
public class Propertymanager {

    private static final Properties PROPS = new Properties();
    private static final List<Entry<String, String>> ALLE_PROPS = new ArrayList<>();

    public static void ladenProperties() {
        try (FileInputStream in = new FileInputStream("MoneyFlow.properties")) {
            PROPS.load(in);
            for (Entry e : PROPS.entrySet()) {
                Entry newEntry = new SimpleEntry<>(e.getKey(), e.getValue());
                ALLE_PROPS.add(newEntry);
            }
        } catch (IOException ex) {
            Logger.getLogger(Propertymanager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void schreibenProperties() {
        try (FileOutputStream out = new FileOutputStream("MoneyFlow.properties")) {
            PROPS.store(out, "MoneyFlow.properties");
        } catch (IOException ex) {
            Logger.getLogger(Propertymanager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getProperty(String property) {
        return PROPS.getProperty(property);
    }
    
    public static void setProperty(String property, String propertyText) {
        PROPS.setProperty(property, propertyText);
    }

    public static List<Entry<String, String>> getAlleProperties() {
        return ALLE_PROPS;
    }

}
