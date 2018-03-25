Feature: PRODPFIP-26 Re-search phrases
    As a user I want to be able to search the service again 

    Scenario Outline: Searching for phrases again
        Given there are no services added
        And I add services via BE
            |zmywanie|
            |pranie  |
            |mycie   |
    
        Given I am on the search result page for "<phrase1>"
        When I enter new phrase "<phrase2>" into the search field
        And I click the search button
        Then I am on the search result page for "<phrase2>"
        And I see search results containing "<phrase2>" in the title
        And I see lp., title, price and added data of each record 

        Examples:
            |phrase1 |phrase2|
            |zmywanie|pranie |

    Scenario: Searching for phrases again after no offer message
        Given I can see no offer message
        When I enter new phrase "pranie" into the search field
        And I click the search button
        Then I am on the search result page for "pranie"
        And I see search results containing "pranie" in the title
        And I see lp., title, price and added data of each record 

    Scenario: Adding new offer and searching for phrases again
        Given I am on the search result page for "mycie"
        When I add some new offer "mycie samochodów" containing this phrase in the title
        And I enter phrase "mycie" into the search field
        And I click the search button
        Then I am on the search result page for "mycie"
        And I see search results containing "mycie" in the title
        And I see lp., title, price and added data of each record
        And I see my new offer at the first place
