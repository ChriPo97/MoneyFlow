/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josua
 */
public class DBVerbindung {

    private static Connection con = null;
    private static String url = "jdbc:sqlite:C:/Programme/SQLiteStudio/MoneyDB.db";
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static void main(String args[]) {

        verbinden();
        // System.out.println(artikelNametoArtikelID("Apfel"));
        // System.out.println(artikelNametoKategorie("Stuhl"));
        //System.out.println(artikelNametoArtikelNummer("Bier"));
        System.out.println(artikelNametoMehrwersteuerklasse("Apfel"));
        //System.out.println(artikelNametoEinheit("Bier"));
        //System.out.println(artikelNametoPreis("Bier"));
        System.out.println(mehrwersteuerklassetoMehrwertsteuer("1"));
        
        verbindungSchliessen();

        /*         stmt = con.createStatement();
            String sql = "SELECT * FROM ARTIKEL";
            result = stmt.executeQuery(sql);

            while (result.next()) {

                System.out.println(result.getString(2));

            }
            //stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
         */
    }

//Verbindung zur Datenbank herstellen
    public static void verbinden() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(url);
            System.out.println("Opened database successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Verbindung zur Datenbank schliesse

    public static void verbindungSchliessen() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kategorie;
    }

    //zu gegebenem Artikelnamen Artikelnummer ausgeben
    public static int artikelNametoArtikelNummer(String artikelname) {
        int artikelnummer = 0;
        try {
            ps = con.prepareStatement("SELECT Artikelnummer FROM Artikel WHERE Artikelname = ?");
            ps.setString(1, artikelname);
            rs = ps.executeQuery();
            rs.next();
            artikelnummer = rs.getInt("Artikelnummer");
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artikelnummer;
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mwstk;
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mwst;
    }
}
