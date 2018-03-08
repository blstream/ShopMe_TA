package com.intive.patronage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    WebDriver driver;
    AuthenticationPage authenticationPage;
    ProductPage productPage;
    ShoppingCartSummaryPage shoppingCartSummaryPage;

    @FindBy(how = How.LINK_TEXT, using = "Sign out")
    public WebElement signOut;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public ProductPage getProductPage() {
        driver.findElement(By.className("product-container")).click();
        return productPage = new ProductPage(driver);
    }

    public AuthenticationPage getAuthenticationPage() {
        driver.findElement(By.className("login")).click();
        return authenticationPage = new AuthenticationPage(driver);

    }

    public void SignOut() {
        signOut.click();
    }


}
