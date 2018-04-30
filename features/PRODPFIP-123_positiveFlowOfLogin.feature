Feature: PRODPFIP-123 Authenticate to the ShopMe website
  As a registered user I want to be able to successful sign in to the ShopMe website

  Background:
    Given I should be registered user in database
        | login1@test.pl | Abcdefg1 | Jan | Kowalski |
    And I go to ShopMe main page
    And I am not authenticated

  Scenario Outline: Positive flow of authentication
    When I click Sign in button
    And I enter an email "<email>" into the email field
    And I enter a password "<password>" into the password field
    And I click the login button
    Then I should see ShopMe main page
    And I am an authenticated user
    And I can see information that I am signed in "Zalogowano: <first_name> <last_name>"
    And I can see the Log out button

    Examples: 
        | email          | password     |first_name|last_name|
        | login1@test.pl | Abcdefg1     |Jan       |Kowalski |
