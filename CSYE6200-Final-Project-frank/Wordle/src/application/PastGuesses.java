package application;

import java.util.ArrayList;

public class PastGuesses {
	ArrayList <Guess> guesses;

	public PastGuesses() {
		this.guesses = new ArrayList<>();
	}
	
	public ArrayList<Guess> getGuesses() {
		return guesses;
	}

	public void setGuesses(ArrayList<Guess> guesses) {
		this.guesses = guesses;
	}
	
	public Guess newGuess() {
		Guess ng = new Guess();
		guesses.add(ng);
		return ng;
	}
	
	public Guess newGuess(String str) {
		Guess ng = new Guess(str);
		guesses.add(ng);
		return ng;
	}
	
	public void deleteGuess(Guess guess) {
		guesses.remove(guess);
	}
	
	public int getSize() {
		int size = this.guesses.size();
		return size;
	}
}
