@workingGood
Feature: PRODPFIP-20 Adding new service - alternative flow
  As a user I want to be able to add new service so that can be found by potential clients

  Background:
    Given I go to ShopMe main page
    And I am an signed in to the application with email "john.doe@gmail.com" and password "Password1234"
    And I push add service button
    And I can see adding form

  Scenario Outline: Adding new basic service with at least one empty required field
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in basicDescription with "<basic_description>"
    And I fill in basicPrice with "<basic_price>"
    And I fill in province with "Zachodniopomorskie"
    And I fill in city with "Szczecin"
    And I press Add service button with fail
    Then I should see an error message "Pole wymagane" next to the required field
    And I should see a message "Proszę wypełnić wszystkie wymagane pola"
    And I should see inserted values in filled fields

    Examples:
      | title  | category | basic_price | basic_description |
      | Budowa | Budowa   |             | description       |
      | Budowa | Budowa   | 10          |                   |
      |        | Budowa   | 10          | description       |
      | Budowa |          | 10          | description       |

  Scenario Outline: Adding new service with extensions with at least one empty required field
    When I fill in title with "Test title"
    And I choose category "Budowa"
    And I fill in basicDescription with "description"
    And I fill in basicPrice with "10"
    And I fill in expandedDescription with "<expanded_description>"
    And I fill in expandedPrice with "<expanded_price>"
    And I fill in extraDescription with "<extra_description>"
    And I fill in extraPrice with "<extra_price>"
    And I fill in province with "Zachodniopomorskie"
    And I fill in city with "Szczecin"
    And I press Add service button
    Then I should see an error message "Pole wymagane" next to the required field
    And I should see a message "Proszę wypełnić wszystkie wymagane pola"
    And I should see inserted values in filled fields

    Examples:
      | expanded_price | expanded_description | extra_description | extra_price |
      | 20             | description          |                   | 20          |
      |                | description          |                   |             |
      | 20             | description          | description       |             |
      | 20             |                      |                   |             |

  Scenario Outline: Adding new service with invalid credentials
    When I fill in title with "<title>"
    And I choose category "Transport"
    And I fill in basicDescription with "description"
    And I fill in basicPrice with "10"
    And I fill in province with "Zachodniopomorskie"
    And I fill in city with "Szczecin"
    And I press Add service button
    Then I should see inserted values in filled fields
    And I should see an error message "<error_message>" next to the field with invalid data

    Examples:
      | title     | error_message           |
      | t         | Zbyt mała liczba znaków |
      | Transpor! | Niedozwolone znaki      |
      | Transpor@ | Niedozwolone znaki      |
      | Transpor# | Niedozwolone znaki      |
      | Transpor$ | Niedozwolone znaki      |
      | Transpor% | Niedozwolone znaki      |
      | Transpor^ | Niedozwolone znaki      |
      | Transpor& | Niedozwolone znaki      |
      | Transpor* | Niedozwolone znaki      |
      | Transpor( | Niedozwolone znaki      |
      | Transpor) | Niedozwolone znaki      |
      | Transpor_ | Niedozwolone znaki      |
      | Transpor+ | Niedozwolone znaki      |
      | Transpor= | Niedozwolone znaki      |
      | Transpor~ | Niedozwolone znaki      |
      | Transpor` | Niedozwolone znaki      |
      | Transpor! | Niedozwolone znaki      |
      | Transpor! | Niedozwolone znaki      |


  Scenario: Adding new basic service with 501 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with 501 characters
    Then I should see in basic_description maximum 500 characters

  Scenario: Adding new service with extensions with 501 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with "description"
    And I fill in expandedDescription with 501 characters
    And I fill in expandedPrice with "300"
    And I fill in extraDescription with 501 characters
    And I fill in extraPrice with "400"
    Then I should see in expanded_description maximum 500 characters
    And I should see in extra_description maximum 500 characters

  Scenario: Inserting more than maximum number of characters in form fields
    When I fill in title with "Loremipsumdolorsitametturpisdui"
    And I choose category "Prawo"
    And I fill in basicPrice with "100,002"
    And I fill in basicDescription with "description"
    Then I should see in title maximum 30 characters
    And I should see in basic_price maximum 2 decimal places

  Scenario: Attempt to fill the fields of expanded service without filling basic service fields
    When I fill in basicDescription with ""
    And I fill in basicPrice with ""
    Then I should see that expanded_description and expanded_price fields are blocked

  Scenario: Attempt to fill the fields of extra service without filling expanded service fields
    When I fill in basicDescription with "description"
    And I fill in basicPrice with "50"
    And I fill in expandedDescription with ""
    And I fill in expandedPrice with ""
    Then I should see that extra_description and extra_price fields are blocked
