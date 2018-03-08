Feature: signin


  Background:
    Given I am not signed in
    And I go to sign in form

  @smokeTest @signIn
  Scenario Outline: Sign in application with valid parameters
    Given Account with credentials "<login>" is already registered
    When I enter login with "<login>" and password with "<password>"
    Then Request succeeded
    And I go to My Personal Information page
    And the name attribute with value "<name>" is visible
    And the surname attribute with value "<surname>" is visible
    And the email attribute with value "<email>" is visible

    Examples:
      | login                                                        | password | name  | surname | email                                                        | address          |
      | jerry.doe@example.com                                        | jerrydoe | Jerry | Doe     | jerry.doe@example.com                                        | 555, 53rd Street |
      | aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@60chars.com | henrydoe | Henry | Doe     | aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@60chars.com | 123, West Street |

  @smokeTest @signIn
  Scenario: Sign in application with incorrect password
    Given Account with credentials "jerry.doe@example.com" is already registered
    When I enter login with "jerry.doe@example.com" and password with "12345"
    Then Request fails with error message

  @smokeTest @signIn
  Scenario Outline: Sign in application with invalid login
    When I enter login with "<login>" and password with "<password>"
    Then Request fails with error message

    Examples:
      | login                                                         | password |
      | a                                                             | passwd   |
      | mike@examplecom                                               | passwd   |
      | georgeexample.com                                             | passwd   |
      | steven@.com                                                   | passwd   |
      | s@example                                                     | passwd   |
      | 1234567890                                                    | passwd   |
      | aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@61chars.com | passwd   |

  @smokeTest @signIn
  Scenario: Sign in application with unregistered login
    Given Account with credentials "johnnie.walkier@whisky.com" is not already registered
    When I enter login with "johnnie.walkier@whisky.com" and password with "12345"
    Then Request fails with error message

  @smokeTest @signIn
  Scenario Outline: Sign in application with at least one empty field
    When I enter login with "<login>" and password with "<password>"
    Then Request fails with error message

    Examples:
      | login                | password |
      | john.doe@example.com |          |
      |                      | passwd   |