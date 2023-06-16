Feature: Users Page POC

  Scenario: Test New user page
    Given I open the AURORA add users page
    Then Login into the AURORA application
    And Fill new user section
      | role        | Producer             |
      | first name  | Alirio               |
      | middle name | Jose                 |
      | last name   | Becerra              |
      | email       | aliriob@advancio.com |
#    Then Click register button
    Then Just wait 10000

  Scenario: edit user page
    Given I open the AURORA edit users page
    Then Login into the AURORA application
    And Fill edit user section
      | first name  | Alirio               |
      | middle name | Jose                 |
      | last name   | Becerra              |
#    Then Click register button
    Then Just wait 10000