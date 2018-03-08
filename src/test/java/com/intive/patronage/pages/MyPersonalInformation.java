package com.intive.patronage.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import java.util.List;


public class MyPersonalInformation {

    WebDriver driver;

    @FindBy(how = How.ID, using = ("identity"))
    public WebElement myPersonalInformationWebPart;


    public MyPersonalInformation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void VerifyIfMyPersonalInformationPageisDisplayed() throws InterruptedException {
        assert (myPersonalInformationWebPart.isDisplayed());
    }

    public void VerifyFirstNameField(String name) throws InterruptedException {
        boolean foundMatch = false;
        List<WebElement> results = driver.findElements(By.id("firstname"));
        for (WebElement element : results) {
            String val = element.getAttribute("value");
            if (val.contains(name)) {
                foundMatch = true;
                break;
            }
        }
        assert (foundMatch);
    }

    public void VerifyLastNameField(String lname) throws InterruptedException {
        boolean foundMatch = false;
        List<WebElement> results = driver.findElements(By.id("lastname"));
        for (WebElement element : results) {
            String val = element.getAttribute("value");
            if (val.contains(lname)) {
                foundMatch = true;
                break;
            }
        }
        assert (foundMatch);

    }

    public void VerifyEmailField(String email) throws InterruptedException {
        boolean foundMatch = false;
        List<WebElement> results = driver.findElements(By.id("email"));
        for (WebElement element : results) {
            String val = element.getAttribute("value");
            if (val.contains(email)) {
                foundMatch = true;
                break;
            }
        }
        assert (foundMatch);
    }

}
