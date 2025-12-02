package com.douglas.objectReposotory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PerfumePage {
	public PerfumePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(css="[data-testid='product-overview-headline']")
	private WebElement headerElement;
	@FindBy(css= "[data-testid='menu-button-facets']")
	private WebElement filterButton;
	@FindBy(css = ".facets-mobile-template__header-wrapper")
	private WebElement filterTextElement;
	@FindBy(css= ".main-facet")
	private List<WebElement> listOfFilters;
	@FindBy(css="[data-testid='RadioButton']")
	private List<WebElement> filteredOptions;
	@FindBy(xpath = "//h3[@class='facets-mobile-template__header-title facets-mobile-template__header-title--with-back-button']/ancestor::div[@class='facets-mobile-template']/descendant::button[@data-testid='button-primary']")
	private WebElement zurockButtonElement;
	@FindBy(xpath = "//button[text()='Schliessen']")
	private WebElement schliessenButtonElement;
	@FindBy(xpath = "//div[@class='cms-container']/descendant::div[@class='text top-brand']")
	private List<WebElement> filtereditemListElement;

	//Business library/Methods
	public String getHeaderText() {
		return headerElement.getText();
	}
	public void clickFilterButton() {
		filterButton.click();
	}

	public String getFilterText() {
		return filterTextElement.getText();
	}

	public void clickFilterList(String filter) {
		for(WebElement wb : listOfFilters) {
			if(wb.getText().equalsIgnoreCase(filter)) {
				wb.click();
				break;
			}
		}
	}

	public void selectFilteredOpton(String optionItem) {
		if(filteredOptions.isEmpty()) {
			zurockButtonElement.click();

		}
		else {
			for(WebElement option:filteredOptions) {
				if(option.getText().equalsIgnoreCase(optionItem)) {
					option.click();
					zurockButtonElement.click();
					break;
				}
			}
		}
	}

	public void clickSchliessenButton() {
		schliessenButtonElement.click();
	}

	public void verifyItemList(String itemName) {
		List<WebElement> allItemList =  filtereditemListElement;
		if(allItemList.isEmpty()) {

			System.out.println("No item found");
		}
		else {
			for(WebElement item:allItemList) {
				if(item.getText().equalsIgnoreCase(itemName)) {
					Assert.assertEquals(item.getText(), itemName);
					System.out.println("Item : " +item.getText() + " : is present");
					break;
				}
			}
		}
	}

}
