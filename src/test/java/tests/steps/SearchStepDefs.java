package tests.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.helpers.RestAssuredMethods;
import tests.pages.SearchResultsPage;
import tests.pages.SearchServicePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SearchStepDefs {

    SearchServicePage searchServicePage = new SearchServicePage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();
    RestAssuredMethods restAssuredMethods = new RestAssuredMethods("https://patronage2018.intive-projects.com/api");

    @Given("^that there are no services added$")
    public void thatThereAreNoServicesAdded() {
        restAssuredMethods.deleteAllServices();
    }

    @And("^I add services$")
    public void iAddServices(DataTable services) {
        String token = restAssuredMethods.authorizeAndGetBearerToken();
        restAssuredMethods.addServices(services, token);
    }

    @And("^I enter a searching phrase \"([^\"]*)\" into the search field$")
    public void iEnterASearchingPhraseIntoTheSearchField(String searchPhrase) {
        searchServicePage.getSearchResult(searchPhrase);
    }

    @And("^I click the search button$")
    public void iClickTheSearchButton() {
        searchServicePage.submitMySearch();
    }

    @Then("^search results are visible$")
    public void searchResultAreVisible() {
        searchResultsPage.waitForNewResults();
    }

    @And("^I see that title of the service contains \"([^\"]*)\"$")
    public void iSeeThatTitleOfTheServiceContains(String searchPhrase) {
        List<String> titles = searchResultsPage.getServicesTitles();
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i).toLowerCase();
            assertTrue(title.contains(searchPhrase.toLowerCase()));
        }
    }

    @And("^I see basic price and added data of each record$")
    public void iSeeBasicPriceAndAddedDataOfEachRecord() {
        List<String> prices = searchResultsPage.getServicesPrices();
        List<String> dates = searchResultsPage.getServicesDates();

        assertTrue(prices.size() == dates.size());
        for (int i = 0; i < prices.size(); i++) {
            String price = prices.get(i);
            String date = dates.get(i);
            assertTrue(!price.isEmpty() && !date.isEmpty());
        }
    }

    @And("^all results are sorted by date descending$")
    public void allResultsAreSortedByDateDescending() {
        List<String> dates = searchResultsPage.getServicesDates();
        for (int i = 0; i < dates.size() - 1; i++) {
            String nextDataL = String.valueOf(dates.get(i + 1));
            String actualDateL = String.valueOf(dates.get(i));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate dateNext = LocalDate.parse(nextDataL, formatter);
            LocalDate dateActual = LocalDate.parse(actualDateL, formatter);
            assertTrue(dateNext.compareTo(dateActual) >= 0);
        }
    }

    @Then("^I can see error message \"([^\"]*)\"$")
    public void iCanSeeErrorMessage(String expectedErrorMessage) {
        String errorMessage = searchResultsPage.getErrorMessage();
        assertTrue(errorMessage.contains(expectedErrorMessage));
    }

    @And("^I press Enter key$")
    public void iPressEnterKey() {
        searchServicePage.submitByEnter();
    }

    @Then("^I can see no results message \"([^\"]*)\"$")
    public void iCanSeeNoResultsMessage(String expectedNoResultsMessage) {
        String noResultsMessage = searchResultsPage.getNoResultsMessage();
        assertEquals(expectedNoResultsMessage, noResultsMessage);
    }

    @And("^I see service \"([^\"]*)\" at the first place$")
    public void iSeeServiceAtTheFirstPlace(String newService) {
        String firstService = searchResultsPage.getTitle(0);
        assertTrue(firstService.contains(newService));
    }

    @Then("^search results for the new phrase \"([^\"]*)\" are visible$")
    public void searchResultsForTheNewPhraseAreVisible(String expectedService) {
        searchResultsPage.areNewResultsPresent(expectedService);
    }

    @When("^I try to fill in search field with (\\d+) characters$")
    public void iTryToFillInSearchFieldWithCharacters(int phraseLength) {
        searchServicePage.sendSearchPhrase(phraseLength);
    }

    @Then("^I should see in search field exactly (\\d+) characters$")
    public void iShouldSeeInSearchFieldExactlyCharacters(int expectedPhraseLength) {
        int phraseLength = searchServicePage.getSearchPhraseLength();
        assertEquals(expectedPhraseLength, phraseLength);
    }

    @Then("^the search button is not clickable$")
    public void theSearchButtonIsNotClickable() {
        searchServicePage.searchBtnIsNotClickable();
    }
}
