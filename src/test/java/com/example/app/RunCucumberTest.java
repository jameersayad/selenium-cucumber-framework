package com.example.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@tag1",
features = "src/test/resources/features",
		plugin = {"pretty", "html:target/cucumber.html"})
public class RunCucumberTest {
}