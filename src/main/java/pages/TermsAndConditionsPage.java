package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class TermsAndConditionsPage extends RootPage {
	WebDriver driver;

	public TermsAndConditionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Terms & Conditions']")
	private WebElement termsAndConditionsBreadCrumb;

	public boolean didWeNavigateToTermsAndConditionsPage() {
		return termsAndConditionsBreadCrumb.isDisplayed();
	}

}
