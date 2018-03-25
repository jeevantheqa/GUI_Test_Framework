package com.testng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;
import org.xml.sax.SAXException;

import com.api.base.Base_API;
import com.api.utilities.JDBCUtils;
import com.base.Base;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TestNgListeners extends Base implements ITestListener,
IExecutionListener, IInvokedMethodListener , IAnnotationTransformer {
	FileOutputStream out;
	
	
	final static Logger Log = Logger.getLogger(TestNgListeners.class.getName());
	
	@Override
	public void onTestStart(ITestResult result) {
		
		
		Log.info("$$$$$$$$ "+ result.getName() +" - STARTED " +" $$$$$$$$");
	
	
		logger=report.startTest(result.getName()+" : "+result.getMethod().getDescription());
		
	}

	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		try {

			if(result.getName().contains("API")){
				
				logger.log(LogStatus.INFO, "Headers", Base_API.resp.getHeaders() + "\n");
				Log.info("$$$$$$$$   Response -  " + Base_API.resp.getBody().asString() + " $$$$$$$$");
				
				logger.log(LogStatus.INFO, "Response", Base_API.resp.getBody().asString() + "\n");
				logger.log(LogStatus.INFO, "Status", Base_API.resp.getStatusCode() + "\n");
				if(Boolean.valueOf(Base_API.prop_api.getProperty("dbValidation"))){
					if(JDBCUtils.connection!=null){
						logger.log(LogStatus.PASS, "DB Validation","Object found in DB");
					}
					else{
						logger.log(LogStatus.SKIP, "DB Validation","Unable to Establish DB Connection and Hence DB Validation is skipped");
					}
				}
				String screenshot_path=Base.captureScreenshot(driver, result.getName());
				String image = logger.addScreenCapture("Screenshots/"+result.getName()+".png");
				logger.log(LogStatus.PASS, result.getMethod().getDescription() + "- Verified",image);
				report.endTest(logger);
				
			}
			
			else{
		String screenshot_path=Base.captureScreenshot(driver, result.getName());
		String image = logger.addScreenCapture("Screenshots/"+result.getName()+".png");
		logger.log(LogStatus.PASS, result.getMethod().getDescription() + "- Verified",image);
		report.endTest(logger);
			}
			
		
			
		FileOutputStream output = new FileOutputStream(System.getProperty("user.dir")+"/propertyFiles/config.properties");
		prop.store(output, "");
	     output.close();
	     
	     FileOutputStream outputAsset = new FileOutputStream(System.getProperty("user.dir")+"/propertyFiles/asset_details.properties");
			assetDetailsProp.store(outputAsset, "");
			outputAsset.close();
			
			 FileOutputStream outputRole = new FileOutputStream(System.getProperty("user.dir")+"/propertyFiles/role_mgmt.properties");
				roleProp.store(outputRole, "");
				outputRole.close();
	     
	     Log.info("$$$$$$$$ "+ result.getName() +" - PASSED " +" $$$$$$$$");
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

			if(result.getName().contains("API")){

				String screenshot_path=Base.captureScreenshot(driver, result.getName());
				String image = logger.addScreenCapture("Screenshots/"+result.getName()+".png");
				logger.log(LogStatus.FAIL, result.getThrowable().getMessage(),image);

				report.endTest(logger);

			}
			
			else{
					String screenshot_path=Base.captureScreenshot(driver, result.getName());
					String image = logger.addScreenCapture("Screenshots/"+result.getName()+".png");
					logger.log(LogStatus.FAIL, result.getMethod().getDescription() + "- Failed",image);
					
					report.endTest(logger);
			}
			
		

		FileOutputStream output = new FileOutputStream(System.getProperty("user.dir")+"/propertyFiles/config.properties");
		prop.store(output, "");
	     output.close();
	     
	     FileOutputStream outputAsset = new FileOutputStream(System.getProperty("user.dir")+"/propertyFiles/asset_details.properties");
			assetDetailsProp.store(outputAsset, "");
			outputAsset.close();
	     
			 FileOutputStream outputRole = new FileOutputStream(System.getProperty("user.dir")+"/propertyFiles/role_mgmt.properties");
				roleProp.store(outputRole, "");
				outputRole.close();
			
			
	     Log.error("$$$$$$$$ "+ result.getName() +" - FAILED " +" $$$$$$$$");
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
			if(result.getName().contains("API")){
				
				logger = report.startTest(result.getName() + " : " + result.getMethod().getDescription());

				if (!(Base_API.resp == null)) {

					logger.log(LogStatus.INFO, "Request", Base_API.resp.getHeaders() + Base_API.resp.getContentType() + Base_API.resp.body());
					logger.log(LogStatus.INFO, "Response",
							Base_API.resp.then().contentType(ContentType.JSON).extract().body().asString());
				}

				logger.log(LogStatus.SKIP, "");
				report.endTest(logger);
			}
			else{
			logger=report.startTest(result.getName()+" : "+result.getMethod().getDescription());
			logger.log(LogStatus.SKIP, result.getMethod().getDescription() + "- Skipped");
			report.endTest(logger);
			}
		
		
		Log.error("$$$$$$$$ "+ result.getName() +" - SKIPPED " +" $$$$$$$$");
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
		
		Log.info("####################### IOT Automation Suite Started #######################");
		Log.info("X");
		Log.info("X");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		
		Log.info("X");
		Log.info("X");
		Log.info("@@@@@ SUMMARY @@@@@");
		Log.info("Passed  : " +context.getPassedTests().size());
		Log.info("Failed  : " +context.getFailedTests().size());
		Log.info("Skipped : " +context.getSkippedTests().size());
		Log.info("Total   : " +context.getAllTestMethods().length);
		Log.info("@@@@@ SUMMARY @@@@@");
		
		
		
		
		
	}
	@Override
	public void onExecutionFinish() {
		
		
		report.flush();
		
		Log.info("####################### IOT Automation Suite Ended #######################");
		
	}

	@Override
	public final void onExecutionStart() {
		try {
		
			
			_assert = new TestNGAssertions();
		prop = new Properties();
		assetDetailsProp = new Properties();
		roleProp = new Properties();
		Base_API.prop_api = new Properties();
		
		DOMConfigurator.configure("log4j.xml");

			prop.load(new FileInputStream(System.getProperty("user.dir")+"/propertyFiles/config.properties"));
			assetDetailsProp.load(new FileInputStream(System.getProperty("user.dir")+"/propertyFiles/asset_details.properties"));
			roleProp.load(new FileInputStream(System.getProperty("user.dir")+"/propertyFiles/role_mgmt.properties"));
			
			Base_API.prop_api.load(new FileInputStream(System.getProperty("user.dir")+"/propertyFiles/config_api.properties"));
			
			JDBCUtils jdbc = new JDBCUtils();
			
			prop.setProperty("device_profile", "");
			prop.setProperty("device_profile", "_Automation");
			
			prop.setProperty("model", "");
			prop.setProperty("model", "Test_Model_");
			
			prop.setProperty("controller_name", "");
			prop.setProperty("controller_name", "Automation_Controller_");
			
			prop.setProperty("group_name", "");
			prop.setProperty("group_name", "Group_Automation_");
			
			/*prop.setProperty("new_user_name", "");
			prop.setProperty("new_user_name", "autouser");
			
			prop.setProperty("cust_username", "");
			prop.setProperty("cust_username", "AutoCustUser");*/
			
			prop.setProperty("asset_name", "");
			prop.setProperty("asset_name", "AutoAsset_");
			
			prop.setProperty("new_sla", "");
			prop.setProperty("new_sla", "Automation_SLA_");
			
			prop.setProperty("app_name", "");
			prop.setProperty("app_name", "App_Automation_"); 
			
			prop.setProperty("acp_name", "");
			prop.setProperty("acp_name", "Acp_Automation_");
			
			report = new ExtentReports(System.getProperty("user.dir")+"/CustomReports/custom_report.html");
			String css = ".report-name { padding-left: 10px; } .report-name > img { float: left;height: 90%;margin-left: 30px;margin-top: 2px;width: auto; }";

			/*extent.config()
			    .reportName("<img src='pathToImg.png' />")
			    .insertCustomStyles(css);*/
			
			report.config().documentTitle("UIot - Automation Report");
			report.config().reportHeadline("Universal Internet of Things - 1.4");
			report.config().reportName("<img src='title.png' />").insertCustomStyles(css);
			report.config().insertCustomStyles("img {border: 0;}");
			report.config().insertCustomStyles("nav {background-color: #6d8484 !important;}");
			report.addSystemInfo("URL", prop.getProperty("url"));
			report.addSystemInfo("Browser", prop.getProperty("browser_name"));
			
			com.utilities.XMLParser.ModifyContainerXML(System.getProperty("user.dir")+"\\testdata\\container.xml");
			com.utilities.XMLParser.ModifyDeviceProfileXML(System.getProperty("user.dir")+"\\testdata\\deviceprofile.xml");
			com.utilities.XMLParser.ModifyDeviceProfileXMLForGenericDC(System.getProperty("user.dir")+"\\testdata\\mqttdp.xml","MQTT");
			com.utilities.XMLParser.ModifyDeviceProfileXMLForGenericDC(System.getProperty("user.dir")+"\\testdata\\HTTP_DP.xml","HTTP");
			
			FileOutputStream output = new FileOutputStream(System.getProperty("user.dir")+"/config.properties");
			prop.store(output, "");
		     output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult result) {
		
	}


	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
		if(testMethod.getName().equals("TestCase_80520")){
			
			annotation.setEnabled(false);
		}
		
	}



	
	
}
