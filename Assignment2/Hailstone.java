/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Pick some positive integer: ");
		while( n != 1){
			if(n % 2 == 0){
				println(n + " is even, so I take half: " + (n / 2));
				n /= 2;
			}else {
				println(n + " is odd, so I make 3n + 1: " + (n*3 + 1));
				n = n*3 + 1;
			}
		}
	}
}

