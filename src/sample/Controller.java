package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import sample.Audio.SoundGoogle;
import sample.readFileDictionary.ReadFile;
import sample.readFileDictionary.WriteFileDictionary;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {
    @FXML
    private TextField input;
    @FXML
    private ListView<String> listview;
    @FXML
    private ListView<String> meaning;
    @FXML
    private Button buttonexit;
    private ObservableList<String> control_View = FXCollections.observableArrayList();
    private ObservableList<String> mean_View = FXCollections.observableArrayList();


    private static TreeMap<String,String> favorite_list;
    private static TreeMap<String,String> myList;

    public static TreeMap<String, String> getMyList() {
        return myList;
    }

    public static void setMyList(TreeMap<String, String> myList) {
        Controller.myList = myList;
    }


    public void isEmptyOfTextField(ActionEvent event){
        String checkEmpty = input.getText();
        if(checkEmpty.isEmpty()){
            listview.getItems().clear();
            meaning.getItems().clear();
        }
    }
    public void removeBackspace(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.BACK_SPACE){
            listview.getItems().clear();
            meaning.getItems().clear();
        }
        if(keyEvent.getCode() == KeyCode.DOWN){
            listview.requestFocus();
            listview.getSelectionModel().select(0);
            listview.getFocusModel().focus(0);
        }
    }

    public void Translate(ActionEvent event){
        try {
            listview.getItems().clear();
            meaning.getItems().clear();
            String give = input.getText();
            if (give.isEmpty()) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("Bạn chưa nhập từ đúng không?");
                al.show();
            }
            else {
                for (Map.Entry<String, String> m : myList.entrySet()) {
                    if (m.getKey().contains(give)) {
                        control_View.add(m.getKey());
                        mean_View.add(m.getValue());
                        break;
                    }
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    public void suggestWords(KeyEvent keyevent){
        String give = input.getText();
        TreeMap<String, String> gest = new TreeMap<>();
        for (Map.Entry<String, String> sg : myList.entrySet()) {
            if (sg.getKey().startsWith(give)) {
                gest.put(sg.getKey(), sg.getValue());
            }
        }
        listview.getItems().clear();
        meaning.getItems().clear();
        for (Map.Entry<String, String> k : gest.entrySet()) {
            control_View.add(k.getKey());
            mean_View.add(k.getValue());
        }
        if(give.isEmpty() && (keyevent.getCode() == KeyCode.BACK_SPACE ||
                keyevent.getCode() == KeyCode.ESCAPE)){
            input.clear();
            listview.getItems().clear();
            meaning.getItems().clear();
        }
    }


    //text to speech
    public void Sound(ActionEvent event){
        SoundGoogle.speak(input.getText(),"en");
    }
    public void SoundViet(MouseEvent event) {
        String mean = meaning.getSelectionModel().getSelectedItem();
        if (mean != null){
            SoundGoogle.speak(mean,"vi");
        }
    }
    //event of key event list view
    public void keyHangleOnListView(KeyEvent event) {
        input.setText(listview.getSelectionModel().getSelectedItem());
        String eng = input.getText();
        for (Map.Entry<String,String> focus : myList.entrySet()){
            if(focus.getKey().equals(eng)){
                meaning.getItems().clear();
                mean_View.add(focus.getValue());
            }
        }
    }

    //add favorite
    public void addMyFavorite(){
        try {
            String word_target = "";
            String word_focus = listview.getSelectionModel().getSelectedItem();
            for (Map.Entry<String,String> favo : myList.entrySet()){
                if(favo.getKey().equals(word_focus)){
                    word_target = favo.getValue();
                }
            }
            if (!favorite_list.containsKey(word_focus)) {
                favorite_list.put(word_focus, word_target);
                Alert succes = new Alert(Alert.AlertType.CONFIRMATION);
                WriteFileDictionary.Ouputfile(favorite_list, "Favorite.txt");
                succes.setTitle("Congratulation");
                succes.setContentText("đã thêm vào danh sách yêu thích");
                succes.show();
            }
            else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setContentText("Từ đã có trong danh sách yêu thích của bạn");
                error.show();
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }
    //add favorite
    public void clickOnButtonFavorite(MouseEvent mouseEvent){
        //do something here
        if(input.getText().isEmpty()){
            Alert infor = new Alert(Alert.AlertType.ERROR);
            infor.setContentText("Bạn chưa chọn từ đúng không");
            infor.show();
        }
        else {
            addMyFavorite();
        }
    }

    public void clickedOnListView(MouseEvent event) {
        try {
            input.setText(listview.getSelectionModel().getSelectedItem());
            String uk = input.getText();
            for (Map.Entry<String, String> fx : myList.entrySet()) {
                if (fx.getKey().equals(uk)) {
                    meaning.getItems().clear();
                    meaning.getItems().add(fx.getValue());
                }
            }
            if(event.getButton().equals(MouseButton.PRIMARY)){
                if(event.getClickCount() == 2){
                    //do something in here
                    addMyFavorite();
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    //change to new stage for insert,remove and modify
    public void changeToNewScene(ActionEvent event,String path){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(path));
            Parent newSceneAdd = loader.load();
            Scene insert = new Scene(newSceneAdd);
            //chuyển tiếp giũa 1 window,2 controller
            Stage newWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            newWindow.setScene(insert);
            newWindow.show();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    //quick remove
    public void QuickRemove(ActionEvent event){
        Alert confim = new Alert(Alert.AlertType.CONFIRMATION);
        confim.setTitle("Bạn chọn");
        confim.setHeaderText("Xóa");
        confim.setContentText("Bạn chắc chắn muốn xóa em nó chứ?");
        confim.showAndWait();
        String itemRemove = listview.getSelectionModel().getSelectedItem();
        if(confim.getResult() == ButtonType.OK && itemRemove != null) {
            for (Map.Entry<String, String> del : myList.entrySet()) {
                if (del.getKey().equals(itemRemove)) {
                    listview.getItems().removeAll(itemRemove);
                    meaning.getItems().removeAll(del.getValue());
                }
            }
            myList.remove(itemRemove);
            WriteFileDictionary.OutFileTwo(myList,"MyFile.txt");
            input.clear();
        }
        if (itemRemove == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi");
            alert.setContentText("Bạn hãy chọn từ nhé!");
            alert.show();
        }
    }
    //exit
    public void ExitDict(ActionEvent event){
        Alert confim = new Alert(Alert.AlertType.CONFIRMATION);
        confim.setTitle("Bạn chọn");
        confim.setHeaderText("Thoát");
        confim.setContentText("Bạn chắc chắn muốn thoát chứ?");
        confim.showAndWait();
        if (confim.getResult() == ButtonType.OK){
            Stage stage = (Stage) buttonexit.getScene().getWindow();
            stage.close();
        }

    }
    //add words
    public void gotoSceneAdd(ActionEvent event){
        changeToNewScene(event,"fileFxml/addmorewords.fxml");
    }

    //delete words
    public void gotoSceneDelete(ActionEvent event){
        changeToNewScene(event,"fileFxml/deletewords.fxml");
    }

    //modify words
    public void gotoSceneModify(ActionEvent event){
        changeToNewScene(event,"fileFxml/modifydictionary.fxml");
    }

    //favorite list
    public void gotoSceneFavorite(ActionEvent event){
        changeToNewScene(event,"fileFxml/myfavorite.fxml");
    }

    //api google
    public void gotoSceceAPIGoogle(ActionEvent event) { changeToNewScene(event,"fileFxml/apicontroller.fxml");}

    //quiz
    public void gotoSceneQuiz(ActionEvent event){
        changeToNewScene(event,"fileFxml/quiz.fxml");
    }

    //database
    public void gotoSceneDatabase(ActionEvent event) {changeToNewScene(event,"fileFxml/database.fxml");}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        favorite_list = ReadFile.readFileDictionary("Favorite.txt");
        myList = ReadFile.readFileDictionary("MyFile.txt");
        listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listview.setItems(control_View);
        meaning.setItems(mean_View);
        Tooltip tooltip = new Tooltip("enter your words");
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        input.setTooltip(tooltip);
    }
}
