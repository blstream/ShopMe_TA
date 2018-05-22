package tests.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class LoginPage extends SearchServicePage {

    @FindBy(how = How.NAME, using = "userEmail")
    public WebElement logInUserEmail;

    @FindBy(how = How.NAME, using = "userPassword")
    public WebElement logInUserPassword;

    @FindBy(how = How.ID, using = "login-form__submit-form")
    public WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//a[@href='/signup']")
    public WebElement registerLink;


    public LoginPage() {
        PageFactory.initElements(driver, this);
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

    public void clickTheRegisterBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void verifyIfLoginFormIsVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        Assert.assertTrue(signInButton.isDisplayed());
    }
}
