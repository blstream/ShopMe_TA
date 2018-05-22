@inProgress @workingGood
Feature: PRODPFIP-123 Authenticate to the ShopMe website
  As a registered user I want to be able to successful sign in to the ShopMe website

  Background:
    Given I am a registered user in database with email "john.doe@gmail.com"
    And I go to ShopMe main page
    And I am not authenticated

  Scenario Outline: Positive flow of authentication
    When I push Login button
    And I enter an email "<email>" into the email field
    And I enter a password "<password>" into the password field
    And I click the Sign in button
    Then I should see ShopMe main page
    And I can see information that I am signed in as "<user_name>"
    And I can see the Log out button

    Examples:
      | email              | password     | user_name |
      | john.doe@gmail.com | Password1234 | John Doe  |
