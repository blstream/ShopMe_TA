Feature:  PRODPFIP-354 Signinig user out
  As a signed in user I want to be able to sign myself out

  Background:
    Given I go to ShopMe main page
    And I am an signed in to the application with email "unknown@gmail.com" and password "Password9"

  Scenario: Signing out from ShopMe website
    When I push logout button
    Then I am redirected to the main page
    And I am not authenticated

  Scenario: Signing out from ShopMe website with proceeding to previous page
    When I push logout button
    And  I am redirected to the main page
    And I go back to previous page
    Then I am not authenticated

  Scenario: Signing out from ShopMe website with refreshing current page
    When I push logout button
    And I am redirected to the main page
    And I refresh page
    Then I am redirected to the main page
    And I am not authenticated