Feature: PRODPFIP-25 Alternative flow of searching for a service

  Scenario: Display no offer message
    Given there are no services added
    And I add services "mycie okien" via BE
    And I am on the main search page
    When I enter into the search field searching phrase that doesn't exist
      |mycie kota|
      |sprzątanie|
    And I click the search button
    Then I can see message "Niestety nie znaleziono ofert spełniających Twoje kryteria"

  Scenario: Display no offer message after search again
    Given I am on the search results page for phrase "mycie"
    When I enter into the search field searching phrase that doesn't exist
      |mycie kota|
      |sprzątanie|
    And I click the search button
    Then I can see message "Niestety nie znaleziono ofert spełniających Twoje kryteria"
