Feature: Filter Menu Functionality

  Scenario Outline: Apply filters and validate the results
    Given user is on the fruits listing page
    When user selects "<filterOption>" from "<filterType>" filter
    Then only products related to "<filterOption>" are displayed

    Examples:
      | filterType      | filterOption            |
      | Price           | Price - Low to High     |
      | Price           | Price - High to Low     |
      | Alphabetical    | A - Z                   |
      | Alphabetical    | Z - A                   |

  Scenario: Clear all filters after applying a combination
    Given user is on the fruits listing page
    When user selects "A - Z" from "Alphabetical" filter
    And user selects "Price - High to Low" from "Price" filter
    And user clicks on clear all
    Then all products are displayed
