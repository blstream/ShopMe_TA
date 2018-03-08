package tests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.apache.log4j.Logger;
import tests.pages.*;

import static org.junit.Assert.assertTrue;

public class StartStepDefs {

    final static Logger logger = Logger.getLogger(StartStepDefs.class);

    StartPage startPage = new StartPage();
    String mainPageUrl = "http://automationpractice.com/index.php";

    @Given("^user is on the main page$")
    public void userIsOnTheMainPage() {
        logger.info("TEST START - User is on the main page");
        String startPageUrl = startPage.goToStartPage();
        assertTrue(startPageUrl.equals(mainPageUrl));
    }

    @And("^user is not authenticated$")
    public void userIsNotAuthenticated() {
        assertTrue(startPage.isSignInBtnVisible());
    }
}
