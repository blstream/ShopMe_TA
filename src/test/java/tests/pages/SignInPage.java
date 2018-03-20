package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static tests.Hooks.driver;

@Deprecated
public class SignInPage {

    @FindBy(how = How.LINK_TEXT, using = "Sign in")
    public WebElement logInButton;

    @FindBy(how = How.ID, using = "email")
    public WebElement userEmail;

    @FindBy(how = How.ID, using = "passwd")
    public WebElement userPassword;

    @FindBy(how = How.ID, using = "SubmitLogin")
    public WebElement submitButton;

    @FindBy(how = How.CLASS_NAME, using = "alert-danger")
    public WebElement errorMessageField;

    public SignInPage() {
        PageFactory.initElements(driver, this);
    }

    public void signInLink() {
        logInButton.click();
    }

    public void sendEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void sendPassword(String password) {
        userPassword.sendKeys(password);
    }

    public void logIn() {
        submitButton.click();
    }

    public String getErrorMessage() {
        return errorMessageField.getText();
    }

}
