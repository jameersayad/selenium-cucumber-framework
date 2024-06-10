package com.example.app.stepdefs;

import org.junit.Assert;

import com.example.app.CommonActions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonUISteps {
	@Given("I am on {string} page")
	public void i_am_on_page(String page) {
		CommonActions.loadCurrentPageLocators(String.format("%sPage", page));
		Assert.assertTrue(page, CommonActions.isElementDisplayed("header"));
	}

	@When("I enter {string} in the {string} field")
	public void i_enter_in_the_search_field(String text, String field) {
		CommonActions.enterText(field, text);
	}

	@When("I click on {string} button")
	public void i_click_on_button(String button) {
		CommonActions.click(button);
	}
}
