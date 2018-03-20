package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

@Deprecated
public class ProceedingPage {

    @FindBy(how = How.CSS, using = "a.button-medium")
    public WebElement firstProceed;

    @FindBy(how = How.LINK_TEXT, using = "Proceed to checkout")
    public WebElement summaryProceed;

    @FindBy(how = How.NAME, using = "processAddress")
    public WebElement addressProceed;

    @FindBy(how = How.ID, using = "cgv")
    public WebElement tosCheck;

    @FindBy(how = How.NAME, using = "processCarrier")
    public WebElement shippingProceed;

    @FindBy(how = How.CSS, using = "a.bankwire")
    public WebElement payByBankWire;

    @FindBy(how = How.CSS, using = "a.cheque")
    public WebElement payByCheck;

    @FindBy(how = How.CSS, using = "button.button.btn.btn-default.button-medium")
    public WebElement confirmButton;

    @FindBy(how = How.LINK_TEXT, using = "Back to orders")
    public WebElement backToOrdersButton;

    public ProceedingPage() {
        PageFactory.initElements(driver, this);
    }

    public void waitForFirstProceedBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProceed)).click();
    }

    public void getSummaryProceed() {
        summaryProceed.click();
    }

    public void getAddressProceed() {
        addressProceed.click();
    }

    public void getShippingProceed() {
        shippingProceed.click();
    }

    public void getTosCheck() {
        if (!tosCheck.isSelected()) {
            tosCheck.click();
        }
    }

    public void getPayByBankWire() {
        payByBankWire.click();
    }

    public void getPayByCheck() {
        payByCheck.click();
    }

    public void getConfirm() {
        confirmButton.click();
    }

    public void waitForBackToOrdersBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(backToOrdersButton)).click();
    }
}
