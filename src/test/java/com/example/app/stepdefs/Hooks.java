package com.example.app.stepdefs;

import com.example.app.CommonActions;
import com.example.app.DriverManager;
import com.example.app.TestContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

public class Hooks {

	@BeforeAll
	public static void beforeAll() {
		TestContext.getTestContext();
		CommonActions.loadRepository();
	}

	@Before
	public void before(Scenario scenario) {
		TestContext.getTestContext().setScenario(scenario);
	}

	@After
	public void after(Scenario scenario) {
		if (!scenario.getStatus().equals(Status.PASSED)) {
			CommonActions.takeScreenshot();
		}
		CommonActions.closeApplication();
	}

}
