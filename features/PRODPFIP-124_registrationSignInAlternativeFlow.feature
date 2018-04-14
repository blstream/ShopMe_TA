Feature: PRODPFIP-124 User registration/signing in - alternative flow
  As a user I will not be able to register new account with e-mail address that was already used to create account.

  Background:
    Given I go to ShopMe main page
    And I push Login button
    And I can see registration form
    And Email "registration@test.com" used in registration is not in database

  Scenario Outline: Signing up ShopMe website with invalid credentials
    When I fill in all necessary registration data with "<name>", "<surname>", "<email>"
    And I push Register button
    Then I can see inserted values in filled fields
    And I can see an error message "<error_message>"

    Examples:
      | name | surname | email          | error_message            |
      | John | Doe     | @test.com      | Niepoprawny adres e-mail |
      | John | Doe     | testemail      | Niepoprawny adres e-mail |
      | John | Doe     | test@com       | Niepoprawny adres e-mail |
      | Joh! | Doe     | test@email.com | Niedozwolone znaki       |
      | Joh@ | Doe     | test@email.com | Niedozwolone znaki       |
      | Joh% | Doe     | test@email.com | Niedozwolone znaki       |
      | Joh* | Doe     | test@email.com | Niedozwolone znaki       |
      | Joh- | Doe     | test@email.com | Niedozwolone znaki       |
      | Joh_ | Doe     | test@email.com | Niedozwolone znaki       |
      | John | Do&     | test@email.com | Niedozwolone znaki       |
      | John | Do^     | test@email.com | Niedozwolone znaki       |
      | John | Do$     | test@email.com | Niedozwolone znaki       |
      | John | Do<     | test@email.com | Niedozwolone znaki       |
      | John | Do?     | test@email.com | Niedozwolone znaki       |
      | John | Do/     | test@email.com | Niedozwolone znaki       |
      | John | Do\     | test@email.com | Niedozwolone znaki       |
      | Jo   | Doe     | test@email.com | Zbyt mała liczba znaków  |
      | John | D       | test@email.com | Zbyt mała liczba znaków  |


  Scenario Outline: Signing in ShopMe website with invalid credentials
    When I fill in all necessary sign in data with "<email>", "<password>"
    And I push Sign in button
    Then I can see inserted values in filled fields
    And I can see an error message "<error_message>"

    Examples:
      | email            | password | error_message            |
      | @test.com        | passwd   | Niepoprawny adres e-mail |
      | testemail        | passwd   | Niepoprawny adres e-mail |
      | test@com         | passwd   | Niepoprawny adres e-mail |
      | example@test.com | tururu   | Niepoprawne hasło ?      |

  Scenario Outline: Signing in ShopMe website with empty fields
    When I fill in all necessary sign in data with "<email>", "<password>"
    And I push Sign in button
    Then I can see inserted values in filled fields
    And I can see an error message "<error_message>"

    Examples:
      | email          | password | error_message                  |
      | test@email.com |          | Proszę uzupełnić wymagane pola |
      |                | passwd   | Proszę uzupełnić wymagane pola |
      |                |          | Proszę uzupełnić wymagane pola |

  Scenario Outline: Signing up ShopMe website with empty fields
    When I fill in all necessary registration data with "<name>", "<surname>", "<email>"
    And I push Register button
    Then I can see inserted values in filled fields
    And I can see an error message "<error_message>"

    Examples:
      | name | surname | email          | error_message                  |
      | John | Doe     |                | Proszę uzupełnić wymagane pola |
      | John |         | test@email.com | Proszę uzupełnić wymagane pola |
      |      | Doe     | test@email.com | Proszę uzupełnić wymagane pola |
      |      |         |                | Proszę uzupełnić wymagane pola |