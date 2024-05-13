package org.prog.cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.prog.cucumber.steps.WebSteps;
import org.prog.web.GooglePage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.prog.cucumber.steps"
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    private static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        CucumberRunner.driver = new ChromeDriver();
        WebSteps.googlePage = new GooglePage(driver);
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown() {
        CucumberRunner.driver.quit();
    }
}
