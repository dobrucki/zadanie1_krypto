package EncryptApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EncryptApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent textScene = FXMLLoader.load(getClass().getResource("textScene.fxml"));
        primaryStage.setTitle("Program szyfrujący");
        primaryStage.setScene(new Scene(textScene));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
