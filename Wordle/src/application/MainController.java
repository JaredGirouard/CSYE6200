package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class MainController {
	@FXML
	Label lbl1,lbl2,lbl3,lbl4,lbl5;
	@FXML
	private Label[] allLbl;
	@FXML
	Button btn1;
	@FXML
	Label solLbl;
	int nums = 5;
	int idx = 0;
	String answer = "APPLE";
	PastGuesses pastguess = new PastGuesses();
	//a guess is create once initialize, after that key "enter" on "idx=gamemode.length", will create a new guess
	Guess guess = pastguess.newGuess();
	
	@FXML
	public void initialize() {
		this.allLbl = new Label[]{lbl1, lbl2, lbl3, lbl4, lbl5};
	}
	@FXML
	public void SetLabelText(KeyEvent event) {
//		if case == character, setlabel text, point on next label
//		   case == backspace, setlabel to blank, point on prev label
	
		switch(event.getCode()) {
		case BACK_SPACE:
			Backward();
			allLbl[idx].setText("");		
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
					allLbl[idx].setText(s.toUpperCase());
					Forward();	
	        	}
	        }
			break;
		}

	}
	
	public void SubmitGuess() {
		solLbl.setText(answer);
		String str="";
		for(Label label:allLbl) {
			str+=label.getText();
		}
		System.out.println("Full String is: "+str);
		for(int i=0;i<allLbl.length;i++) {
			//instance created
			 
			Letter letter = guess.newLetter(str.charAt(i));
			//setStyle
			if(allLbl[i].getText().equals(String.valueOf(answer.charAt(i)))) {
				allLbl[i].setStyle("-fx-border-color:green;-fx-border-width:4;-fx-background-color:EFEEEE");
				letter.setColor("GREEN");
			}else{
				for(int j=0;j<answer.length();j++) {
					if(allLbl[i].getText().equals(String.valueOf(answer.charAt(j)))) {
						allLbl[i].setStyle("-fx-border-color:cfad27;-fx-border-width:4;-fx-background-color:EFEEEE");
						letter.setColor("YELLOW");
						break;
					}else {
						allLbl[i].setStyle("-fx-border-color:gray;-fx-border-width:4;-fx-background-color:EFEEEE");
					}
				}
			}
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
