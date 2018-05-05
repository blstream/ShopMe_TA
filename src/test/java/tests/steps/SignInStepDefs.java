package tests.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import tests.pages.LoginPage;
import tests.pages.SearchServicePage;

import static org.junit.Assert.assertEquals;

public class SignInStepDefs {

    SearchServicePage searchServicePage = new SearchServicePage();
    LoginPage loginPage = new LoginPage();

    @Given("^I am a registered user in database with email \"([^\"]*)\"$")
    public void iAmARegisteredUserInDatabaseWithEmail(String email) {
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RestAssured.given().contentType("application/json").when().get("/users/email=" + email).then().assertThat().statusCode(200);
    }

    @And("^I am not authenticated$")
    public void iAmNotAuthenticated() {
        searchServicePage.loginButtonIsDisplayed();
    }

    @And("^I enter an email \"([^\"]*)\" into the email field$")
    public void iEnterAnEmailIntoTheEmailField(String email) {
        loginPage.enterEmail(email);
    }

    @And("^I enter a password \"([^\"]*)\" into the password field$")
    public void iEnterAPasswordIntoThePasswordField(String password) {
        loginPage.enterPassword(password);
    }

    @And("^I click the Sign in button$")
    public void iClickTheSignInButton() {
        loginPage.clickTheSignInBtn();
    }

    @And("^I am an authenticated user$")
    public void iAmAnAuthenticatedUser() {
//TODO
    }

    @And("^I can see \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iCanSeeInformationThatIAmSignedIn(String iAmLoggedInText, String firstName, String lastName) {
//TODO
    }

    @And("^I can see the Log out button$")
    public void iCanSeeTheLogOutButton() {
        searchServicePage.logoutButtonIsDisplayed();
    }
}
