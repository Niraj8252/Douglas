package PentaGroup;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.douglas.genericUtility.BaseClass;

public class VerifyProductListTest extends BaseClass{
	

	
	@Test
	public void verifyProductListTest() throws InterruptedException {
		String filter = excelFileUtility.getDataFromExcelFile("Douglas",1,1 );
		String option = excelFileUtility.getDataFromExcelFile("Douglas",2,1 );
		String item = excelFileUtility.getDataFromExcelFile("Douglas",3,1 );
		String headerText = excelFileUtility.getDataFromExcelFile("Douglas",4,1 );
		String filterText = excelFileUtility.getDataFromExcelFile("Douglas",5,1 );

		
		logStep("privacy popup handled and redirected to home page");
		homePage.clickDeodrantInPerfum();
		logStep("Clicked Deodorant option");
		Assert.assertEquals(homePage.getHeaderText().toLowerCase(), headerText.toLowerCase());
		homePage.clickFilterButton();
		logStep("Filter button clicked");
		Assert.assertEquals(homePage.getFilterText().toLowerCase(), filterText.toLowerCase());
		homePage.clickFilterList(filter);
		logStep("Filter item clicked");
		homePage.selectFilteredOpton(option);
		logStep("opton selected");
		homePage.clickSchliessenButton();
		logStep("Item filtered");
		homePage.verifyItemList(item);

        logStep("Script executed successfully");

		
	}
	
//	@Test(retryAnalyzer = com.douglas.genericUtility.RetryAnalyzer.class)
//	public void verifyProductListTest1() throws InterruptedException {
//		String filter = excelFileUtility.getDataFromExcelFile("Douglas",1,1 );
//		String option = excelFileUtility.getDataFromExcelFile("Douglas",2,1 );
//		String item = excelFileUtility.getDataFromExcelFile("Douglas",3,1 );
//		String headerText = excelFileUtility.getDataFromExcelFile("Douglas",4,1 );
//		String filterText = excelFileUtility.getDataFromExcelFile("Douglas",5,1 );
//
//		
//		logStep("privacy popup handled and redirected to home page");
//		homePage.clickDeodrantInPerfum();
//		logStep("Clicked Deodorant option");
//		Assert.assertEquals(homePage.getHeaderText().toLowerCase(), headerText);
//		homePage.clickFilterButton();
//		logStep("Filter button clicked");
//		Assert.assertEquals(homePage.getFilterText().toLowerCase(), filterText);
//		homePage.clickFilterList(filter);
//		logStep("Filter item clicked");
//		homePage.selectFilteredOpton(option);
//		logStep("opton selected");
//		homePage.clickSchliessenButton();
//		logStep("Item filtered");
//		homePage.verifyItemList(item);
//
//        logStep("Script executed successfully");
//
//		
//	}

}


