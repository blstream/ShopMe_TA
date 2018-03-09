package com.intive.academy.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UserAccountPage {
    WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "icon-user")
    private WebElement informationButton;

    @FindBy(how = How.CSS, using = "div.row.addresses-lists")
    private WebElement accountInfo;

    @FindBy(how = How.CSS, using = "a.home")
    private WebElement homePageButton;

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToUserInformationPage() {
        informationButton.click();
    }

    public boolean checkIfYouAreOnPage() {
        if (accountInfo.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void useHomePageButton() {
        homePageButton.click();
    }


}
