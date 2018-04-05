package tests.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import tests.pages.AddServicePage;
import tests.pages.SearchServicePage;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class AddServiceStepDefs {

    AddServicePage addServicePage = new AddServicePage();
    SearchServicePage searchServicePage = new SearchServicePage();
    AddServicePage.AddServiceData addServiceData;
    public AddServicePage.AddServiceData getAddServiceData() {
        return addServiceData;
    }

    @Given("^I go to ShopMe main page$")
    public void iGoToShopMeMainPage() {
        searchServicePage.goToMainPage();
    }

    @And("^I push add service button$")
    public void iPushAddServiceButton() {
        searchServicePage.pushNewServiceButton();
    }

    @And("^I can see adding form$")
    public void iCanSeeAddingForm() {
        //addServicePage.verifyIfFormIsVisible();
    }

    @When("^I fill in title with \"([^\"]*)\"$")
    public void iFillInTitleWith(String title) {
        addServicePage.sendTitle(title);
    }

    @And("^I choose category \"([^\"]*)\"$")
    public void iChooseCategory(String category) {
        //addServicePage.selectServiceCategory(category);
       // addServiceData.category=category
    }

    @And("^I fill in name with \"([^\"]*)\"$")
    public void iFillInNameWith(String name) {
        addServicePage.sendName(name);
        addServiceData.setName(name);
    }

    @And("^I fill in email with \"([^\"]*)\"$")
    public void iFillInEmailWith(String email) {
        addServicePage.sendEmail(email);
        addServiceData.email=email;
    }

    @And("^I fill in phone with \"([^\"]*)\"$")
    public void iFillInPhoneWith(String phone) {
        addServicePage.sendUserPhone(phone);
        addServiceData.phone=phone;
    }

    @And("^I fill in basicPrice with \"([^\"]*)\"$")
    public void iFillInBasicPriceWith(String basicPrice) {
        addServicePage.sendBasicPrice(basicPrice);
        addServiceData.basicPrice=basicPrice;
    }

    @And("^I fill in basicDescription with \"([^\"]*)\"$")
    public void iFillInBasicDescriptionWith(String basicDescription) {
        addServicePage.sendBasicDescription(basicDescription);
        addServiceData.basicDescription=basicDescription;
    }

    @And("^I fill in aboutMe with \"([^\"]*)\"$")
    public void iFillInAboutMeWith(String aboutMe) {
        addServicePage.sendAboutMe(aboutMe);
        addServiceData.aboutMe=aboutMe;
    }

    @And("^I press Add service button$")
    public void iPressAddServiceButton() {
        addServicePage.pushSubmitButton();
    }


    @And("^I fill in expandedPrice with \"([^\"]*)\"$")
    public void iFillInExpandedPriceWith(String expandedPrice) {
        addServicePage.sendExpandedPrice(expandedPrice);
        addServiceData.extendedPrice=expandedPrice;
    }

    @And("^I fill in extraPrice with \"([^\"]*)\"$")
    public void iFillInExtraPriceWith(String extraPrice) {
        addServicePage.sendExtraPrice(extraPrice);
        addServiceData.extraPrice=extraPrice;
    }

    @And("^I fill in expandedDescription with \"([^\"]*)\"$")
    public void iFillInExpandedDescriptionWith(String expandedDescription) {
        addServicePage.sendExpandedDescription(expandedDescription);
        addServiceData.extendedDescription=expandedDescription;
    }

    @And("^I fill in extraDescription with \"([^\"]*)\"$")
    public void iFillInExtraDescriptionWith(String extraDescription) {
        addServicePage.sendExtraDescription(extraDescription);
        addServiceData.extraDescription=extraDescription;
    }

    @And("^I fill in basicDescription with (\\d+) characters$")
    public void iFillInBasicDescriptionWithCharacters(int arg0) {
        addServicePage.sendBasicDescription(arg0);
       // addServiceData.basicDescription=arg0;
    }

    @Then("^New service with (\\d+) character description is added$")
    public void newServiceWithCharacterDescriptionIsAdded(int arg0) {
    }

    @And("^I fill in expandedDescription with (\\d+) characters$")
    public void iFillInExpandedDescriptionWithCharacters(int arg0) {
        addServicePage.sendExpandedDescription(arg0);
    }

    @And("^I fill in extraDescription with (\\d+) characters$")
    public void iFillInExtraDescriptionWithCharacters(int arg0) {
        addServicePage.sendExtraDescription(arg0);
    }

    @Then("^New service with (\\d+) character descriptions is added$")
    public void newServiceWithCharacterDescriptionsIsAdded(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I fill in aboutMe with (\\d+) characters$")
    public void iFillInAboutMeWithCharacters(int arg0) {
        addServicePage.sendAboutMeDescription(arg0);
    }

    @Then("^New service with (\\d+) character aboutMe is added$")
    public void newServiceWithCharacterAboutMeIsAdded(int arg0) {

    }


    @Then("^I should see confirmation message \"([^\"]*)\"$")
    public void iShouldSeeConfirmationMessage(String confirmationMessage) {
        addServicePage.verifyIfConfirmationMessageIsDisplayed(confirmationMessage);
        addServicePage.RestAssured(addServiceData);
    }
}

