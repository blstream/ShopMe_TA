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

public class LoginPage {

    @FindBy(how = How.NAME, using = "users__name")
    public WebElement userName;

    @FindBy(how = How.NAME, using = "users__surname")
    public WebElement userSurname;

    @FindBy(how = How.NAME, using = "users__email")
    public WebElement userEmail;

    @FindBy(how = How.CSS, using = "#signup-form__submit")
    public WebElement registerButton;

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

    public void pushRegisterButtonWithFail() {
        saveAllValues(valuesBefore);
        registerButton.click();
        saveAllValues(valuesAfter);
    }

    public String generateTimeStamp() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
        return timeStamp;
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

    public void verifyIfErrorMessageVisible(String expectedMessage) {
        String actualMessage = emailErrorMessage.getAttribute("value");
        Assert.assertEquals(expectedMessage, actualMessage);
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
