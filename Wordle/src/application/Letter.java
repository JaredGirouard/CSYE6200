package application;

public class Letter {
	private char letter;
	private String color;

	public Letter(char letter) {
		super();
		this.letter = letter;
		this.color = "GRAY";
	}
	public Letter(char letter, String color) {
		super();
		this.letter = letter;
		this.color = color;
	}
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
