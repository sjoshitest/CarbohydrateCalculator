Feature: Carbohydrate Calculator

  Background:
    Given I launch the carbohydrate calculator

  @Smoke
  Scenario: Load calculator page successfully
    Then the calculator page should load with the main form visible

  @Smoke
  Scenario: Calculate with US units and default activity
    When I select the US unit system
    And I enter the following values:
      | AgeYears | HeightFeet | HeightInches | WeightLbs | Sex   |
      | 30       | 4          | 10           | 170       | Male  |
    And I click Calculate
    Then I should see a carbohydrate result

  @Smoke
  Scenario: Blank Age field throws validation error
    When I select the US unit system
    And I enter the following values:
      | AgeYears | HeightFeet | HeightInches | WeightLbs | Sex   |
      | 0       | 4          | 10           | 170       | Male  |
    And I click Calculate
    Then I should see validation message
