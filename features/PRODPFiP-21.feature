Feature: PRODPFIP-21 Searching for a service in the ShopMe website
  As a user I want to be able to search the service so that I can see search results

  Scenario Outline: Positive flow of searching - search results are displayed in a valid way
    Given that there are no services added
    And I add services via BE
      | odśnieżanie                  |
      | programowanie C#             |
      | programowanie Java           |
      | profesjonalne mycie okien    |
      | naprawa pralka Amica AWB10i2 |
    When I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then search results are visible
    And I see that title of the service contains "<searching_phrase>"
    And I see basic price and added data of each record
    And all results are sorted in ascending way

    Examples:
      | searching_phrase             |
      | odśnieżanie                  |
      | programowanie C#             |
      | programowanie Java           |
      | profesjonalne mycie okien    |
      | naprawa pralka Amica AWB10i2 |

  Scenario Outline: Negative flow of searching - search phrase is not correct
    Given I navigate to the main page
    When I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then I can see error message "<error_message>"

    Examples:
      | searching_phrase | error_message                                 |
      | c                | Wpisana fraza jest za krótka                  |
      | 1234567890       | Podaj frazę nie składającą się z samych liczb |

  Scenario Outline: Positive flow of searching - search results are displayed in a valid way with submit by enter key
    Given that there are no services added
    And I add services via BE
      | odśnieżanie                  |
      | programowanie C#             |
      | programowanie Java           |
      | profesjonalne mycie okien    |
      | naprawa pralka Amica AWB10i2 |
    When I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I press Enter key
    Then search results are visible
    And I see that title of the service contains "<searching_phrase>"
    And I see basic price and added data of each record
    And all results are sorted in ascending way

    Examples:
      | searching_phrase             |
      | odśnieżanie                  |
      | programowanie C#             |
      | programowanie Java           |
      | profesjonalne mycie okien    |
      | naprawa pralka Amica AWB10i2 |

  Scenario Outline: Negative flow of searching - search phrase is not correct with submit by enter key
    Given I navigate to the main page
    When I enter a searching phrase "<searching_phrase>" into the search field
    And I press Enter key
    Then I can see error message "<error_message>"

    Examples:
      | searching_phrase | error_message                                 |
      | c                | Wpisana fraza jest za krótka                  |
      | 1234567890       | Podaj frazę nie składającą się z samych liczb |
