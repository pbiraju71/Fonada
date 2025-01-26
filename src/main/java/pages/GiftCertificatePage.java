package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class GiftCertificatePage extends RootPage {
	WebDriver driver;

	public GiftCertificatePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Gift Certificate")
	private WebElement breadCrumbGiftCertificatePage;

	public boolean didWeNavigateToGiftCertificatePage() {
		return breadCrumbGiftCertificatePage.isDisplayed();
	}
}
