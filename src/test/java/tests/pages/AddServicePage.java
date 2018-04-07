
package tests.pages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import tests.steps.AddServiceStepDefs;

import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class AddServicePage {
    @FindBy(how = How.NAME, using = "offer__title")
    public WebElement offerTitle;

    @FindBy(how = How.NAME, using = "offer__category")
    public WebElement serviceCategory;

    @FindBy(how = How.NAME, using = "offer__base-description")
    public WebElement basicDescription;

    @FindBy(how = How.NAME, using = "offer__base-price")
    public WebElement basicPrice;

    @FindBy(how = How.NAME, using = "offer__extended-decscription")
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

    public String getRA_title() {
        return RA_title;
    }

    public String getRA_basicDescription() {
        return RA_basicDescription;
    }

    public String getRA_extendedDescription() {
        return RA_extendedDescription;
    }

    public String getRA_extraDescription() {
        return RA_extraDescription;
    }

    public String getRA_aboutMe() {
        return RA_aboutMe;
    }

    public String RA_title;
    public String RA_basicDescription;
    public String RA_extendedDescription;
    public String RA_extraDescription;
    public String RA_aboutMe;

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
        expandedPrice.sendKeys(expanded_description);
    }

    public void sendExpandedPrice(String expanded_price) {
        expandedPrice.sendKeys(expanded_price);
    }

    public void sendExtraDescription(String extra_description) {
        extraDescription.sendKeys(extra_description);
    }

    public void sendExtraPrice(String extra_price) {
        extraPrice.sendKeys(extra_price);
    }

    public void sendAboutMe(String about_me) {
        aboutMe.sendKeys(about_me);
    }

    public void pushSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
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

    public void verifyIfConfirmationMessageIsDisplayed(String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        // wait.until(ExpectedConditions.textToBePresentInElementValue(confirmationMessage,message));
        // Assert.assertTrue(confirmationMessage.isDisplayed());
        //Assert.assertTrue(confirmationMessage.getText().contains(message));
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
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        Assert.assertTrue(offerTitle.isDisplayed());
    }

    public void RA_checkTitle() {
        Boolean check = false;
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> titles = jsonPathEvaluator.getList("content.name");
        for (String n : titles) {
            String tmp = RA_title;
            if (n == tmp) {
                check = true;
                break;
            }
        }
    }

    public void RA_checkBasicDescription() {
        Boolean check = false;
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> baseDescription = jsonPathEvaluator.getList("baseDescription");
        for (String n : baseDescription) {
            String tmp = RA_basicDescription;
            if (n == tmp) {
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
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> extendedDescription = jsonPathEvaluator.getList("extendedDescription");
        for (String n : extendedDescription) {
            String tmp = RA_extendedDescription;
            if (n == tmp) {
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
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> aboutMe = jsonPathEvaluator.getList("user.additionalInfo");
        for(String n: aboutMe) {
            String tmp = RA_aboutMe;
            if (n == tmp) {
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
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> extraDescription = jsonPathEvaluator.getList("extraDescription");
        for (String n : extraDescription) {
            String tmp = RA_extraDescription;
            if (n == tmp) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    }



