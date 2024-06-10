package com.example.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.yaml.snakeyaml.Yaml;

public class TestContext {
	private static TestContext testContext;
	private Properties properties;
	public static TestContext getTestContext(){
		if(testContext == null) {
			testContext = new TestContext();
		}
		return testContext;
	}
	
	public TestContext(){
		loadApplicationProperties();
	}
	
	private void loadApplicationProperties(){
		InputStream inputStream = this.getClass()
				  .getClassLoader()
				  .getResourceAsStream("application.properties");
		properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public String getApplicationProperty(String property){
		return properties.getProperty(property);
	}	
}
