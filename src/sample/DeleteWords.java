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
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class DeleteWords extends AddController implements Initializable{
    @FXML
    private TextField deleteWords;
    private TreeMap<String,String> newlist = Controller.getMyList();
    public void BackToDictionay(MouseEvent event){
        super.BackToScene(event);
    }


    public void submitDeleteWords(){
        String word_remove = deleteWords.getText();
        if(!newlist.containsKey(word_remove) && !newlist.containsValue(word_remove)){
            Alert infor = new Alert(Alert.AlertType.ERROR);
            infor.setTitle("Lỗi");
            infor.setContentText("Từ bạn muốn xóa hông có trong từ điển");
            infor.show();
            deleteWords.clear();
        }
        else
        {
            String temp = "";
            for (Map.Entry<String,String> tmp : newlist.entrySet()){
                if(tmp.getValue().equals(word_remove)){
                    temp = tmp.getKey();
                }
            }
            newlist.remove(temp);
            newlist.remove(word_remove);
            Alert infor = new Alert(Alert.AlertType.CONFIRMATION);
            infor.setTitle("Congratulations");
            infor.setContentText("Bạn đã xóa thành công!");
            infor.show();
            WriteFileDictionary.Ouputfile(newlist,"MyFile.txt");
            deleteWords.clear();
        }
    }
    public void removeWhenClickedEnter(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER){
            submitDeleteWords();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
