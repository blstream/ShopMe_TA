Feature: My Account Feature

  Scenario: Login with correct email and password
    Given I am registered with email "example@google.com"
    And I am authenticated with username as "example@google.com" and password as "example"
    When I go to the 'My Personal Information'
    Then I should be able to see: first name, last name and email address fields

  Scenario: Order Verification in the "Order history and details"
    Given I am authenticated with username as "example@google.com" and password as "example"
    And I have chosen an item to purchase
    When I pass all steps and confirmed my order
    Then I should be able to see the order in 'Order history and details' view

  Scenario Outline: Login with invalid credentials
    When I enter <email> and <password>
    Then I cannot see 'My Account Page'
    And I see an alert message with <word>

    Examples:
      | email               | password | word    |
      | example2google.com  | example  | invalid |
      | example@google.com  | examplex | failed  |
      | example2@google.com | examplex | failed  |
