package tests.pages;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class LoginPage extends SearchServicePage {

    @FindBy(how = How.NAME, using = "users__name")
    public WebElement userName;

    @FindBy(how = How.NAME, using = "users__surname")
    public WebElement userSurname;

    @FindBy(how = How.NAME, using = "users__email")
    public WebElement userEmail;

    @FindBy(how = How.ID, using = "signup-form__submit")
    public WebElement registerButton;

    @FindBy(how = How.NAME, using = "login__user-email")
    public WebElement logInUserEmail;

    @FindBy(how = How.NAME, using = "login__user-password")
    public WebElement logInUserPassword;

    @FindBy(how = How.ID, using = "login-form__submit-form")
    public WebElement signInButton;

    @FindBy(how = How.CLASS_NAME, using = "user-name")
    public WebElement name;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'zalogowano:')]")
    public WebElement signInInfo;

    @FindBy(how = How.CLASS_NAME, using = "input__error-message")
    public List<WebElement> errorMessages;

    @FindBy(how = How.CLASS_NAME, using = "signup-form__email-exists-message")
    public WebElement emailErrorMessage;


    private List<String> valuesBefore = new ArrayList<>();
    private List<String> valuesAfter = new ArrayList<>();

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void sendName(String name) {
        userName.sendKeys(name);
    }

    public void sendSurname(String surname) {
        userSurname.sendKeys(surname);
    }

    public void sendEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void verifyIfRegisterFormIsVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        Assert.assertTrue(registerButton.isDisplayed());
    }

    public void pushRegisterButton() {
        registerButton.click();
    }

    public String generateTimeStamp() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
        return timeStamp;
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

    public void iSeeAuthenticationInfo(String myName) {
        Assert.assertTrue(myName.equals(name.getText()));
        Assert.assertTrue(signInInfo.isDisplayed());
    }

    private void saveAllValues(List<String> values) {
        values.clear();
        values.add(userName.getAttribute("value"));
        values.add(userSurname.getAttribute("value"));
        values.add(userEmail.getAttribute("value"));
    }

    public void verifyIfValuesEqualsAfterPageRefresh() {
        Assert.assertEquals(valuesBefore, valuesAfter);
    }

    public void pushRegisterButtonWithFail() {
        waitUntilPageIsLoaded();
        saveAllValues(valuesBefore);
        registerButton.click();
        saveAllValues(valuesAfter);
    }

    public void waitUntilPageIsLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
    }

    public void verifyIfValidationErrorMessageIsVisible(String message) {
        waitUntilPageIsLoaded();
        boolean errorVisible = false;
        for (WebElement currentElement : errorMessages) {
            if (currentElement.getText().contains(message)) {
                errorVisible = true;
            }
        }

        Assert.assertTrue(errorVisible);
    }

    public void verifyIfNameInputLimited(int expectedLength) {
        int actualLength = userName.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfSurnameInputLimited(int expectedLength) {
        int actualLength = userSurname.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfErrorMessageVisible(String expectedMessage) {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        boolean messageIsVisible = emailErrorMessage.getText().contains(expectedMessage);
        Assert.assertTrue(messageIsVisible);
    }

    public boolean checkIfEmailAlreadyInUse(String email) {
        String URI = "https://patronage2018.intive-projects.com/api/users/email=" + email;
        Response response = RestAssured.given().get(URI);
        ResponseBody body = response.getBody();
        String checkBody = body.asString();

        if (checkBody.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public void addUserWithEmail(String email) {
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api/";

        JsonObject address = new JsonObject();
        address.addProperty("id", "5d214c01-95c3-4ec4-8f68-51dfb80b191c");
        address.addProperty("street", "Wyzwolenia");
        address.addProperty("number", "10");
        address.addProperty("city", "Szczecin");
        address.addProperty("zipCode", "70-100");

        JsonObject voivodeship = new JsonObject();
        voivodeship.addProperty("id", "1511273a-bb97-4e8a-924b-e6ff7583f135");
        voivodeship.addProperty("name", "WesternPomeranian");

        JsonObject invoiceAddress = new JsonObject();
        invoiceAddress.addProperty("id", "5d214c01-95c3-4ec4-8f68-51dfb80b191c");
        invoiceAddress.addProperty("street", "Wyzwolenia");
        invoiceAddress.addProperty("number", "10");
        invoiceAddress.addProperty("city", "Szczecin");
        invoiceAddress.addProperty("zipCode", "70-100");

        JsonObject invoice = new JsonObject();
        invoice.addProperty("id", "5d214c01-95c3-4ec4-8f68-51dfb80b191c");
        invoice.addProperty("companyName", "Januszex Sp.z.o.");
        invoice.addProperty("nip", "123-456-78-90");
        invoice.add("invoiceAddress", invoiceAddress);

        JsonObject newUser = new JsonObject();
        newUser.addProperty("id", "e37cc0e2-e30c-437c-bced-74c7028b9896");
        newUser.addProperty("name", "John");
        newUser.addProperty("surname", "Doe");
        newUser.addProperty("email", email);
        newUser.addProperty(  "password", "password");
        newUser.addProperty("phoneNumber", "000000000");
        newUser.addProperty("bankAccount", "01234567890123456789012345");
        newUser.add("address", address);
        newUser.add("voivodeship", voivodeship);
        newUser.addProperty("invoiceRequest", true);
        newUser.add("invoice", invoice);
        System.out.println(newUser.toString());


        RestAssured.given().contentType("application/json").body(newUser.toString()).when().post("/users").then().assertThat().statusCode(200);
    }
}

