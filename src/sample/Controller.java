package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.filters.Filter;
import sample.filters.Lang;
import sample.filters.WaterMarkImage;

import javax.imageio.ImageIO;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Controller {

    private Stage mainStage;
    private Image startImage = new Image("file:res/img7.jpg");
    private Image beforeWatermark;

    @FXML
    private ImageView originalImage;

    @FXML
    private ImageView modifiedImage;

    @FXML
    private ComboBox comboFilters;

    @FXML
    private TextField text;

    @FXML
    private TextField size;



    @FXML
    private void initialize() {
        fillLangComboBox();
        originalImage.setImage(startImage);
        modifiedImage.setImage(startImage);
    }

    private void fillLangComboBox() {
        Lang langNo = new Lang(0, "Origin");
        Lang langExpo = new Lang(1, "ExposureFilter");
        Lang langGray = new Lang(2, "GrayFilter");
        Lang langGraysc = new Lang(3, "GrayscaleFilter");
        Lang langBlur = new Lang(4, "GainFilter");
        Lang langSmear = new Lang(5, "SmearFilter");
        Lang langNoise = new Lang(6, "NoiseFilter");


        comboFilters.getItems().add(langNo);
        comboFilters.getItems().add(langExpo);
        comboFilters.getItems().add(langGray);
        comboFilters.getItems().add(langGraysc);
        comboFilters.getItems().add(langBlur);
        comboFilters.getItems().add(langSmear);
        comboFilters.getItems().add(langNoise);

        comboFilters.getSelectionModel().select(0);
    }


    @FXML
    private void cancelWatermark(ActionEvent actionEvent) {
        modifiedImage.setImage(beforeWatermark);
    }

    @FXML
    private void applyFilter(ActionEvent actionEvent){
        Lang selectedLang = (Lang) comboFilters.getSelectionModel().getSelectedItem();
        Filter filter = new Filter();
        modifiedImage.setImage(filter.filter(selectedLang.getName(), modifiedImage.getImage(), originalImage.getImage()));

    }

    @FXML
    private void applyWatermark(ActionEvent actionEvent) throws IOException {
        beforeWatermark = modifiedImage.getImage();
        WaterMarkImage waterMarkImage = new WaterMarkImage();
        modifiedImage.setImage(waterMarkImage.watermark(modifiedImage.getImage(),text.getText(),Integer.parseInt(size.getText())));
    }

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

        File originImage = new File(fileName);

        if (!Objects.equals(fileName, "")) {
            ImageIO.write(SwingFXUtils.fromFXImage(modifiedImage.getImage(), null), "jpg", originImage);
        }
        /*Filter f = new Filter();
        modifiedImage.setImage(f.Filter(originalImage.getImage()));*/
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


}
