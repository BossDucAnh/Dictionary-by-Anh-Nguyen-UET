package sample.Supporter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseController;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveController implements Initializable {
    @FXML private TextField deleteWords;
    @FXML private Button buttonclose;
    @FXML
    void submitDeleteWords(ActionEvent event) {
        String del = deleteWords.getText();
        if(DatabaseController.getMyTrie().Contains(del)){
            DatabaseController.getMyTrie().delete(del);
            DatabaseController.getConnectDatabase().deleteWord(del);
            Stage stage = (Stage) buttonclose.getScene().getWindow();
            stage.close();
        }
        else{
            Alert infor = new Alert(Alert.AlertType.ERROR);
            infor.setContentText("Từ này đã không tồn tại trong từ điển bạn ơi");
            infor.show();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
