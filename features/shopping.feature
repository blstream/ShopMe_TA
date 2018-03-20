@deprecated @test @shopping
Feature: Possibility to buy sth from the Internet shop
  Authenticated users can buy sth from the Internet shop using a bank wire or a check

  Scenario Outline: Positive flow of searching product in store and purchasing
    Given user is logged in to the application with email "firstname.lastname@domain.com" and password "xyz_password"
    And user is on the main page
    When user searches shop page with phrase "Blouse"
    And user adds product to cart
    And user proceeds all steps of ordering
    And user selects "<payment_method>"
    And user confirms an order
    Then user can see "Your order on My store is complete" message
    And user is able to see details of his orders in Order history page
    Examples:
      | payment_method   |
      | pay by bank wire |
      | pay by check     |