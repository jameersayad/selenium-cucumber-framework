package com.example.app;

import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

public class CommonActions {
	private static Map<String, Map<String, String>> repository;
	private static Map<String, String> currentPagelocators;

	public static void loadRepository() {
		Yaml yaml = new Yaml();
		InputStream inputStream = CommonActions.class.getClassLoader().getResourceAsStream("repo.yaml");
		repository = yaml.load(inputStream);
	}

	public static void loadCurrentPageLocators(String page) {
		currentPagelocators = repository.get(page);
	}

	public static By getXpath(String field){
		return By.xpath(currentPagelocators.get(field));
	}
	public static void navigateTo(String url) {
		DriverManager.getDriver().get(url);
		maximizeApplication();
	}

	public static void closeApplication() {
		DriverManager.getDriver().close();
		DriverManager.getDriver().quit();
	}

	public static void maximizeApplication() {
		DriverManager.getDriver().manage().window().maximize();
	}

	public static byte[] takeScreenshot() {
		TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
		return takesScreenshot.getScreenshotAs(OutputType.BYTES);
	}


	private static WebElement findElement(String field) {
		return DriverManager.getDriver().findElement(getXpath(field));
	}

	private static WebElement findElement(By xpath) {
		return DriverManager.getDriver().findElement(xpath);
	}

	private static List<WebElement> findElements(By xpath) {
		// TODO
//		waitUntilExpectedCondition(ExpectedConditions.numberOfElementsToBeMoreThan(xpath,0));
		return DriverManager.getDriver().findElements(xpath);
	}


	public static void click(String field) {
		waitUntilExpectedCondition(ExpectedConditions.elementToBeClickable(getXpath(field)));
		findElement(getXpath(field)).click();
	}

	public static void doubleClick() {
		// TODO
	}


	public static void enterText(String field, String text) {

		waitUntilExpectedCondition(ExpectedConditions.elementToBeClickable(getXpath(field)));
		findElement(getXpath(field)).sendKeys(text);
	}

	public static String getText(String field) {
		waitUntilExpectedCondition(ExpectedConditions.presenceOfElementLocated(getXpath(field)));
		return findElement(getXpath(field)).getText();
	}

	private static void waitUntilExpectedCondition(ExpectedCondition<WebElement> expectedCondition) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
		wait.until(expectedCondition);
	}

    public static void selectTextFromDropDown(String field, String text) {
		waitUntilExpectedCondition(ExpectedConditions.presenceOfElementLocated(getXpath(field)));
		Select select=new Select(findElement(getXpath(field)));
		select.selectByVisibleText(text);
    }

	public static void selectValueFromDropDown(String field, String value) {
		waitUntilExpectedCondition(ExpectedConditions.presenceOfElementLocated(getXpath(field)));
		Select select=new Select(findElement(getXpath(field)));
		select.selectByValue(value);
	}

	public static void selectIndexFromDropDown(String field, int index) {
		waitUntilExpectedCondition(ExpectedConditions.presenceOfElementLocated(getXpath(field)));
		Select select=new Select(findElement(getXpath(field)));
		select.selectByIndex(index);
	}

	public static void deselectAllFromDropDown(String field) {
		waitUntilExpectedCondition(ExpectedConditions.presenceOfElementLocated(getXpath(field)));
		Select select=new Select(findElement(getXpath(field)));
		select.deselectAll();
	}
	public static List<WebElement> getAllSelectedOptionsFromDropDown(String field) {
		waitUntilExpectedCondition(ExpectedConditions.visibilityOfElementLocated(getXpath(field)));
		Select select=new Select(findElement(getXpath(field)));
		return select.getAllSelectedOptions();
	}

	public static List<WebElement> getAllOptionsFromDropDown(String field) {
		waitUntilExpectedCondition(ExpectedConditions.visibilityOfElementLocated(getXpath(field)));
		Select select=new Select(findElement(getXpath(field)));
		return select.getOptions();
	}

	public static List<String> getTextOfAllElements(List<WebElement> elements) {
		List<String> elementsText= new ArrayList<>();
		for(WebElement element : elements){
			elementsText.add(element.getText());
		}
		return elementsText;
	}
    private void waitUntilExpectedCondition(ExpectedCondition<WebElement> expectedCondition, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeInSeconds));
		wait.until(expectedCondition);
	}

	public static boolean isElementDisplayed(String field) {
		try {
			return findElement(field).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementEnabled(String field) {
		try {
			waitUntilExpectedCondition(ExpectedConditions.visibilityOfElementLocated(getXpath(field)));
			return findElement(field).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementDisabled(String field) {
		try {
			waitUntilExpectedCondition(ExpectedConditions.visibilityOfElementLocated(getXpath(field)));
			return !findElement(field).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}
	public static String getTitle(){
		return DriverManager.getDriver().getTitle();
	}

	public static String getElementAttribute(String field, String attribute){
		return findElement(field).getAttribute(attribute);
	}
}
