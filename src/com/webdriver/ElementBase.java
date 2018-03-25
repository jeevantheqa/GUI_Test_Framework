package com.webdriver;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.base.Base;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class ElementBase extends Base {

	ElementAction action = new ElementAction();
	
	final static Logger Log = Logger.getLogger(ElementBase.class.getName());

	public ElementAction findElement(By locator, String element_description) {

		try {

			FluentWait<WebDriver> wait_fluent = new FluentWait<WebDriver>(driver);
			wait_fluent.pollingEvery(250,  TimeUnit.MILLISECONDS);
			wait_fluent.withTimeout(2, TimeUnit.MINUTES);
			wait_fluent.ignoring(NoSuchElementException.class);
			
			Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
			{
				public Boolean apply(WebDriver arg0) {
				
					return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
				}
			};
			wait_fluent.until(function);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
			_WebElement = driver.findElement(locator);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", _WebElement);

		} catch (NoSuchElementException e) {
			Log.error("No such Element exists - " + element_description);
			Log.error(e.getMessage().toString());
			throw (e);
		} catch (org.openqa.selenium.TimeoutException e) {
			Log.error("Time out to Find Element - " + element_description);
			Log.error(e.getMessage().toString());
			throw (e);

		} catch (Exception e) {
			Log.error("Unable to Find Element -" + element_description);
			Log.error(e.getMessage().toString());
			throw (e);
		}

		return action;
	}

	public ElementAction findElements(By locator, String element_description) {

		try {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

			_WebElements = driver.findElements(locator);

		} catch (NoSuchElementException e) {
			Log.error("No such Element exists - " + element_description);
			Log.error(e.getMessage().toString());
			throw (e);
		} catch (org.openqa.selenium.TimeoutException e) {
			Log.error("Time out to Find Element - " + element_description);
			Log.error(e.getMessage().toString());
			throw (e);

		} catch (Exception e) {
			Log.error("Unable to Find Element -" + element_description);
			Log.error(e.getMessage().toString());
			throw (e);
		}

		return action;
	}
	
	public void alertAccept(String description){
		try{
		wait.until(ExpectedConditions.alertIsPresent());
		String alertText = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Log.info("Alert :"+alertText+" : Accepted");
		}catch(Exception e){
			
			Log.error("Unable to Perform action on Alert "+ description);
			throw(e);
		}
		
	}
	
	public void alertDismiss(String description){
		try{
		String alertText = driver.switchTo().alert().getText();
		driver.switchTo().alert().dismiss();
		Log.info("Alert :"+alertText+" : Dismiss");
		}catch(Exception e){
			
			Log.error("Unable to Perform action on Alert "+ description);
			throw(e);
		}
		
	}

}
