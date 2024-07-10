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
