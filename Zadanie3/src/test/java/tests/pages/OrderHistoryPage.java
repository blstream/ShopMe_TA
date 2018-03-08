package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static tests.Hooks.driver;
import static tests.Hooks.wait;

public class OrderHistoryPage {

    @FindBy(how = How.LINK_TEXT, using = "Details")
    public WebElement orderDetails;

    @FindBy(how = How.ID, using = "block-order-detail")
    public WebElement ordersStatus;

    public OrderHistoryPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickOrderDetailsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(orderDetails)).click();
    }

    public boolean isOrdersStatusVisible() {
        wait.until(ExpectedConditions.visibilityOf(ordersStatus));
        return ordersStatus.isDisplayed();
    }

    public String getOrderPageUrl() {
        return driver.getCurrentUrl();
    }
}
