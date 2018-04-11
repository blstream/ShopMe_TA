Feature: PRODPFIP-20 Adding new service - alternative flow
  As a user I want to be able to add new service so that can be found by potential clients

  Background:
    Given I go to ShopMe main page
    And I push Add service button
    And I can see adding form

  Scenario Outline: Adding new basic service with at least one empty required field
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in basic_price with "<basic_price>"
    And I fill in basic_description with "<basic_description>"
    And I fill in name with "<name>"
    And I fill in email with "<email>"
    And I fill in phone with "<phone>"
    And I press Add service button
    Then I should see an error message "Pole wymagane" next to the required field
    And I should see a message "Proszę wypełnić wszystkie wymagane pola"
    And I should see inserted values in filled fields

    Examples:
      | title      | category   | name     | email             | phone     | basic_price | basic_description |
      | Budowa     | budowa     |          | example@gmail.com | 000000000 | 10          | description       |
      | Budowa     | budowa     | testName | example@gmail.com | 000000000 |             | description       |
      | Budowa     | budowa     | testName |                   | 000000000 | 10          | description       |
      | Transport  | Transport  | testName | example@gmail.com |           | 10          | description       |
      | Fotografia | Fotografia | testName | example@gmail.com | 000000000 | 10          |                   |
      |            | Fotografia | testName | example@gmail.com | 000000000 | 10          | description       |
      | Budowa     |            | testName | example@gmail.com | 000000000 | 10          | description       |

  Scenario Outline: Adding new service with extensions with at least one empty required field
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in basic_description with "<basic_description>"
    And I fill in basic_price with "<basic_price>"
    And I fill in expanded_description with "<expanded_description>"
    And I fill in expanded_price with "<expanded_price>"
    And I fill in extra_description with "<extra_description>"
    And I fill in extra_price with "<extra_price>"
    And I fill in name with "<name>"
    And I fill in email with "<email>"
    And I fill in phone with "<phone>"
    And I press Add service button
    Then I should see an error message "Pole wymagane" next to the required field
    And I should see a message "Proszę wypełnić wszystkie wymagane pola"
    And I should see inserted values in filled fields

    Examples:
      | title              | category           | name     | email             | phone     | basic_price | basic_description | expanded_price | extra_price | expanded_description | extra_description |
      | Budowa             | budowa             | testName | example@gmail.com | 000000000 | 10          | description       | 20             |             |                      |                   |
      | Ogród              | ogród              | testName | example@gmail.com | 000000000 | 10          | description       | 20             | 20          | description          |                   |
      | Usługi warsztatowe | usługi warsztatowe | testName | example@gmail.com | 000000000 | 10          | description       |                |             | description          |                   |
      | Transport          | Transport          | testName | example@gmail.com | 000000000 | 10          | description       | 20             |             | description          | description       |

  Scenario Outline: Adding new service with invalid credentials
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in basic_description with "description"
    And I fill in basic_price with "10"
    And I fill in name with "<name>"
    And I fill in email with "<email>"
    And I fill in phone with "<phone>"
    And I press Add service button
    Then I should see form with inserted values in fields filled with valid data
    And I should see an error message "<error_message>" next to the field with invalid data

    Examples:
      | title      | category   | name     | email               | phone     | error_message
      | t          | budowa     | testName | example@gmail.com   | 000000000 | Zbyt mała liczba znaków             |
      | Ogród      | ogród      | t        | example@gmail.com   | 000000000 | Zbyt mała liczba znaków             |
      | Ogród      | ogród      | tt       | example@gmail.com   | 000000000 | Zbyt mała liczba znaków             |
      | Transport  | transport  | testNa11 | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam! | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam@ | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam# | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam$ | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam% | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam^ | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam& | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam* | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam+ | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam= | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam~ | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam` | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam- | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam_ | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam) | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Transport  | transport  | testNam( | example@gmail.com   | 000000000 | Pole może zawierać tylko litery     |
      | Fotografia | fotografia | testName | exampleemail.com    | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | example.email.com   | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | testemail           | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | @gmail.com          | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | test@test@gmail.com | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | test@com            | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | #@%^%#$@#$@#.com    | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | Abc..123@gmail.com  | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | .test@gmail.com     | 000000000 | Podaj prawidłowy adres e-mail       |
      | Fotografia | fotografia | testName | test.@gmail.com     | 000000000 | Podaj prawidłowy adres e-mail       |
      | Budowa     | budowa     | testName | example@gmail.com   | 10010010  | Zbyt mała liczba znaków             |
      | Budowa     | budowa     | testName | example@gmail.com   | 10abcdegh | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000! | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000@ | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000# | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000* | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000% | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000^ | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000& | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000? | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000$ | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000+ | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000= | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000~ | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000` | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000_ | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000( | Pole powinno zawierać jedynie cyfry |
      | Budowa     | budowa     | testName | example@gmail.com   | 00000000) | Pole powinno zawierać jedynie cyfry |

  Scenario: Adding new basic service with 501 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basic_price with "200"
    And I fill in basic_description with 501 characters
    Then I should see in basic_description maximum 500 characters

  Scenario: Adding new service with extensions with 501 character description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basic_price with "200"
    And I fill in basic_description with "description"
    And I fill in expanded_description with 501 characters
    And I fill in expanded_price with "300"
    And I fill in extra_description with 501 characters
    And I fill in extra_price with "400"
    Then I should see in expanded_description maximum 500 characters
    And I should see in extra_description maximum 500 characters

  Scenario: Adding new basic service with 801 character about_me description
    When I fill in title with "Usługi prawnicze"
    And I choose category "Prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basic_price with "200"
    And I fill in basic_description with "description"
    And I fill in about_me with 801 characters
    Then I should see in about_me maximum 800 characters

  Scenario: Inserting more than maximum number of characters in form fields
    When I fill in title with "Loremipsumdolorsitametturpisdui"
    And I choose category "Prawo"
    And I fill in name with "Loremipsumdolorsitame"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "00000000000"
    And I fill in basic_price with "100,002"
    And I fill in basic_description with "description"
    Then I should see in title maximum 30 characters
    Then I should see in name  maximum 20 characters
    Then I should see in basic_price maximum 2 decimal places
    And I should see in phone maximum 10 characters

  Scenario: Attempt to fill the fields of expanded service without filling basic service fields
    When I try to fill in expanded_description with "description" without filling basic_description and basic_price fields
    Then I should see that expanded_description and expanded_price fields are blocked

  Scenario: Attempt to fill the fields of extra service without filling expanded service fields
    When I try to fill in extra_description with "description" without filling expanded_description and expanded_price fields
    Then I should see that extra_description and extra_price fields are blocked
