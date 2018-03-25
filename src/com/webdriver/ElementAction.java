package com.webdriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.base.Attributes;
import com.base.Base;

public class ElementAction extends Base {

	final static Logger Log = Logger.getLogger(ElementAction.class.getName());
	
	

	public void sendKeys(String value, String action_description) {
		try {

			wait.until(ExpectedConditions.visibilityOf(_WebElement));
			_WebElement.clear();
			_WebElement.sendKeys(value);
			Log.info(action_description);

		} catch (Exception e) {

			Log.error("Unable to Perform + " + action_description);
			Log.error(e.toString());
			throw (e);
		}
	}
	
	public void sendKeys(String value,Keys KeyStroke,String action_description) {
		try {

			wait.until(ExpectedConditions.visibilityOf(_WebElement));
			_WebElement.clear();
			_WebElement.sendKeys(value);
			_WebElement.sendKeys(KeyStroke);
			Log.info(action_description);

		} catch (Exception e) {

			Log.error("Unable to Perform + " + action_description);
			Log.error(e.toString());
			throw (e);
		}
	}

	public void click(String action_description) {
		try {

			wait.until(ExpectedConditions.visibilityOf(_WebElement));
			wait.until(ExpectedConditions.elementToBeClickable(_WebElement));
			_WebElement.click();
			Log.info(action_description);

		} catch (Exception e) {

			Log.error("Unable to Perform  " + action_description);
			Log.error(e.toString());
			throw (e);
		}
	}
	
	public void submit(String action_description){
		try {

			wait.until(ExpectedConditions.visibilityOf(_WebElement));
			wait.until(ExpectedConditions.elementToBeClickable(_WebElement));
			_WebElement.submit();
			Log.info(action_description);

		} catch (Exception e) {

			Log.error("Unable to Perform  " + action_description);
			Log.error(e.toString());
			throw (e);
		}
		
		
	}
	
	public void doubleClick(String action_description){
		try{
			wait.until(ExpectedConditions.visibilityOf(_WebElement));
			wait.until(ExpectedConditions.elementToBeClickable(_WebElement));
		Actions action = new Actions(driver);
		action.doubleClick(_WebElement);
		}catch(Exception e){
			
			Log.error("Unable to Click : "+ action_description);
			Log.error(e.toString());
			throw(e);
		}
		
	}

	public String getAttribute(Attributes attribute_type, String... generic_attribute) {
		String attributeValue = "";
		try {
			switch (attribute_type) {
			case TEXT:
				attributeValue = _WebElement.getText().trim();
				break;

			case VALUE:
				attributeValue = _WebElement.getAttribute("value");
				break;

			case TITLE:
				attributeValue = _WebElement.getAttribute("title");
				break;

			case GENERAL:
				attributeValue = _WebElement.getAttribute(generic_attribute[0].toLowerCase());
				break;

			default:
				attributeValue = _WebElement.getAttribute(attribute_type.toString());
			}
		} catch (Exception e) {

			Log.error("Unable to get " + attribute_type + " of the Element ");
			Log.error(e.toString());

			throw (e);
		}

		return attributeValue;
	}

	public void selectByIndex(int index, String description) {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(_WebElement));
			new Select(_WebElement).selectByIndex(index);
			Log.info(description);

		} catch (Exception e) {

			Log.error("Unable to Select Element with Index" + description);
			Log.error(e.toString());
			throw (e);

		}
	}

	public void selectByText(String text, String description) {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(_WebElement));
			new Select(_WebElement).selectByVisibleText(text);
			Log.info(description);

		} catch (Exception e) {

			Log.error("Unable to Select Element with Text " + description);
			Log.error(e.toString());
			throw (e);

		}
	}

	public void selectByValue(String value, String description) {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(_WebElement));
			new Select(_WebElement).selectByValue(value);
			Log.info(description);

		} catch (Exception e) {

			Log.error("Unable to Select Element with Value " + description);
			Log.error(e.toString());
			throw (e);	

		}

	}
	
	public String getFirstSelectedOption(String description) {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(_WebElement));
			Log.info(description);
			
			return new Select(_WebElement).getFirstSelectedOption().getText();

		} catch (Exception e) {

			Log.error("Unable to Get Text of First Selected Option " + description);
			Log.error(e.toString());
			throw (e);	

		}
		

	}
	
	public int getCount(String description){
		int count = 0;
		try{
			
			count = _WebElements.size();
			Log.info(description);
			
		}catch(Exception e ){
			
			Log.error("Unable to perform action " + description);
			
			throw(e);
		}
		return count;
	}
	
	public void moveToElement(String description){
		try{
			Actions action = new Actions(driver);
			Log.info(description);
			action.moveToElement(_WebElement).build().perform();
			
		
		}catch(Exception e){
			
			Log.error("Unable to Move to Element : "+description);
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public boolean isDisplayed(){
		try{
			wait.until(ExpectedConditions.visibilityOf(_WebElement));
			
			return  _WebElement.isDisplayed();
			
		}catch(Exception e){
			
			Log.error("Unable to Check if Element is Displayed");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	
		
		
	}


