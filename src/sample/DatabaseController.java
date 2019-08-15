package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sample.Audio.SoundGoogle;
import sample.Supporter.ConnectDatabase;
import sample.Supporter.InsertController;
import sample.Supporter.Trie;
import sample.Supporter.UpdateController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseController extends AddController implements Initializable {
    @FXML private ListView<String> listviewWord ;
    @FXML private WebView webviewMean ;
    private WebEngine webEngine;
    @FXML private TextField input ;
    @FXML private ComboBox<String> userchoice;
    private ObservableList<String> list_word = FXCollections.observableArrayList();
    private ObservableList<String> language = FXCollections.observableArrayList("Anh-Việt","Việt-Anh");
    private List<String> list = FXCollections.observableArrayList();
    private static ConnectDatabase connectDatabase;
    public static ConnectDatabase getConnectDatabase() {
        return connectDatabase;
    }
    private static Trie myTrie ;
    public static Trie getMyTrie() {
        return myTrie;
    }

    private static Trie vietTrie;
    public static Trie getVietTrie() { return vietTrie; }


    public void SelectedLanguage(ActionEvent event){
        listviewWord.getItems().clear();
        webEngine.loadContent("");
        input.clear();
        if(userchoice.getValue().equals("Anh-Việt"))
        {
            UpdateController.setUpdateLanguage("Anh-Việt");
            InsertController.setLanguage("Anh-Việt");
            connectDatabase.setMyTable("data_dict.av");
        }
        else if (userchoice.getValue().equals("Việt-Anh")){
            UpdateController.setUpdateLanguage("Việt-Anh");
            InsertController.setLanguage("Việt-Anh");
            connectDatabase.setMyTable("data_dict.va");
            vietTrie = connectDatabase.getData();
        }
    }
    public void Autocomplete(KeyEvent keyEvent){
        boolean isMyComboBoxEmpty = userchoice.getSelectionModel().isEmpty();
        if (!isMyComboBoxEmpty){
            String give = input.getText();
            if(userchoice.getValue().equals("Anh-Việt")) list = myTrie.Suggestion(give);
            else if(userchoice.getValue().equals("Việt-Anh")) list = vietTrie.Suggestion(give);
            listviewWord.getItems().clear();
            list_word.addAll(list);
//            for (String s : list){
//                //System.out.println(s);
//                list_word.add(s);
//            }
//            String mean = connectDatabase.getMean(give);
//            String html = "<html>"+mean+"</html>";
//            webEngine.loadContent("");
//            webEngine.loadContent(html);
            if(give.isEmpty() && (keyEvent.getCode() == KeyCode.BACK_SPACE ||
                    keyEvent.getCode() == KeyCode.ESCAPE)){
                input.clear();
                listviewWord.getItems().clear();
                webEngine.loadContent("");
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo admin");
            alert.setContentText("Bạn hãy chọn chế độ");
            alert.show();
        }
    }
    public void keyHangleListview(KeyEvent event){
        input.setText(listviewWord.getSelectionModel().getSelectedItem());
        String give = input.getText();
        String mean = connectDatabase.getMean(give);
        String html = "<html>"+mean+"</html>";
        webEngine.loadContent("");
        webEngine.loadContent(html);
    }
    public void clickOnListView(MouseEvent event){
        input.setText(listviewWord.getSelectionModel().getSelectedItem());
        String give = input.getText();
        String mean = connectDatabase.getMean(give);
        String html = "<html>"+mean+"</html>";
        webEngine.loadContent(html);
    }
    public void KeyDownOfTextField(KeyEvent event){
        if(event.getCode() == KeyCode.DOWN){
            listviewWord.requestFocus();
            listviewWord.getSelectionModel().select(0);
            listviewWord.getFocusModel().focus(0);
        }
    }
    public void Sound(ActionEvent event){
        boolean isMyComboBoxEmpty = userchoice.getSelectionModel().isEmpty();
        if (!isMyComboBoxEmpty){
            if (userchoice.getValue().equals("Việt-Anh")){
                SoundGoogle.speak(input.getText(),"vi");
            }
            else if (userchoice.getValue().equals("Anh-Việt")){
                SoundGoogle.speak(input.getText(),"en");
            }
        }
    }

    public void QuickDelete(ActionEvent event){
        Alert confim = new Alert(Alert.AlertType.CONFIRMATION);
        confim.setTitle("Bạn chọn");
        confim.setHeaderText("Xóa");
        confim.setContentText("Bạn chắc chắn muốn xóa em nó chứ?");
        confim.showAndWait();
        String deleteItem = listviewWord.getSelectionModel().getSelectedItem();
        if(confim.getResult() == ButtonType.OK && deleteItem != null){
            if(userchoice.getValue().equals("Anh-Việt") && myTrie.Contains(deleteItem)){
                connectDatabase.deleteWord(deleteItem);
                listviewWord.getItems().removeAll(deleteItem);
                myTrie.delete(deleteItem);
                input.clear();
                webEngine.loadContent("");
                //System.out.println(myTrie.Contains(deleteItem));
            }
            else if(userchoice.getValue().equals("Việt-Anh") && vietTrie.Contains(deleteItem)){
                vietTrie.delete(deleteItem);
                connectDatabase.deleteWord(deleteItem);
                listviewWord.getItems().removeAll(deleteItem);
                input.clear();
                webEngine.loadContent("");
                //System.out.println(vietTrie.Contains(deleteItem));
            }
        }
        if (deleteItem == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi");
            alert.setContentText("Bạn hãy chọn từ nhé!");
            alert.show();
        }

    }
    public void BackToDictionary(MouseEvent event){
        super.BackToScene(event);
    }

    public void changeTonewScene(ActionEvent event,String path){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void throughInsertController(ActionEvent event){ changeTonewScene(event,"databaseFxml/insert.fxml");}
    //public void throughDeleteController(ActionEvent event){ changeTonewScene(event,"databaseFxml/remove.fxml");}
    public void throughUpdateController(ActionEvent event){ changeTonewScene(event,"databaseFxml/updateword.fxml");}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectDatabase = new ConnectDatabase();
        connectDatabase.getConnection();
        myTrie = connectDatabase.getData();
        //System.out.println(myTrie.Size());
        userchoice.setItems(language);
        webEngine = webviewMean.getEngine();
        listviewWord.setItems(list_word);
    }
}
