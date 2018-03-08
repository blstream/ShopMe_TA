package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    @FindBy(how = How.NAME, using = "email_create")
    public WebElement txtemailtoregister;
    @FindBy(how = How.NAME, using = "SubmitCreate")
    public WebElement btnSubmitCreate;
    @FindBy(how = How.NAME, using = "email")
    public WebElement txtuseremail;
    @FindBy(how = How.NAME, using = "passwd")
    public WebElement txtPassword;
    @FindBy(how = How.NAME, using = "SubmitLogin")
    public WebElement btnLogin;
    @FindBy(how = How.CSS, using = "#create_account_error > ol > li")
    public WebElement emailTakenMsg;

    @FindBy(how = How.CSS, using = "#center_column > div.alert.alert-danger > ol > li")
    public WebElement txtAlertMessage;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void emailRegister(String email) {
        txtemailtoregister.sendKeys(email);
        btnSubmitCreate.click();
    }

    public void authentication(String username, String password) {

        txtuseremail.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

}

