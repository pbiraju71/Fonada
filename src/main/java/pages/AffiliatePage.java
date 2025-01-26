package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class AffiliatePage extends RootPage {
	WebDriver driver;

	public AffiliatePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Login")
	private WebElement breadCrumbLoginAffiliate;

	public boolean didWeNavigateToAffiliatePage() {
		return breadCrumbLoginAffiliate.isDisplayed();
	}
}
