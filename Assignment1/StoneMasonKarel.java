/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		repairDamage();
	}
	private void repairDamage() {
		while(true){
			repairColoumn();
			if(facingSouth() && leftIsBlocked()){
				break;
			}
			moveToNextColoumn();
		}
		turnLeft();
	}
	private void repairColoumn() {
		if(rightIsBlocked()){
			turnLeft();
		}else {
			turnRight();
		}
		while(frontIsClear()){
			if(noBeepersPresent()){
				putBeeper();
			}
			move();
		}
	}
	private void moveToNextColoumn() {
		if(facingNorth()){
			turnRight();
		}else {
			turnLeft();
		}
		for(int i = 0; i < 4; i++){
			move();
		}
	}
}
