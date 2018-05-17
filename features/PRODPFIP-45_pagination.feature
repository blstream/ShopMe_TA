@inProgres
Feature: PRODPFIP-45 Pagination of search results
  As a user I want to navigate between search result pages so that I can see all the search results

    Scenario: Displaying all existing services
      Given that there are no services added
      And I add 54 different services
      # | service name | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
        | test         | law      | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
      When I go to ShopMe main page
      And I enter a searching phrase "test" into the search field
      And I click the search button
      Then I can go to any service from database with title "test"

#  Scenario: Correct displaying pagination
#    When I go to ShopMe main page
#    And I enter a searching phrase "test" into the search field
#    And I click the search button
#    Then search results are visible
#    And I can see 10 records
#    And I can see pagination buttons
#    And I can see that number 1 is bold
#    And I can see how many search result pages there are

#  Scenario Outline: Correct number of results on each page
#    Given I add <number_of_services> different services
#    # | service name       | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
#      | <searching_phrase> | law      | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
 #When I go to ShopMe main page
#    And I enter a searching phrase "<searching_phrase>" into the search field
#    And I click the search button
#   Then Every page but the last one contain 10 results
#    And The last page contains maximum 10 results
#
#    Examples:
#      | searching_phrase | number_of_results |
#      | firstTest           | 1               |
#      | secondTest           | 10                |
#
#  Scenario: Correct navigation with number-button between the pages
#    Given I go to ShopMe main page
#    When I enter a searching phrase "test" into the search field
#    And I click the search button
#    And search results are visible
#    And I click the second page button
#    Then
# I can see that number 2 is bold
  # And I can see 10 results from the second page
#    And previous-button is visible
#    And I can return to the first page
#
#  Scenario: Correct navigation on the last page
#    Given I go to ShopMe main page
#    When I enter a searching phrase "test" into the search field
#    And I click the search button
#    And search results are visible
#    And I click the last page button
#  Then search results are visible
 # And I can see list of last records
#  And I can see first page button
#    And next-button is invisible
#
#  Scenario: Correct navigation with arrow-button between the pages
#    Given I go to ShopMe main page
#    When I enter a searching phrase "test" into the search field
#    And I click the search button
#    And search results are visible
#    And I click next-button
#    Then I can see that number 2 is bold
  # And I can see 10 results from the second page
#    And previous-button is visible
#    And I can return to the first page by clicking previous-button
#
#  Scenario: Add offer and check correctness pagination
#    Given I go to ShopMe main page
#    And I enter a searching phrase "test" into the search field
#    And I click the search button
#    And search results are visible
#  When I click third page button
#    And I have a list of saved services for phrase "test"
#    And I add services
#    # | service name | category | user name | user email      | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
#      | special test | law      | test      | test@domain.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
#    And I click fourth page button
#    Then The results for "test" are shifted one forward
#
#  Scenario: Delete offers and check correctness pagination
#      Given I go to ShopMe main page
#      And I enter a searching phrase "test" into the search field
#      And I click the search button
#      And search results are visible
#      When I click third page button
#      And I have a list of saved services for phrase "test"
#      And I delete 10 services from the page number 2 with searching phrase "test"
#      And I click fourth page button
#      Then The results for "test" are shifted 10 to the back
