package com.intive.academy.tests.steps;

import com.intive.academy.tests.factories.DriverFactory;
import com.intive.academy.tests.pages.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class MakeOrderStepdefs extends DriverFactory {

    HomePage homePage;
    UserAccountPage userAccountPage;
    ProductControllerPage productControllerPage;
    OrderPage orderPage;

    public MakeOrderStepdefs() {
        homePage = new HomePage(driver);
        userAccountPage = new UserAccountPage(driver);
        productControllerPage = new ProductControllerPage(driver);
        orderPage = new OrderPage(driver);
    }

    @And("^I choose item to buy$")
    public void iChooseItemToBuy() {
        userAccountPage.useHomePageButton();
        homePage.chooseFirstItem();
        productControllerPage.addItemToCart();
    }

    @And("^I am ordering choosed item$")
    public void iAmOrderingChoosedItem() {
        orderPage.goThroughOrderingProcess();
    }

    @Then("^New order is visible in order history$")
    public void newOrderIsVisibleInOrderHistory() {
        Assert.assertTrue(orderPage.checkIfOrderVisibleInHistory());
    }


}
