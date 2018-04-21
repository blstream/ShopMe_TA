package tests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import tests.pages.SearchServicePage;

public class NavigationStepDefs {

    SearchServicePage searchServicePage = new SearchServicePage();

    @And("^I click ShopMe button$")
    public void iClickShopMeButton() {
        searchServicePage.pushShopMeButton();
    }

    @Then("^I should see ShopMe main page$")
    public void iShouldSeeShopMeMainPage() {
        searchServicePage.verifyIfMainPageIsVisible();
    }
}
