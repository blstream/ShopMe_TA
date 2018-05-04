package tests.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import tests.helpers.RestAssuredMethods;
import tests.objects.MyService;
import tests.pages.SearchResultsPage;

import java.util.List;


public class PaginationStepDefs {

    SearchResultsPage searchResultsPage = new SearchResultsPage();
    RestAssuredMethods restAssuredMethods = new RestAssuredMethods("https://patronage2018.intive-projects.com/api");
    private List<MyService> servicesBeforeAdd;

    public void setServicesBeforeAdd(List<MyService> servicesBeforeAdd) {
        this.servicesBeforeAdd = servicesBeforeAdd;
    }

    @Then("^The results for \"([^\"]*)\" are shifted one forward$")
    public void theResultsForAreShiftedOneForward(String searchingPhrase) {
        List<MyService> newServices = restAssuredMethods.searchForServices(searchingPhrase);
        List<MyService> List = restAssuredMethods.searchForServicesWithPage(4, 10, searchingPhrase).content;
        for (int i = 0; i < servicesBeforeAdd.size(); i++)
            Assert.assertTrue(servicesBeforeAdd.get(i).equalsOnList(newServices.get(i + 1)));
        for (int i = 0; i < 10; i++)
            Assert.assertTrue(searchResultsPage.getServicesTitles().get(i).substring(4).equals(List.get(i).title));
    }

    @When("^I add (\\d+) different services$")
    public void iAddDifferentServices(int numberOfServices, DataTable services) {
        restAssuredMethods.addALotOfServicesServices(services, numberOfServices);
    }

    @Then("^I can go to any service from database with title \"([^\"]*)\"$")
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

    @And("^The last page contains less than (\\d+) results$")
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

    @And("^I can see list of last records$")
    public void iCanSeeListOfLastRecords() {
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

    @And("^I click third page button and I check searching results for \"([^\"]*)\"$")
    public void iClickThirdPageButton(String searchingPhrase) {
        searchResultsPage.pushPageNumberButton(1, 3);
        List<MyService> allServices = restAssuredMethods.searchForServices(searchingPhrase);
        setServicesBeforeAdd(allServices);
    }

    @And("^I click fourth page button$")
    public void iClickFourthPageButton() {
        searchResultsPage.pushPageNumberButton(3, 4);
    }

    @And("^I delete (\\d+) services from the page number (\\d+)$")
    public void iDeleteServicesFromTheSecondPage(int servicesNumber, int pageNumber) {
        searchResultsPage.deleteServicesFromPage(servicesNumber, pageNumber);
    }

    @Then("^The results for \"([^\"]*)\" are shifted (\\d+) to the back$")
    public void theResultsAreShiftedFourToTheBack(String searchingPhrase, int numberOfDeleteResults) {
        List<MyService> newServices = restAssuredMethods.searchForServices(searchingPhrase);
        List<MyService> List = restAssuredMethods.searchForServicesWithPage(4, 10, searchingPhrase).content;
        for (int i = 0; i < 30; i++)
            Assert.assertTrue(servicesBeforeAdd.get(i).equalsOnList(newServices.get(i)));
        for (int i = 30 + numberOfDeleteResults - 1; i < newServices.size(); i++)
            Assert.assertTrue(servicesBeforeAdd.get(i).equalsOnList(newServices.get(i - 4)));
        for (int i = 0; i < 10; i++)
            Assert.assertTrue(searchResultsPage.getServicesTitles().get(i).substring(4).equals(List.get(i).title));
    }
}
