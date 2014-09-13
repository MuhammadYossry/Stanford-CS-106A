import acm.graphics.*;
import acm.program.*;
import java.awt.*;


public class ProgramHierarchy extends GraphicsProgram {
	private static final int BOX_HEIGHT = 90;
	private static final int BOX_WIDTH = 160;
	
	public void run() {
		GRect supBox = addSupBox();
		GRect leftBox = addLeftBox();
		GRect midBox = addMidBox();
		GRect rightBox = addRightBox();
	    connectTwoBoxes(supBox, leftBox);
	    connectTwoBoxes(supBox, midBox);
	    connectTwoBoxes(supBox, rightBox);
	}
	
	private void connectTwoBoxes(GRect supBox, GRect subBox) {
		GLine line = new GLine(supBox.getX() + (supBox.getWidth() / 2),
				               supBox.getY() + supBox.getHeight(),
				               subBox.getX() + (subBox.getWidth() / 2),
				               subBox.getY() 
				               );
		add(line);
	}
	
	private void addCenteredLabel(GLabel label, GRect Box) {
		label.setFont("Time-18");
		add(label, Box.getX() + (Box.getWidth() / 2) - (label.getWidth() / 2),
				   Box.getY() + (Box.getHeight() / 2) + (label.getAscent() / 2));
		
	}
	private GRect addSupBox() {
		GRect supBox = new GRect((getWidth() - BOX_WIDTH) / 2,
                (getHeight() -BOX_HEIGHT * 3) / 2 ,
                BOX_WIDTH, BOX_HEIGHT);
        add(supBox);
        addCenteredLabel(new GLabel("Program"), supBox);
        return supBox;
	}
	private GRect addLeftBox(){
	    GRect leftBox = new GRect((getWidth() - BOX_WIDTH) / 2 - 25 - BOX_WIDTH,
                getHeight() / 2 ,
                BOX_WIDTH, BOX_HEIGHT);
	    add(leftBox);
	    addCenteredLabel(new GLabel("GraphicsProgram"), leftBox);
	    return leftBox;
	}
	
	private GRect addMidBox() {
		GRect midBox = new GRect((getWidth() - BOX_WIDTH) / 2,
                getHeight() / 2 ,
                BOX_WIDTH, BOX_HEIGHT);
	    add(midBox);
	    addCenteredLabel(new GLabel("ConsoleProgram"), midBox);
	    return midBox;
	}
	
	private GRect addRightBox() {
		GRect rightBox = new GRect((getWidth() - BOX_WIDTH) / 2 + 25 + BOX_WIDTH,
                getHeight() / 2 ,
                BOX_WIDTH, BOX_HEIGHT);
	    add(rightBox);
	    addCenteredLabel(new GLabel("DialogProgram"), rightBox);
	    return rightBox;
	}

}
