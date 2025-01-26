package pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class MyAccountInformationPage extends RootPage {

	WebDriver driver;

	public MyAccountInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement searchBreadCrumb;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement continueButton;

	public boolean didWeNavigateSearchPage() {
		return searchBreadCrumb.isDisplayed();
	}

	public String getFirstNameFieldValue() {
		return firstNameField.getDomAttribute("value");
	}

	public String getLastNameFieldValue() {
		return lastNameField.getDomAttribute("value");
	}

	public String getEmailFieldValue() {
		return emailField.getDomAttribute("value");
	}

	public String getTelephoneFieldValue() {
		return telephoneField.getDomAttribute("value");
	}
}
