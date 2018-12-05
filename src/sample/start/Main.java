package sample.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/sample.fxml"));

        Parent fxmlMain = fxmlLoader.load();
        Controller mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Image Editor");
        primaryStage.getIcons().add(new Image("file:res/icon.png"));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1300);
        primaryStage.setScene(new Scene(fxmlMain, 1300, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
