Feature: Search Bar Functionality

  Scenario Outline: User searches for multiple-word terms
    Given the user is on the homepage
    When the user searches for "<search_term>"
    Then the search results page for "<search_term>" should be displayed
    And search results for "<search_term>" should be displayed

    Examples:
      | search_term         |
      | apple               |
      | milk  |
      | beans     |
      | carrot |
