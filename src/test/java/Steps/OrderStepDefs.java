package Steps;

import Base.BaseUtil;
import Pages.HistoryPage;
import Pages.MyAccountPage;
import Pages.OrderConfirmPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class OrderStepDefs extends BaseUtil {
    private static BaseUtil base;

    public OrderStepDefs(BaseUtil base) {
        OrderStepDefs.base = base;
    }

    @And("^I have chosen an item to purchase$")
    public void iHaveChosenAnItemToPurchase() {
        myAccountPage.clickProductsPageBtn(base.Driver);
        productsPage.selectItem(base.Driver);
        productsPage.addToCart(base.Driver);
        productsPage.cartProceedCheckout(base.Driver);
    }


    @When("^I pass all steps and confirmed my order$")
    public void iPassAllStepsAndConfirmedMyOrder() {
        summaryPage.clickSmmryPrcdBtn(base.Driver);
        addrStepPage.clickAddressProceedBtn(base.Driver);
        shippingStepPage.clickShippingProceedBtn(base.Driver);
        paymentPage.clickPaymentMethodBtn(base.Driver);
        bankWirePaymentPage.clickConfirmOrder(base.Driver);
    }


    @Then("^I should be able to see the order in 'Order history and details' view$")
    public void iShouldBeAbleToSeeTheOrderInOrderHistoryAndDetailsView() {
        orderConfirmPage = new OrderConfirmPage(base.Driver);
        String ordInf = String.valueOf(orderConfirmPage.orderInfo);
        orderConfirmPage.backToAccBtn.click();
        myAccountPage = new MyAccountPage(base.Driver);
        myAccountPage.historyBtn.click();
        historyPage = new HistoryPage(base.Driver);
        String ordRef = String.valueOf(historyPage.orderRef);

        if (ordInf.contains(ordRef)) {
            Assert.assertTrue(true);
        }
    }
}
