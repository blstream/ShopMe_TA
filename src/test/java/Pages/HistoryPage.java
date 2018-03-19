package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HistoryPage {
    @FindBy(how = How.XPATH, using = "//*[@id=\"order-list\"]/tbody/tr[1]/td[1]/a")
    public WebElement orderRef;


    public HistoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}