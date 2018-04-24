package tests.steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.helpers.RestAssuredMethods;
import tests.pages.SearchResultsPage;
import tests.pages.SearchServicePage;

public class PaginationStepDefs {
    SearchServicePage searchServicePage = new SearchServicePage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();
    RestAssuredMethods restAssuredMethods = new RestAssuredMethods("https://patronage2018.intive-projects.com/api");

    @When("^I add (\\d+) different services$")
    public void iAddDifferentServices(int numberOfServices, DataTable services){
        restAssuredMethods.addALotOfServicesServices(services,numberOfServices);
    }

    @Then("^I can see all services from database with title \"([^\"]*)\"$")
    public void iCanSeeAllServicesFromDatabaseWithTitle(String titlePhrase){
        searchResultsPage.areAllDatabaseServicesPresent(titlePhrase);
    }
}
