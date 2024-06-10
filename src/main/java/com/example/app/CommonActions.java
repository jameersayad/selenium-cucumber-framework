package com.example.app;

import java.io.InputStream;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

	public static void navigateTo(String url) {
		DriverManager.getDriver().navigate().to(url);
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
		// TODO
		TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
		return takesScreenshot.getScreenshotAs(OutputType.BYTES);
	}

	private static WebElement findElement(String xpath) {
		return DriverManager.getDriver().findElement(By.xpath(currentPagelocators.get(xpath)));
	}

	private static WebElement findElement(By xpath) {
		return DriverManager.getDriver().findElement(xpath);
	}

	public static void click(String xpath) {
		waitUntilExpectedCondition(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		findElement(xpath).click();
	}

	public static void doubleClick() {
		// TODO
	}

	public static void enterText(String xpath, String text) {
		waitUntilExpectedCondition(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		findElement(xpath).sendKeys(text);
	}

	private String getText(String xpath) {
		this.waitUntilExpectedCondition(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return this.findElement(xpath).getText();
	}

	private static void waitUntilExpectedCondition(ExpectedCondition<WebElement> expectedCondition) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
		wait.until(expectedCondition);
	}

	private void waitUntilExpectedCondition(ExpectedCondition<WebElement> expectedCondition, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeInSeconds));
		wait.until(expectedCondition);
	}

	public static boolean isElementDisplayed(String xpath) {
		try {
			return findElement(xpath).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
