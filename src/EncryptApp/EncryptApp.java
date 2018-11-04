package EncryptApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class EncryptApp extends Application {

    private String alg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Wybór algorytmu
        setAlg("DES");

        primaryStage.setTitle("Program szyfrujący");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("gui.fxml"))));
        primaryStage.show();
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        if(alg.equals("DES"))
            this.alg = alg;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
