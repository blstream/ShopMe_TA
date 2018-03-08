package com.intive.patronage;
import com.intive.patronage.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public WebDriver driver;
    HomePage homePage;

    public DriverFactory(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
    public void Setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public HomePage getHomePage(){
        driver.get("http://automationpractice.com/");
        return homePage=new HomePage(driver);
    }

    public void destroyDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
