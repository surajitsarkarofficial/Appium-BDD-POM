@Login
Feature: Login Scenarios
  @Login
  Scenario Outline: Login with invalid username
    Given I am on the login page
    When I enter the username "<username>"
    And I enter the password "<password>"
    And I login
    Then Login should fail with an error "<errMsg>"
    Examples:
      | username | password | errMsg |
      | invUser  | sauce_user | Username and password do not match any user in this service.  |

  Scenario Outline: Login with invalid password
    Given I am on the login page
    When I enter the username "<username>"
    And I enter the password "<password>"
    And I login
    Then Login should fail with an error "<errMsg>"
    Examples:
      | username | password | errMsg |
      | standard_user  | invPwd | Username and password do not match any user in this service.  |

  Scenario Outline: Login with valid username and password
    Given I am on the login page
    When I enter the username "<username>"
    And I enter the password "<password>"
    And I login
    Then I should see Products page with title "<title>"
    Examples:
      | username | password | title |
      | standard_user  | secret_sauce | PRODUCTS  |