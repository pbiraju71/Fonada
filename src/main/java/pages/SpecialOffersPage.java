package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class SpecialOffersPage extends RootPage {
	WebDriver driver;

	public SpecialOffersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Special Offers']")
	private WebElement specialOfferBreadCrumb;

	public boolean didWeNavigateToSpecialOffersPage() {
		return specialOfferBreadCrumb.isDisplayed();
	}
}
