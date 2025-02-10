package pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import root.RootPage;
import utils.ElementUtilities;

public class SearchPage extends RootPage {
	WebDriver driver;
	ElementUtilities elementUtilities;

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement searchBreadCrumb;

	@FindBy(name = "search")
	private WebElement searchTextBox;

	@FindBy(id = "button-search")
	private WebElement searchBtn;

	@FindBy(xpath = "//*[text()='Products meeting the search criteria']/following-sibling::p[1]")
	private WebElement searchResultText;

	@FindBy(linkText = "HP LP3065")
	private WebElement existingProduct;

	@FindBy(id = "input-search")
	private WebElement searchCriteriaField;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> numberOfProducts;

	@FindBy(id = "description")
	private WebElement searchInProductDescriptioncheckBoxField;

	@FindBy(linkText = "iMac")
	private WebElement iMacProduct;

	@FindBy(name = "category_id")
	private WebElement categoryDropDownField;

	@FindBy(name = "sub_category")
	private WebElement searchInSubCategoriesChekboxField;

	@FindBy(id = "list-view")
	private WebElement listViewbtn;

	@FindBy(id = "grid-view")
	private WebElement gridViewbtn;

	@FindBy(id = "compare-total")
	private WebElement productCompareLink;
	
	@FindBy(id = "input-sort")
	private WebElement shorByDropdownField;
	
	@FindBy(id = "input-limit")
	private WebElement shorByDropdownPagingField;
	
	public void selectOptionInSortByDropdownField(WebElement element,String optionText) {
		elementUtilities.selectOptionInDropDownFieldUsingOptionText(shorByDropdownField, optionText);
	}
	
	public void selectOptionInSortByDropdownPagingField(WebElement element,String optionText) {
		elementUtilities.selectOptionInDropDownFieldUsingOptionText(shorByDropdownPagingField, optionText);
	}

	public ProductComparisionPage selectProductCompareLink() {
		elementUtilities.clickOnElement(productCompareLink);
		return new ProductComparisionPage(driver);
	}

	public void clickOnListViewButton() {
		elementUtilities.clickOnElement(listViewbtn);
	}

	public void clickOnGridViewButton() {
		elementUtilities.clickOnElement(gridViewbtn);
	}

	public void selectSearchInSubCategoriesCheckboxField() {
		elementUtilities.clickOnElement(searchInSubCategoriesChekboxField);
	}

	public int verifyNoOfProductsDisplayedInSearchResults() {
		return elementUtilities.getElementCount(numberOfProducts);
	}

	public boolean didWeNavigateSearchPage() {
		return elementUtilities.isElementDisplayedOnPage(searchBreadCrumb);

	}

	public void enterProductNameIntoSearchTextBox(String ProductNameToSearch) {
		elementUtilities.clearTextFromElement(searchTextBox);
		elementUtilities.enterTextIntoElement(searchTextBox, ProductNameToSearch);
	}

	public void enterProductNameIntosearchCriteriaField(String ProductNameToSearch) {
		elementUtilities.clearTextFromElement(searchCriteriaField);
		elementUtilities.enterTextIntoElement(searchCriteriaField, ProductNameToSearch);
	}

	public void clickOnSearchButton() {
		elementUtilities.clickOnElement(searchBtn);
	}

	public String getNoSearchProductMessage() {
		return elementUtilities.getTextOfElement(searchResultText);

	}

	public boolean isExistingProductDisplayedOnSearchResults() {
		return elementUtilities.isElementDisplayedOnPage(existingProduct);
	}

	public String getPlaceHolderTextOfSearchCriteriaField() {
		return elementUtilities.getDomAttributeOfElement(searchCriteriaField, "placeholder");

	}

	public void selectSearchInProductDescriptioncheckBoxField() {
		elementUtilities.clickOnElement(searchInProductDescriptioncheckBoxField);
	}

	public boolean isProductHavingDescriptionTextDisplayedInSearchResults() {
		return elementUtilities.isElementDisplayedOnPage(iMacProduct);
	}

	public boolean isProductCategoryDisplayedInSearchResults() {
		return elementUtilities.isElementDisplayedOnPage(iMacProduct);

	}

	public void SelectOptionFromCategoryDropDownField(String optionText) {
		Select se = new Select(categoryDropDownField);
		se.selectByContainsVisibleText(optionText);
	}
}
