package com.example.app.stepdefs;

import com.example.app.CommonActions;
import com.example.app.DriverManager;
import com.example.app.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CommonUISteps {

    @Given("I open browser")
    public void iOpenBrowser() {
        DriverManager.getDriver();
    }

    @Given("navigate to application")
    public void navigateToApplication() {
        CommonActions.navigateTo(TestContext.getTestContext().getApplicationProperty("app.url"));
    }

    @Given("I am on {string} page")
    public void i_am_on_page(String page) {
        CommonActions.loadCurrentPageLocators(page);
        Assert.assertTrue(page, CommonActions.isElementDisplayed("header"));
    }

    @Then("the title is {string}")
    public void theTitleIs(String title) {
        Assert.assertEquals("Title is not as expected", title, CommonActions.getTitle());
    }

    @When("I enter {string} in the {string}")
    public void iEnterInThe(String text, String field) {
        CommonActions.enterText(field, text);
    }

    @When("I click on {string}")
    public void iClickOn(String field) {
        CommonActions.click(field);
    }

    @Then("{string} is displayed")
    public void isDisplayed(String field) {
        Assert.assertTrue(field + " is not displayed", CommonActions.isElementDisplayed(field));
    }

    @Then("{string} is not displayed")
    public void isNotDisplayed(String field) {
        Assert.assertFalse(field + " is displayed", CommonActions.isElementDisplayed(field));
    }

    @Then("{string} is enabled")
    public void isEnabled(String field) {
        Assert.assertTrue(field + " is not enabled", CommonActions.isElementEnabled(field));
    }

    @Then("{string} is disabled")
    public void isDisabled(String field) {
        Assert.assertTrue(field + " is not disabled", CommonActions.isElementEnabled(field));
    }

    @Then("text of {string} is {string}")
    public void textOfFieldIs(String field, String expected) {
        Assert.assertEquals("Text is not expected", expected, CommonActions.getText(field));
    }

    @Then("I take screenshot")
    public void iTakeScreenshot() {
        byte[] screenshot = CommonActions.takeScreenshot();
        TestContext.getTestContext().getScenario().attach(screenshot, "image/png", "screenshot");
    }

    @When("I select {string} from dropdown {string}")
    public void iSelectTextFromDropDown(String value, String field) {
        CommonActions.selectTextFromDropDown(field, value);
    }

    @When("I select option {int} from dropdown {string}")
    public void iSelectOptionFromTheDropdown(int option, String field) {
        CommonActions.selectIndexFromDropDown(field, option);
    }

    @When("I select value {string} from dropdown {string}")
    public void iSelectValueFromDropDown(String value, String field) {
        CommonActions.selectValueFromDropDown(field, value);
    }

    @Then("file {string} is downloaded")
    public void assertfileIsDownloaded(String file) {
        Assert.assertNotEquals("File is not downloaded :" + file, null, CommonActions.getRecentDownloadedFile());
    }

    @And("text of {string} contains {string}")
    public void textOfContains(String field, String expected) {
        Assert.assertTrue("Text is not expected", CommonActions.getText(field).contains(expected));
    }
}
