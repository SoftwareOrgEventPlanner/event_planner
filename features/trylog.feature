Feature: Admin logging
  Admin during his logging into the system goes through several cases


  Scenario: successful login to the program
    Given that the admin is not logged in
    When set username "hala" and pass "123"
    Then the login operation succeeds


  Scenario: invalid username
    Given that the admin is not logged in
    When set invalid username "halaw" and pass "123"
    Then the login operation fails


  Scenario: invalid password
    Given that the admin is not logged in
    When set valid username "hala" and invalid pass "1234"
    Then the login operation fails

  Scenario: blank username
    Given I am not in system
    When set empty username "" and pass "1234"
    Then login failed

  Scenario: blank password
    Given I am not in system
    When set valid username "hala" and empty pass ""
    Then login failed


  Scenario: forgot password
    Given that the admin is not logged in
    When set valid username "hala" and  pass "Forget"
    Then take new pass "12345"

  Scenario: User needs to Create Account
    Given I am not in system
    And i don't have an account
    When set new username "noor" and pass "4321" and bd="12\6\2003"
    Then create succeed