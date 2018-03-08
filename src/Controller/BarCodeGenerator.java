/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 *
 * @author ChriPo97 
 * Klasse zur Generierung und Löschung von BarCode-Dateien
 */
public class BarCodeGenerator {

    //Funktion zur Erstellung einer BarCode Datei im Code128 Format
    public static void generateCode128Barcode(int code) {
        OutputStream out = null;
        try {
            Code128Bean eanBean = new Code128Bean();
            //Auflösung
            final int dpi = 600;
            eanBean.setModuleWidth(UnitConv.in2mm(6.0f / dpi));
            eanBean.setFontSize(2.0);
            eanBean.doQuietZone(true);
            File outputFile = new File(Propertymanager.getProperty("BarcodeDirectory") + code + ".png");
            out = new FileOutputStream(outputFile);
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            eanBean.generateBarcode(canvas, String.valueOf(code));
            canvas.finish();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Die Generierung eines Barcodes war nicht möglich!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Funktion zum Löschen einer BarCode Datei
    public static void deleteBarcode(int code) {
        File file = new File(Propertymanager.getProperty("BarcodeDirectory") + code + ".png");
        file.delete();
    }

}
