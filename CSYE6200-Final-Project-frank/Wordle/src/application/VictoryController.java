package application;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class VictoryController {

	@FXML
	Label lbl1,lbl2,lbl3,lbl4,lbl5,lbl6;
	@FXML
	private Label[] allLbl;

	//a guess is create once initialize, after that key "enter" on "idx=gamemode.length", will create a new guess
	
	//scene change
	Stage stage;
	Scene scene;
	@FXML
	public void initialize() {
		this.allLbl = new Label[] {lbl1, lbl2, lbl3, lbl4, lbl5, lbl6};
			GameHistory history = new GameHistory();
			GameHistory.get();
			Integer guesses = history.getGuessNum();
			Integer wins = history.getWins();
			Integer losses = history.getLosses();
			Integer cs = history.getStreak();
			Integer bs = history.getBestStreak();
			Integer gp = wins+losses;
			Double percent = new Double(0);
			percent = wins.doubleValue()/(wins.doubleValue()+losses.doubleValue())*100;
			allLbl[0].setText(gp.toString());
			allLbl[1].setText(cs.toString());
			allLbl[2].setText(bs.toString());
			//allLbl[3].setText(percent.toString());
			allLbl[3].setText(String.format("%.2f", percent));
			allLbl[4].setText(guesses.toString());
			allLbl[5].setText(history.getLastWord());
			};
	
}
