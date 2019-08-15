package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import sample.Audio.GoogleTranslate;
import sample.Audio.SoundGoogle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class APIController extends AddController implements Initializable {
    @FXML private TextField inputwords;
    @FXML private WebView webword;
    private WebEngine webEngineWord;
    @FXML private WebView webview;
    private WebEngine webEngine;
    @FXML private ComboBox<String> combobox;

    private ObservableList<String> language = FXCollections.observableArrayList("Anh - Việt","Việt - Anh","Việt - Đức"
    ,"Việt - Pháp","Việt - Nhật","Việt - Trung","Việt - Tây Ban Nha","Việt - Hàn");
    public void goToBackDictionary(MouseEvent event){
        super.BackToScene(event);
    }


    //event of button translate
    public void Translate(ActionEvent event) throws IOException{
        try{
            boolean isMyComboBoxEmpty = combobox.getSelectionModel().isEmpty();
            if (!isMyComboBoxEmpty){
                webEngineWord.loadContent("");
                webEngine.loadContent("");
                if(combobox.getValue().equals("Việt - Anh")) {
                    String words = inputwords.getText();
                    String mean = GoogleTranslate.translate("en",words);
                    String htmlword = "<html>" + words +"</html>";
                    webEngineWord.loadContent(htmlword);
                    String html = "<html>" + mean +"</html>";
                    webEngine.loadContent(html);
                }
                else if(combobox.getValue().equals("Anh - Việt")){
                    String words = inputwords.getText();
                    String mean = GoogleTranslate.translate("vi",words);
                    String htmlword = "<html>" + words +"</html>";
                    webEngineWord.loadContent(htmlword);
                    String html = "<html>" + mean +"</html>";
                    webEngine.loadContent(html);

                }
                else if(combobox.getValue().equals("Việt - Đức")){
                    String words = inputwords.getText();
                    String mean = GoogleTranslate.translate("de",words);
                    String htmlword = "<html>" + words +"</html>";
                    webEngineWord.loadContent(htmlword);
                    String html = "<html>" + mean +"</html>";
                    webEngine.loadContent(html);
                }
                else if(combobox.getValue().equals("Việt - Pháp")){
                    String words = inputwords.getText();
                    String mean = GoogleTranslate.translate("fr",words);
                    String htmlword = "<html>" + words +"</html>";
                    webEngineWord.loadContent(htmlword);
                    String html = "<html>" + mean +"</html>";
                    webEngine.loadContent(html);
                }
                else if(combobox.getValue().equals("Việt - Nhật")){
                    String words = inputwords.getText();
                    String mean = GoogleTranslate.translate("jv",words);
                    String htmlword = "<html>" + words +"</html>";
                    webEngineWord.loadContent(htmlword);
                    String html = "<html>" + mean +"</html>";
                    webEngine.loadContent(html);
                }
                else if(combobox.getValue().equals("Việt - Trung")){
                    String words = inputwords.getText();
                    String mean = GoogleTranslate.translate("zh",words);
                    String htmlword = "<html>" + words +"</html>";
                    webEngineWord.loadContent(htmlword);
                    String html = "<html>" + mean +"</html>";
                    webEngine.loadContent(html);
                }
                else if(combobox.getValue().equals("Việt - Tây Ban Nha")){
                    String words = inputwords.getText();
                    String mean = GoogleTranslate.translate("es",words);
                    String htmlword = "<html>" + words +"</html>";
                    webEngineWord.loadContent(htmlword);
                    String html = "<html>" + mean +"</html>";
                    webEngine.loadContent(html);
                }
                else if(combobox.getValue().equals("Việt - Hàn")){
                    String words = inputwords.getText();
                    String mean = GoogleTranslate.translate("ko",words);
                    String htmlword = "<html>" + words +"</html>";
                    webEngineWord.loadContent(htmlword);
                    String html = "<html>" + mean +"</html>";
                    webEngine.loadContent(html);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo từ admin");
                alert.setContentText("Bạn hãy chọn chế độ");
                alert.show();
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }
    public void Sound(ActionEvent event){
        boolean isMyComboBoxEmpty = combobox.getSelectionModel().isEmpty();
        if (!isMyComboBoxEmpty){
            if (combobox.getValue().equals("Việt - Anh")){
                SoundGoogle.speak(inputwords.getText(),"vi");
            }
            else if (combobox.getValue().equals("Anh - Việt")){
                SoundGoogle.speak(inputwords.getText(),"en");
            }
            else if(combobox.getValue().equals("Việt - Đức")){
               SoundGoogle.speak(inputwords.getText(),"vi");
            }
            else if(combobox.getValue().equals("Việt - Pháp")){
                SoundGoogle.speak(inputwords.getText(),"vi");
            }
            else if(combobox.getValue().equals("Việt - Nhật")){
                SoundGoogle.speak(inputwords.getText(),"vi");
            }
            else if(combobox.getValue().equals("Việt - Trung")){
                SoundGoogle.speak(inputwords.getText(),"vi");
            }
            else if(combobox.getValue().equals("Việt - Tây Ban Nha")){
                SoundGoogle.speak(inputwords.getText(),"vi");
            }
            else if(combobox.getValue().equals("Việt - Hàn")){
                SoundGoogle.speak(inputwords.getText(),"vi");
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo từ admin");
            alert.setContentText("Bạn hãy chọn chế độ");
            alert.show();
        }
    }
    public void Speech(ActionEvent event) throws IOException{
        String mean = "";
        String words = inputwords.getText();
        boolean isMyComboBoxEmpty = combobox.getSelectionModel().isEmpty();
        if (!isMyComboBoxEmpty){
            if (combobox.getValue().equals("Việt - Anh")){
                mean = GoogleTranslate.translate("en",words);
                SoundGoogle.speak(mean,"en");
            }
            else if (combobox.getValue().equals("Anh - Việt")){
                mean = GoogleTranslate.translate("vi",words);
                SoundGoogle.speak(mean,"vi");
            }
            else if(combobox.getValue().equals("Việt - Đức")){
                mean = GoogleTranslate.translate("de",words);
                SoundGoogle.speak(mean,"de");
            }
            else if(combobox.getValue().equals("Việt - Pháp")){
                mean = GoogleTranslate.translate("fr",words);
                SoundGoogle.speak(mean,"fr");
            }
            else if(combobox.getValue().equals("Việt - Nhật")){
                mean = GoogleTranslate.translate("jv",words);
                SoundGoogle.speak(mean,"jv");
            }
            else if(combobox.getValue().equals("Việt - Trung")){
                mean = GoogleTranslate.translate("zh",words);
                SoundGoogle.speak(mean,"zh");
            }
            else if(combobox.getValue().equals("Việt - Tây Ban Nha")){
                mean = GoogleTranslate.translate("es",words);
                SoundGoogle.speak(mean,"es");
            }
            else if(combobox.getValue().equals("Việt - Hàn")){
                mean = GoogleTranslate.translate("ko",words);
                SoundGoogle.speak(mean,"ko");
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo từ admin");
            alert.setContentText("Bạn hãy chọn chế độ");
            alert.show();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngineWord = webword.getEngine();
        webEngine = webview.getEngine();
        combobox.setItems(language);
    }
}
