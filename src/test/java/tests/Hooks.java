package tests;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static ChromeOptions options;

    @Before(order = 1)
    public void initializeDriver() {
        String oS = System.getProperty("os.name").toLowerCase();
        if (oS.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/2.36/chromedriver.exe");
        } else if (oS.contains("mac")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/2.36/chromedriver.mac");
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/2.36/chromedriver");
        }
        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 5);
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
    public void destroyDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        System.out.println("This will run after the every Scenario");
    }
}
