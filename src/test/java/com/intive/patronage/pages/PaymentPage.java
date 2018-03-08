package com.intive.patronage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

    WebDriver driver;
    OrderConfirmationPage orderConfirmationPage;
    @FindBy(how = How.ID, using = "order")
    public WebElement paymentPage;
    @FindBy(how = How.CLASS_NAME, using = "cheque")
    WebElement pay;


    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public OrderConfirmationPage getOrderConfirmationPage() {
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
        return orderConfirmationPage = new OrderConfirmationPage(driver);
    }

    public void Payment() {
        pay.click();
    }

    public void VerifyIfPaymentPageIsDisplayed() {
        assert (paymentPage.isDisplayed());
    }


}
