package tests.steps;

import cucumber.api.java.en.And;
import tests.pages.SearchResultsPage;
import tests.pages.ServiceProfilePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServiceStepDefs {

    SearchResultsPage searchResultsPage = new SearchResultsPage();
    ServiceProfilePage serviceProfilePage = new ServiceProfilePage();

    @And("^I can click on the first search result to see the details$")
    public void iCanClickOnTheFirstSearchResultToSeeTheDetails() {
        searchResultsPage.openServiceFromResults(0);
        serviceProfilePage.waitForServiceDetails();
    }

    @And("^I can see the title \"([^\"]*)\"$")
    public void iCanSeeTheTitle(String title) {
        String expectedTitle = serviceProfilePage.getTitleFieldText();
        assertEquals(title, expectedTitle);
    }

    @And("^I can see the category \"([^\"]*)\"$")
    public void iCanSeeTheCategory(String category) {
        String expectedCategory = serviceProfilePage.getCategoryFieldText();
        assertEquals(category, expectedCategory);
    }

    @And("^I can see user's name \"([^\"]*)\"$")
    public void iCanSeeUserSName(String userName) {
        String expectedUserName = serviceProfilePage.getUserNameFieldText();
        assertEquals(userName, expectedUserName);
    }

    @And("^I expand user's email and user's phone$")
    public void iExpandUserSEmailAndUserSPhone() {
        serviceProfilePage.showUserEmail();
        serviceProfilePage.showUserPhone();
    }

    @And("^I can see user's email \"([^\"]*)\"$")
    public void iCanSeeUserSEmail(String userEmail) {
        String expectedUserEmail = serviceProfilePage.getUserEmailFieldText();
        assertTrue(expectedUserEmail.contains(userEmail));

    }

    @And("^I can see user's phone \"([^\"]*)\"$")
    public void iCanSeeUserSPhone(String userPhone) {
        String expectedUserPhone = serviceProfilePage.getUserPhoneFieldText();
        assertTrue(expectedUserPhone.contains(userPhone));
    }

    @And("^I can see additional user info \"([^\"]*)\"$")
    public void iCanSeeAdditionalUserInfo(String userInfo) {
        String expectedUserInfo = serviceProfilePage.getUserInfoFieldText();
        assertTrue(expectedUserInfo.contains(userInfo));
    }

    @And("^I can see basic service \"([^\"]*)\" with price \"([^\"]*)\"$")
    public void iCanSeeBasicServiceAndPrice(String basicInfo, String basicPrice) {
        String expectedInfo = serviceProfilePage.getServiceInfoContainerText();
        assertTrue(expectedInfo.contains(basicInfo));
        assertTrue(expectedInfo.contains(basicPrice));
    }

    @And("^I can see extended service \"([^\"]*)\" with price \"([^\"]*)\"$")
    public void iCanSeeExtendedServiceAndPrice(String extendedInfo, String extendedPrice) {
        String expectedInfo = serviceProfilePage.getServiceInfoContainerText();
        assertTrue(expectedInfo.contains(extendedInfo));
        assertTrue(expectedInfo.contains(extendedPrice));
    }

    @And("^I can see extra service \"([^\"]*)\" with price \"([^\"]*)\"$")
    public void iCanSeeExtraServiceAndPrice(String extraInfo, String extraPrice) {
        String expectedInfo = serviceProfilePage.getServiceInfoContainerText();
        assertTrue(expectedInfo.contains(extraInfo));
        assertTrue(expectedInfo.contains(extraPrice));
    }
}
