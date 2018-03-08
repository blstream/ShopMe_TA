package com.intive.patronage.testMakeOrder;

import com.intive.patronage.DriverFactory;
import com.intive.patronage.pages.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class MakeOrderStepdefs {

    WebDriver driver;
    DriverFactory driverFactory = new DriverFactory(driver);
    HomePage homePage = new HomePage(driver);
    AuthenticationPage authenticationPage = new AuthenticationPage(driver);
    ProductPage productPage = new ProductPage(driver);
    ShoppingCartSummaryPage summaryPage = new ShoppingCartSummaryPage(driver);
    ShippingPage shippingPage = new ShippingPage(driver);
    AddressPage addressPage = new AddressPage(driver);
    PaymentPage paymentPage = new PaymentPage(driver);
    OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);

    @Before
    public void before() {
        driverFactory = new DriverFactory(driver);
        driverFactory.Setup();
        homePage = driverFactory.getHomePage();
    }

    @After
    public void after() {
        driverFactory.destroyDriver();
    }

    @Given("^I am an authenticated user with login \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iAmAnAuthenticatedUserWithLoginAndPassword(String arg0, String arg1) throws Throwable {
        authenticationPage = homePage.getAuthenticationPage();
        authenticationPage.InsertDataToSignIn(arg0, arg1);
        authenticationPage.SignIn();
        authenticationPage.VeryfiyIfMyAccountPageIsVisible();

    }

    @When("^I press Home page button$")
    public void iPressHomePageButton() {
        authenticationPage.homePage();
    }

    @And("^I choose product$")
    public void iChooseProduct() {
        productPage = homePage.getProductPage();
    }

    @And("^I press AddToCart button$")
    public void iPressAddToCartButton() {
        productPage.AddToCart();
    }


    @And("^I open Shopping Cart$")
    public void iOpenShoppingCart() {
        summaryPage = productPage.getSummaryPage();
    }

    @And("^I am on  Shopping Cart Summary page$")
    public void iAmOnShoppingCartSummaryPage() throws Throwable {
        summaryPage.VerifyIfShoppingCartSummaryPageIsDisplayed();
    }

    @And("^I press ProceedToCheckout button$")
    public void iPressProceedToCheckoutButton() {
        addressPage = summaryPage.getAddressPage();
    }

    @And("^I am on Addresses page$")
    public void iAmOnAddressesPage() throws Throwable {
        addressPage.VerifyIfAddressPageIsDisplayed();
    }

    @And("^I press Procced to chekout button$")
    public void iPressProccedToChekoutButton() {
        shippingPage = addressPage.getShippingPage();
    }

    @And("^I am on Shipping page$")
    public void iAmOnShippingPage() throws Throwable {
        shippingPage.VerifyIfShippingPageIsDisplayed();
    }

    @And("^I accept terms of service$")
    public void iAcceptTermsOfService() {
        shippingPage.Agree();
        paymentPage = shippingPage.getPaymentPage();
    }

    @And("^I am on Payment page$")
    public void iAmOnPaymentPage() throws Throwable {
        paymentPage.VerifyIfPaymentPageIsDisplayed();
    }


    @And("^I choose payment option$")
    public void iChoosePaymentOption() {
        paymentPage.Payment();
    }

    @And("^I confirm my order$")
    public void iConfirmMyOrder() {
        orderConfirmationPage = paymentPage.getOrderConfirmationPage();
    }

    @Then("^I should see confirmation message$")
    public void iShouldSeeConfirmationMessage() throws Throwable {
        orderConfirmationPage.VerifyIfConfirmationMessageIsDisplayed();
    }


}
