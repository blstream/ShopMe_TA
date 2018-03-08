package task3.pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//*[@id='homefeatured']/li[1]/div/div[2]/h5/a")
    private WebElement product;

    @FindBy(how = How.ID, using = "add_to_cart")
    private WebElement addToCart;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart")
    private WebElement shoppingCart;

    @FindBy(how = How.XPATH, using = "//a[@title='Proceed to checkout']")
    private WebElement proceedCheckout;

    @FindBy(how =How.XPATH, using = "//*[@id='center_column']/p[2]/a[1]")
    private WebElement checkoutNext;

    @FindBy(how = How.CSS, using = "*[class^='address_firstname']")
    private WebElement lastName;

    @FindBy(how = How.CSS, using = "*[class^='address_address1']")
    private WebElement address;

    @FindBy(how = How.CSS, using = "*[class^='address_city']")
    private WebElement cityName;

    @FindBy(how = How.CSS, using = "*[name='processAddress']")
    private WebElement lastStep;

    @FindBy(how = How.ID, using = "cgv")
    private WebElement radioButton;

    @FindBy(how = How.CSS, using = "*[name='processCarrier']")
    private WebElement shippingMethod;

    @FindBy(how = How.CSS, using = "*[class='bankwire']")
    private WebElement payBankWire;

    @FindBy(how = How.XPATH, using = "//*[@id='cart_navigation']/button")
    private WebElement payCheckout;

    @FindBy(how = How.CLASS_NAME, using = "account")
    private WebElement myAccount;

    @FindBy(how = How.XPATH, using = "//a[@title='Orders']")
    private WebElement myOrdersList;

    @FindBy(how = How.ID, using = "order-list")
    private WebElement myOrders;




    public void productButton(){
        product.click();
    }

    public void toCart(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();
    }

    public void checkout(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(proceedCheckout));
        proceedCheckout.click();
    }

    public void checkoutNext(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(checkoutNext));
        checkoutNext.click();
    }

    public void addressStep(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(lastStep));
        lastStep.click();
    }

    public void shippingPackage() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(shippingMethod));
        radioButton.click();
        shippingMethod.click();
    }

    public void payForPackage(){
        payBankWire.click();
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement pay = wait.until(ExpectedConditions.visibilityOf(payCheckout));
        payCheckout.click();
    }

    public void myAccount(){
        myAccount.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement list = wait.until(ExpectedConditions.elementToBeClickable(myOrdersList));

    }

    public void orderHistory(){
        myOrdersList.click();
        WebDriverWait waitSecond = new WebDriverWait(driver, 10);
        WebElement table = waitSecond.until(ExpectedConditions.visibilityOf(myOrders));
        myOrders.isDisplayed();
    }
}