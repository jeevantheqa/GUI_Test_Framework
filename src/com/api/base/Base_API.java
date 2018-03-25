package com.api.base;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.api.utilities.TestData;
import com.base.Base;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Base_API extends Base{

	public static Properties prop_api = null;
	public static ExtentReports report = null;
	public static ExtentTest loggerHTML;
	public static com.api.testng.TestNGAssertions _assert = null;
	public static ResultSet result_set = null;
	public static String asset_name = null;
	public static String nodelink = null;
	public static String app_name = null;
	public static String origin = null;
	public static String auth = null;

	public static Response resp = null;
	public static TestData jsondata = null;
	String nodelinkPattern = null;

	public static WebDriver driver = null;

	public static WebElement _WebElement = null;
	protected static List<WebElement> _WebElements;

	public static WebDriverWait wait = null;
	static DesiredCapabilities capabilities;

	final static Logger Log = Logger.getLogger(Base_API.class.getName());

	/*public static WebDriver invokeBrowser() throws Exception {

		try {
			
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("start-maximized");
				options.addArguments("disable-infobars");

				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");

				driver = new ChromeDriver(capabilities);
			wait = new WebDriverWait(driver, 30);
			// driver.manage().window().maximize();

			if (driver == null)
				driver = new HtmlUnitDriver();

			Log.info("CONFIG : Browser Launched Successfully");

		} catch (Exception e) {

			Log.error("IF YOU ARE SEEING THIS , THEN EVEN JEEVAN CANNOT HELP YOU");
			Log.error("CONFIG ERROR : Either Browser is not installed or its not configured/supported");
			Log.error(e.toString());

			throw (e);
		}

		// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		return driver;

	}

	public static int randomNumber() {

		int randomInt = 0;
		Random randomno = new Random();

		randomInt = randomno.nextInt(10000);

		return randomInt;
	}

	public static void setup() throws Exception {

		try {

			invokeBrowser();

			driver.navigate().to(prop_api.getProperty("dsm_url"));

			Thread.sleep(2000);
			if (Boolean.valueOf(prop_api.getProperty("isSSO"))) {

				if (driver.getTitle().contains("Log in to saml-demo")) {

					Log.info("CONFIG : URL Loaded Successfully");

				} else {
					throw new Exception();
				}
			} else if (!Boolean.valueOf(prop_api.getProperty("isSSO"))) {

				if (driver.getTitle().contains("HPE | Universal Internet of Things")) {

					Log.info("CONFIG : URL Loaded Successfully");

				} else {
					throw new Exception();
				}

			}

		} catch (Exception e) {

			Log.error("CONFIG ERROR : Unable to load URL , Internet/Application is down");

			throw (e);
		}

	}

	public static void closeBrowser() {

		driver.quit();
		Log.info("CONFIG : Browser closed Successfully");
		Log.info("X");
		Log.info("X");

	}

	public static void login(String username, String password) throws Exception {

		try {
			if (Boolean.valueOf(prop_api.getProperty("isSSO"))) {

				driver.findElement(By.id("username")).sendKeys(username);
				driver.findElement(By.id("password")).sendKeys(password);
				driver.findElement(By.name("login")).click();

			} else {

				driver.findElement(By.id("userName")).sendKeys(username);
				driver.findElement(By.name("j_password")).sendKeys(password);
				driver.findElement(By.xpath(".//*[@value='Login']")).click();

			}
		} catch (Exception e) {

			Log.warn("Unable to Login");
			Log.error(e.getMessage().toString());

		}
	}*/

}
