@Search
Feature: Verify search
  Search for different countries
  
  Background:
  Given I open browser
  And navigate to application
  
  @tag1
  Scenario: search results
    And I am on "Home" page
    And the title is "Wikipedia"
    When I enter "uncommon word" in the "searchTextBox"
    And I select "English" from dropdown "searchLanguage"
    And I click on "searchButton"
    Then I am on "SearchResults" page
    And "firstResult" is displayed
    And text of "totalResults" is "5,499"
    And I take screenshot

#    When I select option 2 from dropdown "searchLanguage"
#//checkbox
#    //select
#  cssvalue
#  attribute