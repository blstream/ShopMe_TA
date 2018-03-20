package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static tests.Hooks.driver;

@Deprecated
public class StartPage {

    String mainPageUrl = "http://automationpractice.com/index.php";

    @FindBy(how = How.CLASS_NAME, using = "header_user_info")
    public WebElement noUserLoggedIn;

    public StartPage() {
        PageFactory.initElements(driver, this);
    }

    public String goToStartPage() {
        driver.get(mainPageUrl);
        return driver.getCurrentUrl();
    }

    public boolean isSignInBtnVisible() {
        return noUserLoggedIn.isDisplayed();
    }

}
