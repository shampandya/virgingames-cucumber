package com.virgingames.utility;

import com.google.common.base.Function;
import com.virgingames.browserfactory.ManageBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Utility extends ManageBrowser {

    /*Utility Class extends to ManageDriver for the driver to finding locators
     *All common methods are fixed in the utility Class.
     */

    /*
     * This method will generate random number
     */
    public int generateRandomNumber() {
        return (int) (Math.random() * 5000 + 1);
    }

    /**
     * This method will generate random string
     */

    public String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * length);
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }


    /**
     * This method will click on element
     */
    public void clickOnElement(WebElement element) {
        element.click();
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    /**
     * This method will send text on element
     */
    public void sendTextToElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    //************************* Alert Methods *****************************************************

    /**
     * This method will switch to alert
     */
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    /**
     * This method will accept alert
     */
    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /**
     * This method will dismiss alert
     */
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * This method will get text from alert
     */
    public String getTextFromAlert() {
        return driver.switchTo().alert().getText();
    }

    /**
     * This method will send text from alert
     */
    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    //*************************** Select Class Methods ***************************************//

    /**
     * This method will select the option by visible text
     */
    public void selectByVisibleTextFrommDropDown(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /**
     * This method will select the option by value
     */
    public void selectByValueFromDropDown(WebElement element, String value) {
        new Select(element).selectByValue(value);

    }

    /**
     * This method will select the option by index
     */
    public void selectByIndexFromDropDown(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    /**
     * This method will select the option by contains text
     */
    public void selectByContainsTextFromDropDown(WebElement element, String text) {
        List<WebElement> allOptions = new Select(element).getOptions();
        for (WebElement option : allOptions) {
            if (option.getText().contains(text)) {
                option.click();
            }
        }
    }

//*************************** Window Handle Methods ***************************************//

    /**
     * This method will close all windows
     */

    public void closeAllWindows(List<String> hList, String parentWindow) {
        for (String handle : hList) {
            if (handle != parentWindow) {
                driver.switchTo().window(handle).close();
            }
        }
    }

    /**
     * This method will switch to parent window
     */
    public void switchToParentWindow(String parentWindow) {
        driver.switchTo().window(parentWindow);
    }

    /**
     * This method will find that we switch to right window
     */
    public boolean switchToRightWindow(List<String> hList, String windowTitle) {
        for (String str : hList) {
            String title = driver.switchTo().window(str).getTitle();
            if (title.contains(windowTitle)) {
                System.out.println("Found the right window");
                return true;
            }
        }
        return false;
    }

//*************************** Action Methods ***************************************//

    /**
     * This method will use to hover mouse on element
     */

    public void mouseHoverToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    /**
     * This method will use to hover mouse on element and click
     */
    public void mouseHoverToElementAndClick(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().build().perform();
    }

    public void selectAndClearInputField(WebElement element){
        Actions actions =new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        actions.keyDown(Keys.CONTROL).sendKeys("d").keyUp(Keys.CONTROL).build().perform();
    }
    //************************** Waits Methods *********************************************//

    /**
     * This method will use to wait until  VisibilityOfElementLocated
     */

    public WebElement waitUntilVisibilityOfElementLocated(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitUntilVisibilityOfElementLocated(List<WebElement> elements, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public WebElement waitUntilElementIsClickable(WebElement element, int time){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementWithFluentWait(By by, int time, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(time))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return element;
    }



//************************** ScreenShot Methods *********************************************//

    public static String currentTimeStamp() {
        Date date = new Date();
        return date.toString().replace(":", "_").replace(" ", "_");
    }

    public static String takeScreenShot(String fileName) {
        String filePath = System.getProperty("user.dir") + "/test-output/html/";  //generate a folder for screenshots
        TakesScreenshot screenshot = (TakesScreenshot) driver; //type cast driver with TakesScreenshot interface
        File scr1 = screenshot.getScreenshotAs(OutputType.FILE); //method getScreenshotAs()
        String imageName = fileName + currentTimeStamp() + ".jpg"; //generate a filename
        String destination = filePath + imageName;
        try {
            FileUtils.copyFile(scr1, new File(destination)); //copy screenshot into new file in destination
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }


    //get screenshot
    public static byte[] getScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


}
