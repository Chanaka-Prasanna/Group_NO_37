Feature: Login Page Testing
  This feature tests the login functionality by verifying different scenarios such as valid login,
   invalid credentials, and missing inputs.

Scenario Outline: Verify login functionality by valid and invalid credentials
  Given user is in the login page
  When user enters text: "<text>" and password: "<password>"
  And user clicks on the login button
  Then user should  see the my account section


  Examples:
  | text            | password    |
  | username        | password    |
  | admin           | admin123    |
  | user1           | pass1       |
  | test_user       | test@123    |
  | wrong_user      | wrong_pass  |
  |                 |             |
  | valid_user      |             |