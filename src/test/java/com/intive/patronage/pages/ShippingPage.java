package com.intive.patronage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage {
    @FindBy(how = How.ID, using = "cgv")
    private WebElement acceptCheckBox;
    @FindBy(how = How.ID, using = "order")
    public WebElement shipping;
    WebDriver driver;
    com.intive.patronage.pages.PaymentPage PaymentPage;

    public ShippingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public com.intive.patronage.pages.PaymentPage getPaymentPage() {
        driver.findElement(By.name("processCarrier")).click();
        return PaymentPage = new PaymentPage(driver);
    }

    public void Agree() {

        acceptCheckBox.click();
    }

    public void VerifyIfShippingPageIsDisplayed() {
        assert (shipping.isDisplayed());
    }

}
