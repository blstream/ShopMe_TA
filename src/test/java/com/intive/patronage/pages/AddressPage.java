package com.intive.patronage.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddressPage {


    @FindBy(how = How.ID, using = "order")
    public WebElement addressPage;

    WebDriver driver;
    ShippingPage shippingPage;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ShippingPage getShippingPage() {
        driver.findElement(By.name("processAddress")).click();
        return shippingPage = new ShippingPage(driver);
    }

    public void VerifyIfAddressPageIsDisplayed() {
        assert (addressPage.isDisplayed());
    }
}
