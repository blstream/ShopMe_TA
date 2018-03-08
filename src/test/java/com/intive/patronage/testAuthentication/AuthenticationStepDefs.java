package com.intive.patronage.testAuthentication;

import com.intive.patronage.DriverFactory;
import com.intive.patronage.pages.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;


public class AuthenticationStepDefs {

    WebDriver driver;
    DriverFactory driverFactory = new DriverFactory(driver);
    HomePage homePage = new HomePage(driver);
    AuthenticationPage authenticationPage = new AuthenticationPage(driver);

    @Before
    public void before() {
        driverFactory = new DriverFactory(driver);
        driverFactory.Setup();
        homePage = driverFactory.getHomePage();
    }

    @After
    public void after() {
        driverFactory.destroyDriver();
    }

    @Given("^I am on the authentication form page$")
    public void iAmOnTheAuthenticationFormPage() throws Throwable {
        authenticationPage = homePage.getAuthenticationPage();
        authenticationPage.VerifyIfAuthenticationFormPageIsVisible();
    }

    @When("^I enter email address \"([^\"]*)\"$")
    public void iEnterEmailAddress(String arg0) throws Throwable {
        authenticationPage.InserEmailToCreateAccount(arg0);
    }

    @And("^I press CreateAnAccount button$")
    public void iPressCreateAnAccountButton() {
        authenticationPage.CreateAnAccount();
    }

    @Then("^I should see account create error message$")
    public void iShouldSeeAccountCreateErrorMessage() throws Throwable {
        authenticationPage.VerifyIfCreateAccountErrorIsDisplayed();
    }

    @When("^I enter valid email address \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iEnterValidEmailAddressAndPassword(String arg0, String arg1) {
        authenticationPage.InsertDataToSignIn(arg0, arg1);
    }

    @And("^I press SignIn button$")
    public void iPressSignInButton() {
        authenticationPage.SignIn();
    }

    @Then("^My account page should be open$")
    public void myAccountPageShouldBeOpen() throws Throwable {
        authenticationPage.VeryfiyIfMyAccountPageIsVisible();
    }

    @And("^I should see My personal information button$")
    public void iShouldSeeMyPersonalInformationButton() throws Throwable {
        authenticationPage.VeryfiyIfMyPersonalInformationButtonIsVisible();
    }

    @When("^I enter valid email address \"([^\"]*)\" and invalid password \"([^\"]*)\"$")
    public void iEnterValidEmailAddressAndInvalidPassword(String arg0, String arg1) {
        authenticationPage.InsertDataToSignIn(arg0, arg1);
    }

    @Then("^I should see error message$")
    public void iShouldSeeErrorMessage() throws Throwable {
        authenticationPage.VeryfiyIfErrorMessageIsVisibile();
    }

    @When("^I enter login \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iEnterLoginAndPassword(String arg0, String arg1) {
        authenticationPage.InsertDataToSignIn(arg0, arg1);
    }

    @When("^I enter \"([^\"]*)\" in EmailAdress field$")
    public void iEnterInEmailAdressField(String arg0) throws Throwable {
        authenticationPage.InsertEmailToSignIn(arg0);
    }


    @Then("^I should see error message Invalid email address$")
    public void iShouldSeeErrorMessageInvalidEmailAddress() throws Throwable {
        authenticationPage.VerifyIfEmailSynatxErrorIsDisplayed();
    }


}





