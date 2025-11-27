package com.douglas.objectReposotory;

	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.douglas.genericUtility.WebDriverUtility;

	public class HomePage {
		//Constructor
				public HomePage(WebDriver driver) {
					PageFactory.initElements(driver, this);
				}
				
				// Locators
				@FindBy(id="usercentrics-root")
				private WebElement shadowrootElement;
				@FindBy(css="[data-testid='uc-accept-all-button']")
				private WebElement acceptPolicyButton;
				@FindBy(css="#navigation-main-entry-2")
				private WebElement perfumeLinkElement;
				@FindBy(xpath = "//div[@data-testid='navigation-main-column']//ul[@id='Navigation_Flyout_Content']//li//a[@href='/de/c/parfum/damenduefte/deodorants/010105']")
				private WebElement deodorantElement;
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
				@FindBy(xpath = "(//div[@class='facets-mobile__transition']/descendant::button[@data-testid='button-primary'])[2]")
				private WebElement zurockButtonElement;
				@FindBy(xpath = "//button[text()='Schliessen']")
				private WebElement schliessenButtonElement;
				@FindBy(xpath = "//div[@class='cms-container']/descendant::div[@class='text top-brand']")
				private List<WebElement> filtereditemListElement;
				
				
				//Business library/Methods
				
				public void handlePrivacyPopUp() throws InterruptedException {
//					WebDriverUtility.getInstance().waitUntilElementVisible(acceptPolicyButton);
				    Thread.sleep(10000);
					WebElement shadowhost = shadowrootElement;
					SearchContext shadowroot = shadowhost.getShadowRoot();
					shadowroot.findElement(By.cssSelector("[data-testid='uc-accept-all-button']")).click();
					
				}
				public void clickPerfumLink() {
					perfumeLinkElement.click();
				}
				
				public void clickDeodrantInPerfum(WebDriver driver) {
					WebDriverUtility.getInstance().mouseOverOnElement(perfumeLinkElement);
					deodorantElement.click();
					
				}
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
					List<WebElement> allFilter = listOfFilters;
					for(WebElement wb : allFilter) {
						if(wb.getText().equalsIgnoreCase(filter)) {
							wb.click();
							break;
						}
					}
				}

				public void selectFilteredOpton(String optionItem) {
					List<WebElement> allFilterOptions = filteredOptions;
					
					if(allFilterOptions.isEmpty()) {
						zurockButtonElement.click();
						
					}
					else {
						for(WebElement option:allFilterOptions) {
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


