Feature: Book Management API Tests
Scenarios to validate PUT request using Basic Authentication.

  Scenario: Update the author name by ID in both the link and body
    Given I logged in to the system with admin credentials
    When I send a PUT request to "/api/books/2" with body:
      """
      {
        "id": 2,
        "title": "Harry Potter",
        "author": "J. K. Rowling"
      }
      """
    Then I should receive a 200 status code

  Scenario: Update the book name (existing name) by a non-existent ID in both the link and body
    Given I logged in to the system with admin credentials
    When I send a PUT request to "/api/books/9999" with body:
      """
      {
         "id": 9999,
        "title": "Harry Potter",
        "author": "J. K. Rowling"
      }
      """
    Then I should receive a 404 status code

  Scenario: Update both the book and author names by ID in the link and body
    Given I logged in to the system with admin credentials
    When I send a PUT request to "/api/books/2" with body:
      """
      {
        "id": 2,
        "title": "Harry Potter g",
        "author": "J. K. Rowling"
      }
      """
    Then I should receive a 200 status code

  Scenario: Update a book without mandatory fields (title)
    Given I logged in to the system with admin credentials
    When I send a PUT request to "/api/books/2" with body:
       """
      {
        "id": 2,
        "author": "J. K. Rowling"
      }
      """
    Then I should receive a 400 status code

