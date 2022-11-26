package application;

import java.util.ArrayList;

public class Guess {
	ArrayList <Letter> guess ;
	String word;
	public Guess() {
		this.guess = new ArrayList<>();
	}
	
	public Guess(String str) {
		this.guess = new ArrayList<>();
		this.word = str;
	}
	
	public ArrayList<Letter> getGuess() {
		return guess;
	}

	public void setGuess(ArrayList<Letter> guess) {
		this.guess = guess;
	}

	public Letter newLetter(char ch) {
		Letter nl = new Letter(ch);
		guess.add(nl);
		return nl;
	}
	
	public void deleteLetter(Letter letter) {
		guess.remove(letter);
	}
	
}
