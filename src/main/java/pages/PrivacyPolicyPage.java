package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class PrivacyPolicyPage extends RootPage {
	WebDriver driver;

	public PrivacyPolicyPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Privacy Policy']")
	private WebElement privacyPolicyBreadCrumb;

	public boolean didWeNavigateToPrivacyPolicyPage() {
		return privacyPolicyBreadCrumb.isDisplayed();
	}

}
