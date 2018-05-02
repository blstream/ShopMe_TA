package tests.steps;

import com.google.gson.JsonObject;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import tests.pages.SearchResultsPage;
import tests.pages.SearchServicePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SearchStepDefs {

    SearchServicePage searchServicePage = new SearchServicePage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();


    @Given("^that there are no services added$")
    public void thatThereAreNoServicesAdded() {
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        ResponseBody body = response.getBody();
        HashMap offersResponse = new JsonPath(body.asString()).get(".");
        List<HashMap> offers = (List<HashMap>) offersResponse.get("content");
        Integer total = Integer.parseInt(offersResponse.get("totalElements").toString());

        if (total <= 0) {
            return;
        }

        for (; ; ) {

            for (int i = 0; i < offers.size(); i++) {
                String offer = offers.get(i).get("id").toString();
                RestAssured.given().when().delete("/offers/" + offer);
            }
            response = httpRequest.get("/offers");
            body = response.getBody();
            offersResponse = new JsonPath(body.asString()).get(".");
            offers = (List<HashMap>) offersResponse.get("content");
            total = Integer.parseInt(offersResponse.get("totalElements").toString());

            if (total <= 0) {
                break;
            }
        }
        assertTrue(total == 0);
    }

    @And("^I add services$")
    public void iAddServices(DataTable services) {

        DataTable dt = services;
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/categories");
        ResponseBody body = response.getBody();
        List<HashMap> categories = new JsonPath(body.asString()).get(".");
        HashMap category = categories.get(0);

        for (int i = 0; i < dt.getGherkinRows().size(); i++) {

            DataTableRow someRow = dt.getGherkinRows().get(i);
            String str = someRow.getCells().get(0);

            JsonObject newService = new JsonObject();
            newService.addProperty("id", "aaa2e1cd-6319-4fa3-b05f-d47f4aec7dac");
            newService.addProperty("title", str);
            newService.addProperty("baseDescription", "test");
            newService.addProperty("extendedDescription", "test");
            newService.addProperty("extraDescription", "test");

            newService.addProperty("basePrice", "10");
            newService.addProperty("extendedPrice", "20");
            newService.addProperty("extraPrice", "30");
            JsonObject userJson = new JsonObject();
            userJson.addProperty("id", "aaa2e1cd-6319-4fa3-b05f-d47f4aec7dac");
            userJson.addProperty("name", "test");
            userJson.addProperty("email", "test@domain.com");
            userJson.addProperty("phoneNumber", "888555222");
            userJson.addProperty("additionalInfo", "test");
            newService.add("user", userJson);
            JsonObject categoryJson = new JsonObject();
            categoryJson.addProperty("id", category.get("id").toString());
            categoryJson.addProperty("name", category.get("name").toString());
            newService.add("category", categoryJson);
            RestAssured.given().contentType("application/json").body(newService.toString()).when().post("/offers").then().assertThat().statusCode(200);
        }
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
        assertTrue(searchResultsPage.isResultsPresent());
    }

    @And("^I see that title of the service contains \"([^\"]*)\"$")
    public void iSeeThatTitleOfTheServiceContains(String searchPhrase) {
        List<String> titles = searchResultsPage.getElementsTitles();
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i).toLowerCase();
            assertTrue(title.contains(searchPhrase.toLowerCase()));
        }
    }

    @And("^I see basic price and added data of each record$")
    public void iSeeBasicPriceAndAddedDataOfEachRecord() {
        List<String> prices = searchResultsPage.getElementsPrices();
        List<String> dates = searchResultsPage.getElementsDates();

        assertTrue(prices.size() == dates.size());
        for (int i = 0; i < prices.size(); i++) {
            String price = prices.get(i);
            String date = dates.get(i);
            assertTrue(!price.isEmpty() && !date.isEmpty());
        }
    }

    @And("^all results are sorted in descending way$")
    public void allResultsAreSortedInDescendingWay() {
        List<String> dates = searchResultsPage.getElementsDates();
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
        String firstService = searchResultsPage.getFirstService();
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
