package com.testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class TestNGProgramitacally {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		XmlSuite suite = new XmlSuite();
		suite.setName("Automation Suite");
		
		 
		XmlTest test = new XmlTest(suite);
		test.setName("IOT Tests");
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.automation.drivers.DriverScript_Smoke"));
		classes.add(new XmlClass("com.automation.drivers.DriverScript_CriticalRegression"));
		test.setXmlClasses(classes) ;
		
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();

		
	}

}
