/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		
		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
		dices = new int[N_DICE];
		categoresScores = new int[nPlayers][N_CATEGORIES];
		for (int i = 0; i < categoresScores.length; i++) {
			for (int j = 0; j < categoresScores[0].length; j++) {
				categoresScores[i][j] = 0;
			}
		}
	}
	
	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();
		
		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");
			
			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;
			
			dialog.println("Please enter a valid number of players.");
		}
	}
	
	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	private void playGame() {
		/* You fill this in! */
		int category = 0;
		for(int i = 0; i < N_ROUNDS; i++){
			for (int playerIndex = 0; playerIndex < nPlayers; playerIndex++) {
			  playDices(playerIndex);
			  display.printMessage("Select Category");
			  category = display.waitForPlayerToSelectCategory();
			  if(YahtzeeMagicStub.checkCategory(dices, category)){
				  int score = calculateTurnScore(category, dices);
				  categoresScores[playerIndex][category] += score;
				  display.updateScorecard(category, playerIndex, categoresScores[playerIndex][category]);
				  
			  }else {
				  display.updateScorecard(category, playerIndex, 0);
			  }
			  categoresScores[playerIndex][UPPER_SCORE] = getUpperScore(playerIndex);
			  display.updateScorecard(UPPER_SCORE, playerIndex, categoresScores[playerIndex][UPPER_SCORE]);
			  if (checkForUpperBonus(playerIndex)) {
				  categoresScores[playerIndex][UPPER_BONUS] = 35;
				  display.updateScorecard(UPPER_BONUS, playerIndex, categoresScores[playerIndex][UPPER_BONUS]);	
		      }
			  categoresScores[playerIndex][TOTAL] = getScoreTotal(playerIndex);
			  display.updateScorecard(TOTAL, playerIndex, categoresScores[playerIndex][TOTAL]);
			}
		}
		String winner = getWinner();
		display.printMessage("The Winner is: " + winner + "!!!");
	}
	
	private String getWinner(){
		int winnerIndex = 0;
		for (int i = 0; i < categoresScores.length; i++) {
			if(categoresScores[i][TOTAL] > categoresScores[winnerIndex][TOTAL]){
				winnerIndex = i;
			}
		}
		return playerNames[winnerIndex];
	}
	
	private int getUpperScore(int playerIndex){
		int upperScore = categoresScores[playerIndex][ONES];
		upperScore += categoresScores[playerIndex][TWOS];
		upperScore += categoresScores[playerIndex][THREES];
		upperScore += categoresScores[playerIndex][FOURS];
		upperScore += categoresScores[playerIndex][FIVES];
		upperScore += categoresScores[playerIndex][SIXES];
		return upperScore;
	}
	
	private int getScoreTotal(int playerIndex){
		int totalScore = categoresScores[playerIndex][UPPER_SCORE];
		totalScore += categoresScores[playerIndex][UPPER_BONUS];
		totalScore += categoresScores[playerIndex][THREE_OF_A_KIND];
		totalScore += categoresScores[playerIndex][FOUR_OF_A_KIND];
		totalScore += categoresScores[playerIndex][FULL_HOUSE];
		totalScore += categoresScores[playerIndex][SMALL_STRAIGHT];
		totalScore += categoresScores[playerIndex][LARGE_STRAIGHT];
		totalScore += categoresScores[playerIndex][YAHTZEE];
		totalScore += categoresScores[playerIndex][CHANCE];
		return totalScore;
	}
	
	private boolean checkForUpperBonus(int playerIndex){
		if (getUpperScore(playerIndex) > 63) {
			return true;
		} else {
			return false;
		}
	}
	private void playDices(int player){
		display.printMessage("Roll the Dice");
		display.waitForPlayerToClickRoll(player);
		RandomizeDices(dices);		
		display.displayDice(dices);
		for (int j = 0; j < 3; j++) {
			display.printMessage("Select dice you want to reRoll if any");
			display.waitForPlayerToSelectDice();
			if(isDicesSelected()){
				RandomizeSelectedDices();
				display.displayDice(dices);
			} else {
				return;
			}
		}
		
	}
	
	private int calculateTurnScore(int category, int[] dices){
		int score = 0;
		switch (category) {
		case ONES:
			for (int i = 0; i < dices.length; i++) {
				if(dices[i] == 1) score += 1;
			}
			break;
		case TWOS:
			for (int i = 0; i < dices.length; i++) {
				if(dices[i] == 2) score += 2;
			}
			break;
		case THREES:
			for (int i = 0; i < dices.length; i++) {
				if(dices[i] == 3) score += 3;
			}
			break;
		case FOURS:
			for (int i = 0; i < dices.length; i++) {
				if(dices[i] == 4) score += 4;
			}
			break;
		case FIVES:
			for (int i = 0; i < dices.length; i++) {
				if(dices[i] == 5) score += 5;
			}
			break;
		case SIXES:
			for (int i = 0; i < dices.length; i++) {
				if(dices[i] == 6) score += 6;
			}
			break;
		case THREE_OF_A_KIND:
			int element = 0;
			int matching_count = 0;
			for (int i = 0; i < dices.length; i++) {
				element = dices[i];
				matching_count = 0;
				for (int j = 0; j < dices.length; j++) {
					if(dices[j] == element) matching_count++;
				}
				if(matching_count >= 3) break;
			}
			score = 3 * element;
			break;
		case FOUR_OF_A_KIND:
			element = 0;
			matching_count = 0;
			for (int i = 0; i < dices.length; i++) {
				element = dices[i];
				matching_count = 0;
				for (int j = 0; j < dices.length; j++) {
					if(dices[j] == element) matching_count++;
				}
				if(matching_count >= 4)  break;
			}
			score = 4 * element;
			break;
		case FULL_HOUSE:
			score = 25;
			break;
		case SMALL_STRAIGHT:
			score = 30;
			break;
		case LARGE_STRAIGHT:
			score = 40;
			break;
		case CHANCE:
			for (int i = 0; i < dices.length; i++) {
				score += dices[i];
			}
			break;
		case YAHTZEE:
			score =  50;
			break;
		default:
			score =  -1;
			break;
		}
		
		return score;
		
	}
	
	private void RandomizeDices(int[] dices) {
		rg = RandomGenerator.getInstance();
		for (int i = 0; i < dices.length; i++) {
			dices[i] = rg.nextInt(1, 6);
		}
	}
	
	private void RandomizeSelectedDices(){
		rg = RandomGenerator.getInstance();
		for (int i = 0; i < dices.length; i++) {
			if(display.isDieSelected(i))
			    dices[i] = rg.nextInt(1, 6);
		}
	}
	
	private boolean isDicesSelected(){
		for (int i = 0; i < dices.length; i++) {
			if(display.isDieSelected(i))
				return true;
		}
		return false;
	}
		
	/* Private instance variables */
	private int nPlayers;
	private int[] dices;
	private int[][] categoresScores;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rg;
}
