package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PaymentPage {
    @FindBy(how = How.CSS, using = "#HOOK_PAYMENT > div:nth-child(1) > div > p > a")
    public WebElement paymentMethodBtn;


    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickPaymentMethodBtn(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(wdriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodBtn));
        paymentMethodBtn.click();
    }
}
