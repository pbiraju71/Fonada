package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class BrandPage extends RootPage {
	WebDriver driver;

	public BrandPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Brand")
	private WebElement breadCrumbBrand;

	public boolean didWeNavigateToBrandPage() {
		return breadCrumbBrand.isDisplayed();
	}
}
