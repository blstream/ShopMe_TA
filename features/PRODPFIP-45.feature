Feature: PRODPFIP-45 Pagination of search results
    As a user I want to navigate between search result pages so that I can see all the search results

    Background:
        Given I add more than 10 services with the title "test"
        |500|
        |11 |

    Scenario: Correct displaying pagination
        When I navigate to the main page
        And I enter a searching phrase "test" into the search field
        And I click the search button
        Then search results are visible
        And I am on the first page
        And I can see 10 records
        And I can see pagination buttons
        And I can see that number 1 is bold
        And I can see how many search pages there are

    Scenario: Correct number of results on each page
        Given I navigate to the main page
        And I enter a searching phrase "test" into the search field
        When I click the search button
        Then The last page contains maximum 10 records
        And other pages contain 10 results

    Scenario: Correct navigation with number-button between the pages
        Given I navigate to the main page
        When I enter a searching phrase "test" into the search field
        And I click the search button
        And search results are visible
        And I click the second page
        Then I navigate to the second page
        And I can see that number 2 is bold
        And Previous-button is visible
        And I can return to the first one

    Scenario: Correct navigation on the last page
        Given I navigate to the main page
        When I enter a searching phrase "test" into the search field
        And I click the search button
        And search results are visible
        And I click the last one
        Then I navigate to the last one
        And I can see the numbers of last three pages
        And I can see the number of the first page
        And next-button is invisible

    Scenario: Correct navigation with arrow-button between the pages
        Given I navigate to the main page
        When I enter a searching phrase "test" into the search field
        And I click the search button
        And search results are visible
        And I click next-button
        Then I navigate to the second page
        And previous-button is visible
        And I can return to the first one by clicking previous-button

    Scenario: Add offer and check correctness indexing
        Given search results are visible
        And I am on the second page
        When I add services "test"
        And I return to the first one
        Then newly added service should be displayed in the first place
        And The last result of each page before adding service is the first result of the next page after adding service

