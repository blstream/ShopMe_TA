package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static tests.Hooks.driver;


public class MyAccountPage {

    @FindBy(how = How.CSS, using = "a[title=\"Information\"] > span")
    public WebElement myPersonalInf;

    @FindBy(how = How.ID, using = "firstname")
    public WebElement firstName;

    @FindBy(how = How.ID, using = "lastname")
    public WebElement lastName;

    @FindBy(how = How.ID, using = "email")
    public WebElement email;

    public MyAccountPage() {
        PageFactory.initElements(driver, this);
    }

    public void getMyPersonalInfPage() {
        myPersonalInf.click();
    }

    public String getNameFieldText() {
        return firstName.getAttribute("value");
    }

    public String getSurnameFieldText() {
        return lastName.getAttribute("value");
    }

    public String getEmailFieldText() {
        return email.getAttribute("value");
    }

    public String getMyAccountPageUrl(){
        return driver.getCurrentUrl();
    }
}