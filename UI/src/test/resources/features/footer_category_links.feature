Feature: Verify footer category links

  Scenario Outline: Verify category links navigate to the correct pages
    Given I am on the homepage
    When I click on the "<category>" link in the footer
    Then I should be navigated to the "<category>"

    Examples:
      | category     |
      | Gifting      |
      | Stationery   |
