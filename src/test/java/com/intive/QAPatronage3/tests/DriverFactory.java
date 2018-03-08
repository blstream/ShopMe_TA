package com.intive.QAPatronage3.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    protected WebDriver driver;

        public DriverFactory() {
            System.setProperty("webdriver.chrome.driver", "C:/Users/Dell/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://automationpractice.com/");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    public void destroyDriver(){
        if(driver!=null){
            driver.quit();
            driver = null;
        }
    }
}
