/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    // You fill this in, along with any helper methods //
		nameLabel = new JLabel("Album Name");
		name = new JTextField(20);
		add(nameLabel, SOUTH);
		add(name, SOUTH);
	
		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		
		db = new NameSurferDataBase(NAMES_DATA_FILE);
		graph = new NameSurferGraph();
		add(graph);
		addActionListeners();
		name.addActionListener(this);
		
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getSource() == name) {
			 graph.addEntry(db.findEntry(name.getText()));
		}else if(e.getActionCommand().equals("Graph")) {
			graph.addEntry(db.findEntry(name.getText()));
		}else if(e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
		
	}
	private JLabel nameLabel;
	private JTextField name;
	private NameSurferDataBase db;
	private NameSurferGraph graph;
}
