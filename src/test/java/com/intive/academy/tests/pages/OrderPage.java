package com.intive.academy.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPage extends BasePage {

    @FindBy(how = How.CSS, using = "a.bankwire")
    private WebElement payByBankWire;

    @FindBy(how = How.CSS, using = "a.button.btn.btn-default.standard-checkout.button-medium")
    private WebElement confirmStepOne;

    @FindBy(how = How.CSS, using = "button.button.btn.btn-default.button-medium")
    private WebElement confirmStepTwo;

    @FindBy(how = How.CSS, using = "#form > p > button")
    private WebElement confirmStepFour;

    @FindBy(how = How.CSS, using = "#cart_navigation > button")
    private WebElement confirmStepFive;

    @FindBy(how = How.CSS, using = "#center_column > p > a")
    private WebElement backToOrdersButton;

    @FindBy(how = How.CSS, using = "input#cgv")
    private WebElement checkBox;

    @FindBy(how = How.CSS, using = "div.box")
    private WebElement confirmationBox;

    private String orderReference;


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public boolean waitFor(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        if (webElement.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void goThroughOrderingProcess() {
        orderStepOne();
        orderStepThree();
        orderStepFour();
        orderStepFive();
    }

    public boolean checkIfOrderVisibleInHistory() {
        waitFor(backToOrdersButton);
        backToOrdersButton.click();
        List<WebElement> orders = driver.findElements(By.cssSelector("a.color-myaccount"));

        for (WebElement currentOrder : orders) {
            if (orderReference.contains(currentOrder.getText())) {
                return true;
            }
        }
        return false;
    }

    private void orderStepOne() {
        waitFor(confirmStepOne);
        confirmStepOne.click();
        log.info("Order step one finished");
    }

    private void orderStepThree() {
        waitFor(confirmStepTwo);
        confirmStepTwo.click();
    }

    private void orderStepFour() {
        waitFor(confirmStepFour);
        selectCheckBox();
        confirmStepFour.click();
    }

    private void orderStepFive() {
        waitFor(payByBankWire);
        payByBankWire.click();
        waitFor(confirmStepFive);
        confirmStepFive.click();
        waitFor(confirmationBox);
        orderReference = confirmationBox.getText();
    }

    private void selectCheckBox() {
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBox).click().perform();
    }
}
