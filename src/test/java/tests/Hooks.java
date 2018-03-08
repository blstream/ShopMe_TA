package tests;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks {

    public static WebDriver driver;
    public static WebDriverWait wait;
    String baseUrl = "http://automationpractice.com";

    @Before(order = 1)
    public void beforeScenario() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        System.out.println("This will run before the every Scenario");
    }

    @Before(order = 0)
    public void beforeScenarioStart() {
        System.out.println("-----------------Start of Scenario-----------------");
    }

    @After(order = 0)
    public void afterScenarioFinish() {
        System.out.println("-----------------End of Scenario-----------------");
    }

    @After(order = 1)
    public void afterScenario() {
        driver.quit();
        System.out.println("This will run after the every Scenario");
    }
}