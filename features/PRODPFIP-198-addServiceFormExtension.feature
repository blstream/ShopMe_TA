Feature: PRODPFIP-198 Adding new service - extension of the add service form
  As a user I want to be able to add new service so that can be found by potential clients

  Background:
    Given I go to ShopMe main page
    And I push add service button
    And I can see adding form
    And I fill in all necessary data
      | Oferta testowa | inne | Opis testowy | 100 | Jan | test@email.com | 100200300 |
    And  I can see city field disabled

  Scenario Outline: Adding new basic service with new required fields - positive flow
    When I fill in province with "<province>"
    And I fill in city with "<city>"
    And I press Add service button
    Then I should see confirmation message "Pomyślnie dodano ofertę"
    Examples:
      | province            | city                           |
      | dolnośląskie        | Wrocław                        |
      | kujawsko-pomorskie  | Toruń                          |
      | lubelskie           | Lublin                         |
      | lubuskie            | Zielona Góra                   |
      | łódzkie             | Łódź                           |
      | zachodniopomorskie  | Szczecin                       |
      | małopolskie         | Kraków                         |
      | mazowieckie         | Warszawa                       |
      | opolskie            | Opole                          |
      | podkarpackie        | Rzeszów                        |
      | podlaskie           | Białystok                      |
      | pomorskie           | Gdańsk                         |
      | śląskie             | Katowice                       |
      | świętokrzyskie      | Kielce                         |
      | warmińsko-mazurskie | Olsztyn                        |
      | wielkopolskie       | Poznań                         |
      | wielkopolskie       | TestMaksymalnejLiczbyZnakowwww |
      | śląskie             | Bielsko-Biała                  |

  Scenario: Adding new basic service without selected province
    When I press Add service button
    Then I should see an error message "Pole wymagane" next to the province and city field

  Scenario Outline: Adding new service with invalid data in city field
    When I fill in province with "mazowieckie"
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
      | Test~   | Niedozwolone znaki |
      |         | Pole wymagane      |

  Scenario: Inserting more than maximum number of characters in city field
    When I fill in province with "mazowieckie"
    And I fill in city with 31 characters
    Then I should see in city maximum 30 characters

