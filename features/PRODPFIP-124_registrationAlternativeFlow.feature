@workingGood
Feature: PRODPFIP-124 User registration - alternative flow
  As a user I will not be able to register new account with invalid credentials.

  Background:
    Given I go to ShopMe main page
    And I push Login button
    And I push SignUp button
    And I can see registration form

  Scenario Outline: Signing up ShopMe website with invalid credentials
    When I fill in all necessary registration data with "<name>", "<surname>", "<email>"
    And I push Register button with fail
    Then I can see inserted values in filled fields
    And I can see an error message "<error_message>"
    And I can see a message "Proszę uzupełnić wymagane pola"

    Examples:
      | name | surname | email                 | error_message                   |
      | John | Doe     | @test.com             | Podaj poprawny adres e-mail     |
      | John | Doe     | registrationtest      | Podaj poprawny adres e-mail     |
      | John | Doe     | registration@com      | Podaj poprawny adres e-mail     |
      | Joh! | Doe     | registration@test.com | Pole może zawierać tylko litery |
      | Joh@ | Doe     | registration@test.com | Pole może zawierać tylko litery |
      | Joh% | Doe     | registration@test.com | Pole może zawierać tylko litery |
      | Joh* | Doe     | registration@test.com | Pole może zawierać tylko litery |
      | Joh- | Doe     | registration@test.com | Pole może zawierać tylko litery |
      | Joh_ | Doe     | registration@test.com | Pole może zawierać tylko litery |
      | John | Do&     | registration@test.com | Niedozwolone znaki              |
      | John | Do^     | registration@test.com | Niedozwolone znaki              |
      | John | Do$     | registration@test.com | Niedozwolone znaki              |
      | John | Do<     | registration@test.com | Niedozwolone znaki              |
      | John | Do?     | registration@test.com | Niedozwolone znaki              |
      | John | Do/     | registration@test.com | Niedozwolone znaki              |
      | John | Do\     | registration@test.com | Niedozwolone znaki              |
      | Jo   | Doe     | registration@test.com | Zbyt mała liczba znaków         |
      | John | D       | registration@test.com | Zbyt mała liczba znaków         |

  Scenario Outline: Signing up ShopMe website with empty fields
    When I fill in all necessary registration data with "<name>", "<surname>", "<email>"
    And I push Register button
    Then I can see inserted values in filled fields
    And I can see an error message "<error_message>"

    Examples:
      | name | surname | email                 | error_message |
      | John | Doe     |                       | Pole wymagane |
      | John |         | registration@test.com | Pole wymagane |
      |      | Doe     | registration@test.com | Pole wymagane |

  Scenario Outline: Inserting more than maximum number of characters in registration form
    When I fill in all necessary registration data with "<name>", "<surname>", "<email>"
    Then I should see in name maximum 20 characters
    And I should see in surname maximum 50 characters

    Examples:
      | name                  | surname                                             | email                 |
      | Loremipsumdolorsitame | Loremipsumdolorsitametconsecteturadipiscingelitsedd | registration@test.com |
