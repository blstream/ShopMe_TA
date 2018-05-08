package tests.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class RegistrationFormPage extends LoginPage {

    @FindBy(how = How.CLASS_NAME, using = "form__button")
    public WebElement registerButton;

    @FindBy(how = How.NAME, using = "users__password")
    public WebElement password;

    @FindBy(how = How.NAME, using = "users__confirm-password")
    public WebElement confirmPassword;

    @FindBy(how = How.NAME, using = "users__phone-number")
    public WebElement userPhoneNumber;

    @FindBy(how = How.NAME, using = "users__bank-account")
    public WebElement userBankAccountNumber;

    @FindBy(how = How.NAME, using = "users__address-street")
    public WebElement addressStreet;

    @FindBy(how = How.NAME, using = "users__address-number")
    public WebElement addressNumber;

    @FindBy(how = How.NAME, using = "users__address-city")
    public WebElement addressCity;

    @FindBy(how = How.NAME, using = "users__address-zip-code")
    public WebElement addressZipCode;

    @FindBy(how = How.NAME, using = "invoiceCheckbox")
    public WebElement invoiceCheckbox;

    @FindBy(how = How.NAME, using = "users__personal-data-processing")
    public WebElement usersPersonalDataCheckbox;

    @FindBy(how = How.NAME, using = "users_invoiceCompanyName")
    public WebElement invoiceCompanyName;

    @FindBy(how = How.NAME, using = "users_invoiceNip")
    public WebElement invoiceNip;

    @FindBy(how = How.NAME, using = "users_invoiceAddressStreet")
    public WebElement invoiceAddressStreet;

    @FindBy(how = How.NAME, using = "users_invoiceAddressNumber")
    public WebElement invoiceAddressNumber;

    @FindBy(how = How.NAME, using = "users_invoiceAddressZipCode")
    public WebElement invoiceAddressZipCode;

    @FindBy(how = How.NAME, using = "users_invoiceAddressCity")
    public WebElement invoiceAddressCity;

    @FindBy(how = How.CLASS_NAME, using = "success-register-message__text-wrapper")
    public WebElement confirmationMessage;

    @FindBy(how = How.XPATH, using = "//a[@href='/login']")
    public WebElement loginButton;

    public RegistrationFormPage() {
        PageFactory.initElements(driver, this);
    }

    public void checkIfNameIsFilled(String name) {
        String user_name = userName.getAttribute("value");
        Assert.assertEquals(user_name, name);
    }

    public void checkIfSurnameIsFilled(String surname) {
        String user_surname = userSurname.getAttribute("value");
        Assert.assertEquals(user_surname, surname);
    }

    public void checkIfEmailIsFilled(String email) {
        String user_email = userEmail.getAttribute("value");
        Assert.assertEquals(user_email, email);
    }

    public void sendPassword(String pass) {
        password.sendKeys(pass);
    }

    public void sendConfirmPassword(String pass) {
        confirmPassword.sendKeys(pass);
    }

    public void sendPhone(String phoneNumber) {
        userPhoneNumber.sendKeys(phoneNumber);
    }

    public void sendBankAccountNumber(String bankAccountNumber) {
        userBankAccountNumber.sendKeys(bankAccountNumber);
    }

    public void sendStreet(String street) {
        addressStreet.sendKeys(street);
    }

    public void sendNumber(String number) {
        addressNumber.sendKeys(number);
    }

    public void sendCity(String city) {
        addressCity.sendKeys(city);
    }

    public void sendZipCode(String zipCode) {
        addressZipCode.sendKeys(zipCode);
    }

    public void sendCompanyName(String name) {
        invoiceCompanyName.sendKeys(name);
    }

    public void sendNip(String nip) {
        invoiceNip.sendKeys(nip);
    }

    public void sendCompanyStreet(String street) {
        invoiceAddressStreet.sendKeys(street);
    }

    public void sendCompanyNumber(String number) {
        invoiceAddressNumber.sendKeys(number);
    }

    public void sendCompanyZipCode(String zipCode) {
        invoiceAddressZipCode.sendKeys(zipCode);
    }

    public void sendCompanyCity(String city) {
        invoiceAddressCity.sendKeys(city);
    }

    public void checkInvoice() {
        invoiceCheckbox.click();
    }

    public void acceptTermsOfPersonalDataProcessing() {
        usersPersonalDataCheckbox.click();
        Assert.assertTrue(usersPersonalDataCheckbox.isSelected());
    }

    public void pushRegisterButton() {
        registerButton.click();
    }

    public void verifyIfConfirmationMessageIsVisible(String message) {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        Assert.assertTrue(confirmationMessage.isDisplayed());
        Assert.assertTrue(confirmationMessage.getText().contains(message));
    }

    public void verifyIfLLoginButtonIsVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        Assert.assertTrue(loginButton.isDisplayed());
    }

    public void verifyIfExpandedRegisterFormIsVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
    }
}
