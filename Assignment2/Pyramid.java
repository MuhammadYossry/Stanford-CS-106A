/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 20;
	
	public void run() {
		//initial starting values
		int rowStartX = (getWidth() - (BRICKS_IN_BASE * BRICK_WIDTH)) / 2;
		int rowStartY = getHeight() - BRICK_HEIGHT;
		int bricksInRow = BRICKS_IN_BASE;
		//BRICKS_IN_BASE which is number of rows in the pyramid
		for(int rowNum = 1; rowNum <= BRICKS_IN_BASE; rowNum++, bricksInRow--){
			for(int i = 0; i < bricksInRow; i++) {
				int dx = i * BRICK_WIDTH;
				GRect brick = new GRect(rowStartX + dx, rowStartY, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
			rowStartY -= BRICK_HEIGHT;
			rowStartX += (BRICK_WIDTH / 2);
		}
	}
}

