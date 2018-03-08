package com.intive.QAPatronage3.tests.pages;

import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SignInPage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "email")
    private WebElement LoginField;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement PasswordField;

    @FindBy(how = How.TAG_NAME, using = "ol")
    private WebElement ErrorMessage;

    public SignInPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterLoginAndPassword(DataTable mycredentials) {
        List<List<String>> data = mycredentials.raw();
        LoginField.sendKeys(data.get(0).get(0));
        PasswordField.sendKeys(data.get(0).get(1));
        PasswordField.sendKeys(Keys.ENTER);

    }

    public void enterLogin(String login) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement results = wait.until(ExpectedConditions.visibilityOf(LoginField));
        LoginField.sendKeys(login);

    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement results = wait.until(ExpectedConditions.visibilityOf(PasswordField));
        PasswordField.sendKeys(password);
        PasswordField.sendKeys(Keys.ENTER);
    }

    public void checkErrorMessage(String errorMessage) {
        String errorText = ErrorMessage.getText();
        assertEquals(errorMessage, errorText);
    }

}