Feature: Book Management API Tests
Scenarios to validate DELETE request using Basic Authentication.

  Scenario: Delete a book by ID as an Admin
    Given I logged in to the system with admin credentials
    When I send a DELETE request to "/api/books/2"
    Then I should receive a 200 status code

  Scenario: Delete a book by ID as a User
    Given I logged in to the system with user credentials
    When I send a DELETE request to "/api/books/2"
    Then I should receive a 403 status code

  Scenario: Delete a non existing book by ID as a User
    Given I logged in to the system with user credentials
    When I send a DELETE request to "/api/books/1"
    Then I should receive a 403 status code

  Scenario: Delete a non existing book by ID as an Admin
    Given I logged in to the system with admin credentials
    When I send a DELETE request to "/api/books/9"
    Then I should receive a 404 status code