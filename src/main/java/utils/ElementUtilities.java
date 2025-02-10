package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtilities {

	WebDriver driver;

	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnElement(WebElement element) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			element.click();
		}
	}

	public String getElementText(WebElement element) {
		String elementText = "";
		if (isElementDisplayedOnPage(element)) {
			elementText = element.getText();
		}
		return elementText;
	}

	

	public boolean isElementDisplayedOnPage(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (TimeoutException | NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isElementEnabled(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isEnabled();
		} catch (TimeoutException | NoSuchElementException e) {
			return false;
		}
	}

	public String getElementDomAttribute(WebElement element, String attributeName) {
		return element.getDomAttribute(attributeName);
	}

	public String getElementDomProperty(WebElement element, String attributeName) {
		return element.getDomProperty(attributeName);
	}

	public boolean isElementSelected(WebElement element) {
		boolean b = false;
		if (isElementDisplayedOnPage(element)) {
			b = element.isSelected();
		}
		return b;
	}
	public String getTextOfElement(WebElement element) {
		String searchResultMessage = null;
		try {
			searchResultMessage = element.getText();
		} catch (NoSuchElementException e) {
			searchResultMessage = null;
			e.printStackTrace();
		}
		return searchResultMessage;
	}

	public String getElementCSSValue(WebElement element, String cssPropertyName) {
		String value = "";
		value = element.getCssValue(cssPropertyName);
		return value;
	}

	public void clearTextFromElement(WebElement element) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			element.clear();
		}
	}

	public void enterTextIntoElement(WebElement element, String text) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			clearTextFromElement(element);
			element.sendKeys(text);
		}
	}
	public int getElementCount(List<WebElement> elements) {
		int n = 0;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			return elements.size();
		} catch (TimeoutException | NoSuchElementException e) {
			return 0;
		}
	}
	public String getDomAttributeOfElement(WebElement element, String attribute) {
		String text = null;
		try {
			text = element.getDomAttribute(attribute);
		} catch (NoSuchElementException e) {
			text = null;
		} catch (Exception e) {
			text = null;
		}
		return text;
	}
	
	public void selectOptionInDropDownFieldUsingOptionText(WebElement element, String optionText) {
		if(isElementDisplayedOnPage(element) && isElementEnabled(element)) {
			Select se = new Select(element);
			se.selectByVisibleText(optionText);
		}
	}
}
