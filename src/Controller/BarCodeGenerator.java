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
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 *
 * @author Christoph
 */
public class BarCodeGenerator {

    public static void generateCode128Barcode(int code) throws FileNotFoundException, IOException {
        Code128Bean eanBean = new Code128Bean();
        final int dpi = 300;
        eanBean.setModuleWidth(UnitConv.in2mm(3.0f / dpi));
        eanBean.setFontSize(2.0);
        eanBean.doQuietZone(true);
        File outputFile = new File("barcodes" + "/" + code + ".png");
        OutputStream out = new FileOutputStream(outputFile);
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        eanBean.generateBarcode(canvas, String.valueOf(code));
        canvas.finish();
    }

}
