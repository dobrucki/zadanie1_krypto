package EncryptApp;

import com.sun.istack.internal.NotNull;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    private VBox fileVBox;
    @FXML
    private VBox textVBox;
    @FXML
    private RadioButton textViewSelected;
    @FXML
    private RadioButton fileViewSelected;


    // Obszary dla szyfrownia tekstu z okna
    @FXML
    private TextArea inputTextArea;
    @FXML
    private TextArea outputTextArea;
    @FXML
    private PasswordField keyText;

    // Obszary dla szyfrowania tekstu z pliku
    @FXML
    private PasswordField keyFile;

    private enum Method {
        TEXT, FILE
    }

    private Method currentMethod;

    public Controller() {
        currentMethod = Method.TEXT;
    }

    // Metoda do zmiany widoku (wybór pliku lub wklejanie tekstu)
    @FXML
    private void toggleView(@NotNull ActionEvent event){
        if(event.getSource().equals(fileViewSelected)){
           fileVBox.setDisable(false);
           fileVBox.setVisible(true);
           textVBox.setDisable(true);
           textVBox.setVisible(false);
           currentMethod = Method.FILE;
        }
        else if(event.getSource().equals(textViewSelected)){
            fileVBox.setDisable(true);
            fileVBox.setVisible(false);
            textVBox.setDisable(false);
            textVBox.setVisible(true);
            currentMethod = Method.TEXT;
        }
    }

    // Metoda szyfrujaca tekst z okna lub pliku
    @FXML
    private void encrypt(){
        if(currentMethod == Method.TEXT){

        }
        else if(currentMethod == Method.FILE){

        }
    }

    // Metoda deszyfrujaca tekst z okna lub pliku
    @FXML
    private void decrypt(){
        if(currentMethod == Method.TEXT){

        }
        else if(currentMethod == Method.FILE){

        }
    }
}
