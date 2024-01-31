package com.virgingames.steps;

import com.virgingames.propertyreader.PropertyReader;
import com.virgingames.runners.RunCukeTest;
import com.virgingames.utility.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.Parameters;

public class Hooks extends Utility {
    String browser = PropertyReader.getInstance().getProperty("browser");

    //This method opens the browser before every test method
    @Before
    @Parameters({"browser"})
    public void setUp() {
        String browser= RunCukeTest.BROWSER.get();
        selectBrowser(browser);
    }

    //This method closes the browser after every test method
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = getScreenShot();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
       closeBrowser();
    }

}
