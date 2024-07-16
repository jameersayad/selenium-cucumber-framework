package com.example.app;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (null == driver) {
            driver = createDriver();
        }
        return driver;
    }

    public static void setDriver(WebDriver webdriver) {
        driver = webdriver;
    }

    private static WebDriver createDriver() {
        return createDriver("chrome");
    }

    private static WebDriver createDriver(String browser) {
        var downloadDir = Paths.get("target").toAbsolutePath().toString();

        /**CHROME/Edge Options */
        // Create a map to set preferences
        Map<String, Object> prefs = new HashMap<>();
        TestContext.getTestContext().getApplicationProperty("");
        prefs.put("download.default_directory", downloadDir); // Change the path to your desired download directory
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("plugins.always_open_pdf_externally", true); // Disable the built-in PDF viewer

        // Apply the preferences to ChromeOptions
        ChromiumOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        /** FirefoxOptions */
        // Create a new profile
        FirefoxProfile profile = new FirefoxProfile();

        // Set preferences for the profile
        profile.setPreference("browser.download.dir", downloadDir);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf"); // MIME type
        profile.setPreference("pdfjs.disabled", true); // Disable the built-in PDF viewer

        // Apply the profile to FirefoxOptions
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);

        /** EdgeOptions */

        driver = switch (browser) {
            case "firefox" -> new FirefoxDriver(firefoxOptions);
            case "edge" -> new EdgeDriver((EdgeOptions) options);
            default -> new ChromeDriver((ChromeOptions) options);
        };
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
