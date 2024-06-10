@Search
Feature: Verify search
  Search for different countries
  
  Background:
  #Given I launch application
  
  @tag1
  Scenario: search results
    Given I am on "Home" page
    When I enter "transformation" in the "search" field
    And I click on "search" button
    Then I am on "results" page
    And I see search results
