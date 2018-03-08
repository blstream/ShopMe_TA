package com.intive.academy.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserInformationPage {
    WebDriver driver;

    @FindBy(how = How.CSS, using = "input#firstname")
    private WebElement userFirstName;

    @FindBy(how = How.CSS, using = "input#lastname")
    private WebElement userLastName;

    @FindBy(how = How.CSS, using = "input#email")
    private WebElement userEmail;

    @FindBy(how = How.ID, using = "firstname")
    private WebElement resultsWebPart;

    public UserInformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkIfNameCorrect(String name) {
        if (userFirstName.getAttribute("value").equals(name)) {
            return true;
        }
        return false;
    }

    public boolean checkIfSurnameCorrect(String surname) {
        if (userLastName.getAttribute("value").equals(surname)) {
            return true;
        }
        return false;
    }

    public boolean checkIfEmailCorrect(String email) {
        if (userEmail.getAttribute("value").equals(email)) {
            return true;
        }
        return false;
    }

    public boolean checkIfResultsAreVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(userFirstName),
                ExpectedConditions.visibilityOf(userLastName),
                ExpectedConditions.visibilityOf(userEmail)
                )
        );

        if (resultsWebPart.isDisplayed()) {
            return true;
        }

        return false;
    }
}
