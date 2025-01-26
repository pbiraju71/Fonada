package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class AccountPage extends RootPage {
	WebDriver driver;

	public AccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Edit your account information']")
	private WebElement editYourAccountInformationOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']")
	private WebElement logOutOption;
	
	@FindBy(linkText="Edit your account information")
	private WebElement Edityouraccountinformation;
	
	@FindBy(linkText="Change your password")
	private WebElement Changeyourpassword;
	
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement getSuccessMessage;
	
	public String getMessage() {
		return getSuccessMessage.getText();
	}
	
	public ChangePasswordPage clickOnChangeYourPasswordOption() {
		 Changeyourpassword.click();
		return new ChangePasswordPage(driver);
	}
	public void Edityouraccountinformation() {
		editYourAccountInformationOption.click();
	}
	
	public boolean isUserLoggedIn() {
		return logOutOption.isDisplayed();
	}
	public boolean didWeNavigateToAccountPage() {
		return editYourAccountInformationOption.isDisplayed();
	}
	
	public AccountLogOutPage clickOnLogOutOption() {
		logOutOption.click();
		return new AccountLogOutPage(driver);
	}
}
