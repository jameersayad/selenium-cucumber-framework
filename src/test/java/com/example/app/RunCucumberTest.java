package com.example.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@search",
features = "src/test/resources/features",
		plugin = {"pretty", "html:target/cucumber.html"},
		snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunCucumberTest {
}