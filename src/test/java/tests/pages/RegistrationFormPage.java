package tests.pages;

import com.google.gson.JsonObject;
import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

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

    public boolean checkIfEmailAlreadyInUse(String email) {
        String URI = "https://patronage2018.intive-projects.com/api/users/email=" + email;
        Response response = RestAssured.given().get(URI);
        String body = response.getBody().asString();

        if(body.equals("true")){
            return true;
        } else {
            return false;
        }
    }

    public void verifyIfEmailAlreadyInUse(){

    }

    private void addUserWithEmail(String email) {
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api/";

        JsonObject address = new JsonObject();
        address.addProperty("id", "5939701c-473c-4882-b666-b43694752728");
        address.addProperty("street", "Wyzwolenia");
        address.addProperty("number", "10");
        address.addProperty("city", "Szczecin");
        address.addProperty("zipCode", "70-100");

        JsonObject voivodeship = new JsonObject();
        voivodeship.addProperty("id", "e37cc0e2-e30c-437c-bced-74c7028b9896");
        voivodeship.addProperty("name", "WesternPomeranian");

        JsonObject invoiceAddress = new JsonObject();
        invoiceAddress.addProperty("id", "b815d831-9310-4a4b-a2e6-c71f33ce4507");
        invoiceAddress.addProperty("street", "Wyzwolenia");
        invoiceAddress.addProperty("number", "10");
        invoiceAddress.addProperty("city", "Szczecin");
        invoiceAddress.addProperty("zipCode", "70-100");

        JsonObject invoice = new JsonObject();
        invoice.addProperty("id", "aaa2e1cd-6319-4fa3-b05f-d47f4aec7dac");
        invoice.addProperty("name", "John");
        invoice.addProperty("email", "test@domain.com");
        invoice.addProperty("phoneNumber", "888555222");
        invoice.addProperty("additionalInfo", "test");
        invoice.add("invoiceAddress", invoiceAddress);

        JsonObject newUser = new JsonObject();
        newUser.addProperty("id", "e37cc0e2-e30c-437c-bced-74c7028b9896");
        newUser.addProperty("name", "John");
        newUser.addProperty("surname", "Doe");
        newUser.addProperty("email", email);
        newUser.addProperty("phoneNumber", "000000000");
        newUser.addProperty("bankAccount", "01234567890123456789012345");
        newUser.add("address", address);
        newUser.add("voivodeship", voivodeship);
        newUser.addProperty("invoiceReques", "true");
        newUser.add("invoice", invoice);

        RestAssured.given().contentType("application/json").body(newUser.toString()).when().post("/users").then().assertThat().statusCode(200);

    }
}
