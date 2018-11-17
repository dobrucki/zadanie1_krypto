package encryptApp;

import com.sun.istack.internal.NotNull;
import des.Des;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
        byte[] plainText;
        byte[] key;
        byte[] cipherText;
        Des des = new Des();
        des.ReadWrite rw = new des.ReadWrite();

        if(currentMethod == Method.TEXT){
            plainText = inputTextArea.getText().getBytes(Charset.forName("UTF-8"));
            key = keyText.getText().getBytes(Charset.forName("UTF-8"));
            cipherText = des.encrypt(plainText, key);
            StringBuilder sb = new StringBuilder();
            for (byte b : cipherText) {
                sb.append(String.format("%02x", b));
            }
            outputTextArea.setText(sb.toString());
        }
        else if(currentMethod == Method.FILE){
            plainText = rw.read(selectedInputFile.getText());
            key = keyFile.getText().getBytes(Charset.forName("UTF-8"));
            cipherText = des.encrypt(plainText, key);
            rw.write(selectedOutputFile.getText(), cipherText);
        }

    }

    // Metoda deszyfrujaca tekst z okna lub pliku
    @FXML
    private void decrypt() throws Exception{
        byte[] plainText;
        byte[] key;
        byte[] cipherText;
        Des des = new Des();
        des.ReadWrite rw = new des.ReadWrite();

        if(currentMethod == Method.TEXT){
            cipherText = hexStringToByteArray(inputTextArea.getText());
            key = keyText.getText().getBytes(Charset.forName("UTF-8"));
            plainText = des.decrypt(cipherText, key);
            outputTextArea.setText(new String(plainText, StandardCharsets.UTF_8));
        }
        else if(currentMethod == Method.FILE){
            cipherText = rw.read(selectedInputFile.getText());
            key = keyFile.getText().getBytes(Charset.forName("UTF-8"));
            plainText = des.decrypt(cipherText, key);
            rw.write(selectedOutputFile.getText(), plainText);
        }
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    @FXML
    private void selectFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null)
            if(event.getSource().equals(inputFileButton)) {
                selectedInputFile.setText(file.getPath());
            }
            else if(event.getSource().equals(outputFileButton)){
                selectedOutputFile.setText(file.getPath());
            }
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
