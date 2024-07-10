---

# Selenium-Cucumber BDD Framework with Gherkin Reusable Steps

## Introduction

Welcome to the Open Source Selenium-java and Cucumber Test Automation Framework! This project is designed to help test automation engineers quickly automate web UI applications across multiple browsers, including Chrome, Microsoft Edge, and Firefox.

## Features

- **Cross-Browser Testing**: Supports automation on Chrome, Microsoft Edge, and Firefox.
- **Ready-Made Boilerplate Code**: Comes with pre-configured Selenium, Java, and Cucumber setups.
- **Reusable Steps**: Includes a library of reusable steps to simplify writing new test scenarios.
- **Easy to Start**: Enables test automation engineers to begin automation from day one with minimal setup.
- **Scalable and Maintainable**: Designed with best practices to ensure the framework is scalable and maintainable.

## Prerequisites

Before running the tests, ensure that the following prerequisites are met:

- **Java Development Kit (JDK):** Install JDK 8 or higher.
- **Maven:** Ensure Maven is installed on your system.
- **IDE (Integrated Development Environment):** Recommended IDEs include IntelliJ IDEA, Eclipse, or Visual Studio Code.
- **Web Browser:** Chrome, Firefox and MSedge. 

## Getting Started

To get started with the project, follow these steps:

1. **Clone the Repository:**
   ```
   git clone https://github.com/jameersayad/selenium-cucumber-framework.git
   ```

2. **Navigate to Project Directory:**
   ```
   cd selenium-cucumber-framework
   ```

3. **Install Dependencies:**
   ```
   mvn clean install
   ```

4. **Run Tests:**
   ```
   mvn test
   ```

## Project Structure

- **`src/test/java`:** Contains Java source files including step definitions and utility classes.
  - **`stepDefinitions`:** Contains step definition classes implementing reusable steps.
  - **`utils`:** Contains utility classes for common functionalities.
- **`src/test/resources`:** Contains feature files written in Gherkin syntax.
  - **`features`:** Contains `.feature` files defining test scenarios.
- **`pom.xml`:** Maven project configuration file specifying dependencies and build settings.

## Writing Feature Files

Feature files are written in Gherkin syntax and are located in the `src/test/resources/features` directory. Each feature file represents a specific feature of the application and contains scenarios with given, when, and then steps.

Example Feature File (`login.feature`):
```gherkin
@Search
Feature: Wikipedia Search Functionality

  Background:
    Given I open browser
    And navigate to application

  @tag1
  Scenario: Search for a existing term
    And I am on "Home" page
    And the title is "Wikipedia"
    When I enter "Selenium" in the "searchTextBox"
    And I select "English" from dropdown "searchLanguage"
    And I click on "searchButton"
    Then I am on "Content" page
    And text of "header" is "Selenium"
    And I take screenshot

  @tag2
  Scenario: Search for a non-existing term
    And I am on "Home" page
    And the title is "Wikipedia"
    When I enter "abcxyz123" in the "searchTextBox"
    And I select "English" from dropdown "searchLanguage"
    And I click on "searchButton"
    Then I am on "SearchResults" page
    And "firstResult" is not displayed
    And text of "searchResults" contains "There were no results matching the query."
    And I take screenshot

  @tag3
  Scenario: Search for a generic term
    And I am on "Home" page
    And the title is "Wikipedia"
    When I enter "uncommon word" in the "searchTextBox"
    And I select "English" from dropdown "searchLanguage"
    And I click on "searchButton"
    Then I am on "SearchResults" page
    And "firstResult" is displayed
    And text of "totalResults" is "5,499"
    And I take screenshot
```
## Maintainers

This project is maintained by:

- [Jameer](https://github.com/jameersayad)
- [Rameez](https://github.com/rameezhazra2022)
- [Abhishek](https://github.com/myofficework000)

## Contributors

Thanks to all the contributors who have helped make this project better!

[![Contributors](https://contrib.rocks/image?repo=jameersayad/selenium-cucumber-framework)](https://github.com/jameersayad/selenium-cucumber-framework/graphs/contributors)

You can see the full list of contributors [here](https://github.com/jameersayad/selenium-cucumber-framework/graphs/contributors).


## Contributing

Contributions to this project are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to customize this README according to your project's specific requirements and conventions. Happy testing!
