/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;
	public void run() {
		int input = 0;
		int min =0;
		int max = 0;
		println("This program finds the largest and smallest numbers.");
		while(true) {
			input = readInt("?");
			if(input != SENTINEL){
				min = input < min ? input : min;
				max = input > max ? input : max;
			}else {
				break;
			}
		}
		
		if(max == 0 && min == 0){
			println("You entered no values");
		} else {
			if( min == 0) min = max; 
			if (max == 0) max = min;
			println("The maximum is " + max + " And the minimum is " + min + ".");
		}
}
}
