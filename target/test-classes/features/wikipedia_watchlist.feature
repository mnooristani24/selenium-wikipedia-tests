Feature: Wikipedia Watchlist Functionality

  Scenario: Add two pages to the watchlist
    Given the user is logged into Wikipedia
    When the user adds "Artificial Intelligence" to their watchlist
    And the user adds "Machine Learning" to their watchlist
    Then the user should see "Artificial Intelligence" and "Machine Learning" in their watchlist

  Scenario: Remove one article from the watchlist
    Given the user has "Artificial Intelligence" and "Machine Learning" in their watchlist
    When the user removes "Artificial Intelligence" from their watchlist
    Then the user should not see "Artificial Intelligence" in their watchlist
    And the user should see "Machine Learning" in their watchlist

  Scenario: Ensure the second article is still in the watchlist
    Given the user has removed "Artificial Intelligence" from their watchlist
    Then the user should see "Machine Learning" in their watchlist

  Scenario: Ensure the title matches the article in the watchlist
    Given "Machine Learning" is in the user's watchlist
    When the user goes to the "Machine Learning" article from their watchlist
    Then the title should be "Machine Learning"