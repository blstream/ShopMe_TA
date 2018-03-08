package com.intive.QAPatronage3.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class MyAccountPage {

    private WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "page-heading")
    private WebElement PageTitle;

    @FindBy(how = How.CLASS_NAME, using = "icon-user")
    private WebElement MyPersonalInformationButton;

    @FindBy(how = How.CLASS_NAME, using = "sf-with-ul")
    private WebElement Women;

    @FindBy(how = How.CLASS_NAME, using = "icon-list-ol")
    private WebElement OrderHistoryButton;


    @FindBy(how = How.TAG_NAME, using = "th")
    private WebElement OrderReference;

    public MyAccountPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkPage() {
        String j = PageTitle.getText();
        System.out.println(j);
        assertEquals("MY ACCOUNT", j);

    }

    public void clickPersonalInformation() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(MyPersonalInformationButton));
        MyPersonalInformationButton.click();
    }

    public void clickWomen() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Women));
        Women.click();
    }

    public void checkOrderHistory() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(OrderHistoryButton));
        OrderHistoryButton.click();
        String j = OrderReference.getText();
        assertEquals("Order reference", j);
    }

}
