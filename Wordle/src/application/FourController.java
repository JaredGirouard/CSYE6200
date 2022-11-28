package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FourController {
	@FXML
	Label lbl1,lbl2,lbl3,lbl4,lbl11,lbl21,lbl31,lbl41,lbl12,lbl22,lbl32,lbl42,
	lbl13,lbl23,lbl33,lbl43,lbl14,lbl24,lbl34,lbl44,lbl15,lbl25,lbl35,lbl45;
	@FXML
	private Label[][] allLbl;
	@FXML
	Button btn1;
	@FXML
	Label solLbl;
	@FXML
	ChoiceBox<String> cbMode;
	ObservableList<String> cbOptions = FXCollections.observableArrayList("4-Letters Game","5-Letters Game","6-Letters Game");
	int nums = 4;
	int rnums = 6;
	int idx = 0;
	int ridx = 0;
	String answer;
	PastGuesses pastguess = new PastGuesses();
	ArrayList<String> wordList = new ArrayList<String>();
	//a guess is create once initialize, after that key "enter" on "idx=gamemode.length", will create a new guess
	//scene change
	Stage stage;
	Scene scene;
	
	@FXML
	public void initialize() {
		this.allLbl = new Label[][] {{lbl1, lbl2, lbl3, lbl4},{lbl11, lbl21, lbl31, lbl41},{lbl12, lbl22, lbl32, lbl42},
			{lbl13, lbl23, lbl33, lbl43},{lbl14, lbl24, lbl34, lbl44},{lbl15, lbl25, lbl35, lbl45}};
		cbMode.setItems(cbOptions);
		cbMode.setValue("4-Letters Game");
		wordList = Utilities.ReadWordsFromFile("src/application/four-letter-words.txt");
		answer = wordList.get(new Random().nextInt(wordList.size()));
		cbMode.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value){
				if(new_value.intValue()==1) {
					//set to 5-letter
					try {
						setTo5();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}else if(new_value.intValue()==2) {
					//set to 6-letter 
					try {
						setTo6();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	public void setTo5() throws IOException{
		stage = (Stage)btn1.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		scene  = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void setTo6() throws IOException{
		stage = (Stage)btn1.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Six.fxml"));
		scene  = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void SetLabelText(KeyEvent event) {
	
		switch(event.getCode()) {
		case BACK_SPACE:
			Backward();
			allLbl[ridx][idx].setText("");		
			break;
		case ENTER:
			if(idx==nums) {
				SubmitGuess();
			}
			break;
		default:
	        String s = event.getText();
	        if(Character.isLetter(s.charAt(0))){
	        	if(idx<nums) {
					allLbl[ridx][idx].setText(s.toUpperCase());
					Forward();	
	        	}
	        }
			break;
		}

	}
	
	public void SubmitGuess() {
		solLbl.setText(answer);
		String str="";
		for(Label label:allLbl[ridx]) {
			str+=label.getText();
		}
		System.out.println("Full String is: "+str);
		Guess guess = pastguess.newGuess();
		for(int i=0;i<allLbl[ridx].length;i++) {
			//instance created
			 
			Letter letter = guess.newLetter(str.charAt(i));
			//setStyle
			if(allLbl[ridx][i].getText().equals(String.valueOf(answer.charAt(i)))) {
				allLbl[ridx][i].setStyle("-fx-border-color:green;-fx-border-width:4;-fx-background-color:EFEEEE");
				letter.setColor("GREEN");
			}else{
				for(int j=0;j<answer.length();j++) {
					if(allLbl[ridx][i].getText().equals(String.valueOf(answer.charAt(j)))) {
						allLbl[ridx][i].setStyle("-fx-border-color:cfad27;-fx-border-width:4;-fx-background-color:EFEEEE");
						letter.setColor("YELLOW");
						break;
					}else {
						allLbl[ridx][i].setStyle("-fx-border-color:gray;-fx-border-width:4;-fx-background-color:EFEEEE");
					}
				}
			}
		}
		if(ridx<rnums-1) {
			ridx++;
			idx=0;
		}
		
		System.out.println(guess.getGuess().get(2).getLetter()+", "+guess.getGuess().get(2).getColor());
	}
	
	public void Forward() {
		if(idx<nums) {idx++;}
		
	}
	
	public void Backward() {
		if(idx>0) {idx--;}
	}
}
