package com.intive.patronage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class AuthenticationPage {

    MyPersonalInformation myPersonalInformations;
    WebDriver driver;


    @FindBy(how = How.NAME, using = "email")
    public WebElement emailField;

    @FindBy(how = How.NAME, using = "passwd")
    public WebElement passwordField;

    @FindBy(how = How.NAME, using = "email_create")
    public WebElement emailCreateField;

    @FindBy(how = How.ID, using = "authentication")
    public WebElement authenticationWebPart;

    @FindBy(how = How.ID, using = "my-account")
    public WebElement myAccountWebPart;

    @FindBy(how = How.XPATH, using = "//a[@title='Information']")
    public WebElement myAccountButton;

    @FindBy(how = How.ID, using = "SubmitLogin")
    public WebElement submitLoginButton;

    @FindBy(how = How.XPATH, using = "//li[contains(text(), \"Authentication failed.\")]")
    public WebElement errorMessage;

    @FindBy(how = How.XPATH, using = ("//button[@id=\"SubmitCreate\"]"))
    public WebElement createAccountButton;

    @FindBy(how = How.XPATH, using = ("//li[contains(text(), \"An account using this email\")]"))
    public WebElement createAccountErrorMessage;

    @FindBy(how = How.XPATH, using = ("//li[contains(text(), \"Invalid email address.\")]"))
    public WebElement invalidEmailErrorMessage;
    @FindBy(how = How.XPATH, using = "//a[@title='Home']")
    public WebElement home;

    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MyPersonalInformation getMyPersonalInformation() {
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a")).click();
        return myPersonalInformations = new MyPersonalInformation(driver);
    }

    public void VerifyIfAuthenticationFormPageIsVisible() throws InterruptedException {
        assert (authenticationWebPart.isDisplayed());

    }

    public void InsertDataToSignIn(String login, String password) {
        emailField.sendKeys(login);
        passwordField.sendKeys(password);

    }

    public void SignIn() {
        submitLoginButton.click();
    }

    public void VeryfiyIfMyAccountPageIsVisible() throws InterruptedException {
        assert (myAccountWebPart.isDisplayed());

    }

    public void VeryfiyIfMyPersonalInformationButtonIsVisible() throws InterruptedException {
        assert (myAccountButton.isDisplayed());
    }

    public void VeryfiyIfErrorMessageIsVisibile() throws InterruptedException {

        assert (errorMessage.isDisplayed());
    }

    public void InsertEmailToSignIn(String login) {
        emailField.sendKeys(login);
    }

    public void InserEmailToCreateAccount(String login) {
        emailCreateField.sendKeys(login);
    }

    public void CreateAnAccount() {
        createAccountButton.click();
    }

    public void VerifyIfCreateAccountErrorIsDisplayed() throws InterruptedException {
        assert (createAccountErrorMessage.isDisplayed());
    }

    public void VerifyIfEmailSynatxErrorIsDisplayed() throws InterruptedException {
        assert (invalidEmailErrorMessage.isDisplayed());
    }

    public void homePage() {
        home.click();
    }


}

