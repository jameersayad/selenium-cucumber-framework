package com.example.app;

public class ScenarioContext {
	private static ScenarioContext scenarioContext;

	public static ScenarioContext getScenarioContext(){
		if(scenarioContext == null) {
			scenarioContext = new ScenarioContext();
		}
		return scenarioContext;
	}
}
