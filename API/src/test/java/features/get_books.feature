Feature: Book Management API Tests
Scenarios to validate GET request using Basic Authentication.

  Scenario: Fetch book details for a valid book ID as a User
    Given I logged in to the system with user credentials
    When I send a GET request to "/api/books/1"
    Then I should receive a 200 status code

  Scenario: Fetch book details for a invalid book ID as a User
    Given I logged in to the system with user credentials
    When I send a GET request to "/api/books/100"
    Then I should receive a 404 status code

  Scenario: Fetch all book details as an Admin
    Given I logged in to the system with admin credentials
    When I send a GET request to "/api/books"
    Then I should receive a 200 status code

  Scenario: Fetch book details for a invalid book ID as an Admin
    Given I logged in to the system with admin credentials
    When I send a GET request to "/api/books/100"
    Then I should receive a 404 status code

    # Check API access when a user does not provide a username and password
  Scenario: Fetch book details for a valid book ID as a non registered User
    Given I am a User without Basic Authentication
    When I send a GET request to "/api/books/1"
    Then I should receive a 401 status code