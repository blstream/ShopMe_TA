package tests.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class ServiceProfilePage extends SearchServicePage {

    @FindBy(how = How.CLASS_NAME, using = "offer-details")
    public WebElement serviceDetails;

    @FindBy(how = How.CLASS_NAME, using = "details__header")
    public WebElement serviceTitle;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__category")
    public WebElement serviceCategory;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--data")
    public WebElement userName;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--container")
    public WebElement userContactData;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__contact--button")
    public WebElement showButton;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__additional-info--text")
    public WebElement userInfo;

    @FindBy(how = How.CLASS_NAME, using = "offer-details__offers")
    public WebElement serviceInfoContainers;


    public ServiceProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public void waitForServiceDetails() {
        wait.until(ExpectedConditions.visibilityOf(serviceDetails));
    }

    public String getTitleFieldText() {
        waitForServiceDetails();
        return serviceTitle.getText();
    }

    public void waitForTitleVisibleAfterAddOffer() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(serviceTitle));
        Assert.assertTrue(serviceTitle.isDisplayed());
    }

    public String getCategoryFieldText() {
        return serviceCategory.getText();
    }

    public String getUserNameFieldText() {
        return userName.getText();
    }

    public String getUserEmailFieldText() {
        return userContactData.getText();
    }

    public String getUserPhoneFieldText() {
        return userContactData.getText();
    }

    public String getUserInfoFieldText() {
        return userInfo.getText();
    }

    public String getServiceInfoContainerText() {
        return serviceInfoContainers.getText();
    }

    public void showUserEmail() {
        showButton.click();
    }

    public void showUserPhone() {
        showButton.click();
    }

}
