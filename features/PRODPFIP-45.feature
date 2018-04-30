@1
Feature: PRODPFIP-45 Pagination of search results
  As a user I want to navigate between search result pages so that I can see all the search results

  Scenario: Displaying all existing services
    Given I add 50 different services
     # | service name       | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price |province           | city     |
      | test        | law   | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          |WesternPomeranian | Szczecin |
    When I navigate to the main page
    And I enter a searching phrase "test" into the search field
    And I click the search button
    Then I can go to any service from database with title "test"

  Scenario: Correct displaying pagination
    Given I add 13 different services
     # | service name       | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price |province           | city     |
      | test        | law   | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          |WesternPomeranian | Szczecin |
    When I navigate to the main page
    And I enter a searching phrase "test" into the search field
    And I click the search button
    Then search results are visible
    And I can see first 10 records
    And I can see pagination buttons
    And I can see that number 1 is bold
    And I can see how many search result pages there are

  Scenario Outline: Correct number of results on each page
    Given I add 13 different services
    # | service name       | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price |province           | city     |
      | firstTest        | law   | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          |WesternPomeranian | Szczecin |
    And I add 30 different services
        # | service name       | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price |province           | city     |
      | secondTest        | law   | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          |WesternPomeranian | Szczecin |
    Given I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    When I click the search button
    Then Every page but the last one contain 10 results
    And The last page contains less than 10 results

    Examples:
      | searching_phrase |
      | firstTest           |
      | secondTest          |

  Scenario: Correct navigation with number-button between the pages
    Given I navigate to the main page
    When I enter a searching phrase "test" into the search field
    And I click the search button
    And search results are visible
    And I click the second page button
    Then I can see that number 2 is bold
    And I can see 10 results from the second page
    And previous-button is visible
    And I can return to the first page

  Scenario: Correct navigation on the last page
    Given I navigate to the main page
    When I enter a searching phrase "test" into the search field
    And I click the search button
    And search results are visible
    And I click the last page button
    Then I can see that the last page is bold
    And search results are visible
    And I can see list of last records
    And I can see first page button
    And next-button is invisible

  Scenario: Correct navigation with arrow-button between the pages
    Given I navigate to the main page
    When I enter a searching phrase "test" into the search field
    And I click the search button
    And search results are visible
    And I click next-button
    Then I can see that number 2 is bold
    And I can see 10 results from the second page
    And previous-button is visible
    And I can return to the first page by clicking previous-button

  Scenario: Add offer and check correctness pagination
    Given I navigate to the main page
    And I enter a searching phrase "test" into the search field
    And I click the search button
    And search results are visible
    And I click third page button
    When I add services
    # | service name | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price |
      | specialTest       | budowa   | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          |
    And I click fourth page button
    Then The results are shifted one forward

  Scenario: Delete offers and check correctness pagination
    Given I navigate to the main page
    And I enter a searching phrase "test" into the search field
    And I click the search button
    And search results are visible
    When I click third page button
    And I delete 4 services from the page number 2
    And I click fourth page button
    Then The results are shifted four to the back
