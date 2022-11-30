package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FourController {
	@FXML
	Label lbl1,lbl2,lbl3,lbl4,lbl11,lbl21,lbl31,lbl41,lbl12,lbl22,lbl32,lbl42,
	lbl13,lbl23,lbl33,lbl43,lbl14,lbl24,lbl34,lbl44,lbl15,lbl25,lbl35,lbl45,lbl16, lbl26, lbl36, lbl46;
	@FXML //keyboard elements
	Label lblQ,lblW,lblE,lblR,lblT,lblY,lblU,lblI,lblO,lblP,
		  lblA,lblS,lblD,lblF,lblG,lblH,lblJ,lblK,lblL,
		  lblZ,lblX,lblC,lblV,lblB,lblN,lblM;
	HashMap<Character,Label> keyBoardMap = new HashMap<>();
	HashMap<Character,Letter> keyBoardLetterMap = new HashMap<>();
	@FXML //add,delete numbers of rows
	Button btnAdd,btnDel;
	@FXML
	private Label[][] allLbl;
	@FXML
	Button btn1;
	@FXML
	Label solLbl;
	@FXML
	ChoiceBox<String> cbMode;
	ObservableList<String> cbOptions = FXCollections.observableArrayList("4-Letter Game","5-Letter Game","6-Letter Game");
	int nums = 4;
	int rnums = 6;
	int idx = 0;
	int ridx = 0;
	int rmax = 7;
	int rmin = 3;
	String answer;
	PastGuesses pastguess = new PastGuesses();
	ArrayList<String> commonWordList = new ArrayList<String>();
	ArrayList<String> validWordList = new ArrayList<String>();
	//a guess is create once initialize, after that key "enter" on "idx=gamemode.length", will create a new guess
	//scene change
	Stage stage;
	Scene scene;
	
	@FXML
	public void initialize() {
		this.allLbl = new Label[][] {{lbl1, lbl2, lbl3, lbl4},{lbl11, lbl21, lbl31, lbl41},{lbl12, lbl22, lbl32, lbl42},
			{lbl13, lbl23, lbl33, lbl43},{lbl14, lbl24, lbl34, lbl44},{lbl15, lbl25, lbl35, lbl45},{lbl16, lbl26, lbl36, lbl46}};
		for(Label lastrow:allLbl[rnums]) {lastrow.setVisible(false);}
		//initialize the keyboard
		keyBoardMap.put('A', lblA);keyBoardMap.put('B', lblB);keyBoardMap.put('C', lblC);keyBoardMap.put('D', lblD);keyBoardMap.put('E', lblE);keyBoardMap.put('F', lblF);
		keyBoardMap.put('G', lblG);keyBoardMap.put('H', lblH);keyBoardMap.put('I', lblI);keyBoardMap.put('J', lblJ);keyBoardMap.put('K', lblK);keyBoardMap.put('L', lblL);
		keyBoardMap.put('M', lblM);keyBoardMap.put('N', lblN);keyBoardMap.put('O', lblO);keyBoardMap.put('P', lblP);keyBoardMap.put('Q', lblQ);keyBoardMap.put('R', lblR);
		keyBoardMap.put('S', lblS);keyBoardMap.put('T', lblT);keyBoardMap.put('U', lblU);keyBoardMap.put('V', lblV);keyBoardMap.put('W', lblW);keyBoardMap.put('X', lblX);
		keyBoardMap.put('Y', lblY);keyBoardMap.put('Z', lblZ);
		keyBoardLetterMap.put('A', new Letter('A'));keyBoardLetterMap.put('B', new Letter('B'));keyBoardLetterMap.put('C', new Letter('C'));keyBoardLetterMap.put('D', new Letter('D'));keyBoardLetterMap.put('E', new Letter('E'));
		keyBoardLetterMap.put('F', new Letter('F'));keyBoardLetterMap.put('G', new Letter('G'));keyBoardLetterMap.put('H', new Letter('H'));keyBoardLetterMap.put('I', new Letter('I'));keyBoardLetterMap.put('J', new Letter('J'));
		keyBoardLetterMap.put('K', new Letter('K'));keyBoardLetterMap.put('L', new Letter('L'));keyBoardLetterMap.put('M', new Letter('M'));keyBoardLetterMap.put('N', new Letter('N'));keyBoardLetterMap.put('O', new Letter('O'));
		keyBoardLetterMap.put('P', new Letter('P'));keyBoardLetterMap.put('Q', new Letter('Q'));keyBoardLetterMap.put('R', new Letter('R'));keyBoardLetterMap.put('S', new Letter('S'));keyBoardLetterMap.put('T', new Letter('T'));
		keyBoardLetterMap.put('U', new Letter('U'));keyBoardLetterMap.put('V', new Letter('V'));keyBoardLetterMap.put('W', new Letter('W'));keyBoardLetterMap.put('X', new Letter('X'));keyBoardLetterMap.put('Y', new Letter('Y'));
		keyBoardLetterMap.put('Z', new Letter('Z'));
		cbMode.setItems(cbOptions);
		cbMode.setValue("4-Letter Game");
		commonWordList = Utilities.ReadWordsFromFile("Wordle/src/application/four-letter-words-common.txt");
		validWordList = Utilities.ReadWordsFromFile("Wordle/src/application/four-letter-words-possible.txt");
		answer = commonWordList.get(new Random().nextInt(commonWordList.size()));
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
		if(idx!=nums) {return;}
		String str="";
		for(Label label:allLbl[ridx]) {
			str+=label.getText();
		}
		
		if (!validWordList.contains(str))
		{
			System.out.println("Invalid guess: "+str);
			return;
		}
		if(ridx == rnums-1) {
			solLbl.setText(answer);
			solLbl.setStyle("-fx-background-radius:8;-fx-background-color:black");
		}
		System.out.println("Full String is: "+str);
		Guess guess = pastguess.newGuess();
		ArrayList<Character> answerElems = new ArrayList<Character>();
		for (char c: answer.toCharArray()) {answerElems.add(c);}
		HashMap<Character,Integer> ansMap = new HashMap<>();
		HashMap<Character,Integer> hitMap = new HashMap<>();	
		for(char c:answerElems) {
			if(!ansMap.containsKey(c)) {
				ansMap.put(c, 1);
				hitMap.put(c, 0);
			}else {
				ansMap.replace(c, ansMap.get(c)+1);
			}
		}

		for(int i=0;i<allLbl[ridx].length;i++) {
			//instance created
			 
			Letter letter = guess.newLetter(str.charAt(i));
			//setStyle
			if(allLbl[ridx][i].getText().equals(String.valueOf(answer.charAt(i)))) {
				allLbl[ridx][i].setStyle("-fx-border-color:darkseagreen;-fx-border-width:4;-fx-background-color:EFEEEE");
				letter.setColor("GREEN");
				hitMap.replace(letter.getLetter(), hitMap.get(letter.getLetter())+1);
				//check previous letters if they are set to yellow && , turn it to gray
				if(!answerElems.contains(letter.getLetter()) && i!=0) {
					for(int q=i-1;q>=0;q--) {
						Letter preletter = guess.getGuess().get(q);
						if(allLbl[ridx][q].getText().equals(String.valueOf(answer.charAt(i))) && !preletter.getColor().equals("GREEN") && hitMap.get(letter.getLetter())>=ansMap.get(letter.getLetter())) {
							allLbl[ridx][q].setStyle("-fx-border-color:gray;-fx-border-width:4;-fx-background-color:EFEEEE");							
							preletter.setColor("GRAY");
						}
					}
				}
				answerElems.remove(Character.valueOf(letter.getLetter()));
			
			}else{
				for(int j=0;j<answer.length();j++) {
					if(allLbl[ridx][i].getText().equals(String.valueOf(answer.charAt(j)))) {
						if(answerElems.contains(letter.getLetter())) {
							allLbl[ridx][i].setStyle("-fx-border-color:cfad27;-fx-border-width:4;-fx-background-color:EFEEEE");
							letter.setColor("YELLOW");	
							answerElems.remove(Character.valueOf(letter.getLetter()));
							break;
						}				
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
		PopulateKeyBoard(guess);
		System.out.println(guess.getGuess().get(2).getLetter()+", "+guess.getGuess().get(2).getColor());
	}
	
	public void Forward() {
		if(idx<nums) {idx++;}
		
	}
	
	public void Backward() {
		if(idx>0) {idx--;allLbl[ridx][idx].setText("");	}
	}
	
	public void AddRow() {		
		if(ridx==0 && rnums<rmax) {
			rnums = rnums+1;
			for(Label label:allLbl[rnums-1]) {
				label.setVisible(true);
			}
			System.out.println(rnums);
		}
	}
	
	public void DeleteRow() {
		if(ridx==0 && rnums>rmin) {
			for(Label label:allLbl[rnums-1]) {
				label.setVisible(false);
			}
			rnums=rnums-1;
			System.out.println(rnums);
		}
	}
	public void Start() {
		btnAdd.setDisable(true);
		btnDel.setDisable(true);
	}
	public void PopulateKeyBoard(Guess guess) {
		for(Letter letter:guess.getGuess()) {
			if(letter.getColor()=="GREEN") {
				keyBoardMap.get(letter.getLetter()).setStyle("-fx-background-radius:8;-fx-background-color:darkseagreen");
				keyBoardLetterMap.get(letter.getLetter()).setColor("GREEN");
			}else if(letter.getColor()=="YELLOW") {
				if(keyBoardLetterMap.get(letter.getLetter()).getColor()!="GREEN") {
					keyBoardMap.get(letter.getLetter()).setStyle("-fx-background-radius:8;-fx-background-color:c9b458");
					keyBoardLetterMap.get(letter.getLetter()).setColor("YELLOW");
				}
			}else if(letter.getColor()=="GRAY") {
				if(keyBoardLetterMap.get(letter.getLetter()).getColor()!="GREEN" && keyBoardLetterMap.get(letter.getLetter()).getColor()!="YELLOW") {
					keyBoardMap.get(letter.getLetter()).setStyle("-fx-background-radius:8;-fx-background-color:gray");
					keyBoardLetterMap.get(letter.getLetter()).setColor("GRAY");
				}
			}
		}
	}
	//setup keyboard event listener
	public void onKeyClicked(MouseEvent event) {
        String s = ((Label)event.getSource()).getText();
        System.out.println(s);
        if(Character.isLetter(s.charAt(0))){
        	if(idx<nums) {
				allLbl[ridx][idx].setText(s.toUpperCase());
				Forward();	
        	}
        }
	}
}
