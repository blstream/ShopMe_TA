package com.intive.patronage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {

    WebDriver driver;
    ShoppingCartSummaryPage summaryPage;

    @FindBy(how = How.NAME, using = "Submit")
    private WebElement addButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void AddToCart() {
        addButton.click();

    }

    public ShoppingCartSummaryPage getSummaryPage() {
        driver.findElement(By.linkText("Proceed to checkout")).click();
        return summaryPage = new ShoppingCartSummaryPage(driver);
    }


}
