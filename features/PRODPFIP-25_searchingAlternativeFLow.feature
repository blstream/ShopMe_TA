@workingGood
Feature: PRODPFIP-25 Alternative flow of searching for a service

  Scenario Outline: Display no offer message
    Given that there are no services added
    And I add services
#     | service name       | category    | user name | user email         | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
      | odśnieżanie        | others      | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
      | programowanie Java | programming | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    And I go to ShopMe main page
    When I enter a searching phrase "<phrase_that_does_not_exist>" into the search field
    And I click the search button
    Then I can see no results message "Niestety nie znaleziono ofert spełniających Twoje kryteria"

    Examples:
      | phrase_that_does_not_exist |
      | sprzątanie                 |
      | programowanie Python       |

  Scenario Outline: Display no offer message after search again
    Given I go to ShopMe main page
    And I enter a searching phrase "odśnieżanie" into the search field
    And I click the search button
    When I enter a searching phrase "<phrase_that_does_not_exist>" into the search field
    And I click the search button
    Then I can see no results message "Niestety nie znaleziono ofert spełniających Twoje kryteria"

    Examples:
      | phrase_that_does_not_exist |
      | sprzątanie                 |
      | programowanie Python       |
