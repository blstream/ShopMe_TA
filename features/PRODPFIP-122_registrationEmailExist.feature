@workingGood
Feature: PRODPFIP-122 User registration - using email already existing in database
  As a user I will not be able to register new account with e-mail address that was already used to create account.

  Background:
    Given I go to ShopMe main page
    And I push Login button
    And I push SignUp button
    And I can see registration form
    And Email "john.doe@gmail.com" used in registration is already in database

  Scenario: Signing up ShopMe website with email already existing in database
    When I fill in all necessary registration data with "Adam", "Jensen", "john.doe@gmail.com"
    And I push Register button with fail
    Then I can see inserted values in filled fields
    And I can see an email error message "Użytkownik o podanym adresie e-mail już istnieje"
