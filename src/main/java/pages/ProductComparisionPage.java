package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import root.RootPage;
import utils.ElementUtilities;

public class ProductComparisionPage extends RootPage {
	WebDriver driver;
	ElementUtilities elementUtilities;

	public ProductComparisionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Product Comparison']")
	private WebElement productComparisionBreadCrumb;

	@FindBy(xpath = "//div[@id='content']//p[text()='You have not chosen any products to compare.']")
	private WebElement NoProductChosenMessage;

	@FindBy(xpath = "//div[@id='content']//h1[text()='Product Comparison']")
	private WebElement productComparisionHeader;

	@FindBy(xpath = "//div[@class='pull-right']//a[text()='Continue']")
	private WebElement continueButton;

	public void clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
	}

	public String getProductComparisionHeader() {
		return elementUtilities.getTextOfElement(productComparisionHeader);
	}

	public String getNotProductChosenMessage() {
		return elementUtilities.getTextOfElement(NoProductChosenMessage);
	}

	public boolean didWeNavigateOnProductComparisionPage() {
		return elementUtilities.isElementDisplayedOnPage(productComparisionBreadCrumb);
	}
}
