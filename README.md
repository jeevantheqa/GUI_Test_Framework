# GUI_Test_Framework
Test Framework for GUI based Selenium Tests

## Framework Achitecture
A base class has been designed which has all the global objects _WebElement , _WebElements , Extent Reports , Property files and assertion objects initialized

Common methods to initialize browser and perform the setup actions are written 'public static WebDriver invokeBrowser(String browserName)' and 'public static void setup()' browser options are selected from the property files .
