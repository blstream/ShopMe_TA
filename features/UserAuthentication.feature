Feature: Authentication to the application
  As a user I want to be able to authenticate to the application
  So that I can see my account details


  Background:
    Given I am on the authentication form page

  @IsRegistered
  Scenario: User has been already registered
    When I enter email address "test@migmail.pl"
    And I press CreateAnAccount button
    Then I should see account create error message

  @Login
  Scenario: User successfully authenticate to the application
    When I enter valid email address "test@migmail.pl" and password "test1"
    And I press SignIn button
    Then My account page should be open
    And I should see My personal information button

  @AuthenticationError
  Scenario Outline: Registred user failed authenticate to the application
    When  I enter valid email address "<Test1>" and invalid password "<Test2>"
    And I press SignIn button
    Then I should see error message
    Examples:
      |	   Test1             |    Test2         |
      | test@migmail.pl      |  test2           |
      | test@migmail.pl      |  testtest        |

  @UnregisteredUser
  Scenario: Unregistered user failed authenticate to the application
    When I enter login "newUser@test.com" and password "newPassword"
    And I press SignIn button
    Then I should see error message

  @InvalidEmailSyntax
  Scenario Outline: User enter email address with incorrect syntax
    When I enter "<email>" in EmailAdress field
    And I press SignIn button
    Then I should see error message Invalid email address
    Examples:
      |	   email        |
      | testtest        |
      | test@test@com   |
      | @test.com       |
      | test.test.com   |

