package dk.bhpark.skovbase;

import java.io.DataInputStream;

/** ************************************************************************************************
 *                                                                                                 *
 *  Revision log for the dk.bhpark.skovbase package.                                               *
 *                                                                                                 *
 * <p>                                                                                             *
 * <b>(C) Copyright Claus Jensen. 2017                                                             *
 * @version 1.02 - 04/03/2017                                                                      *
 * @author Claus Jensen (claus@bhpark.dk)                                                          *
 ************************************************************************************************ */

public class Revisions {

	static final String VERSION = "1.02";

	/**
	 * This method is called to get the program version.
	 * 
	 * @return the version string
	 */
	public static String getVersion() {
		
		return VERSION;
	}

	/**
	 * This method simply kicks off the program.
	 * 
	 * @param args are ignored by this program
	 */
	public static void main(String[] args) {

		new Revisions();
	}
	
	/**
	 * The constructor prints the revision log.
	 */
	public Revisions() {
		
		super();
		try {
	        DataInputStream dis = new DataInputStream(getClass().getResourceAsStream("/resources/revision.log"));
	        byte[] theBytes = new byte[dis.available()];
	        dis.read(theBytes, 0, dis.available());
	        System.out.println(new String(theBytes));
	        dis.close();
	    } catch (Exception e) {}
	}
}

