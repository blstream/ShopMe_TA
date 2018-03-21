
Feature: PRODPFIP-26 Re-search phrases

    Scenario Outline: Searching for phrases again
        Given I am on the search result page for "<phrase1>"
        When I enter new phrase "<phrase2>"
        And I click the search button
        Then I am on the search result page for "<phrase2>"
        And I see search results containing "<phrase2>"
        And I see lp., title, price and added data of each record 
        Examples:
            |phrase1 |phrase2|
            |zmywanie|pranie |

    Scenario Outline: Searching for phrases again after no offer message
        Given I can see no offer message
        When I enter new phrase "<phrase>"
        And I click the search button
        Then I am on the search result page for "<phrase>"
        And I see search results containing "<phrase>"
        And I see lp., title, price and added data of each record 
        Examples:
            |phrase |
            |pranie |

    Scenario Outline: Adding new offer and searching for phrases again
        Given I am on the search result page for "<phrase>"
        When I add some new offer containing phrase "<phrase>"
        And I enter phrase "<phrase>" again
        And I submit
        Then I am on the search result page for "<phrase>"
        And I see search results containing "<phrase>"
        And I see lp., title, price and added data of each record
        And I see my new offer at the first place
        Examples:
            |phrase|
            |mycie |
