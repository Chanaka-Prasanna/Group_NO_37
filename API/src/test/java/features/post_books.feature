Feature: Book Management API Tests
Scenarios To validate POST request using Basic Authentication.

    # This scenario tests the successful registration of a new book when a title and author are provided.
    # The request includes the book ID, title, and author, and the expected result is a 201 status code indicating successful creation.
    # The test will ensure that the system correctly processes the POST request and creates the book in the system.

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

    # This scenario tests the registration of a new book where the book ID is not explicitly provided.
    # The system should automatically generate a unique ID for the new book.
    # The expected result is a 201 status code, indicating that the book was created successfully.

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

    # This scenario validates the case where the user tries to register a new book with a title that already exists in the system.
    # The system should return a 208 status code to indicate that the book title already exists and no new book can be created.
    # This test ensures that the system handles duplicate book titles correctly by returning an appropriate response.

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

    # This scenario checks the case where only the author field is provided for a book and the title is omitted.
    # Since the book title is a mandatory field, the system should return a 400 status code with an error message about the missing book title.
    # This test ensures that the system validates the presence of all mandatory fields and returns the correct error response.

  Scenario: Register a new book by only providing an author
    Given I logged in to the system with user credentials
    When I send a POST request to "/api/books" with body:
      """
      {
          "author": "Jerome David Salinger"
      }
      """
    Then I should receive a 400 status code

    # This scenario tests the case where a user tries to register a new book using an existing book ID.
    # The system should return a 409 status code to indicate a conflict because the book ID is already in use.
    # This test verifies that the system properly handles attempts to reuse an existing ID for a new book.

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