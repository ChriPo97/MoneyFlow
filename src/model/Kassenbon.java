/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Controller.DBVerbindung;
import Controller.Einkaufsmanager;
import Controller.Languagemanager;
import Controller.Propertymanager;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;

/**
 *
 * @author lykoju
 */
public class Kassenbon {

    private final List<Artikel> warenkorb;
    private final LocalDateTime zeitstempel;
    private final String impressum;
    private static final DateTimeFormatter ZEITFORMATTER_NAME = DateTimeFormatter.ofPattern("HH-mm-ss");
    private static final DateTimeFormatter DATUMFORMATTER_NAME = DateTimeFormatter.ofPattern("dd-MM-YY");
    private static final DateTimeFormatter ZEITFORMATTER_BON = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATUMFORMATTER_BON = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private ArrayList<String> bonAufbereitet;
    private File bonFile;

    public Kassenbon(List<Artikel> warenkorb) {
        zeitstempel = LocalDateTime.now();
        this.warenkorb = warenkorb;
        impressum = Propertymanager.getProperty("Impressum");
        bonAufbereiten();
    }

    public List<Artikel> getWarenkorb() {
        return warenkorb;
    }

    /**
     * Gibt einen String aus, welcher den Zeitpunk des Einkaufs enhaelt.
     *
     * @return Der Zeitpunkt des Einkaufs als String
     */
    public String getZeitStringName() {
        return zeitstempel.format(Kassenbon.ZEITFORMATTER_NAME);
    }

    /**
     * Gibt einen String aus, welcher den Zeitpunk des Einkaufs enhaelt.
     *
     * @return Der Zeitpunkt des Einkaufs als String
     */
    public String getDatumStringName() {
        return zeitstempel.format(Kassenbon.DATUMFORMATTER_NAME);
    }
    
        /**
     * Gibt einen String aus, welcher den Zeitpunk des Einkaufs enhaelt.
     *
     * @return Der Zeitpunkt des Einkaufs als String
     */
    public String getZeitStringBon() {
        return zeitstempel.format(Kassenbon.ZEITFORMATTER_BON);
    }

    /**
     * Gibt einen String aus, welcher den Zeitpunk des Einkaufs enhaelt.
     *
     * @return Der Zeitpunkt des Einkaufs als String
     */
    public String getDatumStringBon() {
        return zeitstempel.format(Kassenbon.DATUMFORMATTER_BON);
    }

    public String getImpressum() {
        return impressum;
    }

    public String getGesamtpreisString() {
        int gesamtpreis = 0;
        for (Artikel a : warenkorb) {
            gesamtpreis += a.getPreis();
        }
        String nullen = String.format("%03d€", gesamtpreis);
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    public int getBruttoByMwstklasse(char mwstklasse) {
        int brutto = 0;
        //Summiere alle Preise
        for (Artikel a : warenkorb) {
            if (a.getMehrwertsteuerklasse() == mwstklasse) {
                brutto += a.getPreis();
            }
        }
        return brutto;
    }

    public int getNettoByMwstklasse(char mwstklasse) {
        //Ermittle den Mehrwertsteuersatz fuer die gesuchte Klasse;
        float satz = 1 + DBVerbindung.getMwstByKlasse(mwstklasse).getSteuer();
        int netto = 0;
        //Summiere alle Preise
        for (Artikel a : warenkorb) {
            if (a.getMehrwertsteuerklasse() == mwstklasse) {
                netto += a.getPreis() / satz;
            }
        }
        return netto;
    }

    /**
     * Gibt einen String aus, welcher den Gesamtpreis einer Mehrwertsteuerklasse
     * in Brutto (Ladenpreis) formatiert anzeigt.
     *
     * @param mwstklasse die Klasse der Mehrwertsteuer, z.B. 'A'
     * @return ein formatierter String des Gesamtpreises einer
     * Mehrwertsteuerklasse in Brutto
     */
    public String getBruttoByMwstklasseString(char mwstklasse) {
        String nullen = String.format("%03d€", getBruttoByMwstklasse(mwstklasse));
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    /**
     * Gibt einen String aus, welcher den Gesamtpreis einer Mehrwertsteuerklasse
     * in Netto (vor Steuer) formatiert anzeigt.
     *
     * @param mwstklasse die Klasse der Mehrwertsteuer, z.B. 'A'
     * @return ein formatierter String des Gesamtpreises einer
     * Mehrwertsteuerklasse in Netto
     */
    public String getNettoByMwstklasseString(char mwstklasse) {
        String nullen = String.format("%03d€", getNettoByMwstklasse(mwstklasse));
        return nullen.substring(0, nullen.length() - 3) + ',' + nullen.substring(nullen.length() - 3);
    }

    public ArrayList<String> getKassebonAufbereitet() {
        return bonAufbereitet;
    }

    private void bonAufbereiten() {
        bonAufbereitet = new ArrayList<String>();
        ArrayList<String> tempKategorien = new ArrayList<String>();
        bonAufbereitet.add(Propertymanager.getProperty("Impressum"));
        bonAufbereitet.add(this.getDatumStringBon()+ " " + this.getZeitStringBon());
        bonAufbereitet.add("");
        for (Artikel artikel : this.getWarenkorb()) {
            if (!tempKategorien.contains(artikel.getKategorie())) {
                tempKategorien.add(artikel.getKategorie());
            }
        }
        for (String kategorie : tempKategorien) {
            bonAufbereitet.add(kategorie + "\n");
            for (Artikel artikel : this.getWarenkorb()) {
                if (artikel.getKategorie().equals(kategorie)) {
                    bonAufbereitet.add(" " + artikel.getMengeFormatiert() + " " + artikel.getName() + " "
                            + artikel.getPreisString() + " " + artikel.getMehrwertsteuerklasse() + "\n");
                }
            }
        }
        bonAufbereitet.add("");
        bonAufbereitet.add(Languagemanager.getProperty("Kassenbon.Summe") + ": " + this.getGesamtpreisString());
        bonAufbereitet.add("");
        bonAufbereitet.add(Languagemanager.getProperty("Kassenbon.MwSt") + " A: " + this.getNettoByMwstklasseString('A'));
        bonAufbereitet.add(Languagemanager.getProperty("Kassenbon.MwSt") + " B: " + this.getNettoByMwstklasseString('B'));
    }

    public void saveBon() throws IOException {
        bonFile = new File(Propertymanager.getProperty("BonDirectory") + "bon_" + this.getZeitStringName()+ "_" + this.getDatumStringName()+ ".txt");
        bonFile.getParentFile().mkdirs();
        bonFile.createNewFile();
        Files.write(bonFile.toPath(), this.getKassebonAufbereitet(), Charset.forName(Propertymanager.getProperty("BonCharset")));
    }

    public void printBon() throws FileNotFoundException, PrintException, IOException {
        
        Desktop.getDesktop().print(bonFile);
        
//        FileInputStream textStream = new FileInputStream(bonFile);
//        
//        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//        aset.add(new Copies(1));
//        
//        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//        Doc mydoc = new SimpleDoc(textStream, flavor, null);
//
//        PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, aset);
//        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
//
//        if (services.length == 0) {
//            if (defaultService == null) {
//                System.out.println("no printer found");
//            } else {
//                System.out.println("using default");
//                DocPrintJob job = defaultService.createPrintJob();
//                job.print(mydoc, aset);
//            }
//        } else {
//
//            //built in UI for printing you may not use this
//            PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, aset);
//
//            if (service != null) {
//                DocPrintJob job = service.createPrintJob();
//                job.print(mydoc, aset);
//            }
//        }
    }

}
