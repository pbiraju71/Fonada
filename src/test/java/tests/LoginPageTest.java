package tests;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AboutUsPage;
import pages.AccountLogOutPage;
import pages.AccountPage;
import pages.AffiliatePage;
import pages.BrandPage;
import pages.ChangePasswordPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.FooterOptions;
import pages.ForgottenPasswordPage;
import pages.GiftCertificatePage;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.LoginPage;
import pages.PrivacyPolicyPage;
import pages.ProductReturnsPage;
import pages.RightColumnOptions;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialOffersPage;
import pages.TermsAndConditionsPage;
import utils.CommonUtilities;

public class LoginPageTest extends Base {

	public WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountPage;
	ForgottenPasswordPage forgottenPasswordPage;
	ChangePasswordPage changePasswordPage;
	AccountLogOutPage accountLogOutPage;
	HeaderOptions headerOptions;
	ContactUsPage contactUsPage;
	ShoppingCartPage shoppingCartPage;
	SearchPage searchPage;
	FooterOptions footerOptions;
	AboutUsPage aboutUsPage;
	DeliveryInformationPage deliveryInformationPage;
	PrivacyPolicyPage privacyPolicyPage;
	TermsAndConditionsPage termsAndConditionsPage;
	ProductReturnsPage productReturnsPage;
	SiteMapPage siteMapPage;
	BrandPage brandPage;
	GiftCertificatePage giftCertificatePage;
	SpecialOffersPage specialOffersPage;
	AffiliatePage affiliatePage;

	@BeforeMethod
	public void setup() {
		driver = openBrowserAndApplicationPageURL();
		prop = CommonUtilities.loadPropertiesFile();
		landingPage = new LandingPage(driver);
		landingPage.clickOnMyAccount();
		loginPage = landingPage.selectLoginOption();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void verifyWithLoginWithValidCredentials() {
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
	}

	@Test(priority = 2)
	public void verifyWithLoginWithInValidCredentials() {
		loginPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("mismatchingPassword"));
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
	}

	@Test(priority = 3)
	public void verifyWithLoginWithInValidEmailAndValidPassword() {
		loginPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
	}

	@Test(priority = 4)
	public void verifyWithLoginWithValidEmailAndInValidPassword() {
		loginPage.enterEmail(CommonUtilities.validEmailRandomizeGenerator());
		loginPage.enterPassword(prop.getProperty("mismatchingPassword"));
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
	}

	@Test(priority = 6)
	public void verifyForgettenPasswordLink() {
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		Assert.assertTrue(loginPage.availabilityOfForgettenPasswordLink());
		forgottenPasswordPage = loginPage.clickOnForgottenPasswordLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgettenPasswordPage());
	}

	@Test(priority = 7)
	public void verifyLoggigIntoApplicationUsingKeyboardKeys() {
		driver = pressKeyMultipleTimes(driver, Keys.TAB, 23);
		driver = enterDetailsIntoLoginPageFields(driver);
		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertTrue(accountPage.didWeNavigateToAccountPage());

	}

	@Test(priority = 8)
	public void verifyLoginFieldsPlaceHolders() {
		String expectedEmailPlaceHolder = "E-Mail Address";
		String expectedPasswordPlaceHolder = "Password";
		Assert.assertEquals(loginPage.getEmailFieldPlaceholderText(), expectedEmailPlaceHolder);
		Assert.assertEquals(loginPage.getPasswordFieldPlaceholderText(), expectedPasswordPlaceHolder);

	}

	@Test(priority = 9)
	public void verifyBrowserBackAfterLogin() {
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		driver = navigateBackInBrowser(driver);
		loginPage = new LoginPage(driver);
		accountPage = loginPage.clickOnMyAccountRightColumnOption();
		Assert.assertTrue(accountPage.isUserLoggedIn());

	}

	@Test(priority = 10)
	public void verifyBrowserBackAfterLogingOut() {
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		loginPage.clickOnLogoutOption();
		driver = navigateBackInBrowser(driver);
		accountPage = new AccountPage(driver);
		accountPage.Edityouraccountinformation();
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

	}

	@Test(priority = 11)
	public void verifyLoginWithInactiveCredentials() {
		loginPage.enterEmail(prop.getProperty("inactiveEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
	}

	@Test(priority = 12)
	public void verifyNumberOfUnsuccessfulLoginAttempt() {
		loginPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		String expectedWarning1 = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning1);
	}

	@Test(priority = 13)
	public void verifyTextEnteredIntoPasswordFieldIsToggledToHideItsVisibility() {
		String expectedType = "password";
		Assert.assertEquals(loginPage.getPasswordFieldType(), expectedType);
	}

	@Test(priority = 14)
	public void verifyCopingOfTextEnteredIntoPasswordField() {
		String passwordText = prop.getProperty("validPassword");
		loginPage.enterPassword(passwordText);
		driver = loginPage.selectPasswordFieldTextAndCopy(driver);
		driver = loginPage.pasteCopiedPasswordTextIntoEmailField(driver);
		Assert.assertNotEquals(loginPage.getTextCopiedIntoEmailField(), passwordText);
	}

	@Test(priority = 15)
	public void verifyPasswordIsStoredInHTMLCodeOfThePage() {
		String passwordText = prop.getProperty("validPassword");
		loginPage.enterPassword(passwordText);
		Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));
		loginPage.clickOnLoginButton();
		Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));
	}

	@Test(priority = 16)
	public void verifyLoggingIntoApplicationAfterChangingPassword() {
		String oldPassword = null;
		String newPassword = null;
		try {
			oldPassword = prop.getProperty("validPassword");
			newPassword = prop.getProperty("validSamplePassword");
			loginPage.enterEmail(prop.getProperty("existingSampleEmail"));
			loginPage.enterPassword(oldPassword);
			accountPage = loginPage.clickOnLoginButton();
			changePasswordPage = accountPage.clickOnChangeYourPasswordOption();
			changePasswordPage.enterPassword(newPassword);
			changePasswordPage.enterConfirmPassword(newPassword);
			accountPage = changePasswordPage.clickOnContinueButton();
			String expectedMessage = "Success: Your password has been successfully updated.";
			Assert.assertEquals(accountPage.getMessage(), expectedMessage);
			accountLogOutPage = accountPage.clickOnLogOutOption();
			accountLogOutPage.clickOnMyAccountOption();
			loginPage = accountLogOutPage.clickOnLoginOption();
			loginPage.enterEmail(prop.getProperty("existingSampleEmail"));
			loginPage.enterPassword(oldPassword);
			loginPage.clickOnLoginButton();
			String existingWarning = "Warning: No match for E-Mail Address and/or Password.";
			Assert.assertEquals(loginPage.getWarningMessage(), existingWarning);
			loginPage.clearPasswordField();
			loginPage.enterPassword(newPassword);
			accountPage = loginPage.clickOnLoginButton();
			Assert.assertTrue(accountPage.isUserLoggedIn());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtilities.setProperties("validPassword", newPassword, prop);
			CommonUtilities.setProperties("validSamplePassword", oldPassword, prop);
		}
	}

	@Test(priority = 17)
	public void verifyNavigatingToDifferentPagesFromLoginPage() {
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		contactUsPage = headerOptions.selectPhoneIconOption();
		Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		loginPage = headerOptions.selectHeartIconOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		shoppingCartPage = headerOptions.selectShoppingCartIconOption();
		Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		shoppingCartPage = headerOptions.selectcheckOutIconOption();
		Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		landingPage = headerOptions.selectLogoOption();
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("landingPageURL"));
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.selectSearchOption();
		Assert.assertTrue(searchPage.didWeNavigateSearchPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		loginPage = loginPage.clickOnloginBreadcrumb();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		landingPage = headerOptions.clickOnHomeBreadCrumb();
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("landingPageURL"));
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		registerPage = loginPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		forgottenPasswordPage = loginPage.clickOnForgottenPasswordLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgettenPasswordPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		registerPage = rightColumnOptions.clickOnRegisterOption();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		forgottenPasswordPage = rightColumnOptions.clickOnForgottenPasswordOption();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgettenPasswordPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		loginPage = rightColumnOptions.clickOnMyAccountOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnAddressBookOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnWishListOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnOrderHistoryOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnDownloadsOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRecurringPaymentsOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRewarPointsOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnReturnsOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnTransactionsOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnNewsletterOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		aboutUsPage = footerOptions.selectAboutUsOption();
		Assert.assertTrue(aboutUsPage.didWeNavigateToAboutUsPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		deliveryInformationPage = footerOptions.selectDeliveryInformationOption();
		Assert.assertTrue(deliveryInformationPage.didWeNavigateToDeliveryInformationPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		privacyPolicyPage = footerOptions.selectPrivacyPolicyOption();
		Assert.assertTrue(privacyPolicyPage.didWeNavigateToPrivacyPolicyPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		termsAndConditionsPage = footerOptions.selectTermsAndConditionsOption();
		Assert.assertTrue(termsAndConditionsPage.didWeNavigateToTermsAndConditionsPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		contactUsPage = footerOptions.selectContactUsOption();
		Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		productReturnsPage = footerOptions.selectReturnsOption();
		Assert.assertTrue(productReturnsPage.didWeNavigateToProductReturnsPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		siteMapPage = footerOptions.selectSiteMapOption();
		Assert.assertTrue(siteMapPage.didWeNavigateToSiteMapPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		brandPage = footerOptions.selectBrandsOption();
		Assert.assertTrue(brandPage.didWeNavigateToBrandPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		giftCertificatePage = footerOptions.selectGiftCertificatesOption();
		Assert.assertTrue(giftCertificatePage.didWeNavigateToGiftCertificatePage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		affiliatePage = footerOptions.selectAffiliatePageOption();
		Assert.assertTrue(affiliatePage.didWeNavigateToAffiliatePage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		specialOffersPage = footerOptions.selectSpecialsOption();
		Assert.assertTrue(specialOffersPage.didWeNavigateToSpecialOffersPage());
		driver = navigateBackInBrowser(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		loginPage = footerOptions.selectMyAccountOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		loginPage = footerOptions.selectOrderHistoryOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		loginPage = footerOptions.selectWishListOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		loginPage = footerOptions.selectNewsletterOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
	}

	@Test(priority = 18)
	public void verifyDifferentWaysToNavigatingToLoginPage() {
		registerPage = loginPage.clickOnContinueButton();
		loginPage = registerPage.clickOnLoginPageLink();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
	}

	@Test(priority = 19)
	public void verifyBreadCrumbPageHeadingTitleAndPageOfLoginPage() {
		Assert.assertTrue(loginPage.isProperBreadCrumbDisplayed());
		Assert.assertEquals(getPageTitle(driver), prop.getProperty("loginPageTitle"));
		Assert.assertEquals(getPageURL(), prop.getProperty("loginPageURL"));
		Assert.assertEquals(loginPage.getPageHeading1(), prop.getProperty("registerPageHeading1"));
		Assert.assertEquals(loginPage.getPageHeading2(), prop.getProperty("registerPageHeading2"));
	}

	@Test(priority = 20)
	public void verifyUIOfLoginPage() {
		CommonUtilities.takeScreenshot(driver, "D:\\Automation\\Fonada\\resources\\Screenshots\\actualLoginPageUI.png");
				Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
				System.getProperty("user.dir") + "\\resources\\Screenshots\\actualLoginPageUI.png",
				System.getProperty("user.dir") + "\\resources\\Screenshots\\expectedLoginPageUI.png"));
	}
	@Test(priority=21)
	public void verifyLoginFunctionalityInAllEnvironments() {
		loginPage.enterEmail(prop.getProperty("existingSampleEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage=loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
	}
}
