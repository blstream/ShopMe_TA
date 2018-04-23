package tests.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class AddServicePage extends SearchServicePage {
    @FindBy(how = How.NAME, using = "offer__title")
    public WebElement offerTitle;

    @FindBy(how = How.NAME, using = "offer__category")
    public WebElement serviceCategory;

    @FindBy(how = How.NAME, using = "offer__base-description")
    public WebElement basicDescription;

    @FindBy(how = How.NAME, using = "offer__base-price")
    public WebElement basicPrice;

    @FindBy(how = How.NAME, using = "offer__extended-description")
    public WebElement expandedDescription;

    @FindBy(how = How.NAME, using = "offer__extended-price")
    public WebElement expandedPrice;

    @FindBy(how = How.NAME, using = "offer__extra-description")
    public WebElement extraDescription;

    @FindBy(how = How.NAME, using = "offer__extra-price")
    public WebElement extraPrice;

    @FindBy(how = How.NAME, using = "offer__user-name")
    public WebElement userName;

    @FindBy(how = How.NAME, using = "offer__user-email")
    public WebElement userEmail;

    @FindBy(how = How.NAME, using = "offer__user-phone-number")
    public WebElement userPhone;

    @FindBy(how = How.NAME, using = "offer__user-additional-info")
    public WebElement aboutMe;

    @FindBy(how = How.CLASS_NAME, using = "form__button")
    public WebElement submitButton;

    @FindBy(how = How.CSS, using = "#root > div > main > section > div > h1")
    public WebElement confirmationMessage;

    @FindBy(how = How.CLASS_NAME, using = "input-select__item-option")
    public WebElement selectOption;

    @FindBy(how = How.CLASS_NAME, using = "add-form")
    public WebElement error;

    @FindBy(how = How.NAME, using = "offer__voivodeship")
    public WebElement userVoivodeship;

    @FindBy(how = How.NAME, using = "offer__city")
    public WebElement userCity;

    private List<String> valuesBefore = new ArrayList<>();
    private List<String> valuesAfter = new ArrayList<>();
    public String RA_title;
    public String RA_basicDescription;
    public String RA_extendedDescription;
    public String RA_extraDescription;
    public String RA_aboutMe;

    public void waitForResult() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRA_title(String RA_title) {
        this.RA_title = RA_title;
    }

    public void setRA_basicDescription(String RA_basicDescription) {
        this.RA_basicDescription = RA_basicDescription;
    }

    public void setRA_extendedDescription(String RA_extendedDescription) {
        this.RA_extendedDescription = RA_extendedDescription;
    }

    public void setRA_extraDescription(String RA_extraDescription) {
        this.RA_extraDescription = RA_extraDescription;
    }

    public void setRA_aboutMe(String RA_aboutMe) {
        this.RA_aboutMe = RA_aboutMe;
    }

    public AddServicePage() {
        PageFactory.initElements(driver, this);
    }

    public void sendTitle(String title) {
        offerTitle.sendKeys(title);
    }

    public void sendName(String name) {
        userName.sendKeys(name);
    }

    public void sendEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void sendBasicDescription(String basic_description) {
        basicDescription.sendKeys(basic_description);
    }

    public void sendBasicPrice(String price) {
        basicPrice.sendKeys(price);
    }

    public void sendExpandedDescription(String expanded_description) {
        expandedDescription.sendKeys(expanded_description);
    }

    public void sendExpandedPrice(String expanded_price) {
        expandedPrice.sendKeys(expanded_price);
    }

    public void sendExtraDescription(String description) {
        if (description.isEmpty()) {
            return;
        } else {
            extraDescription.sendKeys(description);
        }
    }

    public void sendExtraPrice(String extraPrice) {
        if (extraPrice.isEmpty()) {
            return;
        } else {
            this.extraPrice.sendKeys(extraPrice);
        }
    }

    public void sendAboutMe(String about_me) {
        aboutMe.sendKeys(about_me);
    }

    public void pushSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
        waitForResult();
    }

    public void sendBasicDescription(int length) {
        basicDescription.sendKeys(generateString(length));
    }

    public void sendExpandedDescription(int length) {
        expandedDescription.sendKeys(generateString(length));
    }

    public void sendExtraDescription(int length) {
        extraDescription.sendKeys(generateString(length));
    }

    public void sendAboutMeDescription(int length) {
        aboutMe.sendKeys(generateString(length));
    }

    public String generateString(int length) {
        return StringUtils.leftPad("", length, 'a');
    }

    public void sendUserPhone(String phone) {
        userPhone.sendKeys(phone);
    }

    public void verifyIfConfirmationMessageIsVisible(String message) {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        Assert.assertTrue(confirmationMessage.isDisplayed());
        Assert.assertTrue(confirmationMessage.getText().contains(message));
    }

    public void waitUntilSelectOptionsAreVisible() {
        wait.until(ExpectedConditions.visibilityOf(selectOption));
    }

    public void selectServiceCategory(String category) {
        waitUntilSelectOptionsAreVisible();
        Select selectCategory = new Select(serviceCategory);

        if (category.isEmpty()) {
            return;
        } else {
            selectCategory.selectByValue(category);
        }

    }

    public void verifyIfFormIsVisible() {
        waitUntilPageIsLoaded();
        Assert.assertTrue(offerTitle.isDisplayed());
    }

    public void RA_checkTitle() {
        Boolean check = false;
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        List<String> titles = response.jsonPath().getList("content.title");
        for (String n : titles) {
            if (n.equals(RA_title)) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public void RA_checkBasicDescription() {
        Boolean check = false;
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        List<String> baseDescriptions = response.jsonPath().getList("content.baseDescription");
        for (String n : baseDescriptions) {
            if (n.equals(RA_basicDescription)) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public void RA_checkExtendedDescription() {
        Boolean check = false;
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        List<String> extendedDescriptions = response.jsonPath().getList("content.extendedDescription");
        for (String n : extendedDescriptions) {
            if (n.equals(RA_extendedDescription)) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public void RA_checkAboutMe() {
        Boolean check = false;
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        List<String> additionalInfos = response.jsonPath().getList("content.user.additionalInfo");
        for (String n : additionalInfos) {
            if (n.equals(RA_aboutMe)) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public void RA_checkExtraDescription() {
        Boolean check = false;
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        List<String> extraDescriptions = response.jsonPath().getList("content.extraDescription");
        for (String n : extraDescriptions) {
            if (n.equals(RA_extraDescription)) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    private void saveAllValues(List<String> values) {
        values.clear();
        values.add(offerTitle.getAttribute("value"));
        values.add(serviceCategory.getAttribute("value"));
        values.add(basicDescription.getAttribute("value"));
        values.add(basicPrice.getAttribute("value"));
        values.add(expandedDescription.getAttribute("value"));
        values.add(expandedPrice.getAttribute("value"));
        values.add(extraDescription.getAttribute("value"));
        values.add(extraPrice.getAttribute("value"));
        values.add(userName.getAttribute("value"));
        values.add(userEmail.getAttribute("value"));
        values.add(userPhone.getAttribute("value"));
        values.add(aboutMe.getAttribute("value"));
    }

    public void verifyIfValuesEqualsAfterPageRefresh() {
        Assert.assertEquals(valuesBefore, valuesAfter);
    }

    public void verifyIfValidationErrorMessageIsVisible(String message) {
        waitUntilPageIsLoaded();
        Assert.assertTrue(error.getText().contains(message));
    }

    public void waitUntilPageIsLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
    }

    public void verifyIfTitleInputLimited(int expectedLength) {
        int actualLength = offerTitle.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfNameInputLimited(int expectedLength) {
        int actualLength = userName.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfPhoneInputLimited(int expectedLength) {
        int actualLength = userPhone.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfBasicPriceInputLimited(int expectedLength) {
        String price = basicPrice.getAttribute("value");
        int placeOfComma = price.indexOf(',');
        int actualLength = price.length() - placeOfComma - 1;
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfBasicDescriptionInputLimited(int expectedLength) {
        int actualLength = basicDescription.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfExpandedDescriptionInputLimited(int expectedLength) {
        int actualLength = expandedDescription.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfExtraDescritpionInputLimited(int expectedLength) {
        int actualLength = extraDescription.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfAboutMeInputLimited(int expectedLength) {
        int actualLength = aboutMe.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }

    public void verifyIfExpandedDescriptionAndPriceAreBlocked() {
        boolean isEnabled = expandedDescription.isEnabled();
        Assert.assertFalse(isEnabled);
    }

    public void verifyIfExtraDescriptionAndPriceAreBlocked() {
        boolean isEnabled = extraDescription.isEnabled();
        Assert.assertFalse(isEnabled);
    }

    public void pushSubmitButtonWithFail() {
        waitUntilPageIsLoaded();
        saveAllValues(valuesBefore);
        submitButton.click();
        saveAllValues(valuesAfter);
    }

    public void mainPageIsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.urlToBe("https://patronage2018.intive-projects.com"));
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://patronage2018.intive-projects.com", url);
    }

    public void selectVoivodeship(String province) {
        waitUntilSelectOptionsAreVisible();
        Select selectVoivodeship = new Select(userVoivodeship);
        if (province.isEmpty()) {
            return;
        } else {
            selectVoivodeship.selectByValue(province);
        }

    }

    public void sendCity(String city) {
        userCity.sendKeys(city);
    }

    public void checkIfCityIsDisabled() {
        Assert.assertFalse(userCity.isEnabled());
    }

    public void verifyIfUserCityInputLimited(int expectedLength) {
        int actualLength = userCity.getAttribute("value").length();
        Assert.assertEquals(expectedLength, actualLength);
    }
}
