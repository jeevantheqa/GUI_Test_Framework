package com.api.testng;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utilities.JDBCUtils;
import com.api.utilities.TestData;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TestNGListners extends com.api.base.Base_API
		implements ITestListener, IExecutionListener, IInvokedMethodListener {
	FileOutputStream out;

	final static Logger Log = Logger.getLogger(TestNGListners.class.getName());

	@Override
	public void onTestStart(ITestResult result) {

		Log.info("$$$$$$$$ " + result.getName() + " - STARTED " + " $$$$$$$$");

		loggerHTML = report.startTest(result.getName() + " : " + result.getMethod().getDescription());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		try {

			loggerHTML.log(LogStatus.INFO, "Headers", resp.getHeaders() + "\n");
			Log.info("$$$$$$$$   Response -  " + resp.getBody().asString() + " $$$$$$$$");
			
			loggerHTML.log(LogStatus.INFO, "Response", resp.getBody().asString() + "\n");
			loggerHTML.log(LogStatus.INFO, "Status", resp.getStatusCode() + "\n");
			if(Boolean.valueOf(prop_api.getProperty("dbValidation"))){
				if(JDBCUtils.connection!=null){
					loggerHTML.log(LogStatus.PASS, "DB Validation","Object found in DB");
				}
				else{
					loggerHTML.log(LogStatus.SKIP, "DB Validation","Unable to Establish DB Connection and Hence DB Validation is skipped");
				}
			}
			loggerHTML.log(LogStatus.PASS, "Verify", result.getMethod().getDescription() + " - Verified ");
			report.endTest(loggerHTML);

			Log.info("$$$$$$$$ " + result.getName() + " - PASSED " + " $$$$$$$$");
			Log.info("X");
			Log.info("X");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {

		try {

			if (!(resp == null)) {

				loggerHTML.log(LogStatus.INFO, "Request" + resp.getHeaders());
				loggerHTML.log(LogStatus.INFO, "Response", resp.getBody().asString());
				loggerHTML.log(LogStatus.INFO, "Status", resp.getStatusCode() + "\n");
			}

			loggerHTML.log(LogStatus.FAIL, result.getThrowable().getMessage());

			report.endTest(loggerHTML);

			Log.error("$$$$$$$$ " + result.getName() + " - FAILED " + " $$$$$$$$");
			Log.info("X");
			Log.info("X");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		try {
			loggerHTML = report.startTest(result.getName() + " : " + result.getMethod().getDescription());

			if (!(resp == null)) {

				loggerHTML.log(LogStatus.INFO, "Request", resp.getHeaders() + resp.getContentType() + resp.body());
				loggerHTML.log(LogStatus.INFO, "Response",
						resp.then().contentType(ContentType.JSON).extract().body().asString());
			}

			loggerHTML.log(LogStatus.SKIP, "");
			report.endTest(loggerHTML);

			Log.error("$$$$$$$$ " + result.getName() + " - SKIPPED " + " $$$$$$$$");
			Log.info("X");
			Log.info("X");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {

		Log.info("####################### IOT API Automation Suite Started #######################");
		Log.info("X");
		Log.info("X");
	}

	@Override
	public void onFinish(ITestContext context) {

		Log.info("X");
		Log.info("X");
		Log.info("@@@@@ SUMMARY @@@@@");
		Log.info("Passed  : " + context.getPassedTests().size());
		Log.info("Failed  : " + context.getFailedTests().size());
		Log.info("Skipped : " + context.getSkippedTests().size());
		Log.info("Total   : " + context.getAllTestMethods().length);
		Log.info("@@@@@ SUMMARY @@@@@");

	}

	@Override
	public void onExecutionFinish() {

		report.flush();

		Log.info("####################### IOT API Automation Suite Ended #######################");

	}

	@Override
	public final void onExecutionStart() {
		
		try{
		_assert = new TestNGAssertions();
		prop_api = new Properties();
		jsondata = new TestData();
		

		DOMConfigurator.configure("log4j_api.xml");
		

		report = new ExtentReports(System.getProperty("user.dir") + "/CustomReports/API/report_api.html");
		String css = ".report-name { padding-left: 10px; } .report-name > img { float: left;height: 90%;margin-left: 30px;margin-top: 2px;width: auto; }";

		report.config().documentTitle("UIot - API Automation Report");
		report.config().reportHeadline("Universal Internet of Things - 1.x");
		report.config().reportName("<img src='title.png' />").insertCustomStyles(css);
		report.config().insertCustomStyles("img {border: 0;}");
		report.config().insertCustomStyles("nav {background-color: #6d8484 !important;}");
		
		}catch(Exception e){
			
			
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult result) {

	}

}
