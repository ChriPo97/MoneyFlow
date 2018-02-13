/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lykoju Diese Klasse liest die Properties ein und macht sie fuer das
 * Programm verfuegbar
 */
public class Propertymanager {

    private static Properties props = new Properties();
    
    public static void ladenProperties() {
        try {
            FileInputStream in = new FileInputStream("MoneyFlow.properties");
            props.load(in);
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Propertymanager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getProperty(String property) {
        return props.getProperty(property);
    }

}
