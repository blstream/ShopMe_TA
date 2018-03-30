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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.pages.SearchResultsPage;
import tests.pages.SearchServicePage;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static tests.Hooks.wait;

public class SearchStepDefs {

    SearchServicePage searchServicePage = new SearchServicePage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();


    @Given("^that there are no services added$")
    public void thatThereAreNoServicesAdded() {
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        ResponseBody body = response.getBody();
        List<HashMap> offers = new JsonPath(body.asString()).get(".");
        for (int i = 0; i < offers.size(); i++) {
            String offer = offers.get(i).get("id").toString();
            RestAssured.given().when().delete("/offers/" + offer);
        }
        response = httpRequest.get("/offers");
        body = response.getBody();
        offers = new JsonPath(body.asString()).get(".");
        assertTrue(offers.size() == 0);
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
            newService.addProperty("baseDescription", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            newService.addProperty("basePrice", "10");
            JsonObject userJson = new JsonObject();
            userJson.addProperty("id", "aaa2e1cd-6319-4fa3-b05f-d47f4aec7dac");
            userJson.addProperty("name", "Jan Kowalski");
            userJson.addProperty("email", "example@mail.com");
            userJson.addProperty("phoneNumber", "012345678");
            newService.add("user", userJson);
            JsonObject categoryJson = new JsonObject();
            categoryJson.addProperty("id", category.get("id").toString());
            categoryJson.addProperty("name", category.get("name").toString());
            newService.add("category", categoryJson);
            RestAssured.given().contentType("application/json").body(newService.toString()).when().post("/offers").then().assertThat().statusCode(200);
        }
    }

    @When("^I navigate to the main page$")
    public void iNavigateToTheMainPage() {
        searchServicePage.goToMainPage();
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

    @And("^all results are sorted in ascending way$")
    public void allResultsAreSortedInAscendingWay() {
        List<String> dates = searchResultsPage.getElementsDates();
        for (int i = 0; i < dates.size() - 1; i++) {
            Long nextDataL = Long.valueOf(dates.get(i + 1));
            Long actualDateL = Long.valueOf(dates.get(i));
            assertTrue(actualDateL <= nextDataL);
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
        String NoResultsMessage = searchResultsPage.getNoResultsMessage();
        assertEquals(expectedNoResultsMessage, NoResultsMessage);
    }

    @And("^I see service \"([^\"]*)\" at the first place$")
    public void iSeeServiceAtTheFirstPlace(String NewService) {
        String firstService = searchResultsPage.getFirstService();
        assertTrue(firstService.contains(NewService));
    }

    @Then("^search results for the new phrase \"([^\"]*)\" are visible$")
    public void searchResultsForTheNewPhraseAreVisible(String arg0) {
        searchResultsPage.areNewResultsPresent(arg0);
    }
}
