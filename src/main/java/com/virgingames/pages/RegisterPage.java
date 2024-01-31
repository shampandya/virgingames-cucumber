package com.virgingames.pages;

import com.virgingames.utility.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegisterPage extends Utility {
    //Elements
    @CacheLookup
    @FindBy(name = "email")
    WebElement emailAddress;

    @CacheLookup
    @FindBy(name = "title")
    WebElement titleDropDown;

    @CacheLookup
    @FindBy(name = "firstName")
    WebElement firstName;
    @CacheLookup
    @FindBy(name = "surname")
    WebElement surname;
    @CacheLookup
    @FindBy(id = "dobDay")
    WebElement dobDay;
    @CacheLookup
    @FindBy(id = "dobMonth")
    WebElement dobMonth;
    @CacheLookup
    @FindBy(id = "dobYear")
    WebElement dobYear;
    @CacheLookup
    @FindBy(name = "gender")
    List<WebElement> genders;
    @CacheLookup
    @FindBy(xpath = "//button[text()='Next Step']")
    WebElement nextStepButton;

    @CacheLookup
    @FindBy(xpath = "//div[text()='Username / Password']")
    WebElement userNamePasswordHeading;


    //Methods
    public void enterEmail(String email) {
        sendTextToElement(emailAddress, email);
    }

    public void selectTitleFromDropDown(String title) {
        selectByVisibleTextFrommDropDown(titleDropDown, title);
    }

    public void enterFirstName(String fName) {
        sendTextToElement(firstName, fName);
    }

    public void enterSurname(String sName) {
        sendTextToElement(surname, sName);
    }

    public void enterDateOfBirth(String date, String month, String year) {
        sendTextToElement(dobDay, date);
        sendTextToElement(dobMonth, month);
        sendTextToElement(dobYear, year);
    }

    public void selectGender(String gen) {
        for (WebElement gender : genders) {
            if (getTextFromElement(gender).equalsIgnoreCase(gen)) {
                clickOnElement(gender);
                break;
            }
        }
    }

    public void clickOnNextStep() {
        clickOnElement(nextStepButton);
    }

    public String getUserNamePasswordHeadingText() {
        return getTextFromElement(userNamePasswordHeading);
    }

}
