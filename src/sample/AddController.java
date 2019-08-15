package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.readFileDictionary.WriteFileDictionary;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AddController implements Initializable {
    @FXML
    private TextField UnitedKingdom;
    @FXML
    private TextField VietNamese;
    private TreeMap<String,String> listAdd = Controller.getMyList();


    public void submitWords(){
        String eng = UnitedKingdom.getText();
        String vie = VietNamese.getText();
        if(!eng.isEmpty()){
            if(listAdd.containsKey(eng)){
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Lỗi");
                al.setContentText("Từ đã có trong từ điển rồi ạ!!");
                al.show();
                UnitedKingdom.clear();
                VietNamese.clear();
            }
            else {
                listAdd.put(eng, vie);
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Congratulations");
                al.setContentText("Yeah! bạn đã thêm thành công rồi nhé!");
                al.show();
                WriteFileDictionary.OutFileTwo(listAdd,"MyFile.txt");
                UnitedKingdom.clear();
                VietNamese.clear();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("opps !Có vẻ như bạn chưa nhập từ");
            alert.show();
        }
    }



    public void BackToScene(MouseEvent event){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("sample.fxml"));
            Parent newSceneAdd = loader.load();
            Scene backToMenu = new Scene(newSceneAdd);
            Stage newWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            backToMenu.getStylesheets().add(this.getClass().getResource("myButton.css").toExternalForm());
            newWindow.setScene(backToMenu);
            newWindow.show();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    public void pressOnEnter(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            submitWords();
        }
        if(event.getCode() == KeyCode.ESCAPE){
            try{
               FXMLLoader reload = new FXMLLoader();
               reload.setLocation(this.getClass().getResource("sample.fxml"));
               Parent root = reload.load();
               Scene scene = new Scene(root);
               scene.getStylesheets().add(this.getClass().getResource("myButton.css").toExternalForm());
               Stage back_to_Dictionary = (Stage)((Node)event.getSource()).getScene().getWindow();
                back_to_Dictionary.setScene(scene);
                back_to_Dictionary.show();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
