Feature: Store test

  @validCredentials
    Scenario: Log in to my account

    Given I am on main page
    When  I go to log in page
    And I fill in login field with login "anna.testowy@gmail.com"
    And I fill in password field with password "QAZwsx123"
    And I hit button Sign in
    And I log in and I click on My personal information tab
    Then I see my personal information

  @invalidCredentials
    Scenario Outline: Invalid credentials

      Given I am on main page
      When I go to log in page
      And I fill in login field with login "<login>"
      And I fill in password field with password "<password>"
      And I hit button Sign in
      Then I see error message

      Examples:
      | login | password |
      |       | QAZwsx123 |
      | anna@gmail | QAZwsx123 |
      | @gmail.pl | QAZwsx123 |
      | anna@gmail.com | QAZwsx123 |
      | anna.testowy@gmail.com | testtest |
      | anna.testowy@gmail.com |          |

    @shopping
    Scenario: Order a product from the shop

      Given I am on main page
      When I order a product from store
      And I click in Proceed Checkout buttons
      And I fill in login field with login "anna.testowy@gmail.com" and password field with "QAZwsx123" and I sign in to my profile
      And I finish ordering a product
      Then I go to my order history
      And I can see my order on the list


