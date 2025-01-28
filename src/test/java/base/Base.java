package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import pages.AccountSuccessPage;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.NewsletterPage;
import pages.RegisterPage;
import pages.RightColumnOptions;
import utils.CommonUtilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public String browserName;
	public HeaderOptions headerOptions;
	public LoginPage loginPage;
	public HomePage homePage;
	public RightColumnOptions rightColumnOptions;
	public AccountSuccessPage accountSuccessPage;
	public FooterOptions footerOptions;
	public RegisterPage registerPage;
	public MyAccountPage myAccountPage;
	public NewsletterPage newsletterPage;

	public WebDriver openBrowserAndApplicationPageURL() {
		prop = CommonUtilities.loadPropertiesFile();
		browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("internetexplorer")) {
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		jse.executeScript("document.body.style.zoom='100%'");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("appURL"));
		return driver;
	}
	
	public String getHTMLCodeOfThePage() {
		return driver.getPageSource();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageURL() {
		return driver.getCurrentUrl();
	}

	public WebDriver navigateBackInBrowser(WebDriver driver) {
		 driver.navigate().back();
		 return driver;
	}

	public void closeBrowser(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver refreshPage(WebDriver driver) {
		 driver.navigate().refresh();
		 return driver;
	}

	public Actions getActions(WebDriver driver) {
		Actions action = new Actions(driver);
		return action;
	}

	public Actions clickKeyboardKeyMultipleTimes(Actions actions, Keys keyName, int noOfTimes) {
		for (int i = 1; i <= noOfTimes; i++) {
			actions.sendKeys(keyName).perform();
		}
		return actions;
	}

	public Actions typeTextUsingActions(Actions actions, String text) {
		actions.sendKeys(text).perform();
		return actions;
	}

	public WebDriver pressKeyMultipleTimes(WebDriver driver, Keys keyName, int count) {
		Actions actions = new Actions(driver);
		for (int i = 1; i <= count; i++) {
			actions.sendKeys(keyName).perform();
		}
		return driver;
	}

	public WebDriver enterDetailsIntoLoginPageFields(WebDriver driver) {
		prop = CommonUtilities.loadPropertiesFile();
		Actions actions = new Actions(driver);
		actions.sendKeys(prop.getProperty("existingEmail")).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.sendKeys(prop.getProperty("validPassword")).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		return driver;
	}
}
