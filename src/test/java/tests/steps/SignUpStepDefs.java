package tests.steps;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import tests.pages.*;

public class SignUpStepDefs extends SearchServicePage{

    SearchServicePage searchServicePage = new SearchServicePage();
    LoginPage loginPage = new LoginPage();
    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @And("^I push Login button$")
    public void iPushLoginButton() {
        searchServicePage.pushLoginButton();
    }

    @And("^I can see registration form$")
    public void iCanSeeRegistrationForm() {
        registrationPage.verifyIfRegisterFormIsVisible();
    }

    @When("^I fill in all necessary registration data with testEmail, \"([^\"]*)\", \"([^\"]*)\",$")
    public void iFillInAllNecessaryRegistrationDataWithTestEmail(String name, String surname) {
        String newEmail = "test+" + registrationPage.generateTimeStamp() + "@gmail.com";
        setEmail(newEmail);
        registrationPage.sendName(name);
        registrationPage.sendSurname(surname);
        registrationPage.sendEmail(email);
    }

    @And("^I push Register button$")
    public void iPushRegisterButton() {
        registrationPage.pushRegisterButton();
    }

    @And("^I can see expanded registration form$")
    public void iCanSeeExpandedRegistrationForm() {
        registrationFormPage.verifyIfExpandedRegisterFormIsVisible();
    }

    @And("^I can see name filled with \"([^\"]*)\"$")
    public void iCanSeeNameFilledWith(String name) {
        registrationFormPage.checkIfNameIsFilled(name);
    }

    @And("^I can see surname filled with \"([^\"]*)\"$")
    public void iCanSeeSurnameFilledWith(String surname) {
        registrationFormPage.checkIfSurnameIsFilled(surname);
    }

    @And("^I can see email filled with testEmail$")
    public void iCanSeeEmailFilledWithTestEmail() {
        registrationFormPage.checkIfEmailIsFilled(email);
    }

    @And("^I fill in password with \"([^\"]*)\"$")
    public void iFillInPasswordWith(String password) {
        registrationFormPage.sendPassword(password);
    }

    @And("^I fill in all necessary personal data with \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillInAllNecessaryPersonalDataWith(String phoneNumber, String bankAccountNumber) {
        registrationFormPage.sendPhone(phoneNumber);
        registrationFormPage.sendBankAccountNumber(bankAccountNumber);
    }

    @And("^I select dataForInvoice checkbox$")
    public void iSelectDataForInvoiceCheckbox() {
        registrationFormPage.checkInvoice();
    }

    @And("^I fill in all necessary invoice data with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillInAllNecessaryInvoiceDataWith(String name, String nip, String street, String number, String zipCode, String city) {
        registrationFormPage.sendCompanyName(name);
        registrationFormPage.sendNip(nip);
        registrationFormPage.sendCompanyStreet(street);
        registrationFormPage.sendCompanyNumber(number);
        registrationFormPage.sendCompanyZipCode(zipCode);
        registrationFormPage.sendCompanyCity(city);
    }

    @And("^I accept terms of personal data processing$")
    public void iAcceptTermsOfPersonalDataProcessing() {
        registrationFormPage.acceptTermsOfPersonalDataProcessing();
    }

    @And("^I click Register button$")
    public void iClickRegisterButton() {
        registrationFormPage.pushRegisterButton();
    }

    @Then("^I should see register confirmation message \"([^\"]*)\"$")
    public void iShouldSeeRegisterConfirmationMessage(String message) {
        registrationFormPage.verifyIfConfirmationMessageIsVisible(message);
    }

    @And("^I should see Login button$")
    public void iShouldSeeLoginButton() {
        registrationFormPage.verifyIfLLoginButtonIsVisible();
    }

    @And("^I should be registered user in database$")
    public void iShouldBeRegisteredUserInDatabase() {
        String URI = "https://patronage2018.intive-projects.com/api/users/email=" + email;
        Response response = RestAssured.given().get(URI);
        ResponseBody body = response.getBody();
        String checkBody = body.asString();
        Assert.assertEquals(checkBody, "true");
    }

    @And("^I accept statute$")
    public void iAcceptStatute() {
        registrationFormPage.acceptStatute();
    }

    @And("^I fill in all necessary address data with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillInAllNecessaryAddressDataWith(String street, String number, String city, String zipCode, String voivodeship) {
        registrationFormPage.sendStreet(street);
        registrationFormPage.sendNumber(number);
        registrationFormPage.sendCity(city);
        registrationFormPage.sendZipCode(zipCode);
        registrationFormPage.selectVoivodeship(voivodeship);
    }

    @And("^Email \"([^\"]*)\" used in registration is already in database$")
    public void emailUsedInRegistrationIsAlreadyInDatabase(String email) {
        if(!registrationPage.checkIfEmailAlreadyInUse(email)){
            registrationPage.addUserWithEmail(email);
        }
    }

    @When("^I fill in all necessary registration data with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillInAllNecessaryRegistrationDataWith(String name, String surname, String email)  {
        registrationPage.sendName(name);
        registrationPage.sendSurname(surname);
        registrationPage.sendEmail(email);
    }

    @And("^I push Register button with fail$")
    public void iPushRegisterButtonWithFail() {
        registrationPage.pushRegisterButtonWithFail();
    }

    @Then("^I can see inserted values in filled fields$")
    public void iCanSeeInsertedValuesInFilledFields() {
        registrationPage.verifyIfValuesEqualsAfterPageRefresh();
    }

    @And("^I can see an error message \"([^\"]*)\"$")
    public void iCanSeeAnErrorMessage(String message) {
        registrationPage.verifyIfValidationErrorMessageIsVisible(message);
    }

    @Then("^I should see in name maximum (\\d+) characters$")
    public void iShouldSeeInNameMaximumCharacters(int expected) {
        registrationPage.verifyIfNameInputLimited(expected);
    }

    @And("^I should see in surname maximum (\\d+) characters$")
    public void iShouldSeeInSurnameMaximumCharacters(int expected) {
        registrationPage.verifyIfSurnameInputLimited(expected);
    }

    @And("^I can see an email error message \"([^\"]*)\"$")
    public void iCanSeeAnEmailErrorMessage(String message){
        registrationPage.verifyIfErrorMessageVisible(message);
    }

    @And("^I push SignUp button$")
    public void iPushSignUpButton() {
        loginPage.clickTheRegisterBtn();
    }

    @And("^I can see a message \"([^\"]*)\"$")
    public void iCanSeeAMessage(String expectedMessage) {
        registrationPage.verifyIfFillInAllMessageVisible(expectedMessage);
    }

    @And("^I don't accept terms of personal data processing$")
    public void iDonTAcceptTermsOfPersonalDataProcessing()  {
       registrationFormPage.doNotacceptTermsOfPersonalDataProcessing();
    }

    @And("^I don't accept statute$")
    public void iDonTAcceptStatute()  {
        registrationFormPage.doNotAcceptStatute();
    }

    @When("^I fill in password with (\\d+) characters$")
    public void iFillInPasswordWithCharacters(int length) {
        registrationFormPage.sendPassword(registrationFormPage.generateString(length));
    }

    @Then("^I should see in password maximum (\\d+) characters$")
    public void iShouldSeeInPasswordMaximumCharacters(int length) throws Throwable {
        registrationFormPage.verifyIfPasswordInputLimited(length);
    }

    @When("^I fill in userPhoneNumber with \"([^\"]*)\"$")
    public void iFillInUserPhoneNumberWith(String phone) {
        registrationFormPage.sendPhone(phone);
    }

    @Then("^I should see in userPhoneNumber maximum (\\d+) characters$")
    public void iShouldSeeInUserPhoneNumberMaximumCharacters(int length) {
        registrationFormPage.verifyIfUserPhoneInputLimited(length);
    }

    @When("^I fill in userBankAccountNumber with (\\d+) characters$")
    public void iFillInUserBankAccountNumberWithCharacters(int length)  {
        registrationFormPage.sendBankAccountNumber(registrationFormPage.generateNumber(length));
    }

    @Then("^I should see in userBankAccountNumber maximum (\\d+) characters$")
    public void iShouldSeeInUserBankAccountNumberMaximumCharacters(int length){
     registrationFormPage.verifyIfUserBankAccountNumberInputLimited(length);
    }

    @When("^I fill in addressZipCode with \"([^\"]*)\"$")
    public void iFillInAddressZipCodeWith(String zipCode) {
        registrationFormPage.sendZipCode(zipCode);
    }

    @Then("^I should see in addressZipCode maximum (\\d+) characters$")
    public void iShouldSeeInAddressZipCodeMaximumCharacters(int length){
        registrationFormPage.verifyIfUserZipCodeInputLimited(length);
    }

    @When("^I fill in addressCity with (\\d+) characters$")
    public void iFillInAddressCityWithCharacters(int length) {
        registrationFormPage.sendCity(registrationFormPage.generateString(length));
    }

    @Then("^I should see in addressCity maximum (\\d+) characters$")
    public void iShouldSeeInAddressCityMaximumCharacters(int length) {
        registrationFormPage.verifyIfUserCityInputLimited(length);
    }

    @When("^I fill in nip with \"([^\"]*)\"$")
    public void iFillInNipWith(String nip){
        registrationFormPage.sendNip(nip);
    }

    @Then("^I should see in nip maximum (\\d+) characters$")
    public void iShouldSeeInNipMaximumCharacters(int length) {
       registrationFormPage.verifyIfNipInputLimited(length);
    }

    @When("^I fill in invoiceAddressCity with (\\d+) characters$")
    public void iFillInInvoiceAddressCityWithCharacters(int length)  {
        registrationFormPage.sendCompanyCity(registrationFormPage.generateString(length));
    }

    @Then("^I should see in invoiceAddressCity maximum (\\d+) characters$")
    public void iShouldSeeInInvoiceAddressCityMaximumCharacters(int length){
        registrationFormPage.verifyIfInvoiceAddressCityInputLimited(length);
    }
}
