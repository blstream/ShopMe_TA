package com.intive.academy.tests.steps;

import com.intive.academy.tests.factories.DriverFactory;
import com.intive.academy.tests.pages.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class SignInStepDefs extends DriverFactory {

    HomePage homePage;
    SignInPage signInPage;
    UserAccountPage userAccountPage;
    UserInformationPage userInformationPage;
    CreateAccountPage createAccountPage;

    public SignInStepDefs() {
        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);
        userAccountPage = new UserAccountPage(driver);
        userInformationPage = new UserInformationPage(driver);
        createAccountPage = new CreateAccountPage(driver);
    }

    @Given("^I am not signed in$")
    public void iAmNotSignedIn() {
        Assert.assertFalse(homePage.checkIfSignedIn());
    }

    @And("^I go to sign in form$")
    public void iGoToSignInForm() {
        homePage.goToLoginForm();
        Assert.assertTrue(signInPage.checkIfPageLoaded());
    }

    @Given("^Account with credentials \"([^\"]*)\" is already registered$")
    public void accountWithCredentialsIsAlreadyRegistered(String email) {
        Assert.assertTrue(signInPage.checkIfUserRegistered(email));
    }

    @When("^I enter login with \"([^\"]*)\" and password with \"([^\"]*)\"$")
    public void iEnterLoginWithAndPasswordWith(String login, String password) {
        signInPage.signIn(login, password);
    }

    @Then("^Request succeeded$")
    public void requestSucceeded() {
        Assert.assertTrue(signInPage.checkIfSignedIn());
    }

    @And("^I go to My Personal Information page$")
    public void iGoToMyPersonalInformationPage() {
        userAccountPage.goToUserInformationPage();
        Assert.assertTrue(userInformationPage.checkIfResultsAreVisible());
    }

    @And("^the name attribute with value \"([^\"]*)\" is visible$")
    public void theNameAttributeWithValueIsVisible(String name) {
        Assert.assertTrue(userInformationPage.checkIfNameCorrect(name));
    }

    @And("^the surname attribute with value \"([^\"]*)\" is visible$")
    public void theSurnameAttributeWithValueIsVisible(String surname) {
        Assert.assertTrue(userInformationPage.checkIfSurnameCorrect(surname));
    }

    @And("^the email attribute with value \"([^\"]*)\" is visible$")
    public void theEmailAttributeWithValueIsVisible(String email) {
        Assert.assertTrue(userInformationPage.checkIfEmailCorrect(email));
    }

    @When("^When I enter login with \"([^\"]*)\" and password with \"([^\"]*)\"$")
    public void whenIEnterLoginWithAndPasswordWith(String login, String password) {
        signInPage.signIn(login, password);
    }

    @Then("^Request fails with error message$")
    public void requestFailsWithErrorMessage() {
        Assert.assertTrue(signInPage.checkIfAuthenticationFailed());
    }

    @Given("^Account with credentials \"([^\"]*)\" is not already registered$")
    public void accountWithCredentialsIsNotAlreadyRegistered(String email) {
        signInPage.enterEmailToRegisterForm(email);
        Assert.assertTrue(createAccountPage.checkIfPageLoaded());
        driver.navigate().refresh();
    }
}
