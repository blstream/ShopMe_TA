package com.intive.academy.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductControllerPage extends BasePage {

    @FindBy(how = How.CSS, using = "p#add_to_cart")
    private WebElement addToCartButton;

    @FindBy(how = How.CSS, using = "a.btn.btn-default.button.button-medium")
    private WebElement confirmButton;


    public ProductControllerPage(WebDriver driver) {
        super(driver);
    }


    public boolean verifyIfPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        if (addToCartButton.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyIfAddedToCart() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(confirmButton));
        if (addToCartButton.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void addItemToCart() {
        addToCartButton.click();
        verifyIfAddedToCart();
        confirmButton.click();
    }
}
