package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class LoginPage extends SearchServicePage {


    @FindBy(how = How.NAME, using = "login__user-email")
    public WebElement logInUserEmail;

    @FindBy(how = How.NAME, using = "login__user-password")
    public WebElement logInUserPassword;

    @FindBy(how = How.ID, using = "login-form__submit-form")
    public WebElement signInButton;

    @FindBy(how = How.CLASS_NAME, using = "login-form__register-link")
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

    public void clickTheRegisterBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }
}
