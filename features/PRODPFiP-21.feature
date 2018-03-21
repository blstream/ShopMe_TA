Feature: PRODPFIP-21 Searching for a service in the ShopMe website

  Scenario Outline: Positive flow of searching - search results are displayed in a valid way
    Given that I am on the main page
    When I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then I am on the search result page
    And I see that search results contain "<searching_phrase>"
    And I see number of the offer, title with maximum 30 characters, price and added data of each record
    Examples:
      | searching_phrase          |
      | odśnieżanie               |
      | programowanie java        |
      | programowanie JAVA        |
      | profesjonalne mycie okien |

  Scenario: Search results are sorted by date ascending
    Given that search results are visible
    Then the first search result is the newest one
    And all results are sorted in increasing way

  Scenario Outline: Negative flow of searching - search phrase is not correct
    Given that I am on the main page
    When I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then I can see error message "<error_message>"
    Examples:
      | searching_phrase | error_message                                      |
      | c                | Podaj frazę składającą się z większej liczby liter |
      | 1234567890       | Podaj frazę nie składającą się z samych liczb      |
