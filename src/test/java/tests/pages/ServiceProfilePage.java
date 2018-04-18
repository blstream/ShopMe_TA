package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class ServiceProfilePage {

    @FindBy(how = How.CLASS_NAME, using = "offer-details")
    public WebElement serviceDetails;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__header")
    public WebElement serviceTitle;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__category")
    public WebElement serviceCategory;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--container--name")
    public WebElement userName;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--container--email")
    public WebElement userEmail;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--button")
    public WebElement showButton;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--container--phone")
    public WebElement userPhone;

    @FindBy(how = How.CLASS_NAME, using = "/offer-details__contact--additional-info")
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

    public void waitForServiceDetails() {
        wait.until(ExpectedConditions.visibilityOf(serviceDetails));
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
        return userEmail.getText();
    }

    public String getUserPhoneFieldText() {
        return userPhone.getText();
    }

    public String getUserInfoFieldText() {
        return userInfo.getText();
    }

    public String getBasicInfoFieldText() {
        return basicInfo.getText();
    }

    public String getBasicPriceFieldText() {
        return basicPrice.getText();
    }

    public String getExtendedInfoFieldText() {
        return extendedInfo.getText();
    }

    public String getExtendedPriceFieldText() {
        return extendedPrice.getText();
    }

    public String getExtraInfoFieldText() {
        return extraInfo.getText();
    }

    public String getExtraPriceFieldText() {
        return extraPrice.getText();
    }

    public void showUserEmail(){
        showButton.click();
    }

    public void showUserPhone(){
        showButton.click();
    }
}
