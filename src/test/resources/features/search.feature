Feature: Search Functionality
  As a user, I would like to be able to search casino games

  Background:User navigates to homepage
   Given I navigate to homepage

    #This scenario tests if the search result contains only results that contain all the letters in the search term (order of the letters is not significant)
  @regression @author_sham
  Scenario Outline: User should be able to enter search terms and view corresponding results
    When I enter navigate to search option
    And I enter search terms from sheet name "<sheetName>" and row number "<rowNumber>"
    Then I am able to view search results containing all the letters in the search term from sheet name "<sheetName>" and row number "<rowNumber>" in the result

    Examples:
      | sheetName | rowNumber |
      | Sheet1    | 0         |
      | Sheet1    | 1         |


