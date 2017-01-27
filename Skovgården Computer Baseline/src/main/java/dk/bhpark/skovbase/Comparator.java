package dk.bhpark.skovbase;

import java.util.ArrayList;

/** ************************************************************************************************
 *                                                                                                 *
 * This class makes up the main analysis of software and services on a specific machine that is    *
 * not in the baseline, and vice versa. Baselines and data from a specific machine is loaded when  *
 * the object is created, and the actual analysis are performed when deviations are requested.     *
 *                                                                                                 *
 * <p>                                                                                             *                                                                      *
 * <b>(C) Copyright Claus Jensen. 2017                                                             *
 * @version 1.01 - 13/01/2017                                                                      *
 * @author Claus Jensen (claus@bhpark.dk)                                                          *
 ************************************************************************************************ */

public class Comparator {

	private String[][] computerSoftware = null;
	private String[] computerServices = null;
	private String[][] baselineSoftware = null;
	private String[] baselineServices = null;
	
	/**
	 * Initiate tables containing actual machine data and baseline data.
	 * 
	 * @param computerData is an object containing data related to a specific machine
	 * @param baseline is an object containing data from a defined baseline
	 */
	public Comparator(ComputerData comp, Baseline base) {
		
		computerSoftware = comp.getSoftware();
		computerServices = comp.getServices();
		baselineSoftware = base.getSoftwareBaseline();
		baselineServices = base.getServiceBaseline();
	}
	
	/**
	 * @return a two-dimensional table containing service deviations
	 */
	public String[][] getServiceDeviations() {
		
		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> deviations = new ArrayList<>();
		
		for (String service : computerServices) {
			boolean found = false;
			for (String baseService : baselineServices) {
				if (found = (service.equals(baseService))) break;
			}
			if (!found) {
				names.add(service);
				deviations.add("+");
			}
		}
		
		for (String baseService : baselineServices) {
			boolean found = false;
			for (String service : computerServices) {
				if (found = (baseService.equals(service))) break;
			}
			if (!found) {
				names.add(baseService);
				deviations.add("-");
			}
		}
		
		String[][] result = new String[names.size()][2];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = names.get(i);
			result[i][1] = deviations.get(i);
		}
		
		return result;
	}
	
	/**
	 * @return a two-dimensional table containing software deviations
	 */
	public String[][] getSoftwareDeviations() {
		
		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> versions = new ArrayList<>();
		ArrayList<String> deviations = new ArrayList<>();
		
		for (String[] software : computerSoftware) {
			boolean found = false;
			for (String[] baseSoftware : baselineSoftware) {
				if (found = (software[0].equals(baseSoftware[0]) && software[1].equals(baseSoftware[1]))) break;
			}
			if (!found) {
				names.add(software[0]);
				versions.add(software[1]);
				deviations.add("+");
			}
		}
		
		for (String[] baseSoftware : baselineSoftware) {
			boolean found = false;
			for (String[] software : computerSoftware) {
				if (found = (baseSoftware[0].equals(software[0]) && baseSoftware[1].equals(software[1]))) break;
			}
			if (!found) {
				names.add(baseSoftware[0]);
				versions.add(baseSoftware[1]);
				deviations.add("-");
			}
		}
		
		String[][] result = new String[names.size()][3];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = names.get(i);
			result[i][1] = versions.get(i);
			result[i][2] = deviations.get(i);
		}
		
		return result;
	}
}
