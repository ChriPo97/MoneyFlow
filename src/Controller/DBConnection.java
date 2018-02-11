/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Yoshi
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private static Connection con;
    private static String url = "jdbc:sqlite:D:/sqlitestudio-3.1.1/SQLiteStudio/MoneyDB.db";
    private static PreparedStatement ps;

    public static void main(String args[]) {

        Statement stmt = null;
        ResultSet result = null;

        /*           String sql = "SELECT * FROM ARTIKEL";
            result = stmt.executeQuery(sql);
            
            while (result.next()) {

                System.out.println(result.getString(2));

            }
         */
        verbinden();
        System.out.println(artikelNametoArtikeId("Apfel"));
        verbindungSchliessen();

    }

    //Verbindung zur Datenbank herstellen
    public static void verbinden() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con = DriverManager.getConnection(url);
            System.out.println("Opened database successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Verbindung schliessen
    public static void verbindungSchliessen() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ;
// Abfrage der ArtikelID zu gegebenem Artikelnamen
public static int artikelNametoArtikeId(String artikelname) {
        int artikelId = 0;
        try {

            ps = con.prepareStatement("SELECT AID"
                    + " FROM ARTIKEL WHERE (Artikelname=?)");
            ps.setString(1, artikelname);
            ResultSet rs = ps.executeQuery();
            rs.next();
            artikelId = rs.getInt("AID");
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        return artikelId;

    }
}
