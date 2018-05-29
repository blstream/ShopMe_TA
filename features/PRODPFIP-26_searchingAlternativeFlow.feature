@workingGood
Feature: PRODPFIP-26 Re-search phrases
  As a user I want to be able to search the service again

  Background:
    Given that there are no services added
    And I add services
#     | service name | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
      | zmywanie     | building | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
      | pranie       | building | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
      | mycie        | building | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |

  Scenario Outline: Searching for phrases again
    And I go to ShopMe main page
    And I enter a searching phrase "<phrase1>" into the search field
    And I click the search button
    And search results are visible
    When I enter a searching phrase "<new_phrase>" into the search field
    And I click the search button
    Then search results for the new phrase "<new_phrase>" are visible
    And I see that title of the service contains "<new_phrase>"
    And I see basic price and added data of each record

    Examples:
      | phrase1  | new_phrase |
      | zmywanie | pranie     |

  Scenario: Searching for phrases again after no offer message
    Given I go to ShopMe main page
    And I enter a searching phrase "czyżyk" into the search field
    And I click the search button
    And I can see no results message "Niestety nie znaleziono ofert spełniających Twoje kryteria"
    When I enter a searching phrase "pranie" into the search field
    And I click the search button
    Then search results are visible
    And I see that title of the service contains "pranie"
    And I see basic price and added data of each record

  Scenario: Adding new offer containing phrase in the title and searching for that phrase again
    Given I go to ShopMe main page
    And I enter a searching phrase "mycie" into the search field
    And I click the search button
    When I add services
#     | service name     | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
      | mycie samochodów | building | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    And I enter a searching phrase "mycie" into the search field
    And I click the search button
    Then search results are visible
    And I see that title of the service contains "mycie"
    And I see service "mycie samochodów" at the first place
