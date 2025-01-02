#Feature: Vegetable Category Navigation
#  As a user,
#  I want to navigate through vegetables categories
#  So that I can view products under vegetables category.
#
#  Scenario: Navigate to a category and verify products are displayed
#    Given I am on the Cargills Online homepage
#    When I click on the Vegetables category
#    Then I should see a list of products under Vegetables


Feature: Category Navigation
  As a user,
  I want to navigate through categories
  So that I can view products under those categories.

  Scenario Outline: Navigate to a category and verify products are displayed
    Given I am on the Cargills Online homepage
    When I click on the "<category>" category
    Then I should see a list of products under the "<category>"

    Examples:
      | category   |
      | Vegetables |
      | Fruits     |





