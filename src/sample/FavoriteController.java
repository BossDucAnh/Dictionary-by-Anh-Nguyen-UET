package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sample.readFileDictionary.ReadFile;
import sample.readFileDictionary.WriteFileDictionary;

import java.net.URL;
import java.util.*;

public class FavoriteController extends AddController implements Initializable {
    private TreeMap<String,String> myFavorite = ReadFile.readFileDictionary("Favorite.txt");
    @FXML private ListView<String> favoriteEng;
    @FXML private ListView<String> favoriteViet;
    private ObservableList<String> word_list = FXCollections.observableArrayList();
    private ObservableList<String> mean_list = FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Map.Entry<String,String> favo : myFavorite.entrySet()){
            word_list.add(favo.getKey());
            mean_list.add(favo.getValue());
        }
        //decralation
        favoriteEng.setItems(word_list);
        favoriteViet.setItems(mean_list);
    }
    //back to dictionary
    public void BackToDictionary(MouseEvent event){
        super.BackToScene(event);
    }
    //delete word of favorite list

    public void DeleteWordOfFavorateList(MouseEvent mouseEvent){
        Alert infor = new Alert(Alert.AlertType.CONFIRMATION);
        infor.setContentText("Bạn chắc chắn muốn xóa em nó khỏi danh sách yêu thích chứ");
        infor.setTitle("Bạn chọn");
        infor.setHeaderText("Xóa");
        infor.showAndWait();
        String selectedItemCopy = favoriteEng.getSelectionModel().getSelectedItem();
        if (infor.getResult() == ButtonType.OK && selectedItemCopy!= null){

            for (Map.Entry<String,String> hobbies : myFavorite.entrySet()){
                if (hobbies.getKey().equals(selectedItemCopy)){
                    favoriteEng.getItems().removeAll(selectedItemCopy);
                    favoriteViet.getItems().removeAll(hobbies.getValue());
                }
            }
            if (myFavorite.containsKey(selectedItemCopy)){
                myFavorite.remove(selectedItemCopy);
            }
            WriteFileDictionary.OutFileTwo(myFavorite,"Favorite.txt");
        }
        if (selectedItemCopy == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi");
            alert.setContentText("Bạn hãy chọn từ nhé!");
            alert.show();
        }
    }
}
