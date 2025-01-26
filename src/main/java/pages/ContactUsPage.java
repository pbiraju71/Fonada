package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class ContactUsPage extends RootPage {
	WebDriver driver;

	public ContactUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Contact Us']")
	private WebElement contactUsBreadCrumb;

	public boolean didWeNavigateToContactUsPage() {
		return contactUsBreadCrumb.isDisplayed();
	}

}
