package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class ForgottenPasswordPage extends RootPage{

	WebDriver driver;

	public ForgottenPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Forgotten Password")
	private WebElement Forgotten_Password_Bread_Crumb;
	
	public boolean didWeNavigateToForgettenPasswordPage() {
		return elementUtilities.isElementDisplayed(Forgotten_Password_Bread_Crumb);
	}
}
