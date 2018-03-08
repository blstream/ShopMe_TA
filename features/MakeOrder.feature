Feature: Making order
  As a user I want to be able to make order
  So that I can by some products

  @MakeOrder
  Scenario: User make order
    Given I am an authenticated user with login "test@migmail.pl" and password "test1"
    When I press Home page button
    And I choose product
    And I press AddToCart button
    And I open Shopping Cart
    And I am on  Shopping Cart Summary page
    And I press ProceedToCheckout button
    And I am on Addresses page
    And I press Procced to chekout button
    And I am on Shipping page
    And I accept terms of service
    And I am on Payment page
    And I choose payment option
    And I confirm my order
    Then I should see confirmation message