Feature: Book Management API Tests
Scenarios to validate PUT request using Basic Authentication.

  # Scenario to test successful update of the author's name when book title is not changing
  Scenario: Update the author name by ID in both the link and body
    Given I logged in to the system with admin credentials
    When I send a PUT request to "/api/books/1" with body:
      """
      {
        "id": 1,
        "title": "Harry Potter",
        "author": "J. K. Rowling"
      }
      """
    Then I should receive a 200 status code

  # Scenario to validate the behavior when trying to update a book with a non-existent ID.
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

  # Scenario to test updating both book and author names.
  Scenario: Update both the book and author names by ID in the link and body
    Given I logged in to the system with admin credentials
    When I send a PUT request to "/api/books/2" with body:
      """
      {
        "id": 2,
        "title": "Sherlock Holmes",
        "author": "Sir Arthur Conan Doyle"
      }
      """
    Then I should receive a 200 status code

  # Scenario to check if the API returns a 400 status code when a mandatory field (title) is missing in the body.
  Scenario: Update a book without mandatory fields (title)
    Given I logged in to the system with admin credentials
    When I send a PUT request to "/api/books/3" with body:
       """
      {
        "id": 3,
        "author": "Richard Russell Riordan"
      }
      """
    Then I should receive a 400 status code

  # Scenario to validate that the API returns a 400 status code when the author field is missing in the body,
  # also to ensure 400 is returned even if the title already exists in the database.
  Scenario: Update a book without mandatory fields (author)
    Given I logged in to the system with admin credentials
    When I send a PUT request to "/api/books/3" with body:
       """
      {
        "id": 3,
        "title": "Percy Jackson"
      }
      """
    Then I should receive a 400 status code
