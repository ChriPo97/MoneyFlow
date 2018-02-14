/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Artikel;
import model.Artikel.Einheit;
import model.Kategorie;
import model.Mehrwertsteuer;

/**
 *
 * @author Josua
 */
public class DBVerbindung {

    private static Connection con = null;
    private static final String URL = "jdbc:sqlite:DB/MoneyDB.db";
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static ArrayList<Mehrwertsteuer> mwstListe = new ArrayList<>();

    public static void main(String args[]) {

        verbinden();
        //System.out.println(artikelNametoArtikelID("Bier"));
        // System.out.println(artikelNametoKategorie("Stuhl"));
        //System.out.println(artikelNametoArtikelNummer("Bier"));
        //System.out.println(artikelNametoMehrwersteuerklasse("Apfel"));
        //System.out.println(artikelNametoEinheit("Bier"));
        //System.out.println(artikelNametoPreis("Mango"));
        //System.out.println(artikelNametoMenge("Mango"));
        //System.out.println(mehrwersteuerklassetoMehrwertsteuer("1"));
        //artikelAnlegen("Wodka", "Alkohol", 1000, "Stück", 1, 38);
        Artikel a = getArtikelbyID(4);
        System.out.println(a.getName());
        //artikelTest();
        verbindungSchliessen();

    }

//Verbindung zur Datenbank herstellen
    public static void verbinden() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(URL);
            System.out.println("Opened database successfully");
            // Lade die Mwstklassen
            ps = con.prepareStatement("SELECT * FROM Mehrwertsteuer");
            rs = ps.executeQuery();
            while (rs.next()) {
                mwstListe.add(new Mehrwertsteuer(rs.getInt(1), rs.getString(2).charAt(0), rs.getFloat(3)));
            }
            rs.close();
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artikelName;
    }

    //neuen Artikel anlegen
    public static void artikelAnlegen(String artikelname, String kategorie, int preis, String einheit, int mehrwertsteuerklasse, int menge) {

        try {
            ps = con.prepareStatement("INSERT INTO Artikel "
                    + "(artikelname, "
                    + "kategorie, "
                    + "preis, "
                    + "einheit, "
                    + "mehrwertsteuerklasse, "
                    + "menge) "
                    + "VALUES (?,?,?,?,?,?)");
            ps.setString(1, artikelname);
            ps.setString(2, kategorie);
            ps.setInt(3, preis);
            ps.setString(4, einheit);
            ps.setInt(5, mehrwertsteuerklasse);
            ps.setInt(6, menge);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void artikelTest() {
        String artikelname = "Mango";
        String kategorie = "Obst";
        int preis = 70;
        String einheit = "Stück";
        int mwst = 2;
        int menge = 100;
        try {
            ps = con.prepareStatement("INSERT INTO Artikel "
                    + "(artikelname, "
                    + "kategorie, "
                    + "preis, "
                    + "einheit, "
                    + "mehrwertsteuerklasse, "
                    + "menge) "
                    + "VALUES (?,?,?,?,?,?)");
            ps.setString(1, artikelname);
            ps.setString(2, kategorie);
            ps.setInt(3, preis);
            ps.setString(4, einheit);
            ps.setInt(5, mwst);
            ps.setInt(6, menge);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Artikel getArtikelbyID(int artikelID) {
        String name = "";
        Kategorie kategorie = null;
        int preis = 0;
        Einheit einheit = null;
        char mehrwertsteuerklasse = 'A';
        int menge = 1;

        try {
            ps = con.prepareStatement("SELECT * FROM Artikel WHERE AID = ?");
            ps.setInt(1, artikelID);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("Artikelname");
                kategorie = new Kategorie(1, rs.getString("Kategorie"));
                preis = rs.getInt("Preis");
                String tmp = rs.getString("Einheit");
                if (tmp.equals("Stück")) {
                    einheit = Artikel.Einheit.STÜCK;
                } else {
                    if (tmp.equals("Gewicht")) {
                        einheit = Artikel.Einheit.GEWICHT;
                    }
                }
                //Mehrwertsteuerklasse ermitteln
                int mwstId = rs.getInt("Mehrwertsteuerklasse");
                for (Mehrwertsteuer m : mwstListe) {
                    if (mwstId == m.getId()) {
                        mehrwertsteuerklasse = m.getKlasse();
                    }
                }
                Artikel artikelObjekt = new Artikel(name, kategorie, artikelID, preis, einheit, mehrwertsteuerklasse, menge);
                return artikelObjekt;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Mehrwertsteuer getMwstById(int id) {
        for (Mehrwertsteuer m : mwstListe) {
            if (m.getId() == id) {
                return m;
            }
        }
        return Mehrwertsteuer.MWST_NULL;
    }

    public static Mehrwertsteuer getMwstByKlasse(char klasse) {
        for (Mehrwertsteuer m : mwstListe) {
            if (m.getKlasse() == klasse) {
                return m;
            }
        }
        return Mehrwertsteuer.MWST_NULL;
    }
}
