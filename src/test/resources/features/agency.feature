Feature: Agency Page

  Scenario: Test new agency
    Given I open the AURORA add agency page
    Then Login into the AURORA application
    Then Fill basic user information section in agency page
      | agency name         | agency test     |
      | company name DBA    | company test    |
      | accounting email    | agency@test.com |
      | producer identifier | producer test   |
      | street              | 2819            |
      | phone               | 55555555        |
    Then Fill carriers in agency page
      | carriers | All |
    Then Fill error and omissions section in agency page
      | carrier name    | carrier TEST |
      | policy number   | 12345        |
      | limit           | limit test   |
      | expiration date | 10281990     |
    Then Fill operations for approval in agency page
      | operations            | All |
      | submit for approval   | yes |
      | require bind physical | yes |
      | require bind cargo    | yes |
      | select block agents   | yes |
    Then Fill underwriters in agency page
      | underwritter | All |
    Then Fill office in agency page
      | office | All |
#    Then Click register button
    Then Just wait 10000