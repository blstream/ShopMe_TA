Feature: PRODPFIP-21 Searching for a service in the ShopMe website
  As a user I want to be able to search the service so that I can see search results

  Scenario Outline: Positive flow of searching with submit by the search button - search results contain correct title, price and are correctly sorted by date
#    Given that there are no services added
    And I add services "<title>", "<category>", "<user_name>", "<email>", "<phone>", "<user_info>", "<basic_info>", "<basic_price>", "<extended_info>", "<extended_price>", "<extra_info>", "<extra_price>"
    When I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then search results are visible
    And I see that title of the service contains "<searching_phrase>"
    And I see basic price and added data of each record
    And all results are sorted in descending way

    Examples:
      | searching_phrase             | title | category | user_name | email           | phone     | user_info | basic_info | basic_price | extended_info | extended_price | extra_info | extra_price |
      | test                  | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
#      | programowanie Java           | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
#      | programowanie Python         | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
#      | profesjonalne mycie okien    | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
#      | naprawa pralka Amica AWB10i2 | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |


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
    And I add services "<title>", "<category>", "<user_name>", "<email>", "<phone>", "<user_info>", "<basic_info>", "<basic_price>", "<extended_info>", "<extended_price>", "<extra_info>", "<extra_price>"
    When I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I press Enter key
    Then search results are visible
    And I see that title of the service contains "<searching_phrase>"
    And I see basic price and added data of each record
    And all results are sorted in descending way

    Examples:
      | searching_phrase             | title | category | user_name | email           | phone     | user_info | basic_info | basic_price | extended_info | extended_price | extra_info | extra_price |
      | odśnieżanie                  | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | programowanie Java           | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | programowanie Python         | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | profesjonalne mycie okien    | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |
      | naprawa pralka Amica AWB10i2 | test  | budowa   | test      | test@domain.com | 888555222 | test      | test       | 10          | test          | 20             | test       | 30          |

