Feature: PRODPFIP-123 Authenticate to the ShopMe website
  As a registered user I want to be able to successful log in to the ShopMe

  Background:
    Given the following users are register
        | login1@test.pl | Abcdefg1 | Imie | Nazwisko |

  Scenario Outline: Positive flow of authentication
    Given I navigate to the main page
    When I click button to log in
    And I enter an email "<email>" into the email field
    And I enter a password "<password>" into the password field
    And I click the login button
    Then I am on the main page
    And I am an authenticated user
    And I can see information that I am logged in "Zalogowano: <first_name> <last_name>"
    And I can see the logout button

    Examples: 
        | email          | password     |first_name|last_name|
        | login1@test.pl | Abcdefg1     |Imie      |Nazwisko |
