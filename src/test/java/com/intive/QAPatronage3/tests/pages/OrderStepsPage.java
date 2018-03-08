package com.intive.QAPatronage3.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderStepsPage {
    private WebDriver driver;

    public OrderStepsPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using = "standard-checkout")
    private WebElement ProceedToCheckoutButton;
    @FindBy(how = How.NAME, using = "processAddress")
    private WebElement ProceedToCheckoutAdress;
    @FindBy(how = How.NAME, using = "cgv")
    private WebElement Agreement;
    @FindBy(how = How.NAME, using = "processCarrier")
    private WebElement ProceedToCheckout1;
    @FindBy(how = How.CLASS_NAME, using = "cheque")
    private WebElement PayByCheckButton;
    @FindBy(how = How.XPATH, using = "(//button[@type='submit'])[2]")
    private WebElement ConfirmOrderButton;
    //@FindBy(how = How.CSS, using = "a.btn.btn-default.button.button-medium > span")
    //@FindBy(how = How.XPATH, using = "//a[title='Proceed to checkout']")
    //private WebElement n;


    public void goThroughOrderSteps() {
        driver.get("http://automationpractice.com/index.php?controller=order");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement results = wait.until(ExpectedConditions.elementToBeClickable(ProceedToCheckoutButton));
        //n.click();
        ProceedToCheckoutButton.click();
        ProceedToCheckoutAdress.click();
        Agreement.click();
        ProceedToCheckout1.click();
        PayByCheckButton.click();
        ConfirmOrderButton.click();
    }
}
