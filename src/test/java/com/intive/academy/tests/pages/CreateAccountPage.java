package com.intive.academy.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage {
    WebDriver driver;

    @FindBy(how = How.CSS, using = "div.account_creation")
    private WebElement accountCreationForm;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkIfPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(accountCreationForm)));

        if (accountCreationForm.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
