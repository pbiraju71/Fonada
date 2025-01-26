package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;

public class AboutUsPage extends RootPage {
	WebDriver driver;

	public AboutUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='About Us']")
	private WebElement aboutUsBreadCrumb;

	public boolean didWeNavigateToAboutUsPage() {
		return aboutUsBreadCrumb.isDisplayed();
	}

}
