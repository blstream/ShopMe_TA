package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmPage {
    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/div")
    public WebElement orderInfo;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
    public WebElement backToAccBtn;

    public OrderConfirmPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
