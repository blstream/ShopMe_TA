Feature: Adding service - positive flow
  As a user I want to be able to add new service

  Background:
    Given I go to ShopMe main page
    And I push add service button
    And I can see adding form

  Scenario Outline: I add new basic service with valid credentials
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in name with "<name>"
    And I fill in email with "<email>"
    And I fill in phone with "<phone>"
    And I fill in basicPrice with "<basicPrice>"
    And I fill in basicDescription with "<basicDescription>"
    And I fill in aboutMe with "<aboutMe>"
    And I press Add service button
    Then I should see confirmation message

    Examples:
      | title                                                  | category            | name                 | email          | phone      | basicPrice | basicDescription | aboutMe |
      | bb                                                     | Budowa              | testName             | test@email.com | 000000000  | 20         | description      | aboutMe |
      | Ogród                                                  | Ogród               | tes                  | test@email.com | 000000000  | 200        | description      | aboutMe |
      | Usługi warsztatowe                                     | Usługi warsztatowe  | testName             | t@e.co         | 000000000  | 2000       | description      | aboutMe |
      | Programowanie                                          | Programowanie       | testName             | test@email.com | 000000000  | 20         | description      | aboutMe |
      | Transport                                              | Transport           | testName             | test@email.com | 000000000  | 20.15      | description      | aboutMe |
      | Fotografia                                             | Fotografia          | testName             | test@email.com | 000000000  | 0.15       | description      | aboutMe |
      | Grafika                                                | Grafika             | testName             | test@email.com | 000000000  | 20         | d                | aboutMe |
      | Marketing i reklama                                    | Marketing i reklama | testName             | test@email.com | 000000000  | 20         | description      | a       |
      | Marketing i reklama                                    | Marketing i reklama | testNameeeeeeeeeeeee | test@email.com | 000000000  | 20         | description      | aboutMe |
      | Marketing i reklama                                    | Marketing i reklama | testName             | test@email.com | 0000000000 | 20         | description      | aboutMe |
      | Inne usługi pozwalające na lepsze funkcjonowanie firmy | Inne                | testName             | test@email.com | 000000000  | 20         | description      | aboutMe |

  Scenario Outline: I add new service with valid credentials and additional options
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in name with "testName"
    And I fill in email with "test@email.com"
    And I fill in phone with "000000000"
    And I fill in basicPrice with "20"
    And I fill in expandedPrice with "<expandedPrice>"
    And I fill in extraPrice with "<extraPrice>"
    And I fill in basicDescription with "basicDescription"
    And I fill in expandedDescription with "<expandedDescription>"
    And I fill in extraDescription with "<extraDescription>"
    And I fill in aboutMe with "aboutMe"
    And I press Add service button
    Then I should see confirmation message

    Examples:
      | title            | category         | expandedPrice | extraPrice | expandedDescription | extraDescription |
      | Zespoły i muzyka | Zespoły i muzyka | 30            |            | d                   |                  |
      | Naprawa i serwis | Naprawa i serwis | 30            |            | description         |                  |
      | Korepetycje      | Korepetycje      | 30            | 40         | description         | d                |
      | Księgowość       | Księgowość       | 30            | 40         | description         | description      |


  Scenario: I add new basic service with 500 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with 500 characters
    And I press Add service button
    Then New service with 500 character description is added
    And I should see confirmation message


  Scenario: I add new service with extensions with 500 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
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
    Then New service with 500 character descriptions is added
    And I should see confirmation message


  Scenario: I add new basic service with 800 character aboutMe description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with "description"
    And I fill in aboutMe with 800 characters
    And I press Add service button
    Then New service with 800 character aboutMe is added
    And I should see confirmation message