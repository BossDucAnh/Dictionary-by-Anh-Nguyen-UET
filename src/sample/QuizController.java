package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

public class QuizController extends AddController implements Initializable {
    @FXML private RadioButton radio2;
    @FXML private ToggleGroup answerquestion;
    @FXML private RadioButton radio1;
    @FXML private RadioButton radio3;
    @FXML private RadioButton radio4;
    @FXML private Label questionlabel;
    @FXML private Label scoreofplayer;
    @FXML private Label turnplayer;
    @FXML private Label status;
    @FXML private Label introduce;
    private int score;
    private int turn;
    private TreeMap<String,String> listquestion = Controller.getMyList();
    private ArrayList<String> question_library = new ArrayList<>();//library câu hỏi
    private ArrayList<String> answer_library = new ArrayList<>();//library câu trả lờidas
    private String get_answer;
    private String get_question ;



    //tips 1 create arr(a->b) after swap random index of element
    //create array list random not duplicate 0 -> 3 shuffle
    public void randomNotDuplicate(ArrayList<Integer> randomlist){
        while (randomlist.size() < 4){
            int random = new Random().nextInt(4);//array list contains elements from 0 to 3
            if(!randomlist.contains(random))//not contains
            {
                randomlist.add(random);
            }
        }
    }

    //define method quiz random
    public void clickRandomQuiz(ActionEvent event){
        if (answerquestion.getSelectedToggle() != null)
        {
            RadioButton secleted = (RadioButton) answerquestion.getSelectedToggle();
            get_answer = secleted.getText();
            if (get_answer.equals("")) turn++;
            //compare check score
            boolean check = false;
            for (Map.Entry<String,String> compare : listquestion.entrySet()){
                if(compare.getKey().equals(get_question) && compare.getValue().equals(get_answer)){
                    score++;
                    check = true;
                    break;
                }
            }
            if(check) {
                Image image = new Image("/sample/images/checked.png");
                ImageView viewer = new ImageView(image);
                viewer.setFitWidth(25);
                viewer.setFitHeight(25);
                status.setGraphic(viewer);
            }
            else{
                Image image = new Image("/sample/images/unchecked.png");
                ImageView viewer = new ImageView(image);
                viewer.setFitWidth(25);
                viewer.setFitHeight(25);
                status.setGraphic(viewer);
            }
            introduce.setText("");
            int size = listquestion.size();
            int rd1 = new Random().nextInt(size),
                    rd2 = new Random().nextInt(size),
                    rd3 = new Random().nextInt(size),
                    rd4 = new Random().nextInt(size);
            // câu trả lời
            ArrayList<String> add_answer  = new ArrayList<>();
            add_answer.add(answer_library.get(rd1));//ngẫu nhiên 4 câu hỏi trong thư viện mà mình đã đặt
            add_answer.add(answer_library.get(rd2));
            add_answer.add(answer_library.get(rd3));
            add_answer.add(answer_library.get(rd4));
            //câu hỏi
            ArrayList<String> add_question = new ArrayList<>();
            add_question.add(question_library.get(rd1));
            get_question = add_question.get(0);
            questionlabel.setText(add_question.get(0) + " nghĩa tiếng việt là gì?");
            ArrayList<Integer> notDuplicate = new ArrayList<>();
            randomNotDuplicate(notDuplicate);
            int i = notDuplicate.get(0),
                    j = notDuplicate.get(1),
                    k = notDuplicate.get(2),
                    t = notDuplicate.get(3);
            radio1.setText(add_answer.get(i));
            radio2.setText(add_answer.get(j));
            radio3.setText(add_answer.get(k));
            radio4.setText(add_answer.get(t));
            scoreofplayer.setText(Integer.toString(score));
            turn--;
            turnplayer.setText(Integer.toString(turn));
            radio1.setSelected(false);
            radio2.setSelected(false);
            radio3.setSelected(false);
            radio4.setSelected(false);
            if(turn == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulation");
                alert.setContentText("Bạn đúng " + score + "/10" + " câu hỏi của tôi.performance : "+(double)(score*100/10)
                        + "% Bạn chọn câu hỏi tiếp theo để thử lại nhé!");
                alert.show();
                turn = 10;
                score = 0;
                scoreofplayer.setText(Integer.toString(score));
                turnplayer.setText(Integer.toString(turn));
                radio1.setSelected(true);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Bạn chưa chọn đúng không");
            alert.show();
        }
    }

    public void backToDictionary(MouseEvent event){
        super.BackToScene(event);
    }
    //init
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Map.Entry<String,String> map : listquestion.entrySet()){
            question_library.add(map.getKey());
            answer_library.add(map.getValue());
        }
        get_answer = "";
        get_question = "";
        score = 0;
        turn = 10;
    }
}
