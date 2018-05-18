package tests.pages;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class RegistrationFormPage {

    @FindBy(how = How.ID, using = "users__register-submit")
    public WebElement registerButton;

    @FindBy(how = How.NAME, using = "users__password")
    public WebElement password;

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

    @FindBy(how = How.XPATH, using = "//label[@for='invoiceCheckbox']")
    public WebElement invoiceCheckbox;

    // @FindBy(how = How.ID, using = "invoiceCheckbox")
    // public WebElement invoiceCheckbox;

    @FindBy(how = How.XPATH, using = "//label[@for='users__personal-data-processing']")
    public WebElement usersPersonalDataCheckbox;
    //@FindBy(how = How.ID, using = "users__personal-data-processing")
    //public WebElement usersPersonalDataCheckbox;


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

    @FindBy(how = How.XPATH, using = "//label[@for='users__terms-and-conditions-checkbox']")
    public WebElement statuteCheckbox;
    //@FindBy(how = How.ID, using = "users__terms-and-conditions-checkbox")
    // public WebElement statuteCheckbox;

    @FindBy(how = How.NAME, using = "offer__voivodeship")
    public WebElement voivodeshipSelect;

    @FindBy(how = How.CLASS_NAME, using = "input-select__inline-label")
    public WebElement selectOption;

    @FindBy(how = How.NAME, using = "users__name")
    public WebElement usersName;

    @FindBy(how = How.NAME, using = "users__surname")
    public WebElement usersSurname;

    @FindBy(how = How.NAME, using = "users__email")
    public WebElement usersEmail;

    public RegistrationFormPage() {
        PageFactory.initElements(driver, this);
    }

    public void checkIfNameIsFilled(String name) {
        String user_name = usersName.getAttribute("value");
        Assert.assertEquals(user_name, name);
    }

    public void checkIfSurnameIsFilled(String surname) {
        String user_surname = usersSurname.getAttribute("value");
        Assert.assertEquals(user_surname, surname);
    }

    public void checkIfEmailIsFilled(String email) {
        String user_email = usersEmail.getAttribute("value");
        Assert.assertEquals(user_email, email);
    }

    public void sendPassword(String pass) {
        password.sendKeys(pass);
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
        // Assert.assertTrue(usersPersonalDataCheckbox.isSelected());
    }

    public void doNotacceptTermsOfPersonalDataProcessing() {
        Assert.assertFalse(usersPersonalDataCheckbox.isSelected());
    }

    public void pushRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
        // wait.until(ExpectedConditions.invisibilityOf(registerButton));
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
        wait.until((ExpectedConditions.visibilityOf(registerButton)));
    }

    public void acceptStatute() {
        statuteCheckbox.click();
        //Assert.assertTrue(statuteCheckbox.isSelected());
    }

    public void doNotAcceptStatute() {
        Assert.assertFalse(statuteCheckbox.isSelected());
    }

    public void waitUntilSelectOptionsAreVisible() {
        wait.until(ExpectedConditions.visibilityOf(selectOption));
    }

    public void selectVoivodeship(String voivodeship) {
        waitUntilSelectOptionsAreVisible();
        Select selectVoivodeship = new Select(voivodeshipSelect);

        if (voivodeship.isEmpty()) {
            return;
        } else {
            selectVoivodeship.selectByVisibleText(voivodeship);
        }
    }

    public String generateNumber(int length) {
        return StringUtils.leftPad("", length, '1');
    }

    public String generateString(int length) {
        return StringUtils.leftPad("", length, 'A');
    }

    public void verifyIfPasswordInputLimited(int expectedLength) {
        int actualLength = password.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfUserPhoneInputLimited(int expectedLength) {
        int actualLength = userPhoneNumber.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfUserBankAccountNumberInputLimited(int expectedLength) {
        int actualLength = userBankAccountNumber.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfUserZipCodeInputLimited(int expectedLength) {
        int actualLength = addressZipCode.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfUserCityInputLimited(int expectedLength) {
        int actualLength = addressCity.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfNipInputLimited(int expectedLength) {
        int actualLength = invoiceNip.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfInvoiceAddressCityInputLimited(int expectedLength) {
        int actualLength = invoiceAddressCity.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

}