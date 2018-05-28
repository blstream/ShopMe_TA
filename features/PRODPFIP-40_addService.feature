@workingBad
Feature:  PRODPFIP-40 Adding new service - basic flow
  As a user I want to be able to add new service so that can be found by potential clients

  Background:
    Given I go to ShopMe main page
    And I am an signed in to the application with email "john.doe@gmail.com" and password "Password1234"
    And I push add service button
    And I can see adding form

  Scenario Outline: Adding new basic service with valid credentials
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in province with "Zachodniopomorskie"
    And I fill in city with "Szczecin"
    And I fill in basicDescription with "<basic_description>"
    And I fill in basicPrice with "<basic_price>"
    And I press Add service button
    Then I should see confirmation message "Pomyślnie dodano ofertę"
    And I am redirected to the profile page of the service

    Examples:
      | title                          | category            | basic_price | basic_description |
      | Pr                             | Prawo               | 20          | description       |
      | Programowanie                  | Programowanie       | 20          | description       |
      | Transport                      | Transport           | 20.15       | description       |
      | Fotografia                     | Fotografia          | 0.15        | description       |
      | Grafika                        | Grafika             | 20          | d                 |
      | Marketing i reklama            | Marketing i Reklama | 20          | description       |
      | Inne usługi pozwalające testow | Inne                | 20          | description       |

  Scenario Outline: Adding new service with valid credentials and additional options
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in province with "Zachodniopomorskie"
    And I fill in city with "Szczecin"
    And I fill in basicDescription with "basic_description"
    And I fill in basicPrice with "20"
    And I fill in expandedDescription with "<expanded_description>"
    And I fill in expandedPrice with "<expanded_price>"
    And I fill in extraDescription with "<extra_description>"
    And I fill in extraPrice with "<extra_price>"
    And I press Add service button
    Then I should see confirmation message "Pomyślnie dodano ofertę"
    And I am redirected to the profile page of the service

    Examples:
      | title            | category         | expanded_price | expanded_description | extra_description | extra_price |
      | Zespoły i muzyka | Transport        | 30             | d                    |                   |             |
      | Naprawa i serwis | Naprawa i Serwis | 30             | description          |                   |             |
      | Korepetycje      | Korepetycje      | 30             | description          | d                 | 40          |
      | Księgowość       | Księgowość       | 30             | description          | description       | 40          |

  Scenario: Adding new basic service with 500 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in province with "Zachodniopomorskie"
    And I fill in city with "Szczecin"
    And I fill in basicDescription with 500 characters
    And I fill in basicPrice with "200"
    And I press Add service button
    Then I should see confirmation message "Pomyślnie dodano ofertę"
    And New basic service with 500 character description is added
    And I am redirected to the profile page of the service

  Scenario: Adding new service with extensions with 500 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in province with "Zachodniopomorskie"
    And I fill in city with "Szczecin"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with "description"
    And I fill in expandedDescription with 500 characters
    And I fill in expandedPrice with "300"
    And I fill in extraDescription with 500 characters
    And I fill in extraPrice with "400"
    And I press Add service button
    Then I should see confirmation message "Pomyślnie dodano ofertę"
    And New service with 500 character expanded and extra descriptions is added
    And I am redirected to the profile page of the service


