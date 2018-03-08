Feature: As a signed in user I want to be able to make an order

  Background:
    Given I am not signed in
    And I go to sign in form

  @smokeTest @makeOrder
  Scenario: I make an order in application
    Given Account with credentials "jerry.doe@example.com" is already registered
    When I enter login with "jerry.doe@example.com" and password with "jerrydoe"
    And I choose item to buy
    And I am ordering choosed item
    Then New order is visible in order history


