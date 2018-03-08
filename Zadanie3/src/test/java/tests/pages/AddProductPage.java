package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static tests.Hooks.driver;

public class AddProductPage {

    String shopMessage = "Product successfully added to your shopping cart";

    @FindBy(how = How.CLASS_NAME, using = "icon-th-list")
    public WebElement listView;

    @FindBy(how = How.LINK_TEXT, using = "Add to cart")
    public WebElement addButton;

    public AddProductPage() {
        PageFactory.initElements(driver, this);
    }

    public void getListView() {
        listView.click();
    }

    public void buyProduct() {
        addButton.click();
    }

    public boolean pageContainsShopMessage(){
        return driver.getPageSource().contains(shopMessage);
    }
}