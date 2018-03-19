package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ShippingStepPage {
    @FindBy(how = How.CSS, using = "#cgv")
    public WebElement checkbox;
    @FindBy(how = How.CSS, using = "#form > p > button > span")
    public WebElement shippingProceedBtn;

    public ShippingStepPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickShippingProceedBtn(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(wdriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(shippingProceedBtn));
        checkbox.click();
        shippingProceedBtn.click();
    }


}