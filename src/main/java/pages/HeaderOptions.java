package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;
import utils.ElementUtilities;

public class HeaderOptions extends RootPage {
	WebDriver driver;

	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='fa fa-home']")
	private WebElement homeBreadCrumb;

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][text()='Currency']")
	private WebElement CurrencyIconOption;

	@FindBy(xpath = "//i[@class='fa fa-phone']")
	private WebElement PhoneIconOption;

	@FindBy(xpath = "(//i[@class='fa fa-heart'])[1]")
	private WebElement heartIconOption;

	@FindBy(xpath = "(//i[@class='fa fa-shopping-cart'])[1]")
	private WebElement shoppingCartIconOption;

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][text()='Checkout']")
	private WebElement checkOutIconOption;

	@FindBy(xpath = "//a[text()='Qafox.com']")
	private WebElement logoOption;

	@FindBy(xpath = "//i[@class='fa fa-search']")
	private WebElement searchOption;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	public LandingPage clickOnHomeBreadCrumb() {
		homeBreadCrumb.click();
		return new LandingPage(driver);
	}

	public SearchPage selectSearchOption() {
		searchOption.click();
		return new SearchPage(driver);
	}

	public LandingPage selectLogoOption() {
		logoOption.click();
		return new LandingPage(driver);
	}

	public ShoppingCartPage selectcheckOutIconOption() {
		checkOutIconOption.click();
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage selectShoppingCartIconOption() {
		shoppingCartIconOption.click();
		return new ShoppingCartPage(driver);
	}

	public LoginPage selectHeartIconOption() {
		heartIconOption.click();
		return new LoginPage(driver);
	}

	public void selectCurrencyIconOption() {
		CurrencyIconOption.click();
	}

	public ContactUsPage selectPhoneIconOption() {
		PhoneIconOption.click();
		return new ContactUsPage(driver);
	}

	public void clickOnMyAccountDropMenu() {
		elementUtilities.clickOnElement(myAccountDropMenu);
	}

	public RegisterPage selectRegisterOption() {
		elementUtilities.clickOnElement(registerOption);
		return new RegisterPage(driver);
	}

	public LoginPage selectLoginOption() {
		elementUtilities.clickOnElement(loginOption);
		return new LoginPage(driver);
	}
}
