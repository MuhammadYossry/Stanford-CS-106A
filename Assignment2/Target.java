/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/* You fill this in. */
		GOval outerOval = new GOval( (getWidth()- 72) / (double)2, (getHeight()- 72) / (double)2,
		                      72, 72);
		outerOval.setColor(Color.RED);
		outerOval.setFilled(true);
		add(outerOval);
		GOval midOval = new GOval( (getWidth()- 46.8) / (double)2, (getHeight()- 46.8) / (double)2,
				46.8, 46.8);
		midOval.setColor(Color.WHITE);
		midOval.setFilled(true);
        add(midOval);
        GOval innerOval = new GOval( (getWidth()- 21.6) / (double)2, (getHeight()- 21.6) / (double)2,
        		21.6, 21.6);
        innerOval.setColor(Color.RED);
        innerOval.setFilled(true);
        add(innerOval);
	}
}
