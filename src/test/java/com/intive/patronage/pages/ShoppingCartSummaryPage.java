package com.intive.patronage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartSummaryPage {
    WebDriver driver;
    AddressPage addressPage;

    @FindBy(how = How.ID, using = "order")
    private WebElement order;

    public ShoppingCartSummaryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AddressPage getAddressPage() {

        driver.findElement(By.linkText("Proceed to checkout")).click();
        return addressPage = new AddressPage(driver);
    }

    public void VerifyIfShoppingCartSummaryPageIsDisplayed() throws InterruptedException {
        assert (order.isDisplayed());
    }


}
