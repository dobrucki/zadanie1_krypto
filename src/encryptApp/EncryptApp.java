package encryptApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EncryptApp extends Application {
//
//    private String alg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Program szyfrujący");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("gui.fxml"))));
        primaryStage.show();
    }
//
//    public String getAlg() {
//        return alg;
//    }

//    public void setAlg(String alg) {
//        if (alg.equals("Des"))
//            this.alg = alg;
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
