package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class AccountLogOutPage extends RootPage {

	WebDriver driver;

	public AccountLogOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='My Account']")
	private WebElement myAccountOption;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Logout']")
	private WebElement logoutBreadCrumb;
	
	@FindBy(xpath = "//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//h1[text()='Account Logout']")
	private WebElement accountLogoutPageHeader;
	
	public boolean didWeNavigateToAccountLogoutPage() {
		return logoutBreadCrumb.isDisplayed();
	}
	public LoginPage clickOnLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	public LandingPage clickOnContinueButton() {
		continueButton.click();
		return new LandingPage(driver);
	}

	public void clickOnMyAccountOption() {
		myAccountOption.click();
	}
	public String getPageHeading() {
		return accountLogoutPageHeader.getText();
	}
	
}
