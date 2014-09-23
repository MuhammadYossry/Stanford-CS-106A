/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
       RandomGenerator rg = RandomGenerator.getInstance();
       HangmanLexicon hl = new HangmanLexicon();
       canvas.reset();
	   String word = hl.getWord(rg.nextInt(0, hl.getWordCount() - 1));		
	   int gusses_left = 8;
	   int right_gusses = 0;
	   String dashed_word = getDashedString(word);
	   println("Welcome to Hangman!");
	   while(true){
		   println("The word now Looks like: " + dashed_word);
		   canvas.displayWord(dashed_word);
		   println("You have " + gusses_left + " left");
		   String input = readLine("Your guess: ");
		   int char_index = 0;
		   int starting_from = 0;
		   do {
			   char_index = word.indexOf(input.toUpperCase(), starting_from);
			   if(char_index != -1){
				   boolean previously_known = (dashed_word.charAt(char_index) == word.charAt(char_index));
				   if(previously_known){
					   starting_from = char_index + 1;
				   }else {
					   break;
				   }
			   }
		   } while (char_index != -1);
		   
		   if( char_index != -1){
			   dashed_word = changeChar(dashed_word, char_index, word.charAt(char_index));
			   right_gusses++;
		   }else {
			   canvas.noteIncorrectGuess(input.toUpperCase().charAt(0));
			   gusses_left--;
		   }
		   if(right_gusses >= word.length()){
			   println("The word now Looks like: " + dashed_word);
			   println("Congratulations, You WON!");
			   break;
		   }else if(gusses_left <= 0) {
			   println("The word was Looks like: " + word);
			   println("You Lose!");
			   break;
		   }
	   }
	}
    
    private String changeChar(String word, int index, char character){
    	String new_word = word.substring(0, index) + character + word.substring(index +1 );
    	return new_word;
    	
    }
    private String getDashedString(String word){
    	String dashed = "";
    	for(int i = 0; i < word.length(); i++){
    		dashed += "-";
    	}
    	return dashed;
    }
    public void init() {
        canvas = new HangmanCanvas();
    	add(canvas);
    }
    private HangmanCanvas canvas;
}
