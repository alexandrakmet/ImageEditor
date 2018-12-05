package sample.filters;

import com.jhlabs.image.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Alexandra on 05.12.2018.
 */
public class Filter {
/*      Lang langNo = new Lang(0, "Origin");
        Lang langExpo = new Lang(1, "ExposureFilter");
        Lang langGray = new Lang(2, "GrayFilter");
        Lang langGraysc = new Lang(3, "GrayscaleFilter");
        Lang langBlur = new Lang(4, "BlurFilter");
        Lang langSmear = new Lang(5, "SmearFilter");
        Lang langCurv = new Lang(6, "CurvesFilter");*/


    public Image filter(String name, Image image, Image originalImage) {
        BufferedImage img = toBufferedImage(image);

        if (name.equals("Origin")) {
             img = toBufferedImage(originalImage);
        }

        if (name.equals("ExposureFilter")) {
            ExposureFilter exposureFilter = new ExposureFilter();
            exposureFilter.filter(img,img);
        }

        if (name.equals("GrayFilter")) {
            GrayFilter grayFilter = new GrayFilter();
            grayFilter.filter(img,img);
        }

        if (name.equals("GrayscaleFilter")) {
            GrayscaleFilter grayscaleFilter = new GrayscaleFilter();
            grayscaleFilter.filter(img, img);
        }

        if (name.equals("GainFilter")) {
            GainFilter blurFilter = new GainFilter();
            blurFilter.setBias(0.15f);
            blurFilter.setGain(0.07f);
            blurFilter.filter(img, img);
        }

        if (name.equals("SmearFilter")) {
            SmearFilter smearFilter = new SmearFilter();
            smearFilter.filter(img, img);
        }

        if (name.equals("NoiseFilter")) {
            NoiseFilter noiseFilter = new NoiseFilter();
            noiseFilter.filter(img, img);
        }

        image = SwingFXUtils.toFXImage(img, null);
        return image;
    }


    public static BufferedImage toBufferedImage(Image img) {


        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage((int) img.getWidth(), (int) img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(SwingFXUtils.fromFXImage(img, null), 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
