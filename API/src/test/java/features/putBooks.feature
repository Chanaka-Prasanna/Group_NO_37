Feature: Book Management API Tests
Scenarios to validate PUT request using Basic Authentication.

  Scenario: Update the author name by ID in both the link and body
    Given I am an Admin with Basic Authentication
    When I send a PUT request to "/api/books/123" with body:
      """
      {
        "author": "New Author"
      }
      """
    Then I should receive a 200 status code

  Scenario: TC-015 Update the book name (existing name) by a non-existent ID in both the link and body
    Given I am an Admin with Basic Authentication
    When I send a PUT request to "/api/books/9999" with body:
      """
      {
        "title": "Existing Book Name"
      }
      """
    Then I should receive a 208 status code

  Scenario: Update both the book and author names by ID in the link and body
    Given I am an Admin with Basic Authentication
    When I send a PUT request to "/api/books/2" with body:
      """
      {
        "id": 2,
        "title": "Pride and Prejudiceaa sd",
        "author": "Mr. Jane Austenfaa sd"
      }
      """
    Then I should receive a 200 status code

  Scenario: TC-021 Update a book without mandatory fields (title, author)
    Given I am an Admin with Basic Authentication
    When I send a PUT request to "/api/books/123" with body:
      """
      {}
      """
    Then I should receive a 400 status code

  Scenario: TC-001 Update the book name by ID in both the link and body
    Given I am an Admin with Basic Authentication
    When I send a PUT request to "/api/books/123" with body:
      """
      {
        "title": "Updated Book Name"
      }
      """
    Then I should receive a 200 status code
