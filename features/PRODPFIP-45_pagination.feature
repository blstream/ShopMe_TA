Feature: PRODPFIP-45 Pagination of search results
  As a user I want to navigate between search result pages so that I can see all the search results

  Background:
    Given I add 500 services with the title "test 1"
    And I add 11 services with the title "test 2"

  Scenario Outline: Correct displaying pagination
    When I go to ShopMe main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then search results are visible
    And I am on the first page
    And I can see 10 records
    And I can see pagination buttons
    And I can see that number 1 is bold
    And I can see how many search result pages there are

    Examples:
      | searching_phrase |
      | test 1           |
      | test 2           |

  Scenario Outline: Correct number of results on each page
    Given I go to ShopMe main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    When I click the search button
    Then the last page contains maximum 10 results from <number_of_results>
    And other pages contain 10 results

    Examples:
      | searching_phrase | number_of_results |
      | test 1           | 500               |
      | test 2           | 11                |

  Scenario: Correct navigation with number-button between the pages
    Given I go to ShopMe main page
    When I enter a searching phrase "test 1" into the search field
    And I click the search button
    And search results are visible
    And I click the second page
    Then I am on the second page
    And I can see that number 2 is bold
    And previous-button is visible
    And I can return to the first page

  Scenario: Correct navigation on the last page
    Given I go to ShopMe main page
    When I enter a searching phrase "test 1" into the search field
    And I click the search button
    And search results are visible
    And I click the last page
    Then I am on the last page
    And I can see the numbers of last three pages
    And I can see the number of the first page
    And next-button is invisible

  Scenario: Correct navigation with arrow-button between the pages
    Given I go to ShopMe main page
    When I enter a searching phrase "test 1" into the search field
    And I click the search button
    And search results are visible
    And I click next-button
    Then I navigate to the second page
    And previous-button is visible
    And I can return to the first page by clicking previous-button

  Scenario: Add offer and check correctness indexing
    Given I go to ShopMe main page
    And I enter a searching phrase "test 1" into the search field
    And I click the search button
    And search results are visible
    And I navigate to the second page
    When I add services
      | test 1 |
    And I return to the first page
    Then newly added service should be displayed in the first place
    And the last result of each page before adding service is the first result of the next page after adding service
        
