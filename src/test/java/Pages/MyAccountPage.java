package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyAccountPage {
    @FindBy(how = How.CSS, using = "#center_column > div > div:nth-child(1) > ul > li:nth-child(4) > a > span")
    public WebElement accDetailsBtn;
    @FindBy(how = How.CSS, using = "#block_top_menu > ul > li:nth-child(2) > a")
    public WebElement productsPageBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a")
    public WebElement historyBtn;

    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goPersonalInfo(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(wdriver, 30);
        wait.until(ExpectedConditions.visibilityOf(accDetailsBtn));
        accDetailsBtn.click();
    }

    public void clickProductsPageBtn(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(wdriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(productsPageBtn));
        productsPageBtn.click();
    }


}


