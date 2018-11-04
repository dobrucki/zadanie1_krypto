package EncryptApp;

import com.sun.istack.internal.NotNull;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.rmi.server.ExportException;

public class Controller {
    @FXML
    private Text status;


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
    @FXML
    private Button inputFileButton;
    @FXML
    private Button outputFileButton;

    @FXML
    private TextField selectedInputFile;
    @FXML
    private TextField selectedOutputFile;


    private File inputFile;
    private File outputFile;


    private enum Method {
        TEXT, FILE
    }

    private enum Status{
        ERR, COM
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
    private void encrypt() throws Exception{
        String plainText = "";
        String key = "";
        String cipherText;
        Des.Des des = Des.Des.getInstance();

        if(currentMethod == Method.TEXT){
            plainText = inputTextArea.getText();
            key = keyText.getText();
            cipherText = des.encrypt(plainText, key);
            outputTextArea.setText(cipherText);
        }
        else if(currentMethod == Method.FILE){
            if(inputFile != null && outputFile!= null){
                plainText = readFile(inputFile);
                key = keyFile.getText();
                cipherText = des.encrypt(plainText, key);
                FileWriter fw = new FileWriter(outputFile.getPath());
                fw.write(cipherText);
                fw.close();
            }
        }

    }

    // Metoda deszyfrujaca tekst z okna lub pliku
    @FXML
    private void decrypt() throws Exception{
        String cipherText = "";
        String key = "";
        String plainText = "";
        Des.Des des = Des.Des.getInstance();
        if(currentMethod == Method.TEXT){
            cipherText = inputTextArea.getText();
            key = keyText.getText();
            plainText = des.decrypt(cipherText, key);
            outputTextArea.setText(plainText);
        }
        else if(currentMethod == Method.FILE){
            if(inputFile != null && outputFile!= null){
                cipherText = readFile(inputFile);
                key = keyFile.getText();
                plainText = des.decrypt(cipherText, key);
                FileWriter fw = new FileWriter(outputFile.getPath());
                fw.write(plainText);
                fw.close();
            }
        }
    }
    @FXML
    private void selectFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null)
            if(event.getSource().equals(inputFileButton)) {
                inputFile = file;
                selectedInputFile.setText(file.getPath());
            }
            else if(event.getSource().equals(outputFileButton)){
                outputFile = file;
                selectedOutputFile.setText(file.getPath());
            }
    }

    private String readFile(File file) throws Exception{
        byte[] encoded = {};
        try {
            encoded= Files.readAllBytes(file.toPath());
        }
        catch(IOException e){
            setStatus("Nie udało się odczytać z pliku!", Status.ERR);
        }
        return new String(encoded, "UTF-8");
    }

    private void setStatus(String text, Status s){
        String beg = "";
        if(s == Status.ERR){
            beg = "Błąd: ";
            status.setFill(Paint.valueOf("RED"));
        }
        status.setText(beg + text);
    }

}
