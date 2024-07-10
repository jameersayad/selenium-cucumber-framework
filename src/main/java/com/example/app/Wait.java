package com.example.app;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The Wait class provides utility methods to wait for certain conditions 
 * using Selenium WebDriver.
 */
public class Wait {

    /**
     * Waits until jQuery calls are done using the default timeout specified in the configuration.
     *
     * @param driver the WebDriver instance
     */
    public static void untilJqueryIsDone(WebDriver driver){
        untilJqueryIsDone(driver, Duration.ofSeconds(10));
    }

    /**
     * Waits until jQuery calls are done using a specified timeout.
     *
     * @param driver the WebDriver instance
     * @param timeoutInSeconds the maximum time to wait in seconds
     */
    public static void untilJqueryIsDone(WebDriver driver, Duration timeoutInSeconds){
        until(driver, (d) -> {
            Boolean isJqueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
    }

    /**
     * Waits until the page load is complete using the default timeout specified in the configuration.
     *
     * @param driver the WebDriver instance
     */
    public static void untilPageLoadComplete(WebDriver driver) {
        untilPageLoadComplete(driver, 10L);
    }

    /**
     * Waits until the page load is complete using a specified timeout.
     *
     * @param driver the WebDriver instance
     * @param timeoutInSeconds the maximum time to wait in seconds
     */
    public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds){
        until(driver, (d) -> {
            Boolean isPageLoaded = (Boolean)((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) System.out.println("Document is loading");
            return isPageLoaded;
        }, Duration.ofSeconds(10));
    }

    /**
     * Waits for a specified condition to be true using the default timeout specified in the configuration.
     *
     * @param driver the WebDriver instance
     * @param waitCondition the condition to wait for
     */
    public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition){
        until(driver, waitCondition, Duration.ofSeconds(10));
    }

    /**
     * Waits for a specified condition to be true using a specified timeout.
     *
     * @param driver the WebDriver instance
     * @param waitCondition the condition to wait for
     * @param timeoutInSeconds the maximum time to wait in seconds
     */
    private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Duration timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(timeoutInSeconds);
        try{
            webDriverWait.until(waitCondition);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }  
}
