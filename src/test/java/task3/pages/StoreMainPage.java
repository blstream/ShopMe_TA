package task3.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.How.CLASS_NAME;

public class StoreMainPage {
    private WebDriver driver;

    @FindBy(how = CLASS_NAME, using = "login")
    private WebElement loginPage;

    @FindBy(how = How.ID, using = "email")
    private WebElement emailField;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement button;

    @FindBy(how = CLASS_NAME, using = "icon-user")
    private WebElement myInfo;

    @FindBy(how = CLASS_NAME, using = "alert alert-danger")
    private WebElement alert;


    public StoreMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginButton(){
        loginPage.click();
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement myAccount = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_form")));
    }

    public void loginStep(String login) {
        emailField.sendKeys(login);

    }
    public void sendPassword(String password){
        passwordField.sendKeys(password);
    }
    public void logIn(){
        button.click();
    }

    public void MyInfo(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement myAccount = wait.until(ExpectedConditions.visibilityOf(myInfo));
        myInfo.click();
    }

    public void errorAlert(){
        Assert.assertTrue("alert.isDisplayed()", true);
    }

}
