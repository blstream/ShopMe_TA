package task3.steps;


import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import task3.DriverFactory;
import task3.pages.OrderPage;
import task3.pages.StoreMainPage;
import task3.pages.StoreMyInfoPage;

public class Stepdefs extends DriverFactory {
    StoreMainPage storeMainPage = new StoreMainPage(driver);
    StoreMyInfoPage info = new StoreMyInfoPage(driver);
    OrderPage orderPage = new OrderPage(driver);

    @After public void setUp(){
       destroyDriver();
    }


    @Given("^I am on main page$")
    public void iAmOnMainPage() throws Throwable {
        StoreMainPage storeMainPage = new StoreMainPage(driver);
    }

    @When("^I go to log in page$")
    public void iGoToLogInPage() throws Throwable {
      storeMainPage.loginButton();}

    @And("^I fill in login field with login \"([^\"]*)\"$")
    public void iFillInLoginFieldWithLogin(String login) throws Throwable {
       storeMainPage.loginStep(login);
    }

    @And("^I fill in password field with password \"([^\"]*)\"$")
    public void iFillInPasswordFieldWithPassword(String password) throws Throwable {
        storeMainPage.sendPassword(password);
    }

    @And("^I hit button Sign in$")
    public void iHitButtonSignIn() throws Throwable {
        storeMainPage.logIn();
    }

    @And("^I log in and I click on My personal information tab$")
    public void iLogInAndIClickOnMyPersonalInformationTab() throws Throwable {
        storeMainPage.MyInfo();
    }

    @Then("^I see my personal information$")
    public void iSeeMyPersonalInformation() throws Throwable {
        info.assertInfo();
    }

    @Then("^I see error message$")
    public void iSeeErrorMessage() throws Throwable {
        storeMainPage.errorAlert();
    }

    @When("^I order a product from store$")
    public void iOrderAProductFromStore() throws Throwable {
        orderPage.productButton();
        orderPage.toCart();

    }

    @And("^I click in Proceed Checkout buttons$")
    public void iClickInProceedCheckoutButtons() throws Throwable {
        orderPage.checkout();
        orderPage.checkoutNext();

    }

    @And("^I fill in login field with login \"([^\"]*)\" and password field with \"([^\"]*)\" and I sign in to my profile$")
    public void iFillInLoginFieldWithLoginAndPasswordFieldWithAndISignInToMyProfile(String arg0, String arg1) throws Throwable {
        storeMainPage.loginStep(arg0);
        storeMainPage.sendPassword(arg1);
        storeMainPage.logIn();
    }

    @And("^I finish ordering a product$")
    public void iFinishOrderingAProduct() throws Throwable {
        orderPage.addressStep();
        orderPage.shippingPackage();
        orderPage.payForPackage();
    }


    @Then("^I go to my order history$")
    public void iGoToMyOrderHistory() throws Throwable {
        orderPage.myAccount();

    }

    @And("^I can see my order on the list$")
    public void iCanSeeMyOrderOnTheList() throws Throwable {
        orderPage.orderHistory();
    }


}
