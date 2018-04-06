Feature: PRODPFIP-45 Pagination of search results
    As a user I want to be able to see all the search results so that I can navigate between the pages

    Background:
        Given I add more than 10 services with the name "test"  
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
        And I can see the number of all pages

    Scenario: Correct number of results on each page
        Given I navigate to the main page
        And I enter a searching phrase "test" into the search field   
        When I click the search button   
        Then The last page contains maximum 10 records
        And others pages contain 10 results

    Scenario: Correct navigation with number-button between the pages
        Given I navigate to the main page     
        When I enter a searching phrase "test" into the search field    
        And I click the search button   
        And search results are visible
        And I can click the second page
        Then I navigate to the second page
        And Previous-button is visible
        And I can return to the first one

    Scenario: Correct navigation with number-button between the last page
        Given I navigate to the main page     
        When I enter a searching phrase "test" into the search field    
        And I click the search button   
        And search results are visible
        And I can click the last one
        Then I navigate to the last one
        And I can see number of last three pages

    Scenario: Correct navigation with arrow-button between the pages
        Given I navigate to the main page     
        When I enter a searching phrase "test" into the search field    
        And I click the search button   
        And search results are visible
        And I can click next-button
        Then I navigate to the second page
        And Previous-button is visible
        And I can return to the first one by clicking previous-button
    
     Scenario: Correct navigation with arrow-button between the last page
        Given I navigate to the main page     
        When I enter a searching phrase "test" into the search field    
        And I click the search button   
        And search results are visible
        And I can click the last one
        And I navigate to the last one
        Then Next-button is invisible

    Scenario: Add offer and check correctness indexing
        Given search results are visible   
        And I am on the second page   
        When I add service with the name "test"   
        And I return to the first one    
        Then newly added service should be displayed in the first place
        And The last result of each page before adding service is the first result of the next page after adding service
        