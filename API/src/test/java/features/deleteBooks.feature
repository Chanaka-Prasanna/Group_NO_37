Feature: Book Management API Tests
Scenarios to validate DELETE request using Basic Authentication.

  Scenario: Delete a book by ID as an Admin
    Given I am an Admin with Basic Authentication
    When I send a DELETE request to "/api/books/2"
    Then I should receive a 200 status code