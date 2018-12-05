package sample.filters;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jhlabs.image.SmearFilter;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;


public class WaterMarkImage {

    public static BufferedImage read(File originalImage) throws IOException {
        return ImageIO.read(originalImage);
    }


    public static void resize(BufferedImage image) {
        Scalr.resize(image, Method.QUALITY, 600, Scalr.OP_BRIGHTER);//OP_ANTIALIAS);
    }


    public static BufferedImage watermark(BufferedImage image, String text, int size) throws IOException {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setFont(new Font("Arial", Font.BOLD, size));
        graphics2D.setColor(new Color(0f, 0f, 0f, 0.07f));
        graphics2D.drawString(text, image.getWidth() / 100, image.getHeight() / 4 * 3);
        return image;
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


}