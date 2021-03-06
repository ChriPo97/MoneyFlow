/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Artikel;
import model.Mehrwertsteuer;

/**
 *
 * @author Josua
 */
public class DBVerbindung {

    private static Connection con = null;
    private static final String URL = "jdbc:sqlite:" + Propertymanager.getProperty("DatabaseFile");
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static final ArrayList<Mehrwertsteuer> MWSTLISTE = new ArrayList<>();

//Verbindung zur Datenbank herstellen
    public static void verbinden() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            con = DriverManager.getConnection(URL);
            // Lade die Mwstklassen
            ps = con.prepareStatement("SELECT * FROM Mehrwertsteuer");
            rs = ps.executeQuery();
            while (rs.next()) {
                MWSTLISTE.add(new Mehrwertsteuer(rs.getInt(1), rs.getString(2).charAt(0), rs.getFloat(3)/100));
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
//Verbindung zur Datenbank schliesse

    public static void verbindungSchliessen() {
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Datenbankoperationen 
    // zu gegebenem Artikelnamen ArtikelID ausgeben   
    public static int artikelNametoArtikelID(String artikelname) {
        int artikelID = 0;
        try {
            ps = con.prepareStatement("SELECT AID FROM Artikel WHERE Artikelname = ?");
            ps.setString(1, artikelname);
            rs = ps.executeQuery();
            rs.next();
            artikelID = rs.getInt("AID");
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return artikelID;
    }

    //zu gegebenem Artikelnamen Kategorie ausgeben
    public static String artikelNametoKategorie(String artikelname) {
        String kategorie = "";
        try {
            ps = con.prepareStatement("SELECT Kategorie FROM Artikel WHERE Artikelname = ?");
            ps.setString(1, artikelname);
            rs = ps.executeQuery();
            rs.next();
            kategorie = rs.getString("Kategorie");
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return kategorie;
    }

    //zu gegebenem Artikelnamen Preis ausgeben
    public static int artikelNametoPreis(String artikelname) {
        int preis = 0;
        try {
            ps = con.prepareStatement("SELECT Preis FROM Artikel WHERE Artikelname = ?");
            ps.setString(1, artikelname);
            rs = ps.executeQuery();
            rs.next();
            preis = rs.getInt("Preis");
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return preis;
    }

    //zu gegebenem Artikelnamen Einheit ausgeben
    public static String artikelNametoEinheit(String artikelname) {
        String einheit = "";
        try {
            ps = con.prepareStatement("SELECT Einheit FROM Artikel WHERE Artikelname = ?");
            ps.setString(1, artikelname);
            rs = ps.executeQuery();
            rs.next();
            einheit = rs.getString("Einheit");
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return einheit;
    }

    //zu gegebenem Artikelnamen Mehrwertsteuerklasse ausgeben
    public static String artikelNametoMehrwersteuerklasse(String artikelname) {
        String mwstk = "";
        try {
            ps = con.prepareStatement("SELECT Mehrwertsteuerklasse FROM Artikel WHERE Artikelname = ?");
            ps.setString(1, artikelname);
            rs = ps.executeQuery();
            rs.next();
            mwstk = rs.getString("Mehrwertsteuerklasse");
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return mwstk;
    }

    //zu gegebenem Artikelnamen Menge ausgeben
    public static int artikelNametoMenge(String artikelname) {
        int menge = 0;
        try {
            ps = con.prepareStatement("SELECT Menge FROM Artikel WHERE Artikelname = ?");
            ps.setString(1, artikelname);
            rs = ps.executeQuery();
            rs.next();
            menge = rs.getInt("Menge");
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return menge;
    }

    //Mehrwertsteuer bei gegebener Mehrwertsteuerklasse ausgeben
    public static float mehrwersteuerklassetoMehrwertsteuer(String mehrwertsteuerklasse) {
        float mwst = 0;
        try {
            ps = con.prepareStatement("SELECT  Steuersatz FROM Mehrwertsteuer WHERE MID = ?");
            ps.setString(1, mehrwertsteuerklasse);
            rs = ps.executeQuery();
            rs.next();
            mwst = rs.getFloat("Steuersatz");
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return mwst;
    }

    // zu gegebenem ArtikelID Artikelnamen ausgeben   
    public static String artikelIDtoArtikelName(int artikelid) {
        String artikelName = "";
        try {
            ps = con.prepareStatement("SELECT Artikelname FROM Artikel WHERE AID = ?");
            ps.setInt(1, artikelid);
            rs = ps.executeQuery();
            rs.next();
            artikelName = rs.getString("Artikelname");
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return artikelName;
    }

    //neuen Artikel anlegen
    public static void artikelAnlegen(String artikelname, String kategorie, int preis, String einheit, int mehrwertsteuerklasse) {

        try {
            ps = con.prepareStatement("INSERT INTO Artikel "
                    + "(artikelname, "
                    + "kategorie, "
                    + "preis, "
                    + "einheit, "
                    + "mehrwertsteuerklasse)"
                    + "VALUES (?,?,?,?,?)");
            ps.setString(1, artikelname);
            ps.setString(2, kategorie);
            ps.setInt(3, preis);
            ps.setString(4, einheit);
            ps.setInt(5, mehrwertsteuerklasse);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Artikel getArtikelbyID(int artikelID, int menge) {
        String name;
        String kategorie;
        int preis;
        Artikel.Einheit einheit = null;
        char mehrwertsteuerklasse;
        try {
            ps = con.prepareStatement("SELECT * FROM Artikel WHERE AID = ?");
            ps.setInt(1, artikelID);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("Artikelname");
                kategorie = rs.getString("Kategorie");
                preis = rs.getInt("Preis");
                String tmp = rs.getString("Einheit");
                if (tmp.equals("STUECK")) {
                    einheit = Artikel.Einheit.PIECE;
                } else {
                    if (tmp.equals("GEWICHT")) {
                        einheit = Artikel.Einheit.WEIGHT;
                    }
                }
                //Mehrwertsteuerklasse ermitteln
                mehrwertsteuerklasse = DBVerbindung.getMwstById(rs.getInt("Mehrwertsteuerklasse")).getKlasse();
                Artikel artikelObjekt = new Artikel(name, kategorie, artikelID, preis, einheit, mehrwertsteuerklasse, menge);
                return artikelObjekt;
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    //alle Artikel aus Datenbank auslesen
    public static ArrayList<Artikel> alleArtikelAuslesen() {
        ArrayList<Artikel> artikelListe = new ArrayList<>();
        Artikel artikel;
        int artikelID;
        String artikelname;
        String kategorie;
        int preis;
        Artikel.Einheit einheit = null;
        char mwstklasse;
        try {

            ps = con.prepareStatement("SELECT * FROM Artikel");
            rs = ps.executeQuery();
            while (rs.next()) {
                //auslesen der einzelnen Artikeldaten
                artikelID = rs.getInt("AID");
                artikelname = rs.getString("Artikelname");
                kategorie = rs.getString("Kategorie");
                preis = rs.getInt("Preis");
                 String tmp = rs.getString("Einheit");
                if (tmp.equals("STUECK")) {
                    einheit = Artikel.Einheit.PIECE;
                } else {
                    if (tmp.equals("GEWICHT")) {
                        einheit = Artikel.Einheit.WEIGHT;
                    }
                }
                
               mwstklasse = DBVerbindung.getMwstById(rs.getInt("Mehrwertsteuerklasse")).getKlasse();
               //Erstellen eines Artikel mit seinen zugehörigen Informationen
                artikel = new Artikel(artikelname, kategorie, artikelID, preis, einheit, mwstklasse,0);
                //Hinzufügen des Artikels zur ArrayList
                artikelListe.add(artikel);
           
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
        return artikelListe;
    }

    //Artikel bearbeiten basierend auf AID
    //Artikelname bearbeiten
    public static void artikelBearbeitenName(int artikelID, String artikelnameNeu) {
        try {
            ps = con.prepareStatement("UPDATE Artikel SET Artikelname=? WHERE AID=?");
            ps.setString(1, artikelnameNeu);
            ps.setInt(2, artikelID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Artikelkategorie bearbeiten
    public static void artikelBearbeitenKategorie(int artikelID, String kategorieNeu) {
        try {
            ps = con.prepareStatement("UPDATE Artikel SET Kategorie=? WHERE AID=?");
            ps.setString(1, kategorieNeu);
            ps.setInt(2, artikelID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Artikelpreis bearbeiten
    public static void artikelBearbeitenPreis(int artikelID, int preisNeu) {
        try {
            ps = con.prepareStatement("UPDATE Artikel SET Preis=? WHERE AID=?");
            ps.setInt(1, preisNeu);
            ps.setInt(2, artikelID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Artikeleinheit bearbeiten
    public static void artikelBearbeitenEinheit(int artikelID, String einheitNeu) {
        try {
            ps = con.prepareStatement("UPDATE Artikel SET Einheit=? WHERE AID=?");
            ps.setString(1, einheitNeu);
            ps.setInt(2, artikelID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
    }
    //Artikelmehrwertsteuer bearbeiten

    public static void artikelBearbeitenMwstklasse(int artikelID, int mwstNeu) {
        try {
            ps = con.prepareStatement("UPDATE Artikel SET Mehrwertsteuerklasse=? WHERE AID=?");
            ps.setInt(1, mwstNeu);
            ps.setInt(2, artikelID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Artikel basierend auf der AID löschen
    public static void artikelLoeschen(int artikelID) {
        try {
            ps = con.prepareStatement("DELETE FROM Artikel WHERE AID=?");
            ps.setInt(1, artikelID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Languagemanager.getProperty("DBVerbindung.error.text"), Languagemanager.getProperty("DBVerbindung.error.titel"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Mehrwertsteuer getMwstById(int id) {
        for (Mehrwertsteuer m : MWSTLISTE) {
            if (m.getId() == id) {
                return m;
            }
        }
        return Mehrwertsteuer.MWST_NULL;
    }

    public static Mehrwertsteuer getMwstByKlasse(char klasse) {
        for (Mehrwertsteuer m : MWSTLISTE) {
            if (m.getKlasse() == klasse) {
                return m;
            }
        }
        return Mehrwertsteuer.MWST_NULL;
    }
}
