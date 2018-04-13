Feature: PRODPFIP-122 User registration - existing email
  As a user I will not be able to register new account with e-mail address that was already used to create account.

  Background:
    Given I go to ShopMe main page
    And I push Login button
    And I can see registration form
    And Email "registration@test.com" used in registration is already in database

  Scenario: Singing up ShopMe website with invalid credentials
    When I fill in all necessary registration data with "Adam", "Jensen", "registration@test.com"
    And I push Register button
    Then I can see registration form
    And I can see an error message "Użytkownik o podanym adresie e-mail już istnieje"