package com.douglas.objectReposotory;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	@FindBy(xpath="//li[@data-uid='AppParfumNavNode']//a[text()='Parfum']")
	private WebElement perfumeLinkElement;
	@FindBy(xpath = "//div[@class='navigation-backdrop']//div[@data-testid='navigation-main-column']//ul[@id='Navigation_Flyout_Content']//li[@data-testid='navigation-link-container']//a")
	private List<WebElement> allHowerList;


	//Business library/Methods
	public void handlePrivacyPopUp(WebDriver driver, long longTimeout) {

		WebElement shadowhost = shadowrootElement;
		SearchContext shadowroot = shadowhost.getShadowRoot();
		shadowroot.findElement(By.cssSelector("[data-testid='uc-accept-all-button']")).click();

	}
	public void clickPerfumLink() {
		perfumeLinkElement.click();
	}

	public void clickPerfumHowerOptionLink(WebDriver driver, String itemLinkText) {
		
		WebDriverUtility.getInstance().mouseOverOnElement(driver, perfumeLinkElement);
		for(WebElement item : allHowerList) {
			if(item.getText().equalsIgnoreCase(itemLinkText)) {
				item.click();
				break;
			}
		}

	}


}


