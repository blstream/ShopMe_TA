package tests.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class LoginPage extends SearchServicePage {

    @FindBy(how = How.NAME, using = "users__name")
    public WebElement userName;

    @FindBy(how = How.NAME, using = "users__surname")
    public WebElement userSurname;

    @FindBy(how = How.NAME, using = "users__email")
    public WebElement userEmail;

    @FindBy(how = How.ID, using = "signup-form__submit")
    public WebElement registerButton;

    @FindBy(how = How.NAME, using = "login__user-email")
    public WebElement logInUserEmail;

    @FindBy(how = How.NAME, using = "login__user-password")
    public WebElement logInUserPassword;

    @FindBy(how = How.ID, using = "login-form__submit-form")
    public WebElement signInButton;

    @FindBy(how = How.CLASS_NAME, using = "user-name")
    public WebElement name;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'zalogowano:')]")
    public WebElement signInInfo;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void sendName(String name) {
        userName.sendKeys(name);
    }

    public void sendSurname(String surname) {
        userSurname.sendKeys(surname);
    }

    public void sendEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void verifyIfRegisterFormIsVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        Assert.assertTrue(registerButton.isDisplayed());
    }

    public void pushRegisterButton() {
        registerButton.click();
    }

    public String generateTimeStamp() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
        return timeStamp;
    }

    public void enterEmail(String email) {
        logInUserEmail.sendKeys(email);
    }

    public void enterPassword(String password) {
        logInUserPassword.sendKeys(password);
    }

    public void clickTheSignInBtn() {
        signInButton.click();
    }

    public void iSeeAuthenticationMessage(String myName) {
        Assert.assertTrue(myName.equals(name.getText()));
        Assert.assertTrue(signInInfo.isDisplayed());
    }
}

