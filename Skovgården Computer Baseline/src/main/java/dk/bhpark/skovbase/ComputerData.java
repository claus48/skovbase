package dk.bhpark.skovbase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/** ************************************************************************************************
 *                                                                                                 *
 * This class creates an object that holds all software installed and services running on a speci- *
 * fic machine. The data is loaded from two machine specific files and are kept for later use.     *
 *                                                                                                 *
 * <p>                                                                                             *                                                                      *
 * <b>(C) Copyright Claus Jensen. 2017                                                             *
 * @version 1.01 - 13/01/2017                                                                      *
 * @author Claus Jensen (claus@bhpark.dk)                                                          *
 ************************************************************************************************ */

public class ComputerData {
	
	private String[][] software = null;
	private String[] services = null;

	/**
	 * All data related to software and services on the specific machine is read into two arrays.
	 * 
	 * @param compName is the name of the computer that the data are from
	 */
	public ComputerData(String compName) {
		
		// get all software
		String prefix = (System.getProperty("Prefix") == null) ? "." : System.getProperty("Prefix");
		try {
			FileInputStream inStream = new FileInputStream(prefix + "/" + compName + "_software.csv");
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "Unicode"));
			String record = null;
			ArrayList<String> names = new ArrayList<>();
			ArrayList<String> versions = new ArrayList<>();
			while ((record = in.readLine()) != null) {
				String[] token = record.split(",");
				if (token.length < 28) continue;		// skip line with too few fields
				if (token[0].equals("Node")) continue;  // skip header line
				names.add(token[2]);
				versions.add(token[26]);
			}
			in.close();			
			software = new String[names.size()][2];
			for (int i = 0; i < software.length; i++) {
				software[i][0] = names.get(i);
				software[i][1] = versions.get(i);
			}
			names = null;
			versions = null;			
		} catch (Exception e) {
			System.err.println("Error occurred processing software list");
			e.printStackTrace(System.err);
		}

		// get all services
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(prefix + "/" + compName + "_services.csv"), "Unicode"));
			String record = null;
			ArrayList<String> names = new ArrayList<>();
			while ((record = in.readLine()) != null) {
				String[] token = record.split(",");
				if (token.length < 27) continue;
				if (token[0].equals("Node")) continue;
				names.add(token[3]);
			}
			in.close();
			services = new String[names.size()];
			for (int i = 0; i < services.length; i++) {
				services[i] = names.get(i);
			}
			names = null;			
		} catch (Exception e) {
			System.err.println("Error occurred processing services list");
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * This method is called to return all services running on the machine.
	 * 
	 * @return a one dimentional array of service names
	 */
	public String[] getServices() {
		
		return services;
	}
	
	/**
	 * This method is called to return all software installed on the machine.
	 * 
	 * @return a two dimentional array of software names & versions
	 */
	public String[][] getSoftware() {
		
		return software;
	}
}
