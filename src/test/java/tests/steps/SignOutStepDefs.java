package tests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import tests.pages.SearchServicePage;

public class SignOutStepDefs {

    SearchServicePage searchServicePage = new SearchServicePage();

    @When("^I push logout button$")
    public void iPushLogoutButton() {
        searchServicePage.pushLogoutButton();
    }

    @And("^I go back to previous page$")
    public void iGoBackToPreviousPage() {
        searchServicePage.navigateToPreviousPage();
    }

    @And("^I refresh page$")
    public void iRefreshPage() {
        searchServicePage.refreshPage();
    }
}
