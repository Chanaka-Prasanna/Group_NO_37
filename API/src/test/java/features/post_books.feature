Feature: Book Management API Tests
Scenarios To validate POST request using Basic Authentication.
  Scenario: Register a new book with a new title and an author
    Given I logged in to the system with user credentials
    When I send a POST request to "/api/books" with body:
      """
      {
        "id": 6,
        "title": "The Adventures of Sherlock Holmes",
        "author": "Arthur Conan Doyle"
      }
      """
    Then I should receive a 201 status code

  Scenario: Register a new book with a new title and an author without providing id explicitly
    Given I logged in to the system with user credentials
    When I send a POST request to "/api/books" with body:
      """
      {
         "title": "The Hobbit",
         "author": "J.R.R. Tolkien"
      }
      """
    Then I should receive a 201 status code
    
  Scenario: Register a book with existing book title
    Given I logged in to the system with user credentials
    When I send a POST request to "/api/books" with body:
      """
        {
          "title": "The Hobbit",
          "author": "J.R.R. Tolkien"
        }
      """
    Then I should receive a 208 status code

  Scenario: Register a new book by only providing an author
    Given I logged in to the system with user credentials
    When I send a POST request to "/api/books" with body:
      """
      {
          "author": "Jerome David Salinger"
      }
      """
    Then I should receive a 400 status code

  Scenario: Register a new book with a new title and an author by using an existing id
    Given I logged in to the system with user credentials
    When I send a POST request to "/api/books" with body:
      """
      {
        "id": 4,
        "title": "The Great Gatsby",
        "author": "F. Scott Fitzgerald"
      }
      """
    Then I should receive a 409 status code