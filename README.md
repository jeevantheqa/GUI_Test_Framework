# GUI_Test_Framework
Test Framework for GUI based Selenium Tests

## Framework Achitecture
A `com.base` class has been designed which has all the global objects _WebElement , _WebElements , Extent Reports , Property files and assertion objects initialized

Common methods to initialize browser and perform the setup actions are written `public static WebDriver invokeBrowser(String browserName)` and `public static void setup()` browser options are selected from the property files .

### WebDriver Wrappers

Wrapper classes for Selenium has been written to customise the core Selenium libraries in `com.webdriver`

`ElementBase` implements findElement and findElements methods from Selenium which returns an `ElementAction` object so the action methods such as sendyKeys , click etc can be accessed in the Page Object Class .

### TestNG Assertions

Similar to wrapper classes for Selenium , a assertion wrapper class for testng has been imlemented .

Eg : `public void equals(String actual, String expected, String message)`

### TestNG Listners

This custom TestNG Listner implements ITestListner and is used to generate HTML reports using Extent Reports , logs using Log4J , capturing screenshots and other OnExecutionStart activities.

### TestNG Annotation Transformer

Implements IAnnotationTransformer which is used to select subset of test cases that needs to be executed through a CSV file .

## Code Snippets

### findElement

`findElement(locator,Element Description).click(Action Description);`

### findElements

`findElements(locator,Element Description).click(Action Description);`

### Asserts

`_assert.contains(asset_name, "Automation_Asset", "Verifying Asset Search");`

### Scroll into View

`((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", _WebElement);`

### Check DOM Readyness

```
Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
			{
				public Boolean apply(WebDriver arg0) {
				
					return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
				}
			};
```
