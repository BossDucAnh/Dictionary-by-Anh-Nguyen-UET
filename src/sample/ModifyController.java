package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.readFileDictionary.WriteFileDictionary;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ModifyController extends AddController implements Initializable {
    @FXML
    private TextField wordsUK;
    @FXML
    private TextField wordsViet;
    private TreeMap<String,String> fixList = Controller.getMyList();
    //back to stage dictionary
    public void backToDictionary(MouseEvent event){
        super.BackToScene(event);
    }
    //fix word
    public void submitFixWords(){
        String eng = wordsUK.getText();
        String vie = wordsViet.getText();
        if(!fixList.containsKey(eng)){
            Alert infor = new Alert(Alert.AlertType.ERROR);
            infor.setTitle("Lỗi");
            infor.setContentText("Từ không có trong từ điển,bạn có thể Thêm từ nhé!!");
            infor.show();
            wordsUK.clear();
            wordsViet.clear();
        }
        else
        {
            fixList.put(eng,vie);
            Alert tb = new Alert(Alert.AlertType.CONFIRMATION);
            tb.setTitle("Congratulations");
            tb.setContentText("bạn đã sửa thành công");
            tb.show();
            WriteFileDictionary.OutFileTwo(fixList,"MyFile.txt");
            wordsUK.clear();
            wordsViet.clear();
        }
    }

    public void clickEnter(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER){
            submitFixWords();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
