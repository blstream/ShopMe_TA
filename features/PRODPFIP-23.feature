Feature: PRODPFIP-23 Possibility to see the profile page of the service
  As a user I want to be able to click on the search result so that I can see details of the service

  Scenario Outline: Profile page of the service is displayed after clicking on the search result
    Given that there are no services added
    And I add service with <"title">, <"category">, <"name>", <"mail">, <"phone">, <"user_info">, <"basic_info">,<"basic_price">, <"extended_info">,<"extended_price">, <"extra_info">, <"extra_price">
    When I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then search results are visible
    And I can click on the first search result to see the details
    And I can see the title <"title">
    And I can see the category <"category">
    And I can see user's name <"user_name">
    And I can see user's email <"email"> after clicking to expand that
    And I can see user's phone <"phone"> after clicking to expand that
    And I can see additional user info <"user_info">
    And I can see basic service <"basic_info"> and price <"basic_price">
    And I can see extended service <"extended_info"> and price <"extended_price">
    And I can see extra service <"extra_info"> and price <"extra_price">

    Examples:
      | searching_phrase | title | category | user_name | email           | phone     | user_info | basic_info | basic_price | extended_info | extended_price | extra_info | extra_price |
      | test             | test  | inne     | test      | test@domain.com | 888555222 | test      | test       | 10,00 zł    | test          | 11,00 zł       | test       | 12,00 zł    |

