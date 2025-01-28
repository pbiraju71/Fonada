package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class LandingPage extends RootPage{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountOption;

	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	public void clickOnMyAccount() {
		myAccountOption.click();
	}
	public LoginPage navigateToLoginPage() {
		clickOnMyAccount();
		return selectLoginOption();
	}
}
