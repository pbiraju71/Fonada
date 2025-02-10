package tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.ProductComparisionPage;
import pages.SearchPage;
import utils.CommonUtilities;

public class SearchPageTest extends Base {

	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	SearchPage searchPage;
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	HeaderOptions headerOptions;
	ProductComparisionPage productComparisionPage;

	@AfterMethod
	public void tearDown() {
		closeBrowser(driver);
	}

	@BeforeMethod()
	public void setup() {
		driver = openBrowserAndApplicationPageURL();
		prop = CommonUtilities.loadPropertiesFile();
		landingPage = new LandingPage(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		landingPage.enterProductNameIntoSearchTextBox(prop.getProperty("existingProductName"));
		searchPage = landingPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateSearchPage());
		Assert.assertTrue(searchPage.isExistingProductDisplayedOnSearchResults());
	}

	@Test(priority = 2)
	public void verifySearchWithNonValidProduct() {
		String expectedMessage = "There is no product that matches the search criteria.";
		landingPage.enterProductNameIntoSearchTextBox(prop.getProperty("NonexistingProductName"));
		searchPage = landingPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateSearchPage());
		Assert.assertFalse(searchPage.isExistingProductDisplayedOnSearchResults());
		Assert.assertEquals(searchPage.getNoSearchProductMessage(), expectedMessage);
	}

	@Test(priority = 3)
	public void verifySearchWithoutEnteringAnyProduct() {
		String expectedMessage = "There is no product that matches the search criteria.";
		searchPage = landingPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateSearchPage());
		Assert.assertEquals(searchPage.getNoSearchProductMessage(), expectedMessage);
	}

	@Test(priority = 4)
	public void verifySearchAfterLogin() {
		loginPage = landingPage.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingSampleEmail"),
				prop.getProperty("validPassword"));
		driver = myAccountPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.enterProductNameIntoSearchTextBox(prop.getProperty("existingProductName"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateSearchPage());
		Assert.assertTrue(searchPage.isExistingProductDisplayedOnSearchResults());
	}

	@Test(priority = 5)
	public void verifySearchResultingInMultipleProducts() {
		landingPage.enterProductNameIntoSearchTextBox(prop.getProperty("searchTermResultMultipeProducts"));
		searchPage = landingPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.verifyNoOfProductsDisplayedInSearchResults() > 1);
	}

	@Test(priority = 6)
	public void verifySearchFieldsPlaceHolders() {
		headerOptions = new HeaderOptions(driver);
		String expectedSearchBoxPlaceHolderText = "Search";
		Assert.assertEquals(headerOptions.getPlaceHolderTextOfSearchBoxField(), expectedSearchBoxPlaceHolderText);
		searchPage = headerOptions.clickOnSearchButton();
		String expectedSearchCriteriaPlaceHolderText = "Keywords";
		Assert.assertEquals(searchPage.getPlaceHolderTextOfSearchCriteriaField(),
				expectedSearchCriteriaPlaceHolderText);
	}

	@Test(priority = 7)
	public void verifySearchingForProductUsingSearchCriteria() {
		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterProductNameIntosearchCriteriaField(prop.getProperty("existingProductName"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isExistingProductDisplayedOnSearchResults());

	}

	@Test(priority = 8)
	public void verifySearchingForProductUsingSomeTextInProductDescription() {
		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterProductNameIntosearchCriteriaField(prop.getProperty("termInProductDescription"));
		searchPage.selectSearchInProductDescriptioncheckBoxField();
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductHavingDescriptionTextDisplayedInSearchResults());
	}

	@Test(priority = 9)
	public void verifySearchBySelectingSubCategory() throws InterruptedException {
		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterProductNameIntosearchCriteriaField(prop.getProperty("existingImacProductName"));
		searchPage.SelectOptionFromCategoryDropDownField(prop.getProperty("existingProductCategory"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductCategoryDisplayedInSearchResults());
	}

	@Test(priority = 10)
	public void verifySearchByUsingParentCategoryAndSearchInSubCategoriesOption() throws InterruptedException {
		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterProductNameIntosearchCriteriaField(prop.getProperty("existingProductCategory"));
		searchPage.SelectOptionFromCategoryDropDownField(prop.getProperty("existingProductInSubCategory"));
		searchPage.clickOnSearchButton();
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(searchPage.getNoSearchProductMessage(), expectedMessage);
		searchPage.selectSearchInSubCategoriesCheckboxField();
		searchPage.clickOnSearchButton();
		Thread.sleep(3000);
		Assert.assertTrue(searchPage.isProductCategoryDisplayedInSearchResults());
	}

	@Test(priority = 12)
	public void vefiyMultipleProductsInSearchResultInListAndGridView() {
		landingPage.enterProductNameIntoSearchTextBox(prop.getProperty("existingProductCategory"));
		searchPage = landingPage.clickOnSearchButton();
		searchPage.clickOnListViewButton();
		Assert.assertTrue(searchPage.verifyNoOfProductsDisplayedInSearchResults() >1);
		searchPage.clickOnGridViewButton();
		Assert.assertTrue(searchPage.verifyNoOfProductsDisplayedInSearchResults() >1);
	}
	
	@Test(priority=13)
	public void verifyNavigationToProductCompagePage() {
		headerOptions = new HeaderOptions(driver);
		headerOptions.enterProductNameIntoSearchTextBox(prop.getProperty("existingProductCategory"));
		searchPage = landingPage.clickOnSearchButton();
		productComparisionPage=searchPage.selectProductCompareLink();
		Assert.assertTrue(productComparisionPage.didWeNavigateOnProductComparisionPage());
	}
	
	@Test(priority=14)
	public void verifyShortingProductsInSearchResultPage() {
		headerOptions = new HeaderOptions(driver);
		headerOptions.enterProductNameIntoSearchTextBox(prop.getProperty("existingProductCategory"));
		searchPage = landingPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.verifyNoOfProductsDisplayedInSearchResults() >1);
		searchPage.selectOptionInSortByDropdownField(null, browserName);
	}
}
