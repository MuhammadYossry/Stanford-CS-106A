/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setupBoard();
		addMouseListeners();
		while(true){
			ball.move(vx, vy);
			checkCollision();
			pause(20);
		}
	}
	
	private void setupBoard(){
		setupBricks();
		setupPaddle();
		setupBall();
	}
	
	private void setupBricks() {
	    int  y_offset = BRICK_Y_OFFSET;
	    int x_offset = BRICK_SEP / 2; 
	    Color brick_color = rgen.nextColor();
		for(int i = 0; i < NBRICK_ROWS; i++){
			for(int j = 0; j < NBRICKS_PER_ROW; j++){
				GRect brick = new GRect(x_offset, y_offset, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setColor(brick_color);
				brick.setFilled(true);
				add(brick);
				x_offset += BRICK_WIDTH + BRICK_SEP;
			}
			y_offset += BRICK_HEIGHT + BRICK_SEP;
			x_offset = BRICK_SEP / 2;
			//change color every two rows
			if(i % 2 != 0 && i != 0){
				brick_color = rgen.nextColor();
			}
		}
	}
	
	private void setupPaddle(){
		paddle = new GRect((getWidth() - PADDLE_WIDTH) / 2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT ,
				                  PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		first_mouse_move = true;
	}
	
	private void setupBall() {
		ball = new GOval(getWidth()/2 -BALL_RADIUS ,
				getHeight()/2 - BALL_RADIUS,
				BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
		vy = 3.0;
	}
	
	private void checkCollision(){
		GObject collider = getCollidingObject();//getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS*2);
		if(collider == paddle){
			reverseVelocity(true, true);
			if (rgen.nextBoolean(0.5)) vx = -vx;
		}else if(collider != null) {
			reverseVelocity(true, true);
			if (rgen.nextBoolean(0.5)) vx = -vx;
			remove(collider);
		} else if(ball.getX() <= 0 || (ball.getX()+BALL_RADIUS*2) >= WIDTH){
			reverseVelocity(true, false);
		}else if(ball.getY() <= 0){
			reverseVelocity(false, true);
		}
	}
	
	private GObject getCollidingObject(){
		GObject collider = getElementAt(ball.getX(), ball.getY());
		if(collider != null) return collider;
		collider = getElementAt(ball.getX()+ BALL_RADIUS*2, ball.getY());
		if(collider != null) return collider;
		collider = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS*2);
		if(collider != null) return collider;
		collider = getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY()+ BALL_RADIUS*2);
		if(collider != null) return collider;
		return null;
	}
	
	public void mouseMoved(MouseEvent e){
		if(!first_mouse_move){
			int dx = e.getX() - last_mouse_x;
			dx /= 8;
			if( dx > 0 && (paddle.getX() + PADDLE_WIDTH ) >= WIDTH){
			}else if( dx < 0 && (paddle.getX() ) <= 0){
			}else{
				paddle.setLocation((double) e.getX(),(double) getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT );
			}
			
		}else {
			last_mouse_x = e.getX();
			first_mouse_move = false;
		}
	}
	
	private void reverseVelocity(boolean reverse_x, boolean reverse_y) {
		if(reverse_x){
			if(vx >= 0){
			  vx = rgen.nextDouble(-1.0, -3.0);
		    }else{
		      vx = rgen.nextDouble(1.0, 3.0);
		    }
		}
		if(reverse_y){
			vy = -vy;
		}
	}
	
	private GRect paddle;
	private GOval ball;
	private double vx, vy;
	private int last_mouse_x;
	private boolean first_mouse_move;
	RandomGenerator rgen = RandomGenerator.getInstance();

}
