package sample.filters;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;




public class WaterMarkImage {

    public static BufferedImage read(File originalImage) throws IOException {
        return ImageIO.read(originalImage);
    }


   /* public static void resize(BufferedImage image) {
        Scalr.resize(image, Method.QUALITY, 600, Scalr.OP_BRIGHTER);//OP_ANTIALIAS);
    }*/


    public Image watermark(Image img, String text, int size) throws IOException {
        BufferedImage image = toBufferedImage(img);
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setFont(new Font("Arial", Font.BOLD, size));
        graphics2D.setColor(new Color(0f, 0f, 0f, 0.07f));
        graphics2D.drawString(text, image.getWidth() / 100, image.getHeight() / 4 * 3);
        return SwingFXUtils.toFXImage(image, null);
    }

    public static void apply(BufferedImage img) {
        //0,21R+ 0,71 G+ 0,07 B
        for (int y = 0; y < img.getHeight(); ++y) {
            for (int x = 0; x < img.getWidth(); ++x) {
                // Get color channels of the pixel located at x,y.
                Color color = new Color(img.getRGB(x, y));
                int graylevel = (int) Math.floor(0.21 * color.getRed() + 0.71 * color.getGreen() + 0.07 * color.getBlue());
                // The following line creates the gray level as a color
                color = new Color(graylevel, graylevel, graylevel);
                // Set the color of the pixel located at x,y.
                img.setRGB(x, y, color.getRGB());
            }
        }


    }


    public static void write(BufferedImage image) throws IllegalArgumentException, ImagingOpException, IOException {
        ImageIO.write(image, "JPG", new File("C:\\Users\\Alexandra\\IdeaProjects\\labWork3\\res\\img7_t.jpg"));
    }

    public static BufferedImage toBufferedImage(Image image) {
        BufferedImage bimage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bmGr = bimage.createGraphics();
        bmGr.drawImage(SwingFXUtils.fromFXImage(image, null), 0, 0, null);
        bmGr.dispose();
        return bimage;
    }
}