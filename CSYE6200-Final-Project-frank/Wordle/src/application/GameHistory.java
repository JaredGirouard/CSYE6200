package application;

public class GameHistory {
	
	static int wins = 0;
	static int losses = 0;
	static int streak = 0;
	static int bestStreak = 0;
	static int guesses = -1;
	static String lastWord = new String();
	
	private static GameHistory history = new GameHistory();
	public static GameHistory get() { return history; }
	
	public void addGuess() {
		guesses = guesses+1;
	}
	public int getGuessNum() {
		return guesses;
	}
	public void addWin() {
		wins = wins + 1;
		streak = streak+1;
		if (streak>bestStreak) {
			bestStreak = streak;
		}
	}
	public int getWins() {
		return wins;
	}
	public int getStreak() {
		return streak;
	}
	public int getBestStreak() {
		return bestStreak;
	}
	public void addLoss() {
		losses = losses + 1;
		streak = 0;
	}
	public int getLosses() {
		return losses;
	}
	public static void newGame() {
		guesses = 0;
	}
	public void setLastWord(String lw) {
		lastWord = lw;
	}
	public String getLastWord() {
		return lastWord;
	}
}
