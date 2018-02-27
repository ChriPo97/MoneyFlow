/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author Christoph
 */
public class Druckaufbereitung {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException, PrintException {

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.txt"), "Cp437"));
        out.write("äöüa");
        out.close();
        print();

    }

    private static void print() throws FileNotFoundException, PrintException {
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        FileInputStream in = new FileInputStream(new File("test.txt"));
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc mydoc = new SimpleDoc(in, flavor, null);
        DocPrintJob job = service.createPrintJob(); 
        job.print(mydoc, null);
    }

}
