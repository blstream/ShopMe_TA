package com.intive.patronage.testMyPersonalInformation;

import com.intive.patronage.DriverFactory;
import com.intive.patronage.pages.AuthenticationPage;
import com.intive.patronage.pages.HomePage;
import com.intive.patronage.pages.MyPersonalInformation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class MyPersonalInformationStepdefs {


    WebDriver driver;
    DriverFactory driverFactory = new DriverFactory(driver);
    HomePage homePage = new HomePage(driver);
    AuthenticationPage authenticationPage = new AuthenticationPage(driver);
    MyPersonalInformation personalInformation = new MyPersonalInformation(driver);

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

    @Given("^I am on the login page$")
    public void iAmOnTheLoginPage() {
        authenticationPage = homePage.getAuthenticationPage();
    }

    @When("^I authenticate to the application with login \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iAuthenticateToTheApplicationWithLoginAndPassword(String arg0, String arg1) {
        authenticationPage.InsertDataToSignIn(arg0, arg1);
        authenticationPage.SignIn();
    }

    @And("^I click MyPersonalInformation button$")
    public void iClickMyPersonalInformationButton() throws Throwable {
        personalInformation = authenticationPage.getMyPersonalInformation();

    }

    @Then("^I should see Your Personal Information form$")
    public void iShouldSeeYourPersonalInformationForm() throws Throwable {
        personalInformation.VerifyIfMyPersonalInformationPageisDisplayed();
    }

    @And("^First name filed should be filled with name \"([^\"]*)\"$")
    public void firstNameFiledShouldBeFilledWithName(String arg0) throws Throwable {
        personalInformation.VerifyFirstNameField(arg0);
    }

    @And("^Last name filed should be filled with name \"([^\"]*)\"$")
    public void lastNameFiledShouldBeFilledWithName(String arg0) throws Throwable {
        personalInformation.VerifyLastNameField(arg0);
    }

    @And("^E-mail address should be filled with email \"([^\"]*)\"$")
    public void eMailAddressShouldBeFilledWithEmail(String arg0) throws Throwable {
        personalInformation.VerifyEmailField(arg0);
    }


}
