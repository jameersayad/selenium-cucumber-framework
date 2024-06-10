package com.example.app.stepdefs;

import com.example.app.DriverManager;
import com.example.app.TestContext;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import com.example.app.CommonActions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

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
        CommonActions.loadCurrentPageLocators(String.format("%sPage", page));
        Assert.assertTrue(page, CommonActions.isElementDisplayed("header"));
    }

    @Given("the title is {string}")
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
}
