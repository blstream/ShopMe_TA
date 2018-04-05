
package tests.pages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy(how = How.CLASS_NAME, using = "add-form__submit")
    public WebElement submitButton;

    @FindBy(how = How.CSS, using = "add-form")
    public WebElement confirmationMessage;

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
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
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
        assert (confirmationMessage.isDisplayed());
        assert (confirmationMessage.getText().contains(message));
    }

    public void selectServiceCategory(String category) {
        Select selectCategory = new Select(serviceCategory);
        selectCategory.selectByValue(category);
    }

    public void verifyIfFormIsVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        Assert.assertTrue(offerTitle.isDisplayed());
    }

    public class AddServiceData {
        public String title;
        public String category;
        public String basicDescription;
        public String basicPrice;
        public String extendedDescription;
        public String extendedPrice;
        public String extraDescription;
        public String extraPrice;
        public String name;
        public String email;
        public String phone;
        public String aboutMe;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getBasicDescription() {
            return basicDescription;
        }

        public void setBasicDescription(String basicDescription) {
            this.basicDescription = basicDescription;
        }

        public String getBasicPrice() {
            return basicPrice;
        }

        public void setBasicPrice(String basicPrice) {
            this.basicPrice = basicPrice;
        }

        public String getExtendedDescription() {
            return extendedDescription;
        }

        public void setExtendedDescription(String extendedDescription) {
            this.extendedDescription = extendedDescription;
        }

        public String getExtendedPrice() {
            return extendedPrice;
        }

        public void setExtendedPrice(String extendedPrice) {
            this.extendedPrice = extendedPrice;
        }

        public String getExtraDescription() {
            return extraDescription;
        }

        public void setExtraDescription(String extraDescription) {
            this.extraDescription = extraDescription;
        }

        public String getExtraPrice() {
            return extraPrice;
        }

        public void setExtraPrice(String extraPrice) {
            this.extraPrice = extraPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAboutMe() {
            return aboutMe;
        }

        public void setAboutMe(String aboutMe) {
            this.aboutMe = aboutMe;
        }
    }


    public void RestAssured(AddServiceData addServiceData) {
        Boolean check = false;
        ArrayList<AddServiceData> objects = new ArrayList<AddServiceData>();
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        ResponseBody body = response.getBody();
        List<HashMap> offers = new JsonPath(body.asString()).get(".");
        for (int i = 0; i < offers.size(); i++) {
            AddServiceData restAssuredData = new AddServiceData();
            restAssuredData.title = offers.get(i).get("title").toString();
            restAssuredData.category = offers.get(i).get("category").toString();
            restAssuredData.basicDescription = offers.get(i).get("baseDescription").toString();
            restAssuredData.basicPrice = offers.get(i).get("basePrice").toString();
            restAssuredData.extendedDescription = offers.get(i).get("extendedDescription").toString();
            restAssuredData.extendedPrice = offers.get(i).get("extendedPrice").toString();
            restAssuredData.extraDescription = offers.get(i).get("extraDescription").toString();
            restAssuredData.extraPrice = offers.get(i).get("extraPrice").toString();
            restAssuredData.name = offers.get(i).get("name").toString();
            restAssuredData.phone = offers.get(i).get("phoneNumber").toString();
            restAssuredData.email = offers.get(i).get("email").toString();
            restAssuredData.aboutMe = offers.get(i).get("additionalInfo").toString();
            objects.add(restAssuredData);
            if (restAssuredData.equals(addServiceData)) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

}

