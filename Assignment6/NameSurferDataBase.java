//import acmx.export.java.util.HashMap;
//import acmx.export.java.util.Map;
import java.util.*;
import acmx.export.java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;
/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		db = new HashMap<String, NameSurferEntry>();
		try {
			  FileReader fr = new FileReader(filename);	
			  BufferedReader rd = new BufferedReader(fr);
			  while (true) {
				String line = rd.readLine();
				if(line == null) break;
				NameSurferEntry tmpEntry = new NameSurferEntry(line);
				db.put(tmpEntry.getName(), tmpEntry);
			  }
			  rd.close();
			  fr.close();
			} catch (Exception e) {
			}
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		return db.get(name);
	}
	
	private HashMap<String, NameSurferEntry> db;
}

