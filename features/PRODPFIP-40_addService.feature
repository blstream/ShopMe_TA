Feature:  PRODPFIP-40 Adding new service - basic flow
  As a user I want to be able to add new service so that can be found by potential clients

  Background:
    Given I go to ShopMe main page
    And I push add service button
    And I can see adding form

  Scenario Outline: Adding new basic service with valid credentials
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in name with "<name>"
    And I fill in email with "<email>"
    And I fill in phone with "<phone>"
    And I fill in basicDescription with "<basic_description>"
    And I fill in basicPrice with "<basic_price>"
    And I fill in aboutMe with "<about_me>"
    And I press Add service button
    Then I should see confirmation message "Pomyślnie dodano ofertę"

    Examples:
      | title                          | category            | name                 | email          | phone      | basic_price | basic_description | about_me |
      | Pr                             | prawo               | testName             | test@email.com | 000000000  | 20          | description       | aboutMe  |
      | Ogród                          | ogród               | tes                  | test@email.com | 000000000  | 200         | description       | aboutMe  |
      | Usługi warsztatowe             | usługi warsztatowe  | testName             | t@e.co         | 000000000  | 2000        | description       | aboutMe  |
      | Programowanie                  | programowanie       | testName             | test@email.com | 000000000  | 20          | description       | aboutMe  |
      | Transport                      | transport           | testName             | test@email.com | 000000000  | 20.15       | description       | aboutMe  |
      | Fotografia                     | fotografia          | testName             | test@email.com | 000000000  | 0.15        | description       | aboutMe  |
      | Grafika                        | grafika             | testName             | test@email.com | 000000000  | 20          | d                 | aboutMe  |
      | Marketing i reklama            | marketing i reklama | testName             | test@email.com | 000000000  | 20          | description       | a        |
      | Księgowość                     | księgowość          | testNameeeeeeeeeeeee | test@email.com | 000000000  | 20          | description       | aboutMe  |
      | Marketing i reklama            | tłumaczenia         | testName             | test@email.com | 0000000000 | 20          | description       | aboutMe  |
      | Inne usługi pozwalające testow | inne                | testName             | test@email.com | 000000000  | 20          | description       | aboutMe  |

  Scenario Outline: Adding new service with valid credentials and additional options
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in name with "testName"
    And I fill in email with "test@email.com"
    And I fill in phone with "000000000"
    And I fill in basicDescription with "basic_description"
    And I fill in basicPrice with "20"
    And I fill in expandedDescription with "<expanded_description>"
    And I fill in expandedPrice with "<expanded_price>"
    And I fill in extraDescription with "<extra_description>"
    And I fill in extraPrice with "<extra_price>"
    And I fill in aboutMe with "aboutMe"
    And I press Add service button
    Then I should see confirmation message "Pomyślnie dodano ofertę"

    Examples:
      | title            | category         | expanded_price | expanded_description | extra_description | extra_price |
      | Zespoły i muzyka | transport        | 30             | d                    |                   |             |
      | Naprawa i serwis | naprawa i serwis | 30             | description          |                   |             |
      | Korepetycje      | korepetycje      | 30             | description          | d                 | 40          |
      | Księgowość       | księgowość       | 30             | description          | description       | 40          |

  Scenario: Adding new basic service with 500 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with 500 characters
    And I press Add service button
    Then New basic service with 500 character description is added
    And I should see confirmation message "Pomyślnie dodano ofertę"

  Scenario: Adding new service with extensions with 500 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with "description"
    And I fill in expandedDescription with 500 characters
    And I fill in expandedPrice with "300"
    And I fill in extraDescription with 500 characters
    And I fill in extraPrice with "400"
    And I press Add service button
    Then New service with 500 character expanded and extra descriptions is added
    And I should see confirmation message "Pomyślnie dodano ofertę"

  Scenario: Adding new basic service with 800 character aboutMe description
    When I fill in title with "Usługi prawnicze"
    And I choose category "transport"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in aboutMe with 800 characters
    And I fill in basicDescription with "description"
    And I fill in basicPrice with "200"
    And I press Add service button
    Then New service with 800 character aboutMe is added
    And I should see confirmation message "Pomyślnie dodano ofertę"
    And I am redirected to the main page