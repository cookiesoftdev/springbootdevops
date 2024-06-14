Feature: Category Service

  Scenario: Find all categories
    Given there are categories in the system
    When I request all categories
    Then I should receive a list of categories

  Scenario: Find category by id
    Given there is a category with id 1
    When I request the category with id 1
    Then I should receive the category with id 1

  Scenario: Category not found by id
    Given there is no category with id 99
    When I request the category with id 99
    Then I should receive a resource not found error