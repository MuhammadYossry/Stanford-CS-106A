/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
	//fill even blocks
	//turn left
	// put beeper
	// while(fornIsClear)
	// move
	// if(FrontIsClear)
	//  move
	// putBeeper
	public void run() { 
		 if(leftIsBlocked()){
			 fillOneRow();
		 }else{
			 turnLeft();
		     fillColumn();
		 }
		}
	
	private void fillColumn() {
		if(beepersPresent() && frontIsClear()){
			move();
		}
		while(frontIsClear()){
			putBeeper();
			if(frontIsClear()){
			  move();
			}
			if(frontIsClear()){
				move();
				if(frontIsBlocked() && noBeepersPresent()){
					putBeeper();
					switchColumn();
					if(frontIsClear()){
					  move();
					}
				}
			}else{
				switchColumn();
			}
		}
	}
	
	private void switchColumn(){
		if(facingNorth() && rightIsClear()){
			turnRight();
			move();
			turnRight();
		}else if(facingSouth() && leftIsClear()) {
			turnLeft();
			move();
			turnLeft();
		}
	}
	
	private void fillOneRow(){
		putBeeper();
		while(frontIsClear()){
			move();
			if(frontIsClear()){
				move();
				putBeeper();
			}
		}
	}

}
