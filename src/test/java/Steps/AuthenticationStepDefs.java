package Steps;

import Base.BaseUtil;
import Pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class AuthenticationStepDefs extends BaseUtil {
    private static BaseUtil base;

    public AuthenticationStepDefs(BaseUtil base) {
        AuthenticationStepDefs.base = base;
    }


    @Given("^I am registered with email \"([^\"]*)\"$")
    public void iAmRegisteredWithEmail(String email) {
        base.Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(base.Driver);
        loginPage.emailRegister(email);
        assertTrue(loginPage.emailTakenMsg.isEnabled());
    }

    @And("^I am authenticated with username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iAmAuthenticatedWithUsernameAsAndPasswordAs(String username, String password)

    {
        loginPage = new LoginPage(base.Driver);
        loginPage.authentication(username, password);
    }


    @When("^I go to the 'My Personal Information'$")
    public void iGoToTheMyPersonalInformation() {
        myAccountPage.goPersonalInfo(base.Driver);
    }


    @Then("^I should be able to see: first name, last name and email address fields$")
    public void iShouldBeAbleToSeeFirstNameLastNameAndEmailAddressFields() {
        myPersonalDetails.waitForElmnts(base.Driver);
        assertTrue(myPersonalDetails.firstName.isEnabled());
        assertTrue(myPersonalDetails.lastName.isEnabled());
        assertTrue(myPersonalDetails.emailAddress.isEnabled());
    }

    @When("^I enter ([^\"]*) and ([^\"]*)$")
    public void iEnterEmailAndPassword(String email, String password) {
        loginPage = new LoginPage(base.Driver);
        loginPage.authentication(email, password);
    }

    @Then("^I cannot see 'My Account Page'$")
    public void iCannotSeeMyAccountPage() {
        loginPage = new LoginPage(base.Driver);
        assertTrue(loginPage.btnLogin.isEnabled());
    }

    @And("^I see an alert message with ([^\"]*)$")
    public void iSeeAnAlertMessageWithWord(String word) {
        loginPage = new LoginPage(base.Driver);
        String AlertMessage = loginPage.txtAlertMessage.getText();
        if (AlertMessage.contains(word)) {
            Assert.assertTrue(true);
        }
    }
}