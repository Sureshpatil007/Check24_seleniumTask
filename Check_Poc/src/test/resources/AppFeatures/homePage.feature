Feature: Selenium Test Task

  Scenario: To verify the Check24 functionality
    Given I click on cookie button
    When I verify cookie
    And I click on Further button
    And I Enter Email id
    And I click on Ich möchte als Gast fortfahren button
    Then I Verify all the error messages
    When I Enter all details and click on further button
    Then I should see No Error message
