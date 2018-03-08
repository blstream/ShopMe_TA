package com.intive.patronage.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {
    WebDriver driver;

    @FindBy(how = How.ID, using = "order-confirmation")
    WebElement confirmation;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void VerifyIfConfirmationMessageIsDisplayed() {
        assert (confirmation.isDisplayed());
    }
}
