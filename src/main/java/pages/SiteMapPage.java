package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class SiteMapPage extends RootPage {
	WebDriver driver;

	public SiteMapPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Site Map']")
	private WebElement siteMapBreadCrumb;

	public boolean didWeNavigateToSiteMapPage() {
		return siteMapBreadCrumb.isDisplayed();
	}

}
