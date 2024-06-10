package com.example.app;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ReadYAML {

	public static void main(String[] args) {
		ReadYAML yaml= new ReadYAML();
		yaml.readYamlFile();
	}

	private  void readYamlFile() {
		Yaml yaml = new Yaml();
		InputStream inputStream = this.getClass()
		  .getClassLoader()
		  .getResourceAsStream("repo.yaml");
		Map<String, Object> obj = yaml.load(inputStream);
		System.out.println(obj);
		Map<String, Object> obj2=(Map<String, Object>) obj.get("HomePage");
		System.out.println(obj2.get("header"));
	}

}
