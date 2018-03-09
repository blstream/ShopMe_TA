package com.intive.academy.tests.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    protected static WebDriver driver;

    public DriverFactory() {
        System.setProperty("webdriver.chrome.driver", "C:\\devTools\\chromedriver\\chromedriver.exe");
    }

    public void initChromeDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.get("http://automationpractice.com");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
}
