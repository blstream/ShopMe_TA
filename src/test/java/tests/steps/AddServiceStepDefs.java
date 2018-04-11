package tests.steps;

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
    public void iCanSeeAddingForm() {
        addServicePage.verifyIfFormIsVisible();
    }

    @When("^I fill in title with \"([^\"]*)\"$")
    public void iFillInTitleWith(String title) {
        addServicePage.sendTitle(title);
        addServicePage.setRA_title(title);
    }

    @And("^I choose category \"([^\"]*)\"$")
    public void iChooseCategory(String category) {
        addServicePage.selectServiceCategory(category);
    }

    @And("^I fill in name with \"([^\"]*)\"$")
    public void iFillInNameWith(String name) {
        addServicePage.sendName(name);
    }

    @And("^I fill in email with \"([^\"]*)\"$")
    public void iFillInEmailWith(String email) {
        addServicePage.sendEmail(email);
    }

    @And("^I fill in phone with \"([^\"]*)\"$")
    public void iFillInPhoneWith(String phone) {
        addServicePage.sendUserPhone(phone);
    }

    @And("^I fill in basicPrice with \"([^\"]*)\"$")
    public void iFillInBasicPriceWith(String basicPrice) {
        addServicePage.sendBasicPrice(basicPrice);
    }

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
    public void iPressAddServiceButton() {
        addServicePage.pushSubmitButton();
    }

    @And("^I fill in expandedPrice with \"([^\"]*)\"$")
    public void iFillInExpandedPriceWith(String expandedPrice) {
        addServicePage.sendExpandedPrice(expandedPrice);
    }

    @And("^I fill in extraPrice with \"([^\"]*)\"$")
    public void iFillInExtraPriceWith(String extraPrice) {
        addServicePage.sendExtraPrice(extraPrice);
    }

    @And("^I fill in expandedDescription with \"([^\"]*)\"$")
    public void iFillInExpandedDescriptionWith(String expandedDescription) {
        addServicePage.sendExpandedDescription(expandedDescription);
        addServicePage.setRA_extendedDescription(expandedDescription);
    }


    @And("^I fill in basicDescription with (\\d+) characters$")
    public void iFillInBasicDescriptionWithCharacters(int phraseLength) {
        addServicePage.sendBasicDescription(phraseLength);
        addServicePage.setRA_basicDescription(addServicePage.generateString(phraseLength));
    }

    @And("^I fill in expandedDescription with (\\d+) characters$")
    public void iFillInExpandedDescriptionWithCharacters(int phraseLength) {
        addServicePage.sendExpandedDescription(phraseLength);
        addServicePage.setRA_extendedDescription(addServicePage.generateString(phraseLength));
    }

    @And("^I fill in aboutMe with (\\d+) characters$")
    public void iFillInAboutMeWithCharacters(int phraseLength) {
        addServicePage.sendAboutMeDescription(phraseLength);
        addServicePage.setRA_aboutMe(addServicePage.generateString(phraseLength));
    }

    @Then("^New service with (\\d+) character aboutMe is added$")
    public void newServiceWithCharacterAboutMeIsAdded(int phraseLength) {
        addServicePage.RA_checkAboutMe();
    }

    @Then("^I should see confirmation message \"([^\"]*)\"$")
    public void iShouldSeeConfirmationMessage(String confirmationMessage) {
        addServicePage.verifyIfConfirmationMessageIsVisible(confirmationMessage);
        addServicePage.RA_checkTitle();
    }

    @Then("^New basic service with (\\d+) character description is added$")
    public void newBasicServiceWithCharacterDescriptionIsAdded(int phraseLength) { addServicePage.RA_checkBasicDescription(); }

    @Then("^New service with (\\d+) character expanded and extra descriptions is added$")
    public void newServiceWithCharacterExpandedAndExtraDescriptionsIsAdded(int phraseLength) {
        addServicePage.RA_checkExtendedDescription();
        addServicePage.RA_checkExtraDescription();
    }

    @And("^I fill in extraDescription with \"([^\"]*)\"$")
    public void iFillInExtraDescriptionWith(String extraDescription) {
        addServicePage.sendExtraDescription1(extraDescription);
        addServicePage.setRA_extraDescription(extraDescription);
    }

    @And("^I fill in extraDescription with (\\d+) characters$")
    public void iFillInExtraDescriptionWithCharacters(int phraseLength) {
        addServicePage.sendExtraDescription(phraseLength);
        addServicePage.setRA_extraDescription(addServicePage.generateString(phraseLength));
    }
}

