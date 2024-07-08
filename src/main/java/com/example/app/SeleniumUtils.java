package com.example.app;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SeleniumUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public SeleniumUtils(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    // Click an element
    public void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Enter text in a text box
    public void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    // Wait for an element to be visible
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Get text from an element
    public String getElementText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Get a list of elements
    public List<WebElement> getElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // Check if element is present
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Wait for an element to be clickable
    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Select a checkbox if not already selected
    public void selectCheckbox(By locator) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    // Deselect a checkbox if selected
    public void deselectCheckbox(By locator) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    // Switch to frame by locator
    public void switchToFrame(By locator) {
        driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    // Switch to default content
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
