package tests.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.junit.Assert;
import tests.helpers.RestAssuredMethods;
import tests.objects.MyService;
import tests.objects.User;
import tests.objects.Voivodeship;
import tests.pages.SearchResultsPage;
import static tests.Hooks.driver;

import java.util.List;


public class PaginationStepDefs {

    SearchResultsPage searchResultsPage = new SearchResultsPage();
    RestAssuredMethods restAssuredMethods = new RestAssuredMethods("https://patronage2018.intive-projects.com/api");
    private List<MyService> servicesBeforeAdd;

    public void setServicesBeforeAdd(List<MyService> servicesBeforeAdd) {
        this.servicesBeforeAdd = servicesBeforeAdd;
    }

    @Then("^The results for \"([^\"]*)\" are shifted ten forward$")
    public void theResultsForAreShiftedTenForward(String searchingPhrase) {
        List<MyService> newServices = restAssuredMethods.searchForServices(searchingPhrase);
        List<MyService> servicesFromFourthPage = restAssuredMethods.searchForServicesOnPage(4, 10, searchingPhrase).content;
        for (int i = 0; i < servicesBeforeAdd.size(); i++)
            Assert.assertTrue(servicesBeforeAdd.get(i).equalsOnList(newServices.get(i + 10)));
        for (int i = 0; i < 10; i++)
            Assert.assertTrue(searchResultsPage.getServicesTitles().get(i).equals(servicesFromFourthPage.get(i).title));
    }

    @When("^I add (\\d+) different services$")
    public void iAddDifferentServices(int numberOfServices, DataTable services) {
        DataTable dt = services;
        MyService service = new MyService();
        User user1 = new User();
        for (int i = 0; i < numberOfServices; i++) {
            DataTableRow someRow = dt.getGherkinRows().get(0);

            service.title = someRow.getCells().get(0) + String.valueOf(i);
            service.category = someRow.getCells().get(1);
            user1.voivodeship = new Voivodeship(someRow.getCells().get(12));
            service.voivodeship = someRow.getCells().get(12);
            service.city = someRow.getCells().get(13);
            service.baseDescription = someRow.getCells().get(6);
            service.basePrice = Float.valueOf(someRow.getCells().get(7)) + i;
            service.extendedDescription = someRow.getCells().get(8);
            service.extendedPrice = Float.valueOf(someRow.getCells().get(9));
            service.extraDescription = someRow.getCells().get(10);
            service.extraPrice = Float.valueOf(someRow.getCells().get(11));
            String token = restAssuredMethods.authorizeAndGetBearerToken();
            restAssuredMethods.authenticateAndAddService(service, token);
        }
    }

    @Then("^I can see all of the services with name \"([^\"]*)\" from database$")
    public void iCanSeeAllServicesFromDatabaseWithTitle(String titlePhrase) {
        searchResultsPage.areAllDatabaseServicesPresent(titlePhrase);
    }

    @And("^I can see first (\\d+) records$")
    public void iCanSeeFirstRecords(int numberOfServices) {
        searchResultsPage.waitForNewResults();
        searchResultsPage.isNumberOfServicesCorrect(numberOfServices);
        Assert.assertTrue(searchResultsPage.getServicesTitles().get(0).substring(0, 1).equals("1"));
    }

    @And("^I can see pagination buttons$")
    public void iCanSeePaginationButtons() {
        searchResultsPage.paginationButtonsAreVisible();
    }

    @And("^I can see that number (\\d+) is bold$")
    public void iCanSeeThatNumberIsBold(int pageNumber) {
        searchResultsPage.isActualPageBold(pageNumber);
    }

    @And("^I can see how many search result pages there are$")
    public void iCanSeeHowManySearchResultPagesThereAre() {
        searchResultsPage.isNumberOfAllPagesVisible();
    }

    @Then("^Every page but the last one contain (\\d+) results$")
    public void everyPageButTheLastOneContainResults(int expectedNumberOrServices) {
        searchResultsPage.isCorrectNumberOfResultsOnEachPage(expectedNumberOrServices);
    }

    @And("^The last page contains maximum (\\d+) results$")
    public void theLastPageContainsLessThanResults(int limit) {
        searchResultsPage.isNumberOfLastServicesUnderLimit(limit);
    }

    @And("^I click the second page button$")
    public void iClickPageButton() {
        searchResultsPage.pushPageNumberButton(0, 2);
    }

    @And("^I can see (\\d+) results from the second page$")
    public void iCanSeeResultsFromTheSecondPage(int numberOfServices) {
        searchResultsPage.waitForNewResults();
        searchResultsPage.isNumberOfServicesCorrect(numberOfServices);
        Assert.assertTrue(searchResultsPage.getServicesTitles().get(1).substring(0, 2).equals("12"));
    }

    @And("^I click next-button$")
    public void iClickNextButton() {
        searchResultsPage.pushNextButton();
    }

    @And("^previous-button is visible$")
    public void previousButtonIsVisible() {
        searchResultsPage.previousButtonIsVisible();
    }

    @And("^I can return to the first page$")
    public void iCanReturnToTheFirstPage() {
        Assert.assertTrue(searchResultsPage.isFirstPageButtonClickable());
    }

    @And("^I click the last page button$")
    public void iClickTheLastPageButton() {
        searchResultsPage.pushLastPageButton();
    }

    @And("^I am on the last page$")
    public void iAmOnTheLastPage() {
        searchResultsPage.areLastServicesVisible();
    }

    @And("^I can see first page button$")
    public void iCanSeeFirstPageButton() {
        searchResultsPage.firstPageButtonIsVisible();
    }

    @And("^next-button is invisible$")
    public void nextButtonIsInvisible() {
        Assert.assertTrue(searchResultsPage.isNextButtonInvisible());
    }

    @And("^I can return to the first page by clicking previous-button$")
    public void iCanReturnToTheFirstPageByClickingPreviousButton() {
        searchResultsPage.returnToFirstPage();
    }

    @And("^I click third page button$")
    public void iClickThirdPageButton() {
        searchResultsPage.pushPageNumberButton(1, 3);
    }

    @And("^I have a list of saved services for phrase \"([^\"]*)\"$")
    public void iHaveAListOfSavedServicesFor(String searchingPhrase) {
        List<MyService> allServices = restAssuredMethods.searchForServices(searchingPhrase);
        setServicesBeforeAdd(allServices);
    }

    @And("^I click fourth page button$")
    public void iClickFourthPageButton() {
        searchResultsPage.pushPageNumberButton(3, 4);
    }

    @And("^I delete (\\d+) services from the page number (\\d+) with searching phrase \"([^\"]*)\"$")
    public void iDeleteServicesFromTheSecondPage(int servicesNumber, int pageNumber, String title) {
        String token = restAssuredMethods.authorizeAndGetBearerToken();
        searchResultsPage.deleteServicesFromPage(servicesNumber, pageNumber, title, token);
    }

    @Then("^The results for \"([^\"]*)\" are shifted (\\d+) to the back$")
    public void theResultsAreShiftedFourToTheBack(String searchingPhrase, int numberOfDeleteResults) {
        List<MyService> newServices = restAssuredMethods.searchForServices(searchingPhrase);
        List<MyService> servicesFromFourthPage = restAssuredMethods.searchForServicesOnPage(4, 10, searchingPhrase).content;
        for (int i = 0; i < 10; i++)
            Assert.assertTrue(servicesBeforeAdd.get(i).equalsOnList(newServices.get(i)));
        for (int i = 10 + numberOfDeleteResults; i < newServices.size(); i++)
            Assert.assertTrue(servicesBeforeAdd.get(i).equalsOnList(newServices.get(i - numberOfDeleteResults)));
        for (int i = 0; i < 10; i++)
            Assert.assertTrue(searchResultsPage.getServicesTitles().get(i).equals(servicesFromFourthPage.get(i).title));
    }

    @And("^I am on (\\d+) page$")
    public void iAmOnPage(int pageNumber) {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("page="+pageNumber));
    }
}
