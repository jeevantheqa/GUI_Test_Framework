package com.testng;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class TestNGAssertions {

	final static Logger Log = Logger.getLogger(TestNGAssertions.class.getName());

	public void equals(String actual, String expected, String message) {

		try {

			Log.info("Actual ->"+actual +", Expected ->"+expected +",Assert Condition -> STRING EQUALS");
			Assert.assertEquals(actual, expected);
			

		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}
	}

	public void equals(int actual, int expected, String message) {

		try {

			Log.info("Actual ->"+actual +", Expected ->"+expected +",Assert Condition -> INT EQUALS");
			Assert.assertEquals(actual, expected);
			

		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}
	}
	
	public void greaterThan(int actual, int expected, String message) {

		try {

			Log.info("Actual ->"+actual +", Expected ->"+expected +",Assert Condition -> INT GREATERTHAN");
			Assert.assertTrue(actual>expected);
			

		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}
	}
	
	public void lessThan(int actual, int expected, String message) {

		try {

			Log.info("Actual ->"+actual +", Expected ->"+expected +",Assert Condition -> INT LESSTHAN");
			Assert.assertTrue(actual<expected);
			
			

		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}
	}

	public void contains(String actual, String expected, String message) {

		try {

			Log.info("Actual ->"+actual +", Expected ->"+expected +",Assert Condition -> CONTAINS");
			Assert.assertTrue(actual.toLowerCase().contains(expected.toLowerCase()));
			
		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}

	}
	
	public void equals(boolean actual,String message) {

		try {

			Log.info("Actual ->"+actual +", Expected -> TRUE" +",Assert Condition -> Boolean Equals");
			Assert.assertTrue(actual);
			
		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}

	}
}
