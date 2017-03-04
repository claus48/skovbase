package dk.bhpark.skovbase;

/** ************************************************************************************************
 *                                                                                                 *
 * This class is the main class that creates a full list of deviations for all maskines in the     *
 * computer room at Skovg�rden. The list is written to STDOUT.                                     *
 *                                                                                                 *
 * <p>                                                                                             *
 * <b>(C) Copyright Claus Jensen. 2017                                                             *
 * @version 1.02 - 04/03/2017                                                                      *
 * @author Claus Jensen (claus@bhpark.dk)                                                          *
 ************************************************************************************************ */

public class Main {
	
	private String[] computerList = {"SERVER", "EDB1", "EDB2", "EDB3", "EDB4", "EDB5", "EDB6", "EDB7", "EDB8"};

	/**
	 * This method kicks off the program execution.
	 * 
	 * @param args are not used by this program
	 */
	public static void main(String[] args) {

		new Main();
	}

	/**
	 * A list of software deviations is first created, and next a a list of services deviations
	 * is created. The combined list is written to STDOUT. 
	 */
	public Main() {

		// Create the software deviation list 
		Baseline baseline = new Baseline();
		for (String computer : computerList) {
			ComputerData computerData = new ComputerData(computer);
			Comparator comparator = new Comparator(computerData, baseline);
			String[][] softwareDeviations = comparator.getSoftwareDeviations();			
			System.out.println("Software deviations on computer " + computer + "\n");			
			for (String[] entry : softwareDeviations) {
				System.out.println("  " + entry[2] + " " + entry[0] + " Version " + entry[1]);
			}
			System.out.println();
		}
		
		// create the services deviation list
		for (String computer : computerList) {
			ComputerData computerData = new ComputerData(computer);
			Comparator comparator = new Comparator(computerData, baseline);
			String[][] serviceDeviations = comparator.getServiceDeviations();			
			System.out.println("Service deviations on computer " + computer + "\n");
			for (String[] entry : serviceDeviations) {
				System.out.println("  " + entry[1] + " " + entry[0]);
			}
			System.out.println();
		}
	}
}
