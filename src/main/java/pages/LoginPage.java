package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class LoginPage extends RootPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadcrumb;

	@FindBy(linkText = "Forgotten Password")
	private WebElement forgottendPasswordLink;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement waringinMessage;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='My Account']")
	private WebElement My_Account_RightColumnOption;

	@FindBy(xpath = "//h2[normalize-space()='New Customer']")
	private WebElement NewCustomerHeading;

	@FindBy(xpath = "//h2[normalize-space()='Returning Customer']")
	private WebElement ReturningCustomerHeading;

	public String getPageHeading1() {
		return NewCustomerHeading.getText();
	}
	
	public String getPageHeading2() {
		return ReturningCustomerHeading.getText();
	}

	public void clearEmailField() {
		emailField.clear();
	}

	public void clearPasswordField() {
		passwordField.clear();
	}

	public String getPasswordFieldType() {
		return passwordField.getDomAttribute("type");
	}

	public String getTextCopiedIntoEmailField() {
		return emailField.getDomProperty("value");
	}

	public WebDriver pasteCopiedPasswordTextIntoEmailField(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.click(emailField).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL);
		return driver;
	}

	public WebDriver selectPasswordFieldTextAndCopy(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.doubleClick(passwordField).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL);
		return driver;
	}

	public AccountLogOutPage clickOnLogoutOption() {
		loginButton.click();
		return new AccountLogOutPage(driver);
	}

	public AccountPage clickOnMyAccountRightColumnOption() {
		My_Account_RightColumnOption.click();
		return new AccountPage(driver);
	}

	public String getWarningMessage() {
		return waringinMessage.getText();
	}

	public String getEmailFieldPlaceholderText() {
		return elementUtilities.getElementDomAttribute(emailField, "placeholder");
	}

	public String getPasswordFieldPlaceholderText() {
		return elementUtilities.getElementDomAttribute(passwordField, "placeholder");
	}

	public ForgottenPasswordPage clickOnForgottenPasswordLink() {
		forgottendPasswordLink.click();
		return new ForgottenPasswordPage(driver);
	}

	public RegisterPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new RegisterPage(driver);
	}

//	public MyAccountPage clickOnLoginButton() {
//		elementUtilities.clickOnElement(loginButton);
//		return new MyAccountPage(driver);
//	}
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}

	public void enterEmail(String emailText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
	}

	public void enterPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
	}

	public MyAccountPage loginInToApplication(String emailText, String passwordText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);
	}

	public boolean didWeNavigateToLogin() {
		return elementUtilities.isElementDisplayed(loginBreadcrumb);
	}

	public boolean isProperBreadCrumbDisplayed() {
		return loginBreadcrumb.isDisplayed();
	}

	public LoginPage clickOnloginBreadcrumb() {
		loginBreadcrumb.click();
		return new LoginPage(driver);
	}

	public boolean availabilityOfForgettenPasswordLink() {
		return forgottendPasswordLink.isDisplayed();
	}

}
