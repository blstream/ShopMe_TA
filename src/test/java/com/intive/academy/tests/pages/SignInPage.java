package com.intive.academy.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    WebDriver driver;

    @FindBy(how = How.CSS, using = "input#email_create")
    private WebElement registerEmailInput;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement submitCreateButton;

    @FindBy(how = How.ID, using = "email")
    private WebElement signInEmailInput;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement passwordInput;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement submitLoginButton;

    @FindBy(how = How.CSS, using = "a.logout")
    private WebElement logoutButton;

    @FindBy(how = How.CSS, using = "form#login_form.box")
    private WebElement loginForm;

    @FindBy(how = How.CSS, using = "div.alert")
    private WebElement alert;


    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signIn(String login, String password) {
        signInEmailInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitLoginButton.click();
    }

    public boolean checkIfPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(passwordInput),
                ExpectedConditions.visibilityOf(registerEmailInput),
                ExpectedConditions.visibilityOf(signInEmailInput)
                )
        );

        if (loginForm.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfSignedIn() {
        if (logoutButton.getAttribute("class").contains("logout")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfAuthenticationFailed() {
        if (alert.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfUserRegistered(String email) {
        enterEmailToRegisterForm(email);

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(alert)
                )
        );

        if (alert.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void enterEmailToRegisterForm(String email) {
        registerEmailInput.sendKeys(email);
        submitCreateButton.click();
    }
}
