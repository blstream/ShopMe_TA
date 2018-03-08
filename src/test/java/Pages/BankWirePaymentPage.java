package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BankWirePaymentPage {
    @FindBy(how = How.CSS, using = "#cart_navigation > button > span")
    public WebElement confirmOrder;

    public BankWirePaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickConfirmOrder(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(wdriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrder));
        confirmOrder.click();
    }

}
