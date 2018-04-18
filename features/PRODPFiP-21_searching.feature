Feature: PRODPFIP-21 Searching for a service in the ShopMe website
  As a user I want to be able to search the service so that I can see search results

  Scenario Outline: Positive flow of searching with submit by the search button - search results contain correct title, price and are correctly sorted by date
    Given that there are no services added
    And I add services
      | test                         | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | programowanie Java           | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | programowanie Python         | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | profesjonalne mycie okien    | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | naprawa pralka Amica AWB10i2 | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
    When I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then search results are visible
    And I see that title of the service contains "<searching_phrase>"
    And I see basic price and added data of each record
    And all results are sorted by date descending

    Examples:
      | searching_phrase             |
      | test                         |
      | programowanie Java           |
      | programowanie Python         |
      | profesjonalne mycie okien    |
      | naprawa pralka Amica AWB10i2 |

  Scenario Outline: Negative flow of searching - search phrase is not correct
    Given I navigate to the main page
    When I enter a searching phrase "<searching_phrase>" into the search field
    Then I can see error message "<error_message>"

    Examples:
      | searching_phrase | error_message                                      |
      | c                | Podaj frazę składającą się z większej liczby liter |
      | 1234567890       | Podaj frazę nieskładającą się z samych liczb       |

  Scenario Outline: Positive flow of searching with submit by enter key - search results contain correct title, price and are correctly sorted by date
    Given that there are no services added
    And I add services
      | test                         | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | programowanie Java           | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | programowanie Python         | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | profesjonalne mycie okien    | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | naprawa pralka Amica AWB10i2 | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
    When I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I press Enter key
    Then search results are visible
    And I see that title of the service contains "<searching_phrase>"
    And I see basic price and added data of each record
    And all results are sorted by date descending

    Examples:
      | searching_phrase             |
      | test                         |
      | programowanie Java           |
      | programowanie Python         |
      | profesjonalne mycie okien    |
      | naprawa pralka Amica AWB10i2 |
