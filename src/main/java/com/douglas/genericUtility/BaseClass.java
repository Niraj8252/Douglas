package com.douglas.genericUtility;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.douglas.objectReposotory.HomePage;

public class BaseClass {

    public WebDriver driver;
    public long longTimeOut;
    public int randomNumber;
    public String randomString;
    public Logger logger;
    public HomePage homePage;
    public JavaUtility javaUtility;
    public ExcelFileUtility excelFileUtility;
    public PropertyFileUtility propertyFileUtility;
   

    // Extent Reports
    public ExtentReports extent;
    public ExtentSparkReporter spark;
    public ExtentTest test;

    @BeforeSuite
    public void beforeSuiteTest() {

        logger = LogManager.getLogger(BaseClass.class);

        propertyFileUtility = new PropertyFileUtility();
        excelFileUtility = new ExcelFileUtility();

        propertyFileUtility.openPropertyFile(IconstantPathUtility.PROPERTYFILEPATH);
        excelFileUtility.openExcelFile(IconstantPathUtility.EXCELFILEPATH);

        // Create Reports folder
        File reportDir = new File(System.getProperty("user.dir") + "/Reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // EXTENT REPORT INITIALIZATION
        spark = new ExtentSparkReporter(reportDir + "/Douglas.html");
        spark.config().setDocumentTitle("Douglas Automation Report");
        spark.config().setReportName("Douglas Web Automation Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        logger.info("✔ Extent Report Initialized");
    }

    @Parameters("browser")
    @BeforeClass
    public void beforeClassTest(String browser) throws InterruptedException {

        try {
            logger.info("Loading configuration...");

            String url = propertyFileUtility.getDataFromPropertyFile("url");
            String timeout = propertyFileUtility.getDataFromPropertyFile("Timeout");
//            String browser = propertyFileUtility.getDataFromPropertyFile("Browser");

            longTimeOut = WebDriverUtility.getInstance().javaUtility.stringConvertToLong(timeout);
            randomNumber = WebDriverUtility.getInstance().javaUtility.generateRandomNum(100);
            randomString = WebDriverUtility.getInstance().javaUtility.generateRandomString(3);

            switch (browser.toLowerCase()) {
                case "chrome":
                   driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    logger.warn("Invalid browser selected, defaulting to Chrome");
                    driver = new ChromeDriver();
                    break;
            }

            WebDriverUtility.getInstance().config(longTimeOut, driver, url);
            logger.info("Browser launched: " + browser);
            logger.info("Navigated to: " + url);

        } 
        catch (Exception e) {
            throw new RuntimeException("❌ Failed to launch browser", e);
        }

        homePage = new HomePage(driver);
        homePage.handlePrivacyPopUp();
        logger.info("✔ Privacy popup handled");
    }

    @BeforeMethod
    public void beforeMethodTest(Method method) {
        test = extent.createTest(method.getName());
        logger.info("======== Starting Test: " + method.getName() + " ========");
    }

    @AfterMethod
    public void afterMethodTest(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test PASSED");
        }
        else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            // Capture screenshot on failure
            String screenshotPath = takeScreenshot(result.getName());
            test.addScreenCaptureFromPath(screenshotPath);

            logger.error("Screenshot captured: " + screenshotPath);
        }
        else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test SKIPPED");
        }

        logger.info("======== Ending Test: " + result.getName() + " ========");
    }

    @AfterClass
    public void afterClassTest() {
        try {
            driver.quit();
            logger.info("Browser closed successfully.");
        } 
        catch (Exception e) {
            logger.error("Unable to close browser: " + e.getMessage());
        }
    }

    @AfterSuite
    public void afterSuiteTest() {
        extent.flush();
        logger.info("✔ Extent Report Generated Successfully!");
    }


    // -----------------------------------------------------------
    //  SCREENSHOT METHOD ATTACHED IN REPORT
    // -----------------------------------------------------------
    public String takeScreenshot(String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") +
                "/Screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);

            // Create folder if not exists
            dest.getParentFile().mkdirs();

            FileUtils.copyFile(src, dest);
        } 
        catch (Exception e) {
            logger.error("❌ Screenshot capture failed: " + e.getMessage());
        }

        return screenshotPath;
    }
    
	public void logStep(String message) {
	    logger.info(message);
	    test.info(message);
	}

}
