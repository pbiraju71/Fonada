package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class ChangePasswordPage extends RootPage {

	WebDriver driver;

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@type='submit' ][@value='Continue']")
	private WebElement continuebtn;

	public void enterPassword(String Text) {
		passwordField.sendKeys(Text);
	}

	public void enterConfirmPassword(String Text) {
		confirmPasswordField.sendKeys(Text);
	}
	
	public AccountPage clickOnContinueButton() {
		continuebtn.click();
		return new AccountPage(driver);
	}
}
