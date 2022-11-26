package application;

import java.util.Scanner;

public class Test {
	
	static PastGuesses pastguess = new PastGuesses();
	static int mode = 5;
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str="";
		do {
			System.out.printf("Please input a %d letters word: ", mode);
			str=scn.nextLine();
		}while(str.length()!=mode);
		//turn input string to a guess object
		Guess guess = pastguess.newGuess(str);
		for(int i=0; i<str.length();i++) {
			guess.newLetter(str.charAt(i));
		}
		//compare to solution, set colors for the letters
		//
		//print out all letters of past guess 
		for(Guess guess1 : pastguess.getGuesses()) {
			for(Letter letter: guess1.getGuess()) {
				System.out.println("char: "+String.valueOf(letter.getLetter())+" color: "+letter.getColor());
			}			
		}
		
		scn.close();
	}
	

}
