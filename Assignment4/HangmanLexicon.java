/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import acmx.export.java.io.FileReader;
//import acmx.export.java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class HangmanLexicon {
	
	public HangmanLexicon() {
		try {
			  FileReader fr = new FileReader("HangmanLexicon.txt");	
			  BufferedReader rd = new BufferedReader(fr);
			  words = new ArrayList<String>();
			  while (true) {
				String line = rd.readLine();
				if(line == null) break;
				words.add(line);
			  }
			  rd.close();
			  fr.close();
			} catch (Exception e) {
			}
		
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return words.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		if(index > words.size())
		  index = index % words.size();
		return words.get(index);
	}
	private ArrayList<String> words;
}
