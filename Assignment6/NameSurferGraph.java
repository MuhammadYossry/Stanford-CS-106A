/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		entries = new ArrayList<NameSurferEntry>();
		rg = RandomGenerator.getInstance();
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //
		entries.clear();
		update();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entries.add(entry);
		update();
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		// You fill this in //
		removeAll();
		displayGrid();
		displayEntries();
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private void displayEntries() {
		for (int i = 0; i < entries.size(); i++) {
			displayEntry(entries.get(i));
		}
	}
	
	private void displayEntry(NameSurferEntry entry) {
		int vStep = getWidth() / (NDECADES - 1);
		int xEnd = vStep;
		
		int xStart = 0;
		int decade = START_DECADE;
		Color lineColor = rg.nextColor();
		for (int i = 0; i < NDECADES - 1; i++, decade += 10) {
			GLine line = new GLine(xStart, getRankY(entry.getRank(decade)), xEnd, getRankY(entry.getRank(decade+10)));
			line.setColor(lineColor);
			add(line);
			
			xStart += vStep;
			xEnd += vStep;
		}
		
	}
	
	private int getRankY(int rank){
		if( rank == 0) return getHeight() - GRAPH_MARGIN_SIZE;
		float yIndex = ((float)(getHeight() - 2*GRAPH_MARGIN_SIZE) / 1000) * (1000 - rank);
		return (int)yIndex + GRAPH_MARGIN_SIZE;
	}
	
	private void displayGrid() {
		int vStep = getWidth() / (NDECADES - 1);
		int xIndex = vStep;
		for (int i = 0; i < NDECADES - 2; i++) {
			GLine vLine = new GLine(xIndex, 0, xIndex, getHeight());
			add(vLine);
			xIndex += vStep;
		}
		
		xIndex = 0;
		int decade = START_DECADE;
		for (int i = 0; i < NDECADES - 1; i++, decade += 10) {
			GLabel vLine = new GLabel( "" + decade, xIndex + 3, getHeight() - 4);//new GLine(xIndex, 0, xIndex, getHeight());
			add(vLine);
			xIndex += vStep;
		}
		
		int yIndent = GRAPH_MARGIN_SIZE;
		GLine hTopLine = new GLine(0, yIndent, getWidth(), yIndent);
		add(hTopLine);
		GLine hBotLine = new GLine(0, getHeight() - yIndent, getWidth(), getHeight() - yIndent);
		add(hBotLine);
	}
	
	private ArrayList<NameSurferEntry> entries;
	private RandomGenerator rg;
}
