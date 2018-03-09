package com.intive.academy.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "login")
    private WebElement signInButton;

    @FindBy(how = How.CLASS_NAME, className = "header_user_info")
    private WebElement resultsWebPart;

    @FindBy(how = How.CSS, using = "div.product-container")
    private WebElement productContainer;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToLoginForm() {
        signInButton.click();
    }

    public boolean checkIfSignedIn() {
        if (resultsWebPart.getAttribute("class").contains("logout")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(productContainer));
        if (productContainer.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void chooseFirstItem() {
        checkIfPageLoaded();
        List<WebElement> list = driver.findElements(By.cssSelector("div.product-container"));
        list.get(0).click();
    }
}
