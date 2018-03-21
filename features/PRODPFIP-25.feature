Feature: PRODPFIP-25 Alternative flow of searching for a service

  Scenario: Display no offer message
    Given I am on the main search page
    When I enter searching phrase "mycie kota" into the search field
    And I click the search button
    Then I can see message "Niestety nie znaleziono ofert spełniających Twoje kryteria"