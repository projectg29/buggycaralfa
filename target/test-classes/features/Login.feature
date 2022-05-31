@loginFeature
Feature: Login/Logout

  Scenario Outline: Login Buggy Cars Rating successful with correct credentials
    Given User is on home page
    When User enters credentials "<username>" "<password>"
    Then User should be able to see logout button

    Examples: 
      | username                     | password      |
      | rajan.velayuthan@outlook.com | Tenandone123! |