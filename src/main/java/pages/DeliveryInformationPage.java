package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class DeliveryInformationPage extends RootPage {
	WebDriver driver;

	public DeliveryInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Delivery Information")
	private WebElement breadCrumbDeliveryInformation;

	public boolean didWeNavigateToDeliveryInformationPage() {
		return breadCrumbDeliveryInformation.isDisplayed();
	}
}
