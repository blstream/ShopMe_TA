package tests.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.pages.AddServicePage;
import tests.pages.SearchServicePage;

public class AddServiceStepDefs {

    AddServicePage addServicePage = new AddServicePage();
    SearchServicePage searchServicePage = new SearchServicePage();

    @Given("^I go to ShopMe main page$")
    public void iGoToShopMeMainPage() {
        searchServicePage.goToMainPage();
    }

    @And("^I push add service button$")
    public void iPushAddServiceButton() {
        searchServicePage.pushNewServiceButton();
    }

    @And("^I can see adding form$")
    public void iCanSeeAddingForm() { addServicePage.verifyIfFormIsVisible();
    }

    @When("^I fill in title with \"([^\"]*)\"$")
    public void iFillInTitleWith(String title) {
        addServicePage.sendTitle(title);
        addServicePage.setRA_title(title);
    }

    @And("^I choose category \"([^\"]*)\"$")
    public void iChooseCategory(String category) { addServicePage.selectServiceCategory(category);
    }

    @And("^I fill in name with \"([^\"]*)\"$")
    public void iFillInNameWith(String name) { addServicePage.sendName(name); }

    @And("^I fill in email with \"([^\"]*)\"$")
    public void iFillInEmailWith(String email) { addServicePage.sendEmail(email); }

    @And("^I fill in phone with \"([^\"]*)\"$")
    public void iFillInPhoneWith(String phone) { addServicePage.sendUserPhone(phone); }

    @And("^I fill in basicPrice with \"([^\"]*)\"$")
    public void iFillInBasicPriceWith(String basicPrice) { addServicePage.sendBasicPrice(basicPrice); }

    @And("^I fill in basicDescription with \"([^\"]*)\"$")
    public void iFillInBasicDescriptionWith(String basicDescription) {
        addServicePage.sendBasicDescription(basicDescription);
        addServicePage.setRA_basicDescription(basicDescription);
    }

    @And("^I fill in aboutMe with \"([^\"]*)\"$")
    public void iFillInAboutMeWith(String aboutMe) {
        addServicePage.sendAboutMe(aboutMe);
        addServicePage.setRA_aboutMe(aboutMe);
    }

    @And("^I press Add service button$")
    public void iPressAddServiceButton() { addServicePage.pushSubmitButton(); }

    @And("^I fill in expandedPrice with \"([^\"]*)\"$")
    public void iFillInExpandedPriceWith(String expandedPrice) {
        addServicePage.sendExpandedPrice(expandedPrice); }

    @And("^I fill in extraPrice with \"([^\"]*)\"$")
    public void iFillInExtraPriceWith(String extraPrice) { addServicePage.sendExtraPrice(extraPrice); }

    @And("^I fill in expandedDescription with \"([^\"]*)\"$")
    public void iFillInExpandedDescriptionWith(String expandedDescription) {
        addServicePage.sendExpandedDescription(expandedDescription);
        addServicePage.setRA_extendedDescription(expandedDescription);
    }


    @And("^I fill in basicDescription with (\\d+) characters$")
    public void iFillInBasicDescriptionWithCharacters(int arg0) {
        addServicePage.sendBasicDescription(arg0);
        addServicePage.setRA_basicDescription(addServicePage.generateString(arg0));
    }

    @And("^I fill in expandedDescription with (\\d+) characters$")
    public void iFillInExpandedDescriptionWithCharacters(int arg0) {
        addServicePage.sendExpandedDescription(arg0);
        addServicePage.setRA_extendedDescription(addServicePage.generateString(arg0));
    }


    @And("^I fill in aboutMe with (\\d+) characters$")
    public void iFillInAboutMeWithCharacters(int arg0) {
        addServicePage.sendAboutMeDescription(arg0);
        addServicePage.setRA_aboutMe(addServicePage.generateString(arg0));
    }

    @Then("^New service with (\\d+) character aboutMe is added$")
    public void newServiceWithCharacterAboutMeIsAdded(int arg0) {
        addServicePage.RA_checkAboutMe();
    }

    @Then("^I should see confirmation message \"([^\"]*)\"$")
    public void iShouldSeeConfirmationMessage(String confirmationMessage) {
        addServicePage.verifyIfConfirmationMessageIsVisible(confirmationMessage);
        addServicePage.RA_checkTitle();

    }

    @Then("^New basic service with (\\d+) character description is added$")
    public void newBasicServiceWithCharacterDescriptionIsAdded(int arg0) {
        addServicePage.RA_checkBasicDescription();
    }

    @Then("^New service with (\\d+) character expanded and extra descriptions is added$")
    public void newServiceWithCharacterExpandedAndExtraDescriptionsIsAdded(int arg0) {
        addServicePage.RA_checkExtendedDescription();
        addServicePage.RA_checkExtraDescription();
    }

    @And("^I fill in extraDescription with \"([^\"]*)\"$")
    public void iFillInExtraDescriptionWith(String arg0) {
        addServicePage.sendExtraDescription1(arg0);
        addServicePage.setRA_extraDescription(arg0);
    }

    @And("^I fill in extraDescription with (\\d+) characters$")
    public void iFillInExtraDescriptionWithCharacters(int arg0)  {
        addServicePage.sendExtraDescription(arg0);
        addServicePage.setRA_extraDescription(addServicePage.generateString(arg0));
    }
}

