@workingGood
Feature: PRODPFIP-48 Navigation to the main page
  As a user I want to be able use ShopMe button to return to the main page

  Background:
    Given I go to ShopMe main page

  Scenario: Returning to the main page from search result page
    When I enter a searching phrase "test" into the search field
    And I click the search button
    And search results are visible
    And I click ShopMe button
    Then I should see ShopMe main page

  Scenario: Returning to the main page from add service page
    Given I am an signed in to the application with email "john.doe@gmail.com" and password "Password1234"
    When I push add service button
    And I can see adding form
    And I click ShopMe button
    Then I should see ShopMe main page

  Scenario: Returning to the main page from service profile page
    When I enter a searching phrase "test" into the search field
    And I click the search button
    And search results are visible
    And I can click on the first search result to see the details
    And I click ShopMe button
    Then I should see ShopMe main page

  Scenario: Returning to the main page from login page
    When I push Login button
    And I can see login form
    And I click ShopMe button
    Then I should see ShopMe main page

  Scenario: Returning to the main page from registration page
    When I push Login button
    And I push SignUp button
    And I fill in all necessary registration data with testEmail, "Test", "Test",
    And I push Register button
    And I can see expanded registration form
    And I click ShopMe button
    Then I should see ShopMe main page
