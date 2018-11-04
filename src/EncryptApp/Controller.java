package EncryptApp;

import com.sun.istack.internal.NotNull;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
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

    @FXML
    private void toggleView(@NotNull ActionEvent event){
        if(event.getSource().equals(fileViewSelected)){
           fileVBox.setDisable(false);
           fileVBox.setVisible(true);
           textVBox.setDisable(true);
           textVBox.setVisible(false);
        }
        else if(event.getSource().equals(textViewSelected)){
            fileVBox.setDisable(true);
            fileVBox.setVisible(false);
            textVBox.setDisable(false);
            textVBox.setVisible(true);
        }
    }
}
