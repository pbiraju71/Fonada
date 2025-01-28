package tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountLogOutPage;
import pages.AccountPage;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.RightColumnOptions;
import utils.CommonUtilities;

public class LogoutPageTest extends Base {
	public WebDriver driver;
	LandingPage landingPage;
	Properties prop;
	LoginPage loginPage;
	AccountPage accountPage;
	HeaderOptions headerOptions;
	AccountLogOutPage accountLogoutPage;
	MyAccountPage myAccountPage;
	RightColumnOptions rightColumnOptions;

	@AfterMethod
	public void tearDown() {
		closeBrowser(driver);
	}

	@BeforeMethod
	public void setup() {
		driver = openBrowserAndApplicationPageURL();
		prop = CommonUtilities.loadPropertiesFile();
		landingPage = new LandingPage(driver);
	}

	@Test(priority = 1)
	public void verifyLoggingOutUsingMyAccountDropMenu() {
		loginPage = landingPage.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"),
				prop.getProperty("validPassword"));
		driver = myAccountPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.clickOnLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		driver = accountLogoutPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailable());
		driver = headerOptions.getDriver();
		accountLogoutPage = new AccountLogOutPage(driver);
		landingPage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageURL(), prop.getProperty("landingPageURL"));
	}

	@Test(priority = 2)
	public void verifyLoggingOutUsingLogoutRightColumnOption() {
		loginPage = landingPage.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"),
				prop.getProperty("validPassword"));
		driver = myAccountPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		accountLogoutPage = rightColumnOptions.clickOnRightSideLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		driver = accountLogoutPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailable());
		driver = headerOptions.getDriver();
		accountLogoutPage = new AccountLogOutPage(driver);
		landingPage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageURL(), prop.getProperty("landingPageURL"));
	}

	@Test(priority = 3)
	public void verifyLoggingOutAndBrowsingBack() {
		loginPage = landingPage.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"),
				prop.getProperty("validPassword"));
		driver = myAccountPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.clickOnLogoutOption();
		driver = navigateBackInBrowser(driver);
		driver = refreshPage(driver);
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
	}

	@Test(priority = 4)
	public void verifyThereIsNoLogoutOptionBeforeLogin() throws InterruptedException {
		driver = landingPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertFalse(headerOptions.isLogoutOptionAvailableUnderMyAccountDropMenu());
		driver = headerOptions.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		Assert.assertFalse(rightColumnOptions.isLogoutOptionAvailableAtRightColumnOptions());
	}

	@Test(priority = 5)
	public void verifyLoginAfterLogout() {
		loginPage = landingPage.navigateToLoginPage();
		loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"), prop.getProperty("validPassword"));
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.clickOnLogoutOption();
		driver = accountLogoutPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@Test(priority = 6)
	public void verifyBreadCrumbTitleHeadingAndURLOfAccountLogoutPage() {
		loginPage = landingPage.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"),
				prop.getProperty("validPassword"));
		driver = myAccountPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.clickOnLogoutOption();
		Assert.assertEquals(getPageTitle(driver), prop.getProperty("accountLogoutPageTitle"));
		Assert.assertEquals(getPageURL(), prop.getProperty("accountLogoutPageURL"));
		Assert.assertEquals(accountLogoutPage.getPageHeading(), prop.getProperty("accountLogoutPageHeading"));
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
	}

	@Test(priority = 7)
	public void verifyUIOfLogoutOptionAndAccountLogoutPage() {
		loginPage = landingPage.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"),
				prop.getProperty("validPassword"));
		driver = myAccountPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		if (prop.getProperty("browserName").equals("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\resources\\Screenshots\\actualchromeLogoutOption.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\resources\\Screenshots\\actualchromeLogoutOption.png",
					System.getProperty("user.dir") + "\\resources\\Screenshots\\expectedchromeLogoutOption.png"));
		} else if (prop.getProperty("browserName").equals("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\resources\\Screenshots\\actualfirefoxLogoutOption.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\resources\\Screenshots\\actualfirefoxLogoutOption.png",
					System.getProperty("user.dir") + "\\resources\\Screenshots\\expectedfirefoxLogoutOption.png"));
		}

	}

	@Test(priority = 8)
	public void verifyAccountLogoutFunctionality() {
		loginPage = landingPage.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"),
				prop.getProperty("validPassword"));
		driver = myAccountPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.clickOnLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		landingPage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageURL(), prop.getProperty("landingPageURL"));
		driver = myAccountPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailable());
	}
}
