package com.douglas.genericUtility;


	import java.time.Duration;
	import java.util.List;
	import java.util.Set;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	/*
	 * This class is contains web driver specific methods
	 *  @author Niraj
	 * 
	 */
	public class WebDriverUtility {
		public WebDriverWait wait;
		public Select select;
		public  Actions action;
		public JavascriptExecutor javascriptExecutor;
		public JavaUtility javaUtility = new JavaUtility();
		
		
	private static WebDriverUtility instance;
		
		private WebDriverUtility() {
			
		}
		public static WebDriverUtility getInstance() {
	        if (instance == null) {
	            instance = new WebDriverUtility();
	        }
	        return instance;
	    }
		
		public void config(long longTimeOut, WebDriver driver, String url) {
			browserSetting(longTimeOut,driver);
			explicitlyWait(driver, longTimeOut);
			initializeAction(driver);
			initializeJavaScriptExecutor(driver);
			navigateApp(url, driver);
			
		}
		
		/**
		 * This method is used to launch the browser
		 * @param url
		 * @param driver
		 */
		public void navigateApp(String url, WebDriver driver) {
			driver.get(url);
		}
		/**
		 * This method is used to wait till page load(implicitly wait)
		 * @param longTimeOut
		 * @param driver
		 */
		public void waitImplicitlyWaitTillPageLoad(long longTimeOut, WebDriver driver) {
			if(driver!=null) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeOut));
		}else {
			System.err.println("WebDriver instance is null, can not set implicit wait");
		}
			
		}
		/**
		 * This method is used to maximize the browser 
		 * @param driver
		 */
		public void maximizeBrowser(WebDriver driver) {
			driver.manage().window().maximize();
		}
		/**
		 * This method is used to maximize the browser and implicitly wait
		 * @param longTimeOut
		 * @param driver
		 */
		public void browserSetting(long longTimeOut, WebDriver driver) {
			maximizeBrowser(driver);
			waitImplicitlyWaitTillPageLoad(longTimeOut, driver);
		}
		/**
		 * This method is used to close all the browser instances 
		 * @param driver
		 */
		public void quitBrowser(WebDriver driver) {
			driver.quit();
		}
		/**
		 * This method is used to initialize wait instance(Explicit wait)
		 * @param driver
		 * @param timeOut
		 */
		public void explicitlyWait(WebDriver driver, long timeOut) {
			 wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		}
		/**
		 * This method is used to explicitly wait till the particular element visible
		 * @param element
		 */
		public void waitUntilElementVisible(WebElement element) {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		/**
		 * This method is used to wait till element is click
		 * @param element
		 */
		public void waitUntilElementClickable(WebElement element)
		{
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		/**
		 * This method id used to select the dropdown by index
		 * @param element
		 * @param index
		 */
		public void selectDropdownBox(WebElement element, int index) {
			 select = new Select( element);
			select.selectByIndex(index);
		}
		/**
		 * This method is used to select the dropdown by value
		 * @param element
		 * @param value
		 */
		public void selectDropdownBox(WebElement element,String value)
		{
			select=new Select(element);
			select.selectByValue(value);
		}
		/**
		 * This method is used to select the dropdown by visible text
		 * @param visibleText
		 * @param element
		 */
		public void selectDropdownBox(String visibleText,WebElement element)
		{
			select=new Select(element);
			select.selectByVisibleText(visibleText);
		}
		/**
		 * This method is used to select dropdown which is made by without select-option tag
		 * @param listElement
		 * @param text
		 */
		public void selectDropDownInList(List<WebElement> listElement, String text) {
			List<WebElement> list = listElement;		
			for(WebElement dropDownList:list) {
				if(dropDownList.getText().contains(text)) {
					dropDownList.click();
				break;
				}			
			}
		}	
		/**
		 * This method is used to initialize actions class
		 * @param driver
		 */
		public void initializeAction(WebDriver driver) {
			  action = new Actions(driver);
		}
		/**
		 * This method is used to double click in particular element 
		 * @param element
		 */
		public void doubleClick(WebElement element)
		{
			action.doubleClick(element).perform();
		}
		/**
		 * This method is used to double click in web page
		 */
		public void doubleClick()
		{
			action.doubleClick().perform();
		}
		/**
		 * This method is used to mouse over on the element
		 * @param administratorIcone
		 * @param driver
		 */
		public void mouseOverOnElement(WebElement element)
		{
			action.moveToElement(element).perform();
		}	
		/**
		 * This method is used to switch frame to another frame by using index
		 * @param driver
		 * @param index
		 */
		public void switchToFrame(WebDriver driver,int index) {
			driver.switchTo().frame(index);
		}
		/**
		 * This method is used to switch frame to another frame by using webelement
		 * @param element
		 * @param driver
		 */
		public void switchToFrame(WebElement element, WebDriver driver) {
			driver.switchTo().frame(element);
		}
		/**
		 * This method is used to switch frame to another frame by using name or id
		 * @param driver
		 * @param nameOrId
		 */
		public void switchToFrame(WebDriver driver,String nameOrId)
		{
			driver.switchTo().frame(nameOrId);
		}
		/**
		 * This method is used to switch back to main page  
		 * @param driver
		 */
		public void swicthBackToMainPage(WebDriver driver)
		{
			driver.switchTo().defaultContent();
		}
		/**
		 * This method is used to initialize JavaScriot Executor 
		 * @param driver
		 */
		public void initializeJavaScriptExecutor(WebDriver driver) {
			 javascriptExecutor = (JavascriptExecutor)driver;
		}
		/**
		 * This method is used to scroll page
		 * 
		 */
		public void scrollToSpecificHeight(String height)
		{
			javascriptExecutor.executeScript("window.scrollBy(0,"+height+")");
		}
		/**
		 * This method is used to scroll top of the page
		 * 
		 */
		public void scrollTopToBottom() {
			javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		}
		/**
		 * This method is used to scroll till element
		 * 
		 */
		public void scrollTillElement(WebElement element) {
			javascriptExecutor.executeScript("argument[0].scrollIntoView()", element);
		}
		/**
		 * This method is used to enter the data through java script
		 * @param element
		 * @param data
		 */
		public void enterDataThroughjse(WebElement element,String data)
		{
			javascriptExecutor.executeScript("arguments[0].value=arguments[1]", element, data);
		}
		/**
		 * This method is used to click the particular element through java Script
		 * @param element
		 */
		public void clickThoughJse(WebElement element)
		{
			javascriptExecutor.executeScript("arguments[0].click()", element);
		}
		/**
		 * This method is used to provide the url of the application through javaScript
		 * @param url
		 */
		public void navigateAppThroughJse(String url)
		{
			javascriptExecutor.executeScript("window.location=arguments[0]",url);
		}
		
		/**
		 * This method is used to handle the alert pop up
		 * @param driver
		 */
		public void alertPopUpHandle(WebDriver driver)
		{
			Alert alt = driver.switchTo().alert();
			alt.accept();
			//		alt.dismiss();
			//		alt.getText();	
			//		alt.sendKeys(text);	
		}
		/*
		 * This method is used to delete all cookies
		 */
		public void deleteAllCookies(WebDriver driver) {
			driver.manage().deleteAllCookies();
		}
	
		/**
		 * This method is used to swith the window based on title 
		 * @param driver
		 * @param partialText
		 */
		public void switchToWindowBesedOnTitle(WebDriver driver, String partialText) {
			Set<String> allSessionId = driver.getWindowHandles();
			for(String id : allSessionId) {
				driver.switchTo().window(id);
				if(driver.getTitle().contains(partialText)) {
					break;
				}
			}
		}

	}



