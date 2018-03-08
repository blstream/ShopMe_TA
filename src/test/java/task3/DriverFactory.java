package task3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class DriverFactory {
    protected WebDriver driver;

    public DriverFactory(){
        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");
    }

    public void destroyDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
    }}


}
