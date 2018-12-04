package sample;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jhlabs.image.BoxBlurFilter;
import com.jhlabs.image.ContrastFilter;
import com.jhlabs.image.GrayscaleFilter;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;


public class WaterMarkResizeImage {


    public static void main(String[] args) throws IOException {

        File originalImage = new File("C:\\Users\\Alexandra\\IdeaProjects\\labWork3\\res\\img7.jpg");
        //read as buffered image
        BufferedImage image = read(originalImage);

        //resize
       // resize(image);


       /* ContrastFilter filt = new ContrastFilter();
        filt.setBrightness(1.2f);
        filt.setContrast(1.5f);
        filt.filter(image, image);
*/

        GrayscaleFilter filter = new GrayscaleFilter();
        filter.filter(image,image);
        //write water mark
        //watermark(image);
        //apply(image);
        //save
        write(image);
    }


    public static BufferedImage read(File originalImage) throws IOException {
        return ImageIO.read(originalImage);
    }

    public static void resize(BufferedImage image) {
        Scalr.resize(image, Method.QUALITY, 600, Scalr.OP_BRIGHTER);//OP_ANTIALIAS);
    }


    public static void watermark(BufferedImage image) throws IOException {
        Graphics2D graphics2D = (Graphics2D)image.getGraphics();
        graphics2D.setFont(new Font("Arial", Font.BOLD, 300));
        graphics2D.setColor(new Color(0f,0f,0f,0.07f));
        graphics2D.drawString("Watermarked!", image.getWidth()/100, image.getHeight() / 4*3);

    }

    public static void apply(BufferedImage img) {
        //0,21R+ 0,71 G+ 0,07 B
        for (int y = 0; y < img.getHeight(); ++y) {
            for (int x = 0; x < img.getWidth(); ++x) {
                // Get color channels of the pixel located at x,y.
                Color color = new Color(img.getRGB(x, y));
                int graylevel = (int) Math.floor(0.21*color.getRed()+0.71*color.getGreen()+0.07*color.getBlue());
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