Feature:  PRODPFIP-354 Signinig user out
  As a signed in user I want to be able to myself out

  Background:
    Given I go to ShopMe main page
    And I am an signed in to the application with email "test@gmail.com" and password "TestPassword1"
    And I am redirected to the main page

  Scenario: Signing out from ShopMe website
    When I push logout button
    Then I am not authenticated