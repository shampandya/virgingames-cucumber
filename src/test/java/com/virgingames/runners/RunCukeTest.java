package com.virgingames.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/virgingames/steps",
        plugin = {"html:target/cucumber-reports/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags="@regression"
)

//run from multibrowsertest.xml for multi-browser testing
public class RunCukeTest extends AbstractTestNGCucumberTests {

    //Create a thread-safe container to store the value of browser
    public final static ThreadLocal<String> BROWSER = new ThreadLocal<>();

    @BeforeMethod
    @Parameters({"browser"}) //passing parameter from .xml runner file
    public void defineBrowser(@Optional("chrome") String browser) {
        RunCukeTest.BROWSER.set(browser);
    }

}
