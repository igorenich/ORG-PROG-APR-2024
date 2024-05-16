package org.prog.cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.prog.cucumber.steps.WebSteps;
import org.prog.web.GooglePage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.prog.cucumber.steps"
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    private static WebDriver driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        CucumberRunner.driver = new RemoteWebDriver(
                new URL("http://selenoid-selenoid-1:4444/wd/hub"), remoteOptions());
        WebSteps.googlePage = new GooglePage(driver);
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown() {
        CucumberRunner.driver.quit();
    }

    private static Capabilities remoteOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return options;
    }
}
