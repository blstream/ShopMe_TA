package tests.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.pages.LoginPage;
import tests.pages.RegistrationFormPage;
import tests.pages.SearchServicePage;

public class SignUpStepDefs {

    SearchServicePage searchServicePage = new SearchServicePage();
    LoginPage loginPage = new LoginPage();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @And("^I push Login button$")
    public void iPushLoginButton() {
        searchServicePage.pushLoginButton();
    }

    @And("^I can see registration form$")
    public void iCanSeeRegistrationForm() {
        loginPage.verifyIfRegisterFormIsVisible();
    }

    @When("^I fill in all necessary registration data with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillInAllNecessaryRegistrationDataWith(String name, String surname, String email) {
        loginPage.sendName(name);
        loginPage.sendSurname(surname);
        loginPage.sendEmail(email);
    }

    @And("^I push Register button$")
    public void iPushRegisterButton() {
        loginPage.pushRegisterButton();
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

    @And("^I can see email filled with \"([^\"]*)\"$")
    public void iCanSeeEmailFilledWith(String email) {
        registrationFormPage.checkIfEmailIsFilled(email);
    }

    @And("^I fill in password and repeatPassword with \"([^\"]*)\"$")
    public void iFillInPasswordAndRepeatPasswordWith(String password) {
        registrationFormPage.sendPassword(password);
        registrationFormPage.sendConfirmPassword(password);
    }

    @And("^I fill in all necessary personal data with \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillInAllNecessaryPersonalDataWith(String phoneNumber, String bankAccountNumber) {
        registrationFormPage.sendPhone(phoneNumber);
        registrationFormPage.sendBankAccountNumber(bankAccountNumber);
    }

    @And("^I fill in all necessary address data with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillInAllNecessaryAddressDataWith(String street, String number, String city, String zipCode) {
        registrationFormPage.sendStreet(street);
        registrationFormPage.sendNumber(number);
        registrationFormPage.sendCity(city);
        registrationFormPage.sendZipCode(zipCode);
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
        registrationFormPage.sendCompanyCity(zipCode);
        registrationFormPage.sendCompanyCity(city);
    }

    @And("^I accept terms of personal data processing$")
    public void iAcceptTermsOfPersonalDataProcessing() {
        registrationFormPage.acceptTermsOfPersonalDataProcessing();
    }

    @Then("^I should see register confirmation message \"([^\"]*)\"$")
    public void iShouldSeeRegisterConfirmationMessage(String message) {
        registrationFormPage.verifyIfConfirmationMessageisVisible(message);
    }

    @And("^I should see Login button$")
    public void iShouldSeeLoginButton() {
      registrationFormPage.verifyIfLLoginButtonIsVisible();
    }

    @And("^I should be registered user in database with \"([^\"]*)\"$")
    public void iShouldBeRegisteredUserInDatabaseWith(String email)  {

    }

    @And("^I click Register button$")
    public void iClickRegisterButton() {
      registrationFormPage.pushRegisterButton();
    }
}
