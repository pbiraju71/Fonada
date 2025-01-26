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

	public LoginPage clickOnLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}

	public void clickOnMyAccountOption() {
		myAccountOption.click();
	}
}
