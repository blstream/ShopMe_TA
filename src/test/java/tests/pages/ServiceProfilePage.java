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


}

