package PentaGroup;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.douglas.genericUtility.BaseClass;
import com.douglas.objectReposotory.PerfumePage;

public class VerifyProductListTest extends BaseClass{

	@Test(retryAnalyzer = com.douglas.genericUtility.RetryAnalyzer.class)
	public void verifyProductListTest() {
		String filter = excelFileUtility.getDataFromExcelFile("Douglas",1,1 );
		String option = excelFileUtility.getDataFromExcelFile("Douglas",2,1 );
		String item = excelFileUtility.getDataFromExcelFile("Douglas",3,1 );
		String headerText = excelFileUtility.getDataFromExcelFile("Douglas",4,1 );
		String filterText = excelFileUtility.getDataFromExcelFile("Douglas",5,1 );
		String deodorantText = excelFileUtility.getDataFromExcelFile("Douglas",6,1 );

		PerfumePage perfumePage = new PerfumePage(driver);

		logStep("privacy popup handled and redirected to home page");
		homePage.clickPerfumHowerOptionLink(driver, deodorantText);
		logStep("Clicked Deodorant option");
		Assert.assertEquals(perfumePage.getHeaderText().toLowerCase(), headerText.toLowerCase());
		perfumePage.clickFilterButton();
		logStep("Filter button clicked");
		Assert.assertEquals(perfumePage.getFilterText().toLowerCase(), filterText.toLowerCase());
		perfumePage.clickFilterList(filter);
		logStep("Filter item clicked");
		perfumePage.selectFilteredOpton(option);
		logStep("opton selected");
		perfumePage.clickSchliessenButton();
		logStep("Item filtered");
		perfumePage.verifyItemList(item);
		logStep("Script executed successfully");

	}
}


