package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddrStepPage {
    @FindBy(how = How.CSS, using = "#center_column > form > p > button > span")
    public WebElement addressProceedBtn;


    public AddrStepPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickAddressProceedBtn(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(wdriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(addressProceedBtn));
        addressProceedBtn.click();
    }


}