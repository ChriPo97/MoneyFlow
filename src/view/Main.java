/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.DBVerbindung;
import Controller.Languagemanager;
import Controller.Propertymanager;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ChriPo97 Klasse die zum Start des Programms aufgerufen wird. Sie
 * startet die GUI, lädt die Properties und stellt die Datenbank Verbindung her.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, "The Look and Feel could not be adjusted to the operating system.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Propertymanager.ladenProperties();
        Languagemanager.ladenProperties();
        
        LoginFenster login = new LoginFenster();
        
        

    }

}
