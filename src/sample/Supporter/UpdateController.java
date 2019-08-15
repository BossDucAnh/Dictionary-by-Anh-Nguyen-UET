package sample.Supporter;

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

public class UpdateController implements Initializable {
    @FXML private TextField wordsUK;
    @FXML private TextField wordsViet;
    @FXML private Button modifyButton;
    private static String updateLanguage;
    public static void setUpdateLanguage(String language){
        updateLanguage = language;
    }

    @FXML
    void submitFixWords(ActionEvent event) {
        String eng = wordsUK.getText();
        String vie = wordsViet.getText();
        if (eng.isEmpty() || updateLanguage == null){
            Alert infor = new Alert(Alert.AlertType.ERROR);
            infor.setContentText("Bạn hãy nhập từ cần thêm hoặc chọn chế độ !");
            infor.show();
        }
        else
        {
            if (updateLanguage.equals("Anh-Việt")){
                if(DatabaseController.getMyTrie().Contains(eng)){
                    DatabaseController.getConnectDatabase().updateWord(eng,vie);
                    Stage stage = (Stage) modifyButton.getScene().getWindow();
                    stage.close();
                }
                else{
                    Alert infor = new Alert(Alert.AlertType.ERROR);
                    infor.setContentText("Từ này không có trong từ điển , bạn có thể thêm!");
                    infor.show();
                }
            }
            else if(updateLanguage.equals("Việt-Anh")){
                if(DatabaseController.getVietTrie().Contains(eng)){
                    DatabaseController.getConnectDatabase().updateWord(eng,vie);
                    Stage stage = (Stage) modifyButton.getScene().getWindow();
                    stage.close();
                }
                else{
                    Alert infor = new Alert(Alert.AlertType.ERROR);
                    infor.setContentText("Từ này không có trong từ điển , bạn có thể thêm!");
                    infor.show();
                }
            }
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
