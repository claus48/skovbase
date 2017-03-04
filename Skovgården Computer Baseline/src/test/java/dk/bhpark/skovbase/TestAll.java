package dk.bhpark.skovbase;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/** ************************************************************************************************
 *                                                                                                 *
 * This class performs all unit testing of the classes that makes up the project.                  *
 *                                                                                                 *
 * <p>                                                                                             *
 * <b>(C) Copyright Claus Jensen. 2017                                                             *
 * @version 1.02 - 04/03/2017                                                                      *
 * @author Claus Jensen (claus@bhpark.dk)                                                          *
 ************************************************************************************************ */

public class TestAll {

	/**
	 * Test date in the copyright messages.
	 */
    @Test
    public void testCopyright() {
    	
    	String currentYear = new SimpleDateFormat("yyyy").format(new Date());
    	boolean check1 = (Copyright.COPYRIGHT.indexOf(currentYear) > 0);
    	boolean check2 = (Copyright.COPYRIGHT_SHORT.indexOf(currentYear) > 0);
        assertTrue(check1 && check2);    	
    }
    
    /**
     * Test proper creation of the Baseline object.
     */
    @Test
    public void testBaseline() {

		Baseline baseline = new Baseline();
		String[][] softwareBaseline = baseline.getSoftwareBaseline();
		String [] serviceBaseline = baseline.getServiceBaseline();
        assertTrue(softwareBaseline.length > 0);
        assertTrue(serviceBaseline.length > 0);
        assertFalse(softwareBaseline == null);
        assertFalse(serviceBaseline == null);
    }

    /**
     *  Test proper creation of the ComputerData object.
     */
    @Test
    public void testComputerData() {

		ComputerData computerData = new ComputerData("EDB5");
		String[][] software = computerData.getSoftware();
		String[] services = computerData.getServices();
        assertTrue(software.length > 0);
        assertTrue(services.length > 0);
        assertFalse(software == null);
        assertFalse(services == null);
    }

    /**
     * Test the Comparator object.
     */
    @Test
    public void testComparator() {

		ComputerData computerData = new ComputerData("EDB5");
    	Baseline baseline = new Baseline();
		Comparator comparator = new Comparator(computerData, baseline);
		String[][] softwareDeviations = comparator.getSoftwareDeviations();
		String[][] serviceDeviations = comparator.getServiceDeviations();
        assertTrue(softwareDeviations.length > 0);
        assertTrue(serviceDeviations.length > 0);
        assertFalse(softwareDeviations == null);
        assertFalse(serviceDeviations == null);
    }
}
