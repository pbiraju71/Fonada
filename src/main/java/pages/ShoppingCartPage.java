package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class ShoppingCartPage extends RootPage {

	WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Shopping Cart")
	private WebElement breadCrumbShoppingCart;

	public boolean didWeNavigateToShoppingCartPage() {
		return breadCrumbShoppingCart.isDisplayed();
	}

}
