package com.base;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.testng.TestNGAssertions;
import com.webdriver.ElementBase;

public class Base  {

	public static WebDriver driver = null;
	
	public static WebElement _WebElement = null;
	protected static List<WebElement> _WebElements;
	
	public static WebDriverWait wait = null;
	public static Properties prop , assetDetailsProp , roleProp= null;
	public static ExtentReports report = null;
	public static ExtentTest logger;
	public static String device_profile, model = "";
	public static TestNGAssertions _assert=null;
	public static String nodelink = null;
	static DesiredCapabilities capabilities;
	
	final static Logger Log = Logger.getLogger(Base.class.getName());
	
	public static String captureScreenshot(WebDriver driver, String screenshotname) {
		try {
			
			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			String dest = System.getProperty("user.dir")+"/CustomReports/Screenshots/"+screenshotname+ ".png";

			File destination = new File(dest);

			FileUtils.copyFile(source, destination);

			return dest;

		} catch (IOException e) {

			System.out.println("Error While Taking Screenshot" + e.getMessage());
			return e.getMessage();
		}
	}

	/**
	 * @param browserName
	 * @return
	 * @throws Exception
	 */
	public static WebDriver invokeBrowser(String browserName) throws Exception {

		try {
			if (browserName.equalsIgnoreCase("ff")) {
				
				FirefoxOptions foptions = new FirefoxOptions();
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability("marionette", true);
			/*	ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("S");*/
				
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
				
				driver = new FirefoxDriver(capabilities);
				
			}

			else if (browserName.equalsIgnoreCase("ie")) {
				
		
				System.setProperty("webdriver.ie.driver", prop.getProperty("ie_driver"));

				driver = new InternetExplorerDriver();
				
			}

			else if (browserName.equalsIgnoreCase("chrome")) {

				Map<String, Object> preferences = new Hashtable<String, Object>();
				preferences.put("profile.default_content_settings.popups", 0);
				preferences.put("download.prompt_for_download", "false");
				preferences.put("download.default_directory", System.getProperty("user.dir")+"//testdata//");
				

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("start-maximized");
				options.addArguments("disable-infobars");
				options.setExperimentalOption("prefs", preferences);

				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome_driver"));

				driver = new ChromeDriver(capabilities);
			}
			wait = new WebDriverWait(driver,Integer.parseInt(prop.getProperty("webdriver_wait")));
			driver.manage().window().maximize();

			if (driver == null)
				//driver = new HtmlUnitDriver();
			
			
			Log.info("CONFIG : Browser Launched Successfully");

		} catch (Exception e) {
			
			//Log.error("IF YOU ARE SEEING THIS , THEN EVEN JEEVAN CANNOT HELP YOU");
			Log.error("CONFIG ERROR : Either Browser is not installed or its not configured/supported");
			Log.error(e.toString());

			throw(e);
		}
		
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
		
		
	}

	public static int randomNumber() {

		int randomInt = 0;
		Random randomno = new Random();

		randomInt = randomno.nextInt(10000);

		return randomInt;
	}
	
	
	public static void setup() throws Exception {

		try{

			
		invokeBrowser(prop.getProperty("browser_name"));

		driver.navigate().to(prop.getProperty("url"));
		
		if(prop.getProperty("browser_name").equals("ie"))
			driver.navigate ().to ("javascript:document.getElementById('overridelink').click()");
			
		
		Thread.sleep(2000);
		if(Boolean.valueOf(prop.getProperty("isSSO"))){
			
			if(driver.getTitle().contains("Log in to")){
			
			Log.info("CONFIG : URL Loaded Successfully");
			
			}
			else{
				throw new Exception();
			}
		}
		else if(!Boolean.valueOf(prop.getProperty("isSSO"))){
			
			if(driver.getTitle().contains("HPE | Universal Internet of Things")){
				
				Log.info("CONFIG : URL Loaded Successfully");
				
				}
			else{
				throw new Exception();
			}
			
		}
		
		}catch(Exception e){
			
			Log.error("CONFIG ERROR : Unable to load URL , Internet/Application is down");
			
			throw(e);
		}
		
		
		
	}
	
	public static void closeBrowser() {

		driver.quit();
		Log.info("CONFIG : Browser closed Successfully");
		Log.info("X");
		Log.info("X");
		
	}
	
	public static void login(String username , String password) throws Exception {

		try{
			if(Boolean.valueOf(prop.getProperty("isSSO"))){
				
		driver.findElement(By.id("username")).sendKeys(username);
		Log.info("Enter Login User Name");
		driver.findElement(By.id("password")).sendKeys(password);
		Log.info("Enter Password");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
		driver.findElement(By.name("login")).click();
		Log.info("Click on Login Button");

			}
			else {
				
				driver.findElement(By.id("userName")).sendKeys(username);
				Log.info("Enter Login User Name");
				driver.findElement(By.name("j_password")).sendKeys(password);
				Log.info("Enter Password");
				driver.findElement(By.xpath(".//*[@value='Login']")).click();
				Log.info("Click on Login Button");
				
			}
		}catch(Exception e){
			
			Log.warn("Unable to Login");
			Log.error(e.getMessage().toString());
			
		}
	}
	
	public void logout() throws Exception {

		try{
			if(Boolean.valueOf(prop.getProperty("isSSO"))){
		driver.findElement(By.className("dropdown-toggle")).click();
		driver.findElement(By.xpath(".//*[contains(@href,'logout')]")).click();

		Thread.sleep(2000);
			}
		
		else{
			driver.findElement(By.className("dropdown-toggle")).click();
			driver.findElement(By.xpath(".//*[contains(@href,'logout')]")).click();

			Thread.sleep(2000);
			
		}
		}
		catch(Exception e){
			
			Log.error("Unable to LOGOUT and Hence Re-Lauching the browser");
			Log.error(e.getMessage().toString());
			closeBrowser();
			setup();
			
		}
		}
		
		public void RobotCopyPasteEnter(String filepath) throws Exception{
			
			try{
			// Specify the file location with extension
			 StringSelection sel = new StringSelection(filepath);
			 
			// Copy to clipboard
			 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
			 
			 
			 Thread.sleep(4000);
			// Create object of Robot class
			 Robot robot = new Robot();
			      
			  // Press Enter
			 robot.keyPress(KeyEvent.VK_ENTER);
			 
			// Release Enter
			 robot.keyRelease(KeyEvent.VK_ENTER);
			 
			  // Press CTRL+V
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_V);
			 
			// Release CTRL+V
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_V);
			 Thread.sleep(1000);
			        
			//Press Enter 
			 robot.keyPress(KeyEvent.VK_ENTER);
			 robot.keyRelease(KeyEvent.VK_ENTER);
			 
			}catch(Exception e){
				
				Log.error("Unable to Perform Upload action using Robot Class");
				
				throw(e);
			}
			
			
		}
		
		public boolean isAlertPreset(){
			 try{
			if(wait.until(ExpectedConditions.alertIsPresent())==null)
			    return false;
			else
			    return true;   
			}catch(Exception e){
				
				return false;
			}
			
			
		}
		 
				
}
	


