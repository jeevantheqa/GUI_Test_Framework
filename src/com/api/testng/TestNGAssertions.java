package com.api.testng;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class TestNGAssertions {

	final static Logger Log = Logger.getLogger(TestNGAssertions.class.getName());

	public void equals(String actual, String expected, String message) {

		try {

			Assert.assertEquals(actual, expected);

		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}
	}

	public void equals(int actual, int expected, String message) {

		try {

			Assert.assertEquals(actual, expected);

		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}
	}

	public void contains(String actual, String expected, String message) {

		try {

			Assert.assertTrue(actual.toLowerCase().contains(expected.toLowerCase()));

		} catch (AssertionError e) {

			Log.error("Assert Failed for - " + message);
			Log.error(e.toString());
			throw (e);
		}

	}
}
