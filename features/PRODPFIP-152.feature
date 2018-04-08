Feature: PRODPFIP-152 Specify requirements to searching functionality
  As a user I want to be able to search the service so that I can see search results

  Scenario: Attempt to search for a longer phrase than 30 characters
    Given I navigate to the main page
    When I try to fill in search field with 31 characters
    Then I should see in search field maximum 30 characters

  Scenario Outline: Attempt to search for a shorter phrase than 2 characters
    Given I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    Then the search button is not clickable
    And I can see error message "<error_message>"

    Examples:
      | searching_phrase | error_message                                      |
      | c                | Podaj frazę składającą się z większej liczby liter |
      | 1                | Podaj frazę nieskładającą się z samych liczb       |

  Scenario Outline: Positive flow of searching - searching phrase must have minimum 2 and maximum 30 characters
    Given that there are no services added
    And I add services
      | QA                             |
      | fotografia ślubna tanio okazja |
    When I navigate to the main page
    And I enter a searching phrase "<searching_phrase>" into the search field
    And I click the search button
    Then search results are visible

    Examples:
      | searching_phrase               |
      | QA                             |
      | fotografia ślubna tanio okazja |

