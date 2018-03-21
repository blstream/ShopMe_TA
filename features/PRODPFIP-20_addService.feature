Feature: Adding new service - negative flow
  As a user I want to be able to add new service

  Background:
    Given I go to ShopMe main page
    And I push Add service button
    And I can see adding form

  Scenario Outline: Adding new service with at least one empty required field
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in basicPrice with "<basicPrice>"
    And I fill in basicDescription with "<basicDescription>"
    And I fill in name with "<name>"
    And I fill in email with "<email>"
    And I fill in phone with "<phone>"
    And I press Add service button
    Then I should see an error message "Pole wymagane" next to the required field
    And I should see a message "Proszê wype³niæ wszystkie wymagane pola"
    And I should see inserted values in filled fields

    Examples:
      | title      | category   | name     | email             | phone     | basicPrice | basicDescription |
      | Budowa     | budowa     |          | example@gmail.com | 000000000 | 10         | description      |
      | Budowa     | budowa     | testName | example@gmail.com | 000000000 |            | description      |
      | Budowa     | budowa     | testName |                   | 000000000 | 10         | description      |
      | Transport  | Transport  | testName | example@gmail.com |           | 10         | description      |
      | Fotografia | Fotografia | testName | example@gmail.com | 000000000 | 10         |                  |
      |            | Fotografia | testName | example@gmail.com | 000000000 | 10         | description      |
      | Budowa     |            | testName | example@gmail.com | 000000000 | 10         | description      |

  Scenario Outline: I add new service with extensions with one empty required field
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in basicDescription with "<basicDescription>"
    And I fill in basicPrice with "<basicPrice>"
    And I fill in expandedDescription with "<expandedDescription>"
    And I fill in expandedPrice with "<expandedPrice>"
    And I fill in extraDescription with "<extraDescription>"
    And I fill in extraPrice with "<extraPrice>"
    And I fill in name with "<name>"
    And I fill in email with "<email>"
    And I fill in phone with "<phone>"
    And I press Add service button
    Then I should see an error message "Pole wymagane" next to the required field
    And I should see a message "Proszê wype³niæ wszystkie wymagane pola"
    And I should see inserted values in filled fields

    Examples:
      | title              | category           | name     | email             | phone     | basicPrice | basicDescription | expandedPrice | extraPrice | expandedDescription | extraDescription |
      | Budowa             | budowa             | testName | example@gmail.com | 000000000 | 10         | description      | 20            |            |                     |                  |
      | Ogród              | ogród              | testName | example@gmail.com | 000000000 | 10         | description      | 20            | 20         | description         |                  |
      | Us³ugi warsztatowe | us³ugi warsztatowe | testName | example@gmail.com | 000000000 | 10         | description      |               |            | description         |                  |
      | Transport          | Transport          | testName | example@gmail.com | 000000000 | 10         | description      | 20            |            | description         | description      |


  Scenario Outline: Adding new service with invalid credentials
    When I fill in title with "<title>"
    And I choose category "<category>"
    And I fill in basicDescription with "description"
    And I fill in basicPrice with "10"
    And I fill in name with "<name>"
    And I fill in email with "<email>"
    And I fill in phone with "<phone>"
    And I press Add service button
    Then I should see form with inserted values in fields filled with valid data
    And I should see an error message "<errorMessage>" next to the field with invalid data

    Examples:
      | title      | category   | name            | email               | phone     | errorMessage
      | t          | budowa     | testName        | example@gmail.com   | 000000000 | Zbyt ma³a liczba znaków             |
      | Ogród      | ogród      | t               | example@gmail.com   | 000000000 | Zbyt ma³a liczba znaków             |
      | Transport  | transport  | testNa11        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam!        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam@        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam#        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam$        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam%        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam^        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam&        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam*        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam+        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Transport  | transport  | testNam=        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
	  | Transport  | transport  | testNam~        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
	  | Transport  | transport  | testNam`        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
	  | Transport  | transport  | testNam-        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
	  | Transport  | transport  | testNam_        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
	  | Transport  | transport  | testNam)        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
	  | Transport  | transport  | testNam(        | example@gmail.com   | 000000000 | Pole mo¿e zawieraæ tylko litery     |
      | Fotografia | fotografia | testName        | exampleemail.com    | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | example.email.com   | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | testemail           | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | @gmail.com          | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | test@test@gmail.com | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | test@com            | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | #@%^%#$@#$@#.com    | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | Abc..123@gmail.com  | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | .test@gmail.com     | 000000000 | Podaj prawid³owy adres e-mail       |
      | Fotografia | fotografia | testName        | test.@gmail.com     | 000000000 | Podaj prawid³owy adres e-mail       |
      | Budowa     | budowa     | testName        | example@gmail.com   | 10010010  | Zbyt ma³a liczba znaków             |
      | Budowa     | budowa     | testName        | example@gmail.com   | 10abcdegh | Pole powinno zawieraæ jedynie cyfry |
      | Budowa     | budowa     | testName        | example@gmail.com   | 00000000! | Pole powinno zawieraæ jedynie cyfry |
      | Budowa     | budowa     | testName        | example@gmail.com   | 00000000@ | Pole powinno zawieraæ jedynie cyfry |
      | Budowa     | budowa     | testName        | example@gmail.com   | 00000000# | Pole powinno zawieraæ jedynie cyfry |
      | Budowa     | budowa     | testName        | example@gmail.com   | 00000000* | Pole powinno zawieraæ jedynie cyfry |
      | Budowa     | budowa     | testName        | example@gmail.com   | 00000000% | Pole powinno zawieraæ jedynie cyfry |
      | Budowa     | budowa     | testName        | example@gmail.com   | 00000000^ | Pole powinno zawieraæ jedynie cyfry |
      | Budowa     | budowa     | testName        | example@gmail.com   | 00000000& | Pole powinno zawieraæ jedynie cyfry |
      | Budowa     | budowa     | testName        | example@gmail.com   | 00000000? | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000$ | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000+ | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000= | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000~ | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000` | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000- | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000_ | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000( | Pole powinno zawieraæ jedynie cyfry |
	  | Budowa     | budowa     | testName        | example@gmail.com   | 00000000) | Pole powinno zawieraæ jedynie cyfry |

	


  Scenario: Adding new basic service with 501 character description
    When I fill in title with "Us³ugi prawnicze"
    And I choose category "Prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with 501 characters
    Then I should see in basicDescription maximum 500 characters


  Scenario: Adding new service with extensions with 501 character description
    When I fill in title with "Us³ugi prawnicze"
    And I choose category "Prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with "description"
    And I fill in expandedDescription with 501 characters
    And I fill in expandedPrice with "300"
    And I fill in extraDescription with 501 characters
    And I fill in extraPrice with "400"
    Then I should see in expandedDescription maximum 500 characters
    And I should see in extraDescription maximum 500 characters

  Scenario: Adding new basic service with 801 character aboutMe description
    When I fill in title with "Us³ugi prawnicze"
    And I choose category "Prawo"
    And I fill in name with "testName"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "000000000"
    And I fill in basicPrice with "200"
    And I fill in basicDescription with "description"
    And I fill in aboutMe with 801 characters
    Then I should see in aboutMe maximum 800 characters

  Scenario: Inserting more than maximum number of characters in form fields
    When I fill in title with "Loremipsumdolorsitametturpisdui"
    And I choose category "Prawo"
    And I fill in name with "Loremipsumdolorsitame"
    And I fill in email with "test@gmail.com"
    And I fill in phone with "00000000000"
    And I fill in basicPrice with "100,002"
    And I fill in basicDescription with "description"
    Then I should see in title maximum 30 characters
    Then I should see in name  maximum 20 characters
    Then I should see in price maximum 2 decimal places
    And I should see in phone maximum 10 characters