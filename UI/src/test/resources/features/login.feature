Feature: Login Page Testing
  This feature tests the login functionality by verifying different scenarios such as valid login,
   invalid credentials, and missing inputs.

Scenario Outline: Verify login functionality by valid and invalid credentials
  Given user is in the login page
  When  user enters text: "<text>" and password: "<password>"
  And   user clicks on the login button
  Then  user should  see the my account section


  Examples:
  | text              | password    |
  | Chanaka           | Vx@123      |
  | 0765602490        | pass1       |
  | 0765602490        | Chanaka@123 |
  |                   |             |
  | valid_user        |             |
  |                   | Chanaka     |