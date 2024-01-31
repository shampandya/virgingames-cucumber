package com.virgingames.pages;

import com.virgingames.utility.Utility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Utility {

    //Elements
    @CacheLookup
    @FindBy(xpath = "//label[text()='Allow all cookies']")
    WebElement allowAllCookiesButton;
    @CacheLookup
    @FindBy(xpath = "//li/a/span[text()='Live Casino']")
    WebElement liveCasinoLink;
    @CacheLookup
    @FindBy(xpath = "//a[@class='StyledRouterLink-sc-1f8on1q-1 ceEHhK']/span")
    List<WebElement> topMenuTabs;


    @CacheLookup
    @FindBy(css = "div.StyledSearchIcon-sc-18uxf75-7")
    WebElement searchIcon;

    @CacheLookup
    @FindBy(xpath = "//a[text()='Login']")
    WebElement loginLink;

    @CacheLookup
    @FindBy(xpath = "//input[@class='StyledSearchInput-sc-2n0l6s-5 fjYljZ']")
    WebElement searchBar;

    @CacheLookup
    @FindBy(xpath = "//div[@class='ResultTitle-sc-cxmw95-4 eRCKim']")
    List<WebElement> searchResults;


    //Methods
    public void clickOnAllowAllCookiesButton() {
        waitUntilVisibilityOfElementLocated(allowAllCookiesButton, 10);
        clickOnElement(allowAllCookiesButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
    }

    public void clickOnSearchIcon() {
        clickOnElement(searchIcon);
    }

    public void enterSearchTerms(String searchTerm) {
        clickOnElement(searchBar);
        sendTextToElement(searchBar, searchTerm);
    }

    public List<String> getSearchResult() {
        List<String> resultTitles = new ArrayList<>();
        for (WebElement result : searchResults) {
            resultTitles.add(getTextFromElement(result));
        }
        //System.out.println("Result titles: "+ resultTitles);
        return resultTitles;
    }

    public void clickOnLoginLink() {
        clickOnElement(loginLink);
    }

    /**
     * This method finds the required link from top menu and clicks on it
     */
    public void clickOnTopMenuTabLink(String link){
        clickOnElement(allowAllCookiesButton);
        for(WebElement topMenuTab:topMenuTabs){
            if(getTextFromElement(topMenuTab).equalsIgnoreCase(link)){
                clickOnElement(topMenuTab);
                break;
            }
        }
    }
    public void clickOnLiveCasinoLink() {
        clickOnElement(liveCasinoLink);
    }
}
