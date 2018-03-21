@deprecated @test @signIn
Feature: Authentication to the application
  Authentication form for a regular user
  Authenticated users can see the personal information such as first name, last name, email address and date of birth
  Only registered users can authenticate to the application

  Background:
    Given user is on the main page
    And user is not authenticated

  Scenario Outline: Successful authentication with valid credentials
    Given user is registered in the application with email "firstname.lastname@domain.com"
    When user clicks Sign in button
    And user enters email "<email>"
    And user enters password "<password>"
    And submit form with the Sign in button
    Then user is redirected to My account page
    And user can see the correct data in My personal information page
    Examples:
      | email                         | password     |
      | firstname.lastname@domain.com | xyz_password |

  Scenario Outline: Unsuccessful authentication with invalid credentials
    When user clicks Sign in button
    And user enters email "<email>"
    And user enters password "<password>"
    And submit form with the Sign in button
    Then user can see relevant validation message: "<expected_error_message>"
    Examples:
      | email                         | password     | expected_error_message     |
      | firstname.lastname@           | xyz_password | Invalid email address.     |
      | firstname.lastname@domain.com | wrong_pass   | Authentication failed.     |
      | tooshort@pass.com             | pass         | Invalid password.          |
      | empty@pass.pl                 |              | Password is required.      |
      |                               | empty_login  | An email address required. |
      | unregistered_email@domain.com | xyz_password | Authentication failed.     |
