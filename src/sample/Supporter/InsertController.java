package sample.Supporter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DatabaseController;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertController implements Initializable {
    @FXML private TextField UnitedKingdom;
    @FXML private TextField VietNamese;
    @FXML private Button addButoon;
    private static String language;

    public static void setLanguage(String lang){
        language = lang;
    }
    @FXML
    void submitWords() {
        String eng = UnitedKingdom.getText();
        String vie = VietNamese.getText();
        if (eng.isEmpty() || language == null){
            Alert infor = new Alert(Alert.AlertType.ERROR);
            infor.setContentText("Bạn hãy nhập từ cần thêm hoặc chọn chế độ !");
            infor.show();
        }
        else {
            if (language.equals("Anh-Việt")){
                if(!DatabaseController.getMyTrie().Contains(eng)){
                    DatabaseController.getMyTrie().insert(eng);
                    DatabaseController.getConnectDatabase().insertWord(eng,vie);
                    Stage stage = (Stage) addButoon.getScene().getWindow();
                    stage.close();
                }
                else{
                    Alert infor = new Alert(Alert.AlertType.ERROR);
                    infor.setContentText("Từ này đã có trong từ điển rồi nha");
                    infor.show();
                }
                //System.out.println(language);
            }
            else if (language.equals("Việt-Anh")){
                if(!DatabaseController.getVietTrie().Contains(eng)){
                    DatabaseController.getVietTrie().insert(eng);
                    DatabaseController.getConnectDatabase().insertWord(eng,vie);
                    Stage stage = (Stage) addButoon.getScene().getWindow();
                    stage.close();
                }
                else{
                    Alert infor = new Alert(Alert.AlertType.ERROR);
                    infor.setContentText("Từ này đã có trong từ điển rồi nha");
                    infor.show();
                }
                //System.out.println(language);
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
