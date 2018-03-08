/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author lykoju Diese Klasse liest die Properties ein und macht sie fuer das
 * Programm verfuegbar
 */
public class Propertymanager {

    private static final Properties PROPS = new Properties();

    public static void ladenProperties() {
        try (FileInputStream in = new FileInputStream("cfg/" + "MoneyFlow.properties")) {
            PROPS.load(in);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "The PROPERTIES file could not be loaded!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void schreibenProperties() {
        try (FileOutputStream out = new FileOutputStream("MoneyFlow.properties")) {
            PROPS.store(out, "MoneyFlow.properties");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Coul not write to the PROPERTIES file!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String getProperty(String property) {
        return PROPS.getProperty(property);
    }

    public static void setProperty(String property, String propertyText) {
        PROPS.setProperty(property, propertyText);
    }

    public static List<Entry<String, String>> getAlleProperties() {
        List<Entry<String, String>> propListe = new ArrayList<>();
        for (Entry e : PROPS.entrySet()) {
            Entry newEntry = new SimpleEntry<>(e.getKey(), e.getValue());
            propListe.add(newEntry);
        }
        return propListe;
    }

}
