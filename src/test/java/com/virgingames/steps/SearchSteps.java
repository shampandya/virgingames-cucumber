package com.virgingames.steps;

import com.virgingames.browserfactory.ManageBrowser;
import com.virgingames.excelutility.ExcelReader;
import com.virgingames.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SearchSteps {
    private static final Logger log = LogManager.getLogger(ManageBrowser.class);

    HomePage homePage = new HomePage();
    ExcelReader reader = new ExcelReader();


    @When("I enter navigate to search option")
    public void iEnterNavigateToSearchOption() {
        log.info("Clicking on Allow All Cookies button....");
        homePage.clickOnAllowAllCookiesButton();
        log.info("Clicking on Search icon....");
        homePage.clickOnSearchIcon();
    }

    @And("I enter search terms from sheet name {string} and row number {string}")
    public void iEnterSearchTermsFromSheetNameAndRowNumber(String sheetName, String rowNumber) {


        try {
            List<Map<String, String>> testData = reader.getData("src/test/resources/testdata/search-data.xlsx", sheetName);
            log.info("Entering search term in the search bar....");
            homePage.enterSearchTerms(testData.get(Integer.parseInt(rowNumber)).get("searchTerm"));

        } catch (IOException e) {
            System.out.println("Test data file not found.");
        }


    }

    @Then("I am able to view search results containing all the letters in the search term from sheet name {string} and row number {string} in the result")
    public void iAmAbleToViewSearchResultsContainingAllTheLettersInTheSearchTermFromSheetNameAndRowNumberInTheResult(String sheetName, String rowNumber) {
        String searchTerm = null;
        try {
            List<Map<String, String>> testData = reader.getData("src/test/resources/testdata/search-data.xlsx", sheetName);
            searchTerm = testData.get(Integer.parseInt(rowNumber)).get("searchTerm");
            // searchTerm="liveb";
            System.out.println("Search term :" + searchTerm);
        } catch (IOException e) {
            System.out.println("Test data file not found.");
        }
        List<String> resultTitles = homePage.getSearchResult();
        log.info("Verifying if the search results contain all the search term letters.... ");
        Assert.assertTrue(isSearchCharactersInResult(resultTitles, searchTerm));
    }


    /**
     * This method loops through all the search results and checks if they contain all the letters in the search term.
     * If any result does not contain all the search term letters, the validation fails and the bug is raised
     */
    public boolean isSearchCharactersInResult(List<String> resultTitles, String searchTerm) {
        boolean isFound = true;

        for (String resultTitle : resultTitles) {
            resultTitle = resultTitle.toLowerCase();
            for (int i = 0; i < searchTerm.length(); i++) {
                if (!resultTitle.contains((Character.toString(searchTerm.charAt(i))))) {
                    System.out.println("This search result title does not include all characters of search term: " + resultTitle);
                    isFound = false;
                    break;
                }
            }
        }
        return isFound;
    }
}
