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
    public void newBasicServiceWithCharacterDescriptionIsAdded(int phraseLength) {
        addServicePage.RA_checkBasicDescription();
    }

    @Then("^New service with (\\d+) character expanded and extra descriptions is added$")
    public void newServiceWithCharacterExpandedAndExtraDescriptionsIsAdded(int phraseLength) {
        addServicePage.RA_checkExtendedDescription();
        addServicePage.RA_checkExtraDescription();
    }

    @And("^I fill in extraDescription with \"([^\"]*)\"$")
    public void iFillInExtraDescriptionWith(String extraDescription) {
        addServicePage.sendExtraDescription(extraDescription);
        addServicePage.setRA_extraDescription(extraDescription);
    }

    @And("^I fill in extraDescription with (\\d+) characters$")
    public void iFillInExtraDescriptionWithCharacters(int phraseLength) {
        addServicePage.sendExtraDescription(phraseLength);
        addServicePage.setRA_extraDescription(addServicePage.generateString(phraseLength));
    }

    @Then("^I should see an error message \"([^\"]*)\" next to the required field$")
    public void iShouldSeeAnErrorMessageNextToTheRequiredField(String message) {
        addServicePage.verifyIfValidationErrorMessageIsVisible(message);
    }

    @And("^I should see a message \"([^\"]*)\"$")
    public void iShouldSeeAMessage(String message) {
        addServicePage.verifyIfValidationErrorMessageIsVisible(message);
    }

    @And("^I should see inserted values in filled fields$")
    public void iShouldSeeInsertedValuesInFilledFields() {
        addServicePage.verifyIfValuesEqualsAfterPageRefresh();
    }

    @And("^I should see an error message \"([^\"]*)\" next to the field with invalid data$")
    public void iShouldSeeAnErrorMessageNextToTheFieldWithInvalidData(String error_message) {
        addServicePage.verifyIfValidationErrorMessageIsVisible(error_message);
    }

    @Then("^I should see in about_me maximum (\\d+) characters$")
    public void iShouldSeeInAbout_meMaximumCharacters(int maximumLength) {
        addServicePage.verifyIfAboutMeInputLimited(maximumLength);
    }

    @Then("^I should see in title maximum (\\d+) characters$")
    public void iShouldSeeInTitleMaximumCharacters(int maximumLength) {
        addServicePage.verifyIfTitleInputLimited(maximumLength);
    }

    @Then("^I should see in name  maximum (\\d+) characters$")
    public void iShouldSeeInNameMaximumCharacters(int maximumLength) {
        addServicePage.verifyIfNameInputLimited(maximumLength);
    }

    @Then("^I should see in basic_price maximum (\\d+) decimal places$")
    public void iShouldSeeInBasic_priceMaximumDecimalPlaces(int maximumLength) {
        addServicePage.verifyIfBasicPriceInputLimited(maximumLength);
    }

    @And("^I should see in phone maximum (\\d+) characters$")
    public void iShouldSeeInPhoneMaximumCharacters(int maximumLength) {
        addServicePage.verifyIfPhoneInputLimited(maximumLength);
    }

    @Then("^I should see that expanded_description and expanded_price fields are blocked$")
    public void iShouldSeeThatExpanded_descriptionAndExpanded_priceFieldsAreBlocked() {
        addServicePage.verifyIfExpandedDescriptionAndPriceAreBlocked();
    }

    @Then("^I should see that extra_description and extra_price fields are blocked$")
    public void iShouldSeeThatExtra_descriptionAndExtra_priceFieldsAreBlocked() {
        addServicePage.verifyIfExtraDescriptionAndPriceAreBlocked();
    }

    @Then("^I should see in expanded_description maximum (\\d+) characters$")
    public void iShouldSeeInExpanded_descriptionMaximumCharacters(int maximumLength) {
        addServicePage.verifyIfExpandedDescriptionInputLimited(maximumLength);
    }

    @And("^I should see in extra_description maximum (\\d+) characters$")
    public void iShouldSeeInExtra_descriptionMaximumCharacters(int maximumLength) {
        addServicePage.verifyIfExtraDescritpionInputLimited(maximumLength);
    }

    @Then("^I should see in basic_description maximum (\\d+) characters$")
    public void iShouldSeeInBasic_descriptionMaximumCharacters(int maximumLength) {
        addServicePage.verifyIfBasicDescriptionInputLimited(maximumLength);
    }

    @And("^I press Add service button with fail$")
    public void iPressAddServiceButtonWithFail() {
        addServicePage.pushSubmitButtonWithFail();
    }
}

