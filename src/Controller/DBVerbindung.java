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
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void main(String args[]) {

        Statement stmt = null;
        ResultSet result = null;

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
}
