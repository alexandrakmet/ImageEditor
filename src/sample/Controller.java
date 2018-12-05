package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Controller {

    private Stage mainStage;

    @FXML
    private ImageView originalImage;

    @FXML
    private ImageView modifiedImage;




    @FXML
    private void handleOpen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        String fileName;
        try {
            fileName = fileChooser.showOpenDialog(mainStage).getAbsolutePath();
        } catch (NullPointerException e) {
            fileName = "";
        }
        if (!Objects.equals(fileName, "")) {
            Image image = new Image("file:" + fileName);
            originalImage.setImage(image);
            modifiedImage.setImage(image);

        }
    }

    @FXML
    private void handleSave(ActionEvent actionEvent) throws IllegalArgumentException, ImagingOpException, IOException {
        FileChooser fileChooser = new FileChooser();
        String fileName;

        try {
            fileName = fileChooser.showSaveDialog(mainStage).getAbsolutePath();
        } catch (NullPointerException e) {
            fileName = "";
        }

        File originalImage = new File(fileName);

        if (!Objects.equals(fileName, "")) {
                ImageIO.write(SwingFXUtils.fromFXImage(modifiedImage.getImage(), null), "jpg", originalImage);
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


}
