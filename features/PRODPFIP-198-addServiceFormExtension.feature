@inProgress
Feature: PRODPFIP-198 Adding new service - extension with province and city of the add service form
  As a user I want to be able to add new service so that can be found by potential clients

  Background:
    Given I go to ShopMe main page
    And I push add service button
    And I can see adding form
    And I fill in all necessary data
    # | title          | category | basic_description | basic_price | name | email          | phone     |
      | Oferta testowa | Inne     | Opis testowy      | 100         | Jan  | test@email.com | 100200300 |

  Scenario Outline: Adding new basic service with new required fields - positive flow
    Given I can see city field disabled
    When I fill in province with "<province>"
    And I fill in city with "<city>"
    And I press Add service button
    Then I should see confirmation message "Pomyślnie dodano ofertę"
    And I am redirected to the main page

    Examples:
      | province            | city                           |
      | Dolnośląskie        | Wrocław                        |
      | Kujawsko-pomorskie  | Toruń                          |
      | Lubelskie           | Lublin                         |
      | Lubuskie            | Zielona Góra                   |
      | Łódzkie             | Łódź                           |
      | Zachodniopomorskie  | Szczecin                       |
      | Małopolskie         | Kraków                         |
      | Mazowieckie         | Warszawa                       |
      | Opolskie            | Opole                          |
      | Podkarpackie        | Rzeszów                        |
      | Podlaskie           | Białystok                      |
      | Pomorskie           | Gdańsk                         |
      | Śląskie             | Katowice                       |
      | Świętokrzyskie      | Kielce                         |
      | Warmińsko-mazurskie | Olsztyn                        |
      | Wielkopolskie       | Poznań                         |
      | Wielkopolskie       | TestMaksymalnejLiczbyZnakowwww |
      | Śląskie             | Bielsko-Biała                  |

  Scenario: Adding new basic service without selected province
    When I press Add service button
    Then I should see an error message "Pole wymagane" next to the province and city field

  Scenario Outline: Adding new service with invalid data in city field
    When I fill in province with "Mazowieckie"
    And I fill in city with "<city>"
    And I press Add service button
    Then I should see an error message "<error_message>" next to the field with invalid data
    Examples:
      | city    | error_message      |
      | 3Miasto | Niedozwolone znaki |
      | Test!   | Niedozwolone znaki |
      | Test?   | Niedozwolone znaki |
      | Test@   | Niedozwolone znaki |
      | Test#   | Niedozwolone znaki |
      | Test%   | Niedozwolone znaki |
      | Test^   | Niedozwolone znaki |
      | Test&   | Niedozwolone znaki |
      | Test*   | Niedozwolone znaki |
      | Test(   | Niedozwolone znaki |
      | Test)   | Niedozwolone znaki |
      | Test+   | Niedozwolone znaki |
      | Test=   | Niedozwolone znaki |
      | Test_   | Niedozwolone znaki |
      | Test`   | Niedozwolone znaki |
      | Test-   | Niedozwolone znaki |
      |         | Pole wymagane      |

  Scenario: Inserting more than maximum number of characters in city field
    When I fill in province with "Mazowieckie"
    And I fill in city with 31 characters
    Then I should see in city maximum 30 characters

