package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static tests.Hooks.driver;

public class ServiceProfilePage {

    @FindBy(how = How.CLASS_NAME, using = "offer-details__header")
    public WebElement serviceTitle;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__category")
    public WebElement serviceCategory;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--container--name")
    public WebElement userName;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--container--email")
    public WebElement userEmail;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--container--phone")
    public WebElement userPhone;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--additional-info")
    public WebElement userInfo;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__offers--base--description")
    public WebElement basicInfo;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__offers--base--price")
    public WebElement basicPrice;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__offers--extended--description")
    public WebElement extendedInfo;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__offers--extended--price")
    public WebElement extendedPrice;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__offers--extra--description")
    public WebElement extraInfo;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__offers--extra--price")
    public WebElement extraPrice;


    public ServiceProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public String getTitleFieldText(){
        return serviceTitle.getText();
    }

    public String getCategoryFieldText(){
        return serviceCategory.getText();
    }

    public String getUserNameFieldText(){
        return userName.getText();
    }

    public String getUserEmailFieldText(){
        return userEmail.getAttribute("value");
    }

    public String getUserPhoneFieldText() {
        return userPhone.getAttribute("value");
    }

    public String getUserInfoFieldText() {
        return userInfo.getAttribute("value");
    }

    public String getBasicInfoFieldText() {
        return basicInfo.getAttribute("value");
    }

    public String getBasicPriceFieldText() {
        return basicPrice.getAttribute("value");
    }

    public String getExtendedInfoFieldText() {
        return extendedInfo.getAttribute("value");
    }

    public String getExtendedPriceFieldText() {
        return extendedPrice.getAttribute("value");
    }

    public String getExtraInfoFieldText() {
        return extraInfo.getAttribute("value");
    }

    public String getExtraPriceFieldText() {
        return extraPrice.getAttribute("value");
    }
}

//public void getTosCheck() {
//        if (!tosCheck.isSelected()) {
//            tosCheck.click();
//        }